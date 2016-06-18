package br.com.projeto.run;

import java.io.IOException;

import br.com.projeto.servidor.ControleConexoesCliente;

public class ServidorRun {

    
    public static void main(String[] args) throws IOException{
        
       ControleConexoesCliente conexao = new ControleConexoesCliente();
       conexao.enviarMensagemBroadCast("teste");
        
    }
}







