import greenfoot.*;

public class TelaTransicao extends World
{
    private int pontuacaoSalva;
    
    // Construtor recebe a pontuação da fase anterior
    public TelaTransicao(int pontuacao)
    {    
        super(800, 600, 1); 
        this.pontuacaoSalva = pontuacao;
        
        // Fundo preto simples para destacar
        GreenfootImage fundo = getBackground();
        fundo.setColor(Color.BLACK);
        fundo.fill();
        
        // Textos
        showText("FASE 1 COMPLETADA!", 400, 200);
        showText("Pontuação Atual: " + pontuacao, 400, 300);
        showText("Pressione ENTER para iniciar a Fase 2", 400, 500);
    }
    
    public void act()
    {
        // Espera o jogador estar pronto
        if (Greenfoot.isKeyDown("enter"))
        {
            // Vai para a Fase 2 levando os pontos
            Greenfoot.setWorld(new Fase2(pontuacaoSalva));
        }
    }
}