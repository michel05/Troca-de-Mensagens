package br.com.projeto.model;

import java.util.List;

import br.com.projeto.interfaces.IUsuario;

public class Usuario extends Pessoa implements IUsuario{
	
	private List<Pessoa> listaContatos;

	public void arquivarMensagens(List<Mensagem> listaMensagens) {
		// TODO Auto-generated method stub
		
	}

	public void apagarMensagens(List<Mensagem> listaMensagens) {
		// TODO Auto-generated method stub
		
	}

	public List<Mensagem> listarMensagens() {
		// TODO Auto-generated method stub
		return null;
	}

	public void criarConta(Pessoa pessoa) {
		// TODO Auto-generated method stub
		
	}

	public void excluirConta(Pessoa pessoa) {
		// TODO Auto-generated method stub
		
	}

	public void enviarMensagem(Mensagem mensagem) {
		// TODO Auto-generated method stub
		
	}

	public void incluirContato(Pessoa pessoa) {
		// TODO Auto-generated method stub
		
	}

	public void excluirContato(Pessoa pessoa) {
		// TODO Auto-generated method stub
		
	}

}
