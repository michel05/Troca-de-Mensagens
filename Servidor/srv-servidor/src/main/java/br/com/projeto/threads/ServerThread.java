package br.com.projeto.threads;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import br.com.projeto.servidor.ControleConexoesCliente;


public class ServerThread extends Thread {

    private Socket socket;
    private ControleConexoesCliente controle_conexoes;
    private String nome;
    BufferedReader in; 
    PrintWriter out;
    

    public ServerThread( Socket socket, ControleConexoesCliente controle_conexoes){

    	
        this.socket = socket;
        this.controle_conexoes = controle_conexoes;    
        try{
        	in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(), true); 
            nome = in.readLine();
        
        } catch(Exception e){
            System.out.println(e);
        }
    }
    @Override
    public void run(){
        try{
                
        	String inputLine="";
        	String [] comandos= null;
        	
        while(true){   
                inputLine = in.readLine();
                comandos = inputLine.split(";");
                
                if(comandos[0].equalsIgnoreCase("2")){
                	if(comandos[1].equalsIgnoreCase("Todos")){
                		controle_conexoes.enviarMensagemBroadCast("1;"+nome+":"+comandos[2]);
                	} else {
                		controle_conexoes.enviarMensagemContato(comandos[1],"1;"+nome+":"+comandos[2]);
                	}
                }
                
                if(comandos[0].equalsIgnoreCase("5")){
                	controle_conexoes.enviarMensagemBroadCast("1;"+nome+" saiu.");
                	controle_conexoes.desconectar(this);
                }
        }
                
        } catch(Exception e1){
            System.out.println("Cliente:"+nome+" desconectado.");
        }
    }
    
    public synchronized  void enviarMensagem(String mensagem){
    	out.println(mensagem);
    	out.flush();
    }
    
    public synchronized String getContato(){
    	return nome;
    }
 
}

