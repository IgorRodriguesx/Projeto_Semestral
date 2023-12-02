package main;

import java.util.List;

public class Pessoa {
	
	private String nome;
	private Grupo grupoPertencente;
	private Fila filaAtual;
	private Integer minhaPosicao;
	
	public Pessoa(String nome) {
		this.nome = nome;
		grupoPertencente = new Grupo();
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Fila getFilaAtual() {
		return filaAtual;
	}
	public void setFilaAtua(Fila filaAtua) {
		this.filaAtual = filaAtua;
	}
	
	public void setGrupoPertencente(Grupo grupo) {
		grupoPertencente = grupo;		
	}
	public List<Pessoa> getGrupoPertencente() {
		if(grupoPertencente == null) return null;
		return grupoPertencente.getGrupo();
	}
	
	public void removerDaFila() {
		this.filaAtual.remove(this);
		this.filaAtual.atualizarPosicoes();
		this.filaAtual = null;
		this.minhaPosicao = null;
	}

	public Integer getMinhaPosicao() {
		return minhaPosicao;
	}

	public void setMinhaPosicao(Integer minhaPosicao) {
		this.minhaPosicao = minhaPosicao;
	}
}
