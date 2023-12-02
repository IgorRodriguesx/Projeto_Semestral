

package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Fila {
    private String id;
    private List<Pessoa> fila;

    public Fila(String id) {
        this.id = id;
        this.fila = new ArrayList();
    }

    public String getId() {
        return this.id;
    }

    public List<Pessoa> getFila() {
        return this.fila;
    }

    public void adicionarPessoa(Pessoa p) {
        this.fila.add(p);
        p.setFilaAtua(this);
        p.setMinhaPosicao(this.fila.size() - 1);
    }

    public void remove(Pessoa pessoa) {
        this.fila.remove(pessoa);
        this.atualizarPosicoes();
    }

    public void atualizarPosicoes() {
        Iterator var2 = this.fila.iterator();

        while(var2.hasNext()) {
            Pessoa pessoa = (Pessoa)var2.next();
            pessoa.setMinhaPosicao(this.fila.indexOf(pessoa));
        }

    }

    public void adicionarPessoaComConhecido(Pessoa pessoa, Pessoa conhecido) {
        this.atualizarPosicoes();
        if (this.fila.size() - 1 == conhecido.getMinhaPosicao()) {
            this.adicionarPessoa(pessoa);
        } else if (pessoa.getGrupoPertencente().contains(this.fila.get(conhecido.getMinhaPosicao() + 1))) {
            this.adicionarPessoaComConhecido(pessoa, (Pessoa)conhecido.getFilaAtual().getFila().get(conhecido.getMinhaPosicao() + 1));
        } else {
            this.fila.add(conhecido.getMinhaPosicao() + 1, pessoa);
            pessoa.setFilaAtua(this);
            pessoa.setMinhaPosicao(conhecido.getMinhaPosicao() + 1);
        }
    }
}
