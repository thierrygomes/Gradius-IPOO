import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot)

public class NaveJogador extends Actor
{
    // Variáveis para guardar as imagens
    private GreenfootImage imagemNormal;
    private GreenfootImage imagemEscudo;

    // Variável para controlar o tempo do escudo (0 = sem escudo)
    private int tempoEscudo = 0;

    public NaveJogador()
    {
        // 1. Carrega e prepara a imagem NORMAL
        
        imagemNormal = new GreenfootImage("nave.png");
        imagemNormal.scale(70, 70); 

        // 2. Carrega e prepara a imagem com ESCUDO
        // (Certifique-se de que o arquivo nave_escudo.png existe)
        imagemEscudo = new GreenfootImage("campo.png"); 
        imagemEscudo.scale(70, 70); 

        // Começa com a imagem normal
        setImage(imagemNormal);
    }

    public void act()
    {
        // 1. Verifica em qual fase estamos e se o jogo está ativo
        if (getWorld() instanceof Fase1) {
            if (!((Fase1)getWorld()).isJogoAtivo()) return;
        }
        else if (getWorld() instanceof Fase2) {
            if (!((Fase2)getWorld()).isJogoAtivo()) return;
        }

        // 2. Se a nave morreu, para a execução deste act
        if (verificarColisoes()) {
            return; 
        }

        // 3. Executa as ações normais
        verificarMovimento();
        verificarDisparo();
        verificarColetaItem();
        atualizarEscudo();
    }

    /**
     * Diminui o tempo do escudo e volta a imagem normal se acabar.
     */
    private void atualizarEscudo()
    {
        if (tempoEscudo > 0)
        {
            tempoEscudo--; // Diminui 1 a cada ato

            // Se o tempo chegou a zero, remove o escudo
            if (tempoEscudo == 0)
            {
                setImage(imagemNormal);
            }
        }
    }

    private void verificarMovimento()
    {
        int velocidade = 5; 
        if (getWorld() != null) {
            int mundoLargura = getWorld().getWidth();
            int mundoAltura = getWorld().getHeight();
            int x = getX();
            int y = getY();

            if (Greenfoot.isKeyDown("up") && y > 0) setLocation(x, y - velocidade);
            if (Greenfoot.isKeyDown("down") && y < mundoAltura - 1) setLocation(x, y + velocidade);
            if (Greenfoot.isKeyDown("left") && x > 0) setLocation(x - velocidade, y);
            if (Greenfoot.isKeyDown("right") && x < mundoLargura - 1) setLocation(x + velocidade, y);
        }
    }

    private void verificarDisparo()
    {
        if ("space".equals(Greenfoot.getKey()))
        {
            Greenfoot.playSound("som_tiro.mp3"); 
            Tiro tiro = new Tiro();
            getWorld().addObject(tiro, getX() + 50, getY());
        }
    }

    private boolean verificarColisoes() 
    {
        // Verifica colisão com Inimigos (superclasse) ou Asteroides
        Actor inimigo = getOneIntersectingObject(Inimigo.class);
        Actor asteroide = getOneIntersectingObject(Asteroide.class); 

        if (inimigo != null || asteroide != null)
        {
            // Se tiver escudo, fica invencível!
            if (tempoEscudo > 0)
            {
                if (inimigo != null) getWorld().removeObject(inimigo);
                return false; // Não morre
            }

            // Se não tiver escudo, Game Over:
            Greenfoot.playSound("som_explosao_jogador.mp3");

            // Verifica a fase para chamar o Game Over correto
            if (getWorld() instanceof Fase1) {
                ((Fase1) getWorld()).encerrarJogo();
            } 
            else if (getWorld() instanceof Fase2) {
                ((Fase2) getWorld()).encerrarJogo();
            }

            getWorld().removeObject(this);
            return true; // Morreu
        }
        return false; 
    }

    private void verificarColetaItem()
    {
        Actor item = getOneIntersectingObject(Item.class);

        if (item != null)
        {
            int pontosParaAdicionar = 0;

            // Verifica QUAL tipo de item é
            if (item instanceof PowerUp) {
                pontosParaAdicionar = 50;
                // Ativa o escudo (15 segundos)
                tempoEscudo = 900; 
                setImage(imagemEscudo); 
            }
            else if (item instanceof Moeda) {
                pontosParaAdicionar = 10;
            }
            else if (item instanceof Cristal) {
                pontosParaAdicionar = 100;
            }

            // Adiciona os pontos na fase correta
            if (getWorld() instanceof Fase1) {
                ((Fase1) getWorld()).adicionarPontos(pontosParaAdicionar);
            } 
            else if (getWorld() instanceof Fase2) {
                ((Fase2) getWorld()).adicionarPontos(pontosParaAdicionar);
            }

            Greenfoot.playSound("som_coleta.mp3");
            getWorld().removeObject(item);
        }
    }
}