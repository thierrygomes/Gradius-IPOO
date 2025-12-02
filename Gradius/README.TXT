TRABALHO DE IPOO
ENTREGA FINAL
Nome do jogo: GRADIUS IPOO

Integrantes do grupo:

ALEXSANDER DAVI NAVES OLEGARIO
NIELON CARDOSO DOS REIS    
THIERRY JACINTO GOMES

Escreva abaixo de cada Requisito se ele foi atendido no projeto ou não.

REQUISITOS DE JOGABILIDADE

J4.1 Deve existir um mundo, que podemos chamar de tela inicial, que mostra o nome do jogo e instruções de como jogar.
R.: Sim. A classe TelaInicial exibe o nome, instruções de teclas e aguarda o Enter.

J4.2 Deve existir um mundo que é a primeira fase do jogo.
R.: Sim. Classe Fase1 implementada com scroll de fundo e inimigos básicos.

J4.3 Deve ser possível terminar o jogo com vitória ou derrota e isso deve ser informado de alguma forma para o jogador.
R.: Sim. Classes GameOver (derrota) e telas de transição/vitória foram implementadas.

J4.4 Deve existir um ator que é controlado pelo jogador.
R.: Sim. Classe NaveJogador controlada pelas setas e barra de espaço.

J4.5 Devem existir pelo menos dois tipos de atores que representam inimigos que precisem ser atacados ou evitados.
R.: Sim. InimigoReto e InimigoZigZag.

J4.6 Deve existir pelo menos um tipo de ator que representa um item que o jogador poderá coletar no jogo.
R.: Sim. Classe PowerUp (e subclasses Moeda e Cristal).

J4.7 Deve existir pelo menos um tipo de ator que representa um obstáculo para o jogador.
R.: Sim. Classe Asteroide e LixoEspacial.

J4.8 Deve existir alguma forma de pontuação e deve ser possível que o jogador vença o jogo com diferentes pontuações. A pontuação deve aparecer na tela do jogo durante toda a partida.
R.: Sim. Pontuação visível no canto superior esquerdo em todas as fases.

J4.9 O jogo deve ter sons de efeitos sonoros e deve usar, pelo menos, três arquivos de áudio diferentes.
R.: Sim. Sons de tiro, explosão do inimigo, explosão do jogador e coleta de item.

J4.10 O jogo deve ter som de fundo (pelo menos um).
R.: Sim. Músicas de fundo para as fases e para o chefão.

J5.1 A tela inicial feita na primeira entrega será dividida em dois mundos...
R.: Sim. Implementamos a TelaInicial com instruções na tela e telas de transição (TelaTransicaoFase3) entre as fases para preparar o jogador.

J5.2 Deve existir um mundo que é a segunda fase do jogo.
R.: Sim. Classe Fase2 implementada com fundo diferente, maior velocidade e dificuldade aumentada.

J5.3 Deve existir um mundo que é a terceira fase do jogo e que tenha um ator que é o inimigo final a ser derrotado (chefão).
R.: Sim. Classe Fase3 implementada contendo a batalha contra a classe Chefao.

J5.4 Deve existir pelo menos mais um tipo de ator (além dos dois da primeira entrega) que representam inimigos...
R.: Sim. Classe InimigoAtirador (herda de Inimigo), que dispara projéteis contra o jogador.

J5.5 Deve existir pelo menos mais dois tipos de atores que representam itens que o jogador poderá coletar no jogo...
R.: Sim. Criados itens Moeda e Cristal (além do PowerUp), cada um com pontuação diferente, herdando da superclasse Item.

J5.6 Deve existir pelo menos mais um tipo de ator que representa um obstáculo para o jogador (além do obstáculo da primeira entrega).
R.: Sim. Classe LixoEspacial (herda de Asteroide), com visual e rotação próprios.

J5.7 O jogo deve usar pelo menos mais dois arquivos de áudio, além dos usados na primeira entrega.
R.: Sim. Adicionados sons extras como musica_boss.mp3 e som_coleta.mp3.

J5.8 Deve existir um mundo que aparece ao final do jogo e que mostre se o jogador venceu ou perdeu... Nele devem ser exibidos os pontos...
R.: Sim. Ao derrotar o Chefão, a Fase3 exibe um relatório final utilizando um ArrayList para listar as conquistas e a pontuação total.

REQUISITOS DE IMPLEMENTAÇÃO

I4.1 O trabalho deve usar corretamente os conceitos de Classes, Objetos e Encapsulamento.
R.: Sim. Atributos privados, métodos públicos e classes organizadas.

I4.2 O trabalho deve usar corretamente os conceitos de Abstração e Modularização.
R.: Sim. Lógica separada em métodos (spawnarAtores, verificarColisoes, etc).

I4.3 O trabalho deve usar corretamente os conceitos de atributos, variáveis locais e parâmetros.
R.: Sim. Utilizados em todas as classes.

I4.4 O trabalho deve usar corretamente os conceitos de atributos e métodos estáticos.
R.: Sim. Uso de constantes (PONTUACAO_VITORIA) e métodos da classe Greenfoot.

I4.5 O trabalho deve ser implementado com bom design de classes: baixo acoplamento, alta coesão...
R.: Sim. A Nave não conhece detalhes internos dos inimigos, usa polimorfismo para interagir.

I4.6 O código deve estar legível, organizado e comentado.
R.: Sim. Código indentado e comentado.

I4.7 A implementação deve, necessariamente, utilizar o Greenfoot versão 3.9.0.
R.: Sim.

I4.8 O código entregue deve estar compilando.
R.: Sim.

I4.9 Não é permitido o uso de funções lambda...
R.: Sim. Utilizamos apenas laços for/while tradicionais e iterações padrão.

I5.1 A implementação do jogo deve utilizar algum tipo de coleção (ArrayList, HashMap, etc.). A coleção utilizada precisa ter utilidade real no jogo.
R.: Sim. Utilizamos um ArrayList<String> na classe Fase3 para montar e exibir o relatório final de missão (vitória) linha por linha.

I5.2 A implementação deve usar herança: devem existir pelo menos três superclasses criadas por vocês...
R.: Sim. Foram criadas 3 superclasses principais:
1. Inimigo (pai de InimigoReto, InimigoZigZag, InimigoAtirador, Chefao).
2. Projetil (pai de Tiro e TiroInimigo).
3. Item (pai de PowerUp, Moeda, Cristal).

COMENTÁRIOS (OPCIONAIS)

Algum comentário sobre o tema do trabalho?
Um jogo de nave estilo Gradius com progressão de fases e batalha de chefe.

Algum comentário sobre o Greenfoot?
Ferramenta muito boa para visualizar orientação a objetos na prática.