import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot)

/**
 * A Tela Inicial do jogo.
 * Mostra o nome do jogo e as instruções, conforme requisito J4.1.
 */
public class TelaInicial extends World
{
    public TelaInicial()
    {    
        // Cria um mundo de 800x600 pixels.
        super(800, 600, 1); 
        
        // Prepara a tela
        prepararTela();
    }
    
    /**
     * Prepara os textos da tela inicial.
     */
    private void prepararTela()
{
 
    
    // 1. Carrega a imagem original
    GreenfootImage fundo = new GreenfootImage("fundo_tela_inicial.png");
    
    // 2. FORÇA a imagem a ter o tamanho do mundo (800x600)
    fundo.scale(getWidth(), getHeight()); 
    
    // 3. Define a imagem (agora esticada) como fundo
    setBackground(fundo);
    
   
    
    // Requisito J4.1: Mostra o nome do jogo 
    showText("GRADIUS IPOO", 400, 200); 
    
    // Requisito J4.1: Mostra instruções 
    showText("Use as SETAS para mover", 400, 300);
    showText("Pressione ESPAÇO para atirar", 400, 350);
    showText("Pressione ENTER para começar", 400, 450);
}
    
    /**
     * O método act() da TelaInicial verifica se o jogador
     * pressionou "enter" para começar o jogo.
     */
    public void act()
    {
        if (Greenfoot.isKeyDown("enter"))
        {
            // Cria e define o mundo da Fase 1 como o mundo atual
            Greenfoot.setWorld(new Fase1());
        }
    }
}