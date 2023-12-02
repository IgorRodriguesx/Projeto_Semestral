package main;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class App {
	
	public static void main(String[] args) {
		List<Fila> filas = new ArrayList<>();
		List<Pessoa> pessoas = new ArrayList<>();
		
		var instrucoes = new Parser("input03.txt");
    	
		while(instrucoes.hasNext()) {
    		var comando = instrucoes.nextLine();
    		if(comando.contains("Fabio")){
    			System.out.print("");
    		}
    		if(comando == null || comando.isEmpty()) {
    			continue;
    		} else if (comando.contains("grupo")) {
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

	private static void desiste(List<Pessoa> pessoas, String comando) {
		var pessoasDesistentes = comando.replace("desiste: ", "").split(" ");
		for(String nomeDesistente : pessoasDesistentes) {
			var desistente = localizarPessoaPeloNome(pessoas, nomeDesistente);
			desistente.get().removerDaFila();
		}
	}

	private static void imprime(List<Fila> filas) {
		for(int i = filas.size()-1; i >= 0; i--) {
			var fila = filas.get(i);
			var stringBuilder = new StringBuilder().append("#").append(fila.getId()).append(" [ ");
			stringBuilder.append(String.join(", ", fila.getFila().stream().map(pessoa -> pessoa.getNome()).collect(Collectors.toList())));
			System.out.println(stringBuilder.append(" ]").toString());
		}
	}

	private static void chegou(List<Fila> filas, List<Pessoa> pessoas, String comando) {
		var nomes = comando.replace("chegou: ","").split(" ");
		for(String nome : nomes) {
			entrarNaMelhorPosicaoDasFilas(filas, pessoas, nome);
		}
	}

	private static void entrarNaMelhorPosicaoDasFilas(List<Fila> filas, List<Pessoa> pessoas, String nome) {
		var pessoa = localizarPessoaPeloNome(pessoas, nome).orElse(null);
		var menorFila = encontrarMenorFila(filas);
		if(pessoa != null) {
			if(pessoa.getGrupoPertencente() != null) {
				var filaDoConhecido = obterFilaDoConhecidoComMelhorPosicao(pessoa);
				if (Objects.isNull(filaDoConhecido)) {
					menorFila.adicionarPessoa(pessoa);
					return;
				} else if(filaDoConhecido.getMinhaPosicao() + 1 >= menorFila.getFila().size()) {
					menorFila.adicionarPessoa(pessoa);
					return;
				} else {
					filaDoConhecido.getFilaAtual().adicionarPessoaComConhecido(pessoa, filaDoConhecido);
					return;
				}
			}
		}
		pessoa = gerarPessoa(pessoas, nome);
		menorFila.adicionarPessoa(pessoa);
	}

	private static Pessoa obterFilaDoConhecidoComMelhorPosicao(Pessoa pessoa) {
		var conhecidosEmFilas = pessoa.getGrupoPertencente().stream().filter(conhecido -> !Objects.isNull(conhecido.getFilaAtual())).collect(Collectors.toList());
		
		var conhecidoComMenorPosicaoNaFila = conhecidosEmFilas.stream().min((conhecido1, conhecido2) -> Integer.compare(conhecido1.getMinhaPosicao(), conhecido2.getMinhaPosicao()));
		
		if(conhecidoComMenorPosicaoNaFila.isPresent()) {
			return conhecidoComMenorPosicaoNaFila.get();
		}
		return null;
	}

	private static Fila encontrarMenorFila(List<Fila> filas) {
		return filas.stream().min((fila1, fila2) -> Integer.compare(fila1.getFila().size(), fila2.getFila().size())).orElse(null);
	}

	private static void atenderFila(List<Fila> filas, String comando) {
		//atenderFila: fila1 fila2
		var ids = comando.replace("atendeFila: ", "").split(" ");
		for(String id : ids) {
			localizarFilaPeloId(filas, id).getFila().get(0).removerDaFila();
		}
	}

	private static Fila localizarFilaPeloId(List<Fila> filas, String id) {
		return filas.stream().filter(fila -> fila.getId().equals(id)).findFirst().get();
		
	}

	private static void criarFila(List<Fila> filas, String comando) {
		var ids = comando.replace("criaFila: ", "").split(" ");
		for(String id : ids) {
			filas.add(0,new Fila(id));
		}
	}
	
	private static void conhece(List<Pessoa> pessoas, String comando) {
		//conhece: Nome1 Nome2
		var nomes = comando.replace("conhece: ", "").split(" ");
		var pessoaOpcional = localizarPessoaPeloNome(pessoas, nomes[0]);
		Pessoa pessoa = new Pessoa(nomes[0]);
		if (pessoaOpcional.isPresent()) {
			 pessoa = pessoaOpcional.get();
		}
		var conhece = pessoa.getGrupoPertencente().stream().anyMatch(p -> p.getNome().equals(nomes[1]));
        System.out.println("[" + nomes[0] + "]" + (conhece ? " conhece " : " NÃO conhece ") + "[" + nomes[1] + "]");
	}

	private static Optional<Pessoa> localizarPessoaPeloNome(List<Pessoa> pessoas, String nome) {
		return pessoas.stream().filter(pessoa -> pessoa.getNome().equals(nome)).findFirst();
	}

	private static void existe(List<Pessoa> pessoas, String comando) {
		var nome = comando.replace("existe: ", "").trim();
    	var existe = pessoas.stream().anyMatch(pessoa -> pessoa.getNome().equals(nome));
		System.out.println("[" + nome + "]" + (existe ? " existe!" : " NÃO existe!"));	
	}

	private static void gerarGrupo(List<Pessoa> pessoas, String comando) {
		var nomesDasPessoas = comando.replace("grupo: ", "").split(" ");
		var grupo = new Grupo();
		for(String nome : nomesDasPessoas) {
			grupo.adicionarPessoa(gerarPessoa(pessoas, nome));
		}
	}

	private static Pessoa gerarPessoa(List<Pessoa> pessoas, String nome) {
		var pessoa = new Pessoa(nome);
		pessoas.add(pessoa);
		return pessoa;
	}
}
