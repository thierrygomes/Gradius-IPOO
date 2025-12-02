import greenfoot.*;

public class InimigoZigZag extends Inimigo 
{
    private int velocidadeX = 4;
    private int velocidadeY = 2;
    private int direcaoY = 1;
    private int contadorMovimento = 0;
    private int limiteMovimento = 40;

    public InimigoZigZag()
    {
        setImage("inimigo2.png");
        getImage().mirrorHorizontally();
    }
    
    public void act()
    {
        moverZigZag();
        super.verificarRemocao(); // Usa a lógica da superclasse
    }
    
    private void moverZigZag()
    {
        setLocation(getX() - velocidadeX, getY() + (velocidadeY * direcaoY));
        contadorMovimento++;
        if (contadorMovimento >= limiteMovimento)
        {
            direcaoY = direcaoY * -1;
            contadorMovimento = 0;
        }
    }
}