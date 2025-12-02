import greenfoot.*;

/**
 * Superclasse Item.
 * Serve apenas para mover os itens e removê-los da tela.
 * Atende ao Requisito de Herança (I5.2) sem complicações.
 */
public class Item extends Actor 
{
    public void act()
    {
        // 1. Verifica se o jogo está rodando (para congelar no Game Over)
        if (getWorld() instanceof Fase1) {
             Fase1 mundo = (Fase1) getWorld();
             if (!mundo.isJogoAtivo()) return;
        }

        // 2. Move para a esquerda
        setLocation(getX() - 2, getY());

        // 3. Remove se sair da tela
        if (getX() <= 0) {
            getWorld().removeObject(this);
        }
    }
}