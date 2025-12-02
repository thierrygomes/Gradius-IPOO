import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot)

public class Tiro extends Projetil
{
    public Tiro()
    {
        super(10); // Velocidade 10
        setImage("tiro.png");
    }

    public void act()
    {
        super.act(); // Projetil move e verifica se saiu da tela
        
        // Só verifica colisão se o tiro ainda existir
        if (getWorld() != null) {
            verificarColisaoInimigo();
        }
    }

    private void verificarColisaoInimigo()
    {
        Actor inimigo = getOneIntersectingObject(Inimigo.class);

        if (inimigo != null)
        {
            // Salva o mundo antes de remover qualquer coisa
            World mundo = getWorld(); 
            int x = inimigo.getX();
            int y = inimigo.getY();

            Greenfoot.playSound("som_explosao_inimigo.mp3");
            // --- LÓGICA DO CHEFÃO ---
            if (inimigo instanceof Chefao)
            {
                // Se for o Chefão, ele só toma dano, não morre direto
                ((Chefao) inimigo).tomarDano();
                
                // O tiro some
                mundo.removeObject(this);
                return; // Sai do método para não executar o resto (drop de itens, etc)
            }
            // ------------------------

            // --- CORREÇÃO AQUI: Verifica a Fase para dar pontos ---
            if (mundo instanceof Fase1) {
                ((Fase1) mundo).adicionarPontos(10);
            } 
            else if (mundo instanceof Fase2) {
                // Na fase 2, inimigos podem valer mais pontos se quiser
                ((Fase2) mundo).adicionarPontos(20); 
            }
            // -----------------------------------------------------

            mundo.removeObject(inimigo);
            mundo.removeObject(this); 

            // Lógica de Drop de Itens (igual fizemos antes)
            int sorteio = Greenfoot.getRandomNumber(100); 

            if (sorteio < 15) { // 15% Moeda
                mundo.addObject(new Moeda(), x, y); 
            }
            else if (sorteio >= 15 && sorteio < 20) { // 5% PowerUp
                mundo.addObject(new PowerUp(), x, y); 
            }
            else if (sorteio >= 20 && sorteio < 22) { // 2% Cristal
                mundo.addObject(new Cristal(), x, y);
            }
        }
    }
}