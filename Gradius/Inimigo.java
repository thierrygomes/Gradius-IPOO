import greenfoot.*;

/**
 * Superclasse para todos os inimigos.
 * Atende ao Requisito I5.2 (Herança).
 */
public abstract class Inimigo extends Actor
{
    /**
     * Todo inimigo precisa verificar se saiu da tela.
     * Como isso é igual para todos, fica na superclasse (Reutilização de código).
     */
    protected void verificarRemocao()
    {
        // Se encostar na borda esquerda, some
        if (getX() <= 0)
        {
            getWorld().removeObject(this);
        }
    }
}