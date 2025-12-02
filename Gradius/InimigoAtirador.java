import greenfoot.*;

public class InimigoAtirador extends Inimigo // Herança!
{
    private int cooldownTiro = 0; // Temporizador para não atirar sem parar
    
    public InimigoAtirador()
    {
        // Sugestão: use uma imagem diferente, ex: "inimigo3.png"
        setImage("inimigo3.png"); 
        getImage().mirrorHorizontally();
        getImage().scale(80, 90);
        
    }
    
    public void act()
    {
        move(-2); // Move mais devagar que os outros
        atirar();
        super.verificarRemocao(); // Usa o método da superclasse
    }
    
    private void atirar()
    {
        cooldownTiro++;
        
        // Atira a cada 100 atos (aprox. 1.5 segundos)
        if (cooldownTiro >= 100)
        {
            World mundo = getWorld();
            if (mundo != null) 
            {
                // Cria o tiro na frente do inimigo
                mundo.addObject(new TiroInimigo(), getX() - 30, getY());
                cooldownTiro = 0; // Reseta o timer
            }
        }
    }
}