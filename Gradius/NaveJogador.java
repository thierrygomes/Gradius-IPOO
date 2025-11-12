import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot)

/**
 * A Nave do Jogador.
 * ATUALIZADO: Agora atira por clique, não por segurar.
 */
public class NaveJogador extends Actor
{
    // NÃO precisamos mais das variáveis de cooldown
    
    public NaveJogador()
    {
        setImage("nave.png");
    }

    public void act()
{

    
    // 1. Pega o mundo
    Fase1 mundo = (Fase1) getWorld();
    
    // 2. Se o mundo não existir (ator foi removido) 
    //    OU se o jogo não estiver mais ativo (vitória ou game over)...
    if (mundo == null || !mundo.isJogoAtivo())
    {
        // ...então a nave não faz absolutamente NADA.
        return; 
    }

    // Verifica se morreu
    if (verificarColisoes()) {
        return; 
    }

    // Se não morreu, faz o resto
    verificarMovimento();
    verificarDisparo();
    verificarColetaPowerUp();
}
    /**
     * Controla o movimento da nave (com a correção das bordas)
     */
    private void verificarMovimento()
    {
        int velocidade = 5; 
        int mundoLargura = getWorld().getWidth();
        int mundoAltura = getWorld().getHeight();
        int x = getX();
        int y = getY();

        if (Greenfoot.isKeyDown("up") && y > 0) {
            setLocation(x, y - velocidade);
        }
        if (Greenfoot.isKeyDown("down") && y < mundoAltura - 1) { 
            setLocation(x, y + velocidade);
        }
        if (Greenfoot.isKeyDown("left") && x > 0) {
            setLocation(x - velocidade, y);
        }
        if (Greenfoot.isKeyDown("right") && x < mundoLargura - 1) {
            setLocation(x + velocidade, y);
        }
    }

    /**
     * Verifica se a tecla "espaço" foi pressionada para atirar.
     * ATUALIZADO: Agora usa Greenfoot.getKey() para atirar 
     * apenas UMA VEZ por clique.
     */
    private void verificarDisparo()
    {
        // --- MUDANÇA PRINCIPAL AQUI ---
        
        // Greenfoot.getKey() só retorna "space" no exato momento
        // em que a tecla é pressionada.
        if ("space".equals(Greenfoot.getKey()))
        {
            // Toca o som do tiro
            Greenfoot.playSound("som_tiro.mp3"); 

            // Cria o tiro
            Tiro tiro = new Tiro();
            getWorld().addObject(tiro, getX() + 50, getY());
            
            // NÃO precisamos mais reiniciar o cooldown
        }
    }

    /**
     * Verifica colisões com inimigos ou obstáculos
     */
    private boolean verificarColisoes() 
    {
        Actor inimigo = getOneIntersectingObject(InimigoReto.class);
        if (inimigo == null) {
            inimigo = getOneIntersectingObject(InimigoZigZag.class);
        }
        Actor asteroide = getOneIntersectingObject(Asteroide.class); 

        if (inimigo != null || asteroide != null)
        {
            Greenfoot.playSound("som_explosao_jogador.mp3");
            Fase1 mundo = (Fase1) getWorld();
            mundo.addObject(new GameOver(), mundo.getWidth() / 2, mundo.getHeight() / 2);
            mundo.encerrarJogo();
            getWorld().removeObject(this);
            return true; 
        }
        return false; 
    }

    /**
     * Verifica se a nave coletou um PowerUp
     */
    private void verificarColetaPowerUp()
    {
        Actor powerUp = getOneIntersectingObject(PowerUp.class);
        if (powerUp != null)
        {
            Greenfoot.playSound("som_coleta.mp3");
            Fase1 mundo = (Fase1) getWorld();
            mundo.adicionarPontos(50); 
            getWorld().removeObject(powerUp);
        }
    }
}