

package main;

import java.util.List;

public class Pessoa {
    private String nome;
    private Grupo grupoPertencente;
    private Fila filaAtual;
    private Integer minhaPosicao;

    public Pessoa(String nome) {
        this.nome = nome;
        this.grupoPertencente = new Grupo();
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Fila getFilaAtual() {
        return this.filaAtual;
    }

    public void setFilaAtua(Fila filaAtua) {
        this.filaAtual = filaAtua;
    }

    public void setGrupoPertencente(Grupo grupo) {
        this.grupoPertencente = grupo;
    }

    public List<Pessoa> getGrupoPertencente() {
        return this.grupoPertencente == null ? null : this.grupoPertencente.getGrupo();
    }

    public void removerDaFila() {
        this.filaAtual.remove(this);
        this.filaAtual.atualizarPosicoes();
        this.filaAtual = null;
        this.minhaPosicao = null;
    }

    public Integer getMinhaPosicao() {
        return this.minhaPosicao;
    }

    public void setMinhaPosicao(Integer minhaPosicao) {
        this.minhaPosicao = minhaPosicao;
    }
}
