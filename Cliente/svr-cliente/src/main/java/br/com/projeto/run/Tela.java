package br.com.projeto.run;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import br.com.projeto.negocio.Cliente;

public class Tela extends JFrame {

	private JTextArea mensagens;
	private JList contatos;
	private DefaultListModel listModelContatos;
	private JTextField mensagem;
	private JButton enviar, sair;
	private Cliente cliente;
	
	public Tela(){
		super("CHAT CLIENTE V01");
		JPanel tela = new JPanel();
		tela.setLayout(new BorderLayout());
		
		mensagens = new JTextArea(30, 10);
		listModelContatos = new DefaultListModel();
		contatos = new JList(listModelContatos);
		mensagem = new JTextField(40);
		enviar = new JButton("Enviar");
		GestaoEvento ge = new GestaoEvento();
		enviar.addActionListener(ge);
		sair = new JButton("Sair");
		sair.addActionListener(ge);
		
		JPanel mensagem_enviar = new JPanel();
		mensagem_enviar.add(mensagem);
		mensagem_enviar.add(enviar);
		mensagem_enviar.add(sair);
		
		JPanel jpContatos = new JPanel();
		jpContatos.add(Box.createRigidArea(new Dimension(10,10)));
		JScrollPane jspContatos = new JScrollPane(contatos);
		jspContatos.setPreferredSize(new Dimension(100, 235));
		jpContatos.add(jspContatos);
		jpContatos.add(Box.createRigidArea(new Dimension(10,10)));
		
		tela.add(mensagens, BorderLayout.CENTER);
		tela.add(mensagem_enviar, BorderLayout.SOUTH);
		tela.add(jpContatos, BorderLayout.EAST);
		
		setSize(700, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getContentPane().add(tela);
		setVisible(true);
		
		cliente = new Cliente(this);
	}
	
	protected class GestaoEvento implements ActionListener{
		public void actionPerformed(ActionEvent evento){
			if(evento.getSource() == enviar){
				cliente.enviarMensagem(mensagem.getText());				
			}
			if(evento.getSource() == sair){
				cliente.sair();				
			}
		}
	}

	public JTextArea getMensagens() {
		return mensagens;
	}

	public void setMensagens(JTextArea mensagens) {
		this.mensagens = mensagens;
	}

	public JList getContatos() {
		return contatos;
	}

	public void setContatos(JList contatos) {
		this.contatos = contatos;
	}

	public DefaultListModel getListModelContatos() {
		return listModelContatos;
	}

	public void setListModelContatos(DefaultListModel listModelContatos) {
		this.listModelContatos = listModelContatos;
	}

	public JTextField getMensagem() {
		return mensagem;
	}

	public void setMensagem(JTextField mensagem) {
		this.mensagem = mensagem;
	}

	public JButton getEnviar() {
		return enviar;
	}

	public void setEnviar(JButton enviar) {
		this.enviar = enviar;
	}

	public JButton getSair() {
		return sair;
	}

	public void setSair(JButton sair) {
		this.sair = sair;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
