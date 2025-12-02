import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot)

public class TiroInimigo extends Projetil
{
    public TiroInimigo()
    {
        super(-6); // Velocidade -6 (para a esquerda)
        setImage("tiro.png"); 
        getImage().mirrorHorizontally(); 
        getImage().setColor(Color.RED); 
    }
    
    public void act()
    {
        super.act(); 
        
        if (getWorld() != null) {
            checkColisaoComJogador();
        }
    }
    
    private void checkColisaoComJogador()
    {
        Actor jogador = getOneIntersectingObject(NaveJogador.class);
        
        if (jogador != null)
        {
            Greenfoot.playSound("som_explosao_jogador.mp3");
            
            // --- CORREÇÃO AQUI: Verifica a Fase para dar Game Over ---
            if (getWorld() instanceof Fase1) {
                Fase1 mundo = (Fase1) getWorld();
                mundo.addObject(new GameOver(), mundo.getWidth()/2, mundo.getHeight()/2);
                mundo.encerrarJogo();
            }
            else if (getWorld() instanceof Fase2) {
                Fase2 mundo = (Fase2) getWorld();
                mundo.addObject(new GameOver(), mundo.getWidth()/2, mundo.getHeight()/2);
                mundo.encerrarJogo();
            }
            
            else if (getWorld() instanceof Fase3) {
                ((Fase3) getWorld()).encerrarJogo();
            }
            // --------------------------------------------------------
            
            getWorld().removeObject(jogador);
            getWorld().removeObject(this);
        }
    }
}