

package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class App {
    public App() {
    }

    public static void main(String[] args) {
        List<Fila> filas = new ArrayList();
        List<Pessoa> pessoas = new ArrayList();
        Parser instrucoes = new Parser("input03.txt");

        while(instrucoes.hasNext()) {
            String comando = instrucoes.nextLine();
            if (comando.contains("Fabio")) {
                System.out.print("");
            }

            if (comando != null && !comando.isEmpty()) {
                if (comando.contains("grupo")) {
                    gerarGrupo(pessoas, comando);
                } else if (comando.contains("existe")) {
                    existe(pessoas, comando);
                } else if (comando.contains("conhece")) {
                    conhece(pessoas, comando);
                } else if (comando.contains("criaFila")) {
                    criarFila(filas, comando);
                } else if (comando.contains("atendeFila")) {
                    atenderFila(filas, comando);
                } else if (comando.contains("chegou")) {
                    chegou(filas, pessoas, comando);
                } else if (comando.contains("imprime")) {
                    imprime(filas);
                } else if (comando.contains("desiste")) {
                    desiste(pessoas, comando);
                }
            }
        }

    }

    private static void desiste(List<Pessoa> pessoas, String comando) {
        String[] pessoasDesistentes = comando.replace("desiste: ", "").split(" ");
        String[] var6 = pessoasDesistentes;
        int var5 = pessoasDesistentes.length;

        for(int var4 = 0; var4 < var5; ++var4) {
            String nomeDesistente = var6[var4];
            Optional<Pessoa> desistente = localizarPessoaPeloNome(pessoas, nomeDesistente);
            ((Pessoa)desistente.get()).removerDaFila();
        }

    }

    private static void imprime(List<Fila> filas) {
        for(int i = filas.size() - 1; i >= 0; --i) {
            Fila fila = (Fila)filas.get(i);
            StringBuilder stringBuilder = (new StringBuilder()).append("#").append(fila.getId()).append(" [ ");
            stringBuilder.append(String.join(", ", (Iterable)fila.getFila().stream().map((pessoa) -> {
                return pessoa.getNome();
            }).collect(Collectors.toList())));
            System.out.println(stringBuilder.append(" ]").toString());
        }

    }

    private static void chegou(List<Fila> filas, List<Pessoa> pessoas, String comando) {
        String[] nomes = comando.replace("chegou: ", "").split(" ");
        String[] var7 = nomes;
        int var6 = nomes.length;

        for(int var5 = 0; var5 < var6; ++var5) {
            String nome = var7[var5];
            Pessoa pessoa = (Pessoa)localizarPessoaPeloNome(pessoas, nome).orElse((Object)null);
            Fila menorFila = encontrarMenorFila(filas);
            if (pessoa != null) {
                boolean adicionou = adicionarAFila(pessoa, menorFila);
                if (adicionou) {
                    continue;
                }
            }

            pessoa = gerarPessoa(pessoas, nome);
            menorFila.adicionarPessoa(pessoa);
        }

    }

    private static boolean adicionarAFila(Pessoa pessoa, Fila menorFila) {
        if (pessoa.getGrupoPertencente() != null) {
            Pessoa conhecidoNaFila = obterFilaDoConhecido(pessoa);
            if (conhecidoNaFila == null) {
                menorFila.adicionarPessoa(pessoa);
                return true;
            } else if (conhecidoNaFila.getMinhaPosicao() + 1 >= menorFila.getFila().size()) {
                menorFila.adicionarPessoa(pessoa);
                return true;
            } else {
                conhecidoNaFila.getFilaAtual().adicionarPessoaComConhecido(pessoa, conhecidoNaFila);
                return true;
            }
        } else {
            return false;
        }
    }

    private static Pessoa obterFilaDoConhecido(Pessoa pessoa) {
        List<Pessoa> conhecidosEmFilas = (List)pessoa.getGrupoPertencente().stream().filter((conhecido) -> {
            return conhecido.getFilaAtual() != null;
        }).collect(Collectors.toList());
        Optional<Pessoa> conhecidoComMenorPosicaoNaFila = conhecidosEmFilas.stream().min((conhecido1, conhecido2) -> {
            return Integer.compare(conhecido1.getMinhaPosicao(), conhecido2.getMinhaPosicao());
        });
        return conhecidoComMenorPosicaoNaFila.isPresent() ? (Pessoa)conhecidoComMenorPosicaoNaFila.get() : null;
    }

    private static Fila encontrarMenorFila(List<Fila> filas) {
        return (Fila)filas.stream().min((fila1, fila2) -> {
            return Integer.compare(fila1.getFila().size(), fila2.getFila().size());
        }).orElse((Object)null);
    }

    private static void atenderFila(List<Fila> filas, String comando) {
        String[] ids = comando.replace("atendeFila: ", "").split(" ");
        String[] var6 = ids;
        int var5 = ids.length;

        for(int var4 = 0; var4 < var5; ++var4) {
            String id = var6[var4];
            ((Pessoa)localizarFilaPeloId(filas, id).getFila().get(0)).removerDaFila();
        }

    }

    private static Fila localizarFilaPeloId(List<Fila> filas, String id) {
        return (Fila)filas.stream().filter((fila) -> {
            return fila.getId().equals(id);
        }).findFirst().get();
    }

    private static void criarFila(List<Fila> filas, String comando) {
        String[] ids = comando.replace("criaFila: ", "").split(" ");
        String[] var6 = ids;
        int var5 = ids.length;

        for(int var4 = 0; var4 < var5; ++var4) {
            String id = var6[var4];
            filas.add(0, new Fila(id));
        }

    }

    private static void conhece(List<Pessoa> pessoas, String comando) {
        String[] nomes = comando.replace("conhece: ", "").split(" ");
        Optional<Pessoa> pessoaOpcional = localizarPessoaPeloNome(pessoas, nomes[0]);
        Pessoa pessoa = new Pessoa(nomes[0]);
        if (pessoaOpcional.isPresent()) {
            pessoa = (Pessoa)pessoaOpcional.get();
        }

        boolean conhece = pessoa.getGrupoPertencente().stream().anyMatch((p) -> {
            return p.getNome().equals(nomes[1]);
        });
        System.out.println("[" + nomes[0] + "]" + (conhece ? " conhece " : " NÃO conhece ") + "[" + nomes[1] + "]");
    }

    private static Optional<Pessoa> localizarPessoaPeloNome(List<Pessoa> pessoas, String nome) {
        return pessoas.stream().filter((pessoa) -> {
            return pessoa.getNome().equals(nome);
        }).findFirst();
    }

    private static void existe(List<Pessoa> pessoas, String comando) {
        String nome = comando.replace("existe: ", "").trim();
        boolean existe = pessoas.stream().anyMatch((pessoa) -> {
            return pessoa.getNome().equals(nome);
        });
        System.out.println("[" + nome + "]" + (existe ? " existe!" : " NÃO existe!"));
    }

    private static void gerarGrupo(List<Pessoa> pessoas, String comando) {
        String[] nomesDasPessoas = comando.replace("grupo: ", "").split(" ");
        Grupo grupo = new Grupo();
        String[] var7 = nomesDasPessoas;
        int var6 = nomesDasPessoas.length;

        for(int var5 = 0; var5 < var6; ++var5) {
            String nome = var7[var5];
            grupo.adicionarPessoa(gerarPessoa(pessoas, nome));
        }

    }

    private static Pessoa gerarPessoa(List<Pessoa> pessoas, String nome) {
        Pessoa pessoa = new Pessoa(nome);
        pessoas.add(pessoa);
        return pessoa;
    }
}
