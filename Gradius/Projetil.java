import greenfoot.*;

/**
 * Superclasse para todos os tiros do jogo.
 * Conta como a 2ª Superclasse obrigatória (Requisito I5.2).
 */
public abstract class Projetil extends Actor
{
    private int velocidade;

    public Projetil(int velocidade)
    {
        this.velocidade = velocidade;
    }

    public void act()
    {
        // 1. Verifica se o jogo está pausado (polimorfismo do congelamento!)
        if (getWorld() instanceof Fase1) {
            Fase1 mundo = (Fase1) getWorld();
            if (!mundo.isJogoAtivo()) return;
        }

        // 2. Move
        move(velocidade);
        
        // 3. Verifica se saiu da tela
        verificarRemocao();
    }

    /**
     * Remove o projétil se ele sair da tela.
     */
    protected void verificarRemocao()
    {
        // Precisamos verificar se o mundo não é nulo antes de pegar a largura
        if (getWorld() != null)
        {
            if (getX() >= getWorld().getWidth() - 1 || getX() <= 0)
            {
                getWorld().removeObject(this);
            }
        }
    }
}