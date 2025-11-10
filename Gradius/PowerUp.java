import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot)

/**
 * Item coletável (Requisito J4.6).
 * Dá pontos extras ao jogador quando coletado.
 */
public class PowerUp extends Actor
{
    // Esta velocidade DEVE ser a mesma da 'rolarFundo()' na Fase1
    private int velocidadeCenario = 2; 

    public PowerUp()
    {
        setImage("powerup.png"); // Crie uma imagem para este item (ex: um 'P' piscando)
    }
    
    public void act()
    {
        moverComCenario();
        verificarRemocao();
    }
    
    /**
     * Move o item para a esquerda na velocidade do cenário.
     */
    private void moverComCenario()
    {
        setLocation(getX() - velocidadeCenario, getY());
    }
    
    /**
     * Verifica se o item saiu pela borda esquerda e o remove.
     */
    private void verificarRemocao()
    {
        // 'getWorld()' pode ser nulo se o item acabou de ser coletado
        if (getWorld() != null && getX() <= 0)
        {
            getWorld().removeObject(this);
        }
    }
}