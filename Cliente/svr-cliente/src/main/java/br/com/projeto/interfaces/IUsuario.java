package br.com.projeto.interfaces;

import java.util.List;

import br.com.projeto.model.Mensagem;
import br.com.projeto.model.Pessoa;

public interface IUsuario {

	void arquivarMensagens(List<Mensagem> listaMensagens);
	void apagarMensagens(List<Mensagem> listaMensagens);
	List<Mensagem> listarMensagens();
	void criarConta(Pessoa pessoa);
	void excluirConta(Pessoa pessoa);
	void enviarMensagem(Mensagem mensagem);
	void incluirContato(Pessoa pessoa);
	void excluirContato(Pessoa pessoa);
}
