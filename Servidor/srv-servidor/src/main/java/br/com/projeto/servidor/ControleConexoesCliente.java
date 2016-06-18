package br.com.projeto.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import br.com.projeto.threads.ServerThread;


public class ControleConexoesCliente {

	protected LinkedList<ServerThread> ListaClientes;

	public ControleConexoesCliente(){
		ListaClientes = new LinkedList<ServerThread>();

		iniciar();
	}

	protected void iniciar(){
		try{
			ServerSocket servidor = new ServerSocket(12354);
			System.out.println("Porta 12345 aberta!");


			while (true){
				Socket cliente = servidor.accept();
				System.out.println("Nova conexão com o cliente"+cliente.getInetAddress().getHostAddress());
				ServerThread threadCliente = new ServerThread(cliente, this);
				ListaClientes.add(threadCliente);
				threadCliente.start();
				broadcastCliente();
			}
		} catch(IOException e){
			e.printStackTrace();
		}
	}

	public synchronized void enviarMensagemBroadCast(String mensagem){
		for(int i=0; i < ListaClientes.size(); i++){
			ListaClientes.get(i).enviarMensagem(mensagem);
		}

	}
	
	public synchronized void enviarMensagemContato(String nomecontato,String mensagem){
		for(int i=0; i < ListaClientes.size(); i++){
			if(ListaClientes.get(i).getContato().equalsIgnoreCase(nomecontato)){
				ListaClientes.get(i).enviarMensagem(mensagem);
			}
		}

	}

	public synchronized void desconectar(ServerThread cliente){
		for(int i=0; i < ListaClientes.size(); i++){
			if(ListaClientes.get(i) == cliente){
				ListaClientes.remove(i);			
			}
		}
		broadcastCliente();
	}

	public synchronized void broadcastCliente(){
		String lista_contatos = "3;Todos"; //3 é o comando serv/client indicando lista de contatos
		for(int i=0; i < ListaClientes.size(); i++){
			lista_contatos += ";"+ListaClientes.get(i).getContato();
		}

		if(ListaClientes.size() != 0){
			enviarMensagemBroadCast(lista_contatos);
		}
	}
}
