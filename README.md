# A3 - Algoritmos e Estrutura de Dados 2023/2
### Integrantes:
Eduardo de Santana - 821224129;
Felipe Losano - 821234991;
Giovanna Pilon - 822143026;
Igor Rodrigues - 822138611;
Rafael Colete - 821231041;
Samuel Costa Nascimento - 822159536;
Vitor Sirotenco - 822128201;

#### Explicação Implementações:
Foram criadas classes para representar as seguintes entidades: [Fila](src/main/Fila.java#), [Pessoa](src/main/Fila.java), [Grupo](src/main/Pessoa.java);
Para armazenar os dados, foram utilizados uma lista de objetos do tipo Fila, e uma Lista de objetos do tipo Pessoas;

#### Comandos:
##### grupo:
Nesse comando é chamado o metodo [gerarGrupo](#void-gerargrupopessoas-pessoas-string-comando) que cria um objeto do  tipo grupo e um objeto do tipo Pessoa para cada nome indicado pelo comando.
Ex comando:
grupo: Eduardo Rafael Felipe

##### existe:

Neste comando é verificado se através do metodo [existe](#void-existelistpessoa-pessoas-string-comando) se um nome existe em algum grupo, ou fila;

##### conhece:
Neste comando, é verificado se as pessoas indicadas estão no mesmo grupo através do método [conhece](#void-conhecelistpessoa-pessoas-string-comando), e é produzida uma mensagem informando se as pessoas se conhecem ou não;
##### criaFila:
Neste comando são criadas as filas com os nomes indicados, e as mesmas são adicionadas a lista de filas;

##### atendeFila:
Neste comando, é obtida cada fila indicada e removida a primeira pessoa dessa fila;

##### chegou:
Neste comando, cada pessoa indicada é inserida na melhor posição posivel através do metodo chegou, que para cada nome indicado no comando chama o metodo Utils.[entrarNaMelhorPosicaoDasFilas](void-entrarnamelhorposicaodasfilaslistfila-filas-listpessoa-pessoas-string-nome), que faz toda orquestração e validações de qual a melhor posição nas filas;

##### imprime:
Este metodo pega cada fila na lista de filas e produz o texto exibindo sua situação atual;
##### desiste:
O metodo desiste, localiza a pessoa indicada, e remove ela da sua fila;

#### Métodos e funçoes:

##### App.java
###### void gerarGrupo(Pessoas pessoas, String comando):
    Primeiro o comando é dividio, e os nomes das pessoas são separadas em um array;
    Em seguida é criado o grupo;
    Com o grupo criado é realizado um for each, no qual são percorridos todos os nomes, e para cada nome é criado um objeto do tipo Pessoa com a função Utils.gerarPessoa, e esta pessoa é passada como parametro ao metodo do grupo adicionarPessoa, que adiona esta pessoa ao grupo, e adiciona uma referencia do grupo ao atributo GrupoPertencente desta pessoa;
    
###### void existe(List<Pessoa> pessoas, String comando)
    Primeiro é obtido o nome da pessoa informada no comando;
    E depois a lista de pessoas é percorrida verificando se existe alguma pessoa com o nome informado, e é produzido uma mensagem repassando esta informação;
    

##### Utils.java
###### void entrarNaMelhorPosicaoDasFilas(List<Fila> filas, List<Pessoa> pessoas, String nome)
Primeiro tentamos localizar essa pessoa na lista de pessoas, se a pessoa não existir ela é adicionada à menor fila,
caso a pessoa exista o conhecido com melhor posição é buscado através da função Utils.obterConhecidoComMelhorPosicao que faz uma localiza conhecidos que estão em uma fila, na sequencia é verificado onde a pessoa entraria, caso tentasse entrar entrar junto a esse conhecido atráves da função Fila.[obterUltimoConhecidoNaFila](#pessoa-obterultimoconhecidonafila-pessoa-conhecido), que retorna a posição do ultimo conhecido em sequencia na fila;
Tendo a melhor posição ao entrar com o conhecido, comparamos com a posição que entraria na melhor fila e é tomada a decisão de qual fila entrará com base nisso;

##### Fila.java
###### Pessoa obterUltimoConhecidoNaFila(Pessoa conhecido)
Esta função utiliza da recursividade, para verificar se a proxima pessoa da fila é um conhecido, caso a proxima pessoa seja conheciada, é realizada a rechamada, caso não retorna o conhecido, que é o ultimo conhecido em sequencia;

   
