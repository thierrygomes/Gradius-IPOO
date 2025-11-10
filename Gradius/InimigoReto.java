import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot)

/**
 * Inimigo 1 (Requisito J4.5).
 * Move-se da direita para a esquerda em linha reta.
 */
public class InimigoReto extends Actor
{
    /**
     * Construtor da classe InimigoReto.
     * Define a imagem do inimigo.
     */
    public InimigoReto()
    {
        setImage("inimigo1.png"); // Crie uma imagem para esta nave inimiga
        
        // Vira a imagem para a esquerda (já que ela se move para a esquerda)
        getImage().mirrorHorizontally(); 
    }
    
    /**
     * Método act - chamado a cada "ato" do Greenfoot.
     */
    public void act()
    {
        // 1. Move o inimigo
        moverParaEsquerda();
        
        // 2. Verifica se saiu da tela
        verificarRemocao();
    }
    
    /**
     * Move o inimigo para a esquerda.
     */
    private void moverParaEsquerda()
    {
        int velocidade = 3; // Mais lento que o jogador e o tiro
        setLocation(getX() - velocidade, getY());
    }
    
    /**
     * Verifica se o inimigo saiu pela borda esquerda e o remove.
     */
    private void verificarRemocao()
    {
        if (getX() <= 0)
        {
            getWorld().removeObject(this);
        }
    }
}