import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot)

/**
 * Representa o tiro disparado pela NaveJogador.
 */
public class Tiro extends Actor
{
    /**
     * Construtor da classe Tiro.
     * Define a imagem do tiro.
     */
    public Tiro()
    {
        setImage("tiro.png"); // Crie uma imagem de tiro (ex: um pequeno laser)
    }

    /**
     * Método act - chamado a cada "ato" do Greenfoot.
     */
    public void act()
    {
        // 1. Move o tiro
        moverParaDireita();

        // 2. Verifica se acertou um inimigo
        verificarColisaoInimigo();

        // 3. Verifica se o tiro saiu da tela
        verificarRemocao();
    }

    /**
     * Move o tiro para a direita.
     */
    private void moverParaDireita()
    {
        int velocidade = 10;
        setLocation(getX() + velocidade, getY());
    }

    /**
     * Verifica se este tiro atingiu um Inimigo.
     * ATUALIZADO para acertar inimigos e dropar PowerUps.
     */
    private void verificarColisaoInimigo()
    {
        Actor inimigo = getOneIntersectingObject(InimigoReto.class);

        if (inimigo == null) {
            inimigo = getOneIntersectingObject(InimigoZigZag.class);
        }

        if (inimigo != null)
        {
            // Pega a posição do inimigo ANTES de removê-lo
            int x = inimigo.getX();
            int y = inimigo.getY();

            // Toca o som de explosão
            Greenfoot.playSound("som_explosao_inimigo.wav");

            // Pede ao mundo para adicionar pontos
            Fase1 mundo = (Fase1) getWorld();
            mundo.adicionarPontos(10); 

            // Remove o inimigo e o tiro
            getWorld().removeObject(inimigo);
            getWorld().removeObject(this); // Remove o tiro

            // --- NOVIDADE AQUI (Requisito J4.6) ---
            // 20% de chance de dropar um PowerUp
            if (Greenfoot.getRandomNumber(100) < 20) 
            {
                PowerUp powerUp = new PowerUp();
                // Adiciona o PowerUp onde o inimigo estava
                mundo.addObject(powerUp, x, y); 
            }
            // ----------------------------------------
        }
    }

    /**
     * Verifica se o tiro saiu pela borda direita e o remove.
     */
    private void verificarRemocao()
    {
        // 'getWorld()' pode ser nulo se o tiro acabou de ser removido (após colisão)
        // Por isso, verificamos se 'getWorld() != null' antes de usá-lo.
        if (getWorld() != null && getX() >= getWorld().getWidth() - 1)
        {
            getWorld().removeObject(this);
        }
    }
}