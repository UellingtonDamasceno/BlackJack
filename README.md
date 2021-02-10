# BlackJack
Implementação do famoso jogo blackjack (também chamado de 21). Utilizando uma intraface de linha de comando. 

## Sumário
- [Descritivo do problema](https://github.com/UellingtonDamasceno/BlackJack/blob/master/resources/Descritivo%20-%20Mi%20de%20programa%C3%A7%C3%A3o%20-%20P2.pdf)
- [Regras do jogo](#regras-do-jogo)
- [Modelo Conceitual](#modelo-conceitual)
- [User Stories](#user-stories)
- [Melhorias](#melhorias)
- [Extras](#extras)
- [Relatórios]()

# Regras do jogo

- Todas as cartas com 10 ou menos (2, 3, 4 ... , 10) têm um valor igual a seu número. 
os naipes não influenciam no valor das cartas (O quatro de paus possui o mesmo valor 
que o quatro de ouros). Por exemplo: se um jogador receber as seguintes cartas: 
2, 5 e 8, o valor de todas as três equivalerá a 15.

- Os valetes, damas e reis são equivalentes a 10. Como as cartas numeradas, naipes
diferentes não afetam o valor dessas cartas. Por exemplo: Se um jogador receber um valete
ou um rei como suas duas primeiras cartas viradas para cima, elas equivalerão a 20 pontos,
a segunda maior pontuação, abaixo apenas do 21.

- A última carta, o ás, tem um valor especial no jogo. O ás pode valer 1 ou 11, com 
o número real dependente do valor mais vantajoso em uma mão especifica. Um ás e um rei, 
rainha, ou valete significa 21.

# Modelo Conceitual
O modelo conceitual abaixo demostra como as classes devem se relacionar.
![Modelo conceitual](https://github.com/UellingtonDamasceno/BlackJack/blob/master/resources/modelo-conceitual.png)

Tendo em mente esse modelo conceitual inicial desenvolvemos uma produto que é
representado por esse [diagrama de classe](https://raw.githubusercontent.com/UellingtonDamasceno/BlackJack/master/resources/BlackJack_Diagrama.png).

# User Stories

| User story nº | Título | Breve Descrição | Completa |
| :-----------: | ------ | --------------- | :------: |
| 01 | Manutenção Jogador | Cadastrar jogadores com *user* e senha. | ✅ |
| 02 | Iniciar partida | Iniciar uma nova partida, indicar o número de jogadores e selecioná-los | ✅ |
| 03 | Nova partida | Solicitar uma nova carta. | ✅ |
| 04 | Parar as jogadas | parar de pedir cartas. | ✅ |
| 05 | Listar cartas do baralho | Ver as cartas restantes do baralho do último jogo | ✅ |
| 06 | Listar cartas do baralho ordenadas | Ver as cartas restantes do baralho do último jogo pelo numero e o nipe | ✅ |
| 07 | Mostrar placar | Mostrar um arquivo ordenado por maior pontuação, contendo dados: *user*, pontuação total de cada jogador e a quantidade de jogos vencidos por jogador | ✅ |

# Melhorias
A lista abaixo apresenta algumas melhorias que podem ser implementadas no algoritmo desse projeto para melhorar a qualidade do código sem fugir do conexto de aprendizagem esperado.

| Pacote | Classe | Melhoria |
| ------ | ------ | -------- |
| util | ListaEncadeada | Subistituir por um ArrayList ou LinkedList. | 
| util | Pilha | Utilizar generics para evitar cast de objetos. | 
| model | Jogador | Tornar os atributos da classe para protected para permitir o Croupier tenha acesso. |
| model | Partida | Implementar o padrão observer para notificar a view das ações do Croupier ao invés de utilizar uma lista de historico de jogada. | 
| controller | ControllerArquivos | Utilizar métodos que solicitam o nome dos arquivos ao invés de deixar definido na classe. |
| facade | Facade | Tornar privado os atributos da classe. |
| view | View | Criar classes de menu para reduzi a quantidade de código na classe. |
| view | view | Mudar para uma interface gráfica em JavaFX |


# Extras

Como extra foi desenvolvido um sistema de fichas que simula um cassino real onde 
um jogador pode apostar valores em fichas que podem ser recarregadas "comprando" 
na loja. Neste sistema cada jogador recém cadastrado inicia com 100 pontos e 
aposta 10 fichas para participar de uma partida. Para participar de uma partida o
jogador deverá investir 10 pontos, caso ganhe receberá 20 pontos, perderá 5 pontos caso perca para o croupier e não ganhará nem perderá se empatar.

Além do sistema de fichas, também foi implementado 4 estilos diferente de 
partidas sendo o estilo rápido (2 Baralhos), médio (4 Baralhos), demorado (8 
Baralhos) e o personalizado onde o usuário pode digitar a quantidade de baralhos.
