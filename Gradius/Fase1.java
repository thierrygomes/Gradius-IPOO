import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot)

/**
 * Mundo da Fase 1 (Requisito J4.2).
 * Estilo Gradius com fundo rolando.
 */
public class Fase1 extends World
{
    private int pontuacao;
    private GreenfootSound musicaFundo;

    // Variáveis para o fundo rolando
    private GreenfootImage fundoA;
    private GreenfootImage fundoB;
    private int xFundoA;
    private int xFundoB;

    private static final int PONTUACAO_VITORIA = 200; // Meta de pontos para vencer
    private boolean jogoAtivo = true; // Flag para controlar se o jogo está rodando

    public Fase1()
    {    
        // Mundo de 800x600. O 'false' no final é IMPORTANTE: 
        // ele desliga o "wrap" dos atores nas bordas.
        super(800, 600, 1, false); 

        // 1. Prepara o fundo rolando
        fundoA = new GreenfootImage("fundo_espaco.png");
        fundoB = new GreenfootImage("fundo_espaco.png");
        xFundoA = 0;
        xFundoB = getWidth(); // Posiciona o fundo B logo após o A

        // 2. Adiciona o jogador  (Requisito J4.4)
        NaveJogador jogador = new NaveJogador();
        addObject(jogador, 100, getHeight() / 2); // Adiciona o jogador na esquerda

        // 3. Inicia a pontuação  (Requisito J4.8)
        pontuacao = 0;
        atualizarPontuacao();

        // 4. Inicia a música de fundo  (Requisito J4.10)
        musicaFundo = new GreenfootSound("musica_fase1.mp3");
        musicaFundo.playLoop();
    }

    public void act()
    {

        if (jogoAtivo) 
        {
            // Se o jogo está ativo, faz as coisas normais
            rolarFundo();
            spawnarAtores();
            verificarVitoria();
        }
        else
        {
            // Se o jogo NÃO está ativo (Game Over), 
            // fica verificando se o jogador quer reiniciar
            verificarRestart();
        }
    }

    /**
     * Move os fundos para a esquerda para criar a ilusão de movimento.
     */
    private void rolarFundo()
    {
        // Velocidade da rolagem
        int velocidade = 2; 

        xFundoA -= velocidade;
        xFundoB -= velocidade;

        // Se o fundo A saiu da tela, reposiciona ele depois do B
        if (xFundoA <= -getWidth()) {
            xFundoA = xFundoB + getWidth();
        }
        // Se o fundo B saiu da tela, reposiciona ele depois do A
        if (xFundoB <= -getWidth()) {
            xFundoB = xFundoA + getWidth();
        }

        // Desenha as imagens na nova posição
        getBackground().drawImage(fundoA, xFundoA, 0);
        getBackground().drawImage(fundoB, xFundoB, 0);
    }

    /**
     * Cria inimigos e obstáculos na borda direita da tela.
     */
    private void spawnarAtores()
    {
        // 1% de chance de criar um InimigoReto (J4.5)
        if (Greenfoot.getRandomNumber(100) < 1) 
        {
            InimigoReto inimigo = new InimigoReto();
            int y = Greenfoot.getRandomNumber(getHeight());
            addObject(inimigo, getWidth() - 10, y);
        }

        // 0.8% de chance de criar um InimigoZigZag (J4.5)
        if (Greenfoot.getRandomNumber(100) < 0.8) 
        {
            InimigoZigZag inimigoZ = new InimigoZigZag();
            int y = Greenfoot.getRandomNumber(getHeight());
            addObject(inimigoZ, getWidth() - 10, y);
        }

        // 0.5% de chance de criar um Asteroide (J4.7)
        if (Greenfoot.getRandomNumber(100) < 0.5) 
        {
            Asteroide ast = new Asteroide();
            int y = Greenfoot.getRandomNumber(getHeight());
            addObject(ast, getWidth() - 10, y);
        }
    }

    /**
     * Permite que outros atores (como a NaveJogador) adicionem pontos.
     * Isso segue o bom design de classes[cite: 85].
     */
    public void adicionarPontos(int valor)
    {
        pontuacao += valor;
        atualizarPontuacao();
    }

    /**
     * Atualiza o texto da pontuação na tela.
     */
    private void atualizarPontuacao()
    {
        // Requisito J4.8: Pontuação deve aparecer na tela 
        showText("Pontos: " + pontuacao, 80, 25);
    }

    /**
     * Para a música de fundo (útil para a tela de Game Over).
     */
    public void pararMusica()
    {
        musicaFundo.stop();
    }

    /**
     * Verifica a cada ato se o jogador atingiu a pontuação de vitória.
     */
    private void verificarVitoria()
    {
        if (pontuacao >= PONTUACAO_VITORIA)
        {
            // Informa a vitória (J4.3)
            addObject(new Vitoria(), getWidth() / 2, getHeight() / 2);

            // Encerra o jogo
            encerrarJogo();
        }
    }

    /**
     * Retorna a pontuação atual.
     * Usado pela NaveJogador para informar o GameOver.
     */
    public int getPontuacao()
    {
        return pontuacao;
    }

    /**
     * Método centralizado para parar o jogo (música, spawns, etc.)
     * ATUALIZADO: Agora não usa Greenfoot.stop() e pede o restart.
     */
    public void encerrarJogo()
    {
        this.jogoAtivo = false;
        pararMusica();

        // Mostra a pontuação final (como fizemos antes)
        String textoFinal = "Pontuação Final: " + pontuacao;
        showText(textoFinal, getWidth() / 2, (getHeight() / 2) + 60);

        // Mostra o texto para reiniciar
        showText("Aperte ENTER para reiniciar", getWidth() / 2, (getHeight() / 2) + 100);

    }

    /**
     * Permite que outros atores (como a Nave) saibam se o jogo
     * ainda está rodando.
     */
    public boolean isJogoAtivo()
    {
        return this.jogoAtivo;
    }

    /**
     * Verifica se o jogador apertou ENTER após o Game Over
     * para reiniciar o jogo.
     */
    private void verificarRestart()
    {
        if (Greenfoot.isKeyDown("enter"))
        {
            // Para a música antiga (caso esteja tocando algo)
            pararMusica(); 

            // Cria um NOVO mundo da Fase1, começando tudo do zero
            Greenfoot.setWorld(new Fase1());
        }
    }
}