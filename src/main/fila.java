package main;

import java.util.ArrayList;
import java.util.List;

public class Fila {
	private String id;
	private List<Pessoa> fila;
	
	public Fila(String id) {
		this.id = id;
		fila = new ArrayList<>();
	}
	
	public String getId() {
		return id;
	}
	public List<Pessoa> getFila() {
		return fila;
	}
	
	public void adicionarPessoa(Pessoa p) {
		fila.add(p);
		p.setFilaAtua(this);
		p.setMinhaPosicao(fila.size() - 1);
	}

	public void remove(Pessoa pessoa) {
		fila.remove(pessoa);
		this.atualizarPosicoes();
	}
	
	public void atualizarPosicoes() {
		for(Pessoa pessoa : fila) {
			pessoa.setMinhaPosicao(fila.indexOf(pessoa));
		}
	}

	public void adicionarPessoaComConhecido(Pessoa pessoa, Pessoa conhecido) {
		this.atualizarPosicoes();
		if(fila.size() - 1 == conhecido.getMinhaPosicao()) {
			adicionarPessoa(pessoa);
			return;
		} else if(pessoa.getGrupoPertencente().contains(fila.get(conhecido.getMinhaPosicao()+1))) {
			adicionarPessoaComConhecido(pessoa, conhecido.getFilaAtual().getFila().get(conhecido.getMinhaPosicao()+1));
			return;
		}
		fila.add(conhecido.getMinhaPosicao()+1, pessoa);
		pessoa.setFilaAtua(this);
		pessoa.setMinhaPosicao(conhecido.getMinhaPosicao()+1);
	}
	
	

}
