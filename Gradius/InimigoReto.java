import greenfoot.*;

public class InimigoReto extends Inimigo 
{
    public InimigoReto()
    {
        setImage("inimigo1.png");
        getImage().mirrorHorizontally(); 
    }
    
    public void act()
    {
        moverParaEsquerda();
        
        // Chama o método da superclasse para ver se saiu da tela
        super.verificarRemocao(); 
    }
    
    private void moverParaEsquerda()
    {
        setLocation(getX() - 3, getY());
    }
    
    // O método verificarRemocao() foi APAGADO daqui pois já existe na superclasse.
}