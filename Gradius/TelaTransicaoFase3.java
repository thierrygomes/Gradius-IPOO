import greenfoot.*;

public class TelaTransicaoFase3 extends World
{
    private int pontuacaoSalva;
    
    public TelaTransicaoFase3(int pontuacao)
    {    
        super(800, 600, 1); 
        this.pontuacaoSalva = pontuacao;
        
        // Fundo preto ou vermelho escuro para dar tensão
        GreenfootImage fundo = getBackground();
        fundo.setColor(Color.BLACK);
        fundo.fill();
        
        // Textos dramáticos
        showText("FASE 2 COMPLETADA!", 400, 200);
        showText("Pontuação Atual: " + pontuacao, 400, 300);
        showText("CUIDADO: O CHEFÃO ESTÁ CHEGANDO...", 400, 400);
        showText("Pressione ENTER para a Batalha Final", 400, 550);
    }
    
    public void act()
    {
        if (Greenfoot.isKeyDown("enter"))
        {
            // Vai para a Fase 3 (Chefão) levando os pontos
            Greenfoot.setWorld(new Fase3(pontuacaoSalva));
        }
    }
}