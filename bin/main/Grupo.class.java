

package main;

import java.util.ArrayList;
import java.util.List;

public class Grupo {
    private List<Pessoa> grupo = new ArrayList();

    public Grupo() {
    }

    public List<Pessoa> getGrupo() {
        return this.grupo;
    }

    public void adicionarPessoa(Pessoa pessoa) {
        this.grupo.add(0, pessoa);
        pessoa.setGrupoPertencente(this);
    }
}
