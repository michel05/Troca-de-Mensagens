package br.com.projeto.negocio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

import br.com.projeto.run.Tela;

public class Cliente {
	PrintWriter out;
	BufferedReader inServidor;
	Socket socket;
	boolean terminar = false;
	Tela tela;

	public Cliente(Tela tela){
		this.tela = tela;
		
		cadastrarConta();
		
		new Thread(new Runnable() {
			public void run() {
				receberMensagem();				
			}
		}).start();
	}

	public void cadastrarConta(){
		String teclado="";
		//System.out.print("CADASTRAR CLIENTE:");
		teclado = JOptionPane.showInputDialog("CADASTRAR CLIENTE");
	
		if(teclado == null){
			terminar = true;
			tela.dispose();
		} else {
			try {
				socket = new Socket("127.0.0.1", 12354);
				out = new PrintWriter(socket.getOutputStream(), true);
				inServidor = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println(teclado);
		}
	}

	public  void enviarMensagem(String texto){
		String contato = (String) tela.getListModelContatos().get(tela.getContatos().getSelectedIndex());
		out.println("2;"+contato+";"+texto);
		out.flush();
	}
	
	public void sair(){
		out.println("5");
		terminar = true;
	}

	public  void receberMensagem(){
		String fromServer="";
		while(!terminar){
			try {
				fromServer = inServidor.readLine();
				String comandos[] = fromServer.split(";");
				if(comandos[0].equalsIgnoreCase("1")){
					tela.getMensagens().append(comandos[1]+"\n");
				}
				
				if(comandos[0].equalsIgnoreCase("3")){
					String aux = "Todos";
					if(tela.getListModelContatos().size() !=0){
						aux = (String) tela.getListModelContatos().get(tela.getContatos().getSelectedIndex());
					}
					int posicao = 0;
					tela.getListModelContatos().clear();
					for(int i=1; i< comandos.length; i++){
						if(comandos[i].equalsIgnoreCase(aux)){
							posicao = i-1;
						}
						tela.getListModelContatos().addElement(comandos[i]);
					}
					
					tela.getContatos().setSelectedIndex(posicao);
				}
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		tela.dispose(); // destroi a janela
	}
}
