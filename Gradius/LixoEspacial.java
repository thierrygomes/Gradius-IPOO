import greenfoot.*;

/**
 * Novo Obstáculo (Requisito J5.6).
 * Herda de Asteroide, então a Nave morre se bater nele automaticamente.
 */
public class LixoEspacial extends Asteroide
{
    public LixoEspacial()
    {
        setImage("satelite.png"); 
        
        getImage().scale(40, 40);
    }
    
    public void act()
    {
        super.act(); // Faz tudo que o Asteroide faz (move para esquerda e some)
        
        // NOVIDADE: Ele vai girando enquanto voa!
        turn(3); 
    }
}