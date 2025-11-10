import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot)

/**
 * Inimigo 2 (Requisito J4.5).
 * Move-se da direita para a esquerda em padrão ZigZag.
 * Tem um comportamento diferente do InimigoReto.
 */
public class InimigoZigZag extends Actor
{
    private int velocidadeX = 4; // Velocidade para a esquerda
    private int velocidadeY = 2; // Velocidade para cima/baixo
    private int direcaoY = 1;    // 1 para baixo, -1 para cima
    
    private int contadorMovimento = 0;
    private int limiteMovimento = 40; // Quantos "atos" anda em uma direção

    public InimigoZigZag()
    {
        setImage("inimigo2.png"); // Use uma imagem DIFERENTE do InimigoReto
        getImage().mirrorHorizontally();
    }
    
    public void act()
    {
        moverZigZag();
        verificarRemocao();
    }
    
    /**
     * Move o inimigo para a esquerda e para cima/baixo.
     */
    private void moverZigZag()
    {
        setLocation(getX() - velocidadeX, getY() + (velocidadeY * direcaoY));
        
        contadorMovimento++;
        
        // Se atingiu o limite de movimento, inverte a direção Y
        if (contadorMovimento >= limiteMovimento)
        {
            direcaoY = direcaoY * -1; // Inverte (de 1 para -1, ou -1 para 1)
            contadorMovimento = 0;   // Reseta o contador
        }
    }
    
    /**
     * Verifica se o inimigo saiu pela borda esquerda e o remove.
     * (Este código é IDÊNTICO ao do InimigoReto)
     */
    private void verificarRemocao()
    {
        if (getX() <= 0)
        {
            getWorld().removeObject(this);
        }
    }
}