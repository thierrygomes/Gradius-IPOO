import greenfoot.*;
import java.util.ArrayList;

public class Chefao extends Inimigo
{
    private int vida = 50; // Ele aguenta 50 tiros!
    private int velocidadeY = 2; // Move para cima e para baixo
    private int cooldownTiro = 0;
    
    public Chefao()
    {
        setImage("chefao.png"); // Nome da sua imagem
        getImage().scale(150, 150); // Deixa ele BEM GRANDE
        setRotation(0);
    }
    
    public void act()
    {
        // 1. Verifica se o jogo está ativo (congela no Game Over)
        // Note que verificamos Fase3 aqui (ainda vamos criar)
        if (getWorld() instanceof Fase3) {
            if (!((Fase3)getWorld()).isJogoAtivo()) return;
        }

        moverVerticalmente();
        atirar();
        // O Chefão NÃO usa o verificarRemocao() do pai, pois ele não sai da tela!
    }
    
    private void moverVerticalmente()
    {
        setLocation(getX(), getY() + velocidadeY);
        
        // Se bater no teto ou chão, inverte a direção
        if (getY() <= 50 || getY() >= getWorld().getHeight() - 50)
        {
            velocidadeY = -velocidadeY;
        }
    }
    
    private void atirar()
    {
        cooldownTiro++;
        
        // Atira muito rápido (a cada meio segundo aprox.)
        if (cooldownTiro > 80) 
        {
            // Cria 3 tiros espalhados!
            getWorld().addObject(new TiroInimigo(), getX() - 60, getY());
            getWorld().addObject(new TiroInimigo(), getX() - 60, getY() - 30);
            getWorld().addObject(new TiroInimigo(), getX() - 60, getY() + 30);
            
            cooldownTiro = 0;
        }
    }
    
    /**
     * Método especial para o Chefão levar dano.
     * CORRIGIDO: Evita o erro de NullPointerException.
     */
    public void tomarDano()
    {
        vida--;
        
        // Efeito visual de dano
        getImage().setTransparency(100); 
        Greenfoot.delay(1); 
        getImage().setTransparency(255);
        
        if (vida <= 0)
        {
            // Salva a referência do mundo antes de qualquer coisa
            World mundo = getWorld();
            
            // Se estivermos na Fase 3, chamamos a vitória final
            if (mundo instanceof Fase3) {
                // O método vitoriaFinal() já vai limpar a tela e remover o chefe!
                ((Fase3)mundo).vitoriaFinal();
            }
            else {
                // Se por acaso não for a Fase 3 (segurança), remove normalmente
                if (mundo != null) mundo.removeObject(this);
            }
        }
    }
}