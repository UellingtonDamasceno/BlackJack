# BlackJack
Implementação do famoso jogo blackjack (também chamado de 21). Utilizando uma intraface de linha de comando. 

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
![Modelo conceitual](✅)

# User Stories

| User story nº | Título | Breve Descrição | Completa |
| :-----------: | ------ | --------------- | -------- |
| 01 | Manutenção Jogador | Cadastrar jogadores com *user* e senha. | ✅ |
| 02 | Iniciar partida | Iniciar uma nova partida, indicar o número de jogadores e selecioná-los | ✅ |
| 03 | Nova partida | Solicitar uma nova carta. | ✅ |
| 04 | Parar as jogadas | parar de pedir cartas. | ✅ |
| 05 | Listar cartas do baralho | Ver as cartas restantes do baralho do último jogo | ✅ |
| 06 | Listar cartas do baralho ordenadas | Ver as cartas restantes do baralho do último jogo pelo numero e o nipe | ✅ |
| 07 | Mostrar placar | Mostrar um arquivo ordenado por maior pontuação, contendo dados: *user*, pontuação total de cada jogador e a quantidade de jogos vencidos por jogador | ✅ |
