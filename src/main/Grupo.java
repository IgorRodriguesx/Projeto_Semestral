package main;

import java.util.ArrayList;
import java.util.List;

public class Grupo {
	
	private List<Pessoa> grupo;

	public Grupo() {
		this.grupo = new ArrayList<>();
	}
	
	public List<Pessoa> getGrupo() {
		return grupo;
	}
	
	public void adicionarPessoa(Pessoa pessoa) {
		grupo.add(0, pessoa);
		pessoa.setGrupoPertencente(this);
	}
}
