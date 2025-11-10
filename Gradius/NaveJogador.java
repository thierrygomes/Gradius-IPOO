import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot)

/**
 * A Nave do Jogador. (Requisito J4.4)
 * Controlada pelo usuário para mover e atirar.
 */
public class NaveJogador extends Actor
{
    /**
     * Construtor da NaveJogador.
     * Define a imagem da nave.
     */
    public NaveJogador()
    {
        // Carrega a imagem da nave (coloque 'nave.png' na pasta 'images')
        setImage("nave.png");
    }

    public void act()
    {
        // 1. Verifica se o jogador apertou teclas de movimento
        verificarMovimento();

        // 2. Verifica se o jogador atirou
        verificarDisparo();

        // 3. Verifica se o jogador colidiu com algo
        verificarColisoes();

        // 4. (NOVO) Verifica se o jogador coletou um item
        verificarColetaPowerUp();
    }

    /**
     * Controla o movimento da nave baseado nas setas do teclado.
     */
    private void verificarMovimento()
    {
        int velocidade = 5; // Você pode ajustar este valor

        if (Greenfoot.isKeyDown("up")) {
            setLocation(getX(), getY() - velocidade);
        }
        if (Greenfoot.isKeyDown("down")) {
            setLocation(getX(), getY() + velocidade);
        }
        if (Greenfoot.isKeyDown("left")) {
            setLocation(getX() - velocidade, getY());
        }
        if (Greenfoot.isKeyDown("right")) {
            setLocation(getX() + velocidade, getY());
        }
    }

    /**
     * Verifica se a tecla "espaço" foi pressionada para atirar.
     */
    private void verificarDisparo()
    {
        if (Greenfoot.isKeyDown("space"))
        {
            // Toca o som do tiro (Requisito J4.9)
            Greenfoot.playSound("som_tiro.wav"); 

            // Cria um novo ator 'Tiro' na posição da nave
            Tiro tiro = new Tiro();
            getWorld().addObject(tiro, getX() + 50, getY()); // +50 para sair da frente da nave
        }
    }

    /**
     * Verifica colisões com inimigos ou obstáculos (Requisitos J4.5 e J4.7).
     * Se colidir, o jogo acaba (Requisito J4.3).
     * ATUALIZADO para usar o método 'encerrarJogo()' do Mundo.
     */
    private void verificarColisoes()
    {
        Actor inimigo = getOneIntersectingObject(InimigoReto.class);
        if (inimigo == null) {
            inimigo = getOneIntersectingObject(InimigoZigZag.class);
        }
        Actor asteroide = getOneIntersectingObject(Asteroide.class); 

        if (inimigo != null || asteroide != null)
        {
            // Toca o som de explosão (Requisito J4.9)
            Greenfoot.playSound("som_explosao_jogador.wav");

            // Pega o mundo
            Fase1 mundo = (Fase1) getWorld();

            // Informa a derrota (J4.3)
            mundo.addObject(new GameOver(), mundo.getWidth() / 2, mundo.getHeight() / 2);

            // Pede ao mundo para encerrar o jogo
            mundo.encerrarJogo();

            // Remove a nave do mundo
            getWorld().removeObject(this);
        }
    }

    /**
     * Verifica se a nave coletou um PowerUp (Requisito J4.6).
     */
    private void verificarColetaPowerUp()
    {
        Actor powerUp = getOneIntersectingObject(PowerUp.class);

        if (powerUp != null)
        {
            // Toca um som de coleta
            Greenfoot.playSound("som_coleta.wav"); // Você precisa adicionar este som

            // Pede ao mundo para adicionar mais pontos
            Fase1 mundo = (Fase1) getWorld();
            mundo.adicionarPontos(50); // Bônus de 50 pontos

            // Remove o item do mundo
            getWorld().removeObject(powerUp);
        }
    }
}