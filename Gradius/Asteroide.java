import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot)

/**
 * Obstáculo (Requisito J4.7).
 * Move-se junto com o cenário e não pode ser destruído por tiros.
 * A NaveJogador morre se colidir com ele.
 */
public class Asteroide extends Actor
{
    // Esta velocidade DEVE ser a mesma da 'rolarFundo()' na Fase1
    private int velocidadeCenario = 2; 

    public Asteroide()
    {
        setImage("asteroide.png"); // Imagem de uma pedra espacial
    }
    
    public void act()
    {
        moverComCenario();
        verificarRemocao();
    }
    
    /**
     * Move o asteroide para a esquerda na velocidade do cenário.
     */
    private void moverComCenario()
    {
        setLocation(getX() - velocidadeCenario, getY());
    }
    
    /**
     * Verifica se o asteroide saiu pela borda esquerda e o remove.
     */
    private void verificarRemocao()
    {
        if (getX() <= 0)
        {
            getWorld().removeObject(this);
        }
    }
}