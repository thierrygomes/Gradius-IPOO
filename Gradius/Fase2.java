import greenfoot.*;

public class Fase2 extends World
{
    private int pontuacao;
    private GreenfootSound musicaFundo;
    
    // Fundo rolando
    private GreenfootImage fundoA;
    private GreenfootImage fundoB;
    private int xFundoA;
    private int xFundoB;
    
    // Meta para ir para o Chefão (Fase 3)
    // Ex: Se terminar a fase 1 com 1000, precisa chegar a 2500
    private static final int PONTUACAO_VITORIA = 150; 
    private boolean jogoAtivo = true;
    private int tempoTextoInicial = 180; // 3 segundos (60 * 3)

    /**
     * Construtor da Fase 2.
     * Recebe a pontuação da Fase 1 como parâmetro!
     */
    public Fase2(int pontuacaoInicial)
    {    
        super(800, 600, 1, false); 
        
        // 1. Recebe a pontuação da fase anterior
        this.pontuacao = pontuacaoInicial;
        
        // 2. Configura o fundo (use a imagem nova)
        fundoA = new GreenfootImage("fundo_fase2.png"); 
        fundoB = new GreenfootImage("fundo_fase2.png");
        // Ajusta o tamanho se a imagem for pequena
        fundoA.scale(800, 600);
        fundoB.scale(800, 600);
        
        xFundoA = 0;
        xFundoB = getWidth();

        // 3. Adiciona a Nave
        addObject(new NaveJogador(), 100, getHeight() / 2); 
        
        // 4. Mostra os pontos iniciais
        atualizarPontuacao();
        
        // 5. Música
        musicaFundo = new GreenfootSound("musica_fase1.mp3"); // Ou fase2 se tiver
        musicaFundo.setVolume(50);
        musicaFundo.playLoop();
        
        // Mostra aviso de Fase 2
        showText("FASE 2 - Prepare-se!", 400, 300);
    }
    
    public void act()
    {
        if (jogoAtivo) 
        {
            rolarFundo();
            spawnarAtoresDificeis(); // Método novo mais difícil
            verificarVitoria();
            // --- NOVO: Lógica para apagar o texto ---
            if (tempoTextoInicial > 0) {
                tempoTextoInicial--; // Diminui o tempo
                
                // Quando chegar no zero, apaga o texto!
                if (tempoTextoInicial == 0) {
                    showText("", 400, 300); // Escreve vazio para limpar
                }
            }
            // ----------------------------------------
            // Limpa o texto de aviso depois de um tempo (opcional)
            // (Para simplificar, deixamos o texto sumir se redesenharmos, 
            // mas o showText do Greenfoot fica. Se quiser tirar: showText("", 400, 300))
        }
    }
    
    private void rolarFundo()
    {
        // Fundo mais rápido na fase 2! (Era 2, agora 4)
        int velocidade = 4; 
        
        xFundoA -= velocidade;
        xFundoB -= velocidade;
        
        if (xFundoA <= -getWidth()) xFundoA = xFundoB + getWidth();
        if (xFundoB <= -getWidth()) xFundoB = xFundoA + getWidth();
        
        getBackground().drawImage(fundoA, xFundoA, 0);
        getBackground().drawImage(fundoB, xFundoB, 0);
    }
    
    /**
     * Spawn mais agressivo para a Fase 2
     */
    private void spawnarAtoresDificeis()
    {
        // Inimigo Atirador (Mais comum que na fase 1)
        if (Greenfoot.getRandomNumber(1000) < 10) { 
            addObject(new InimigoAtirador(), getWidth(), Greenfoot.getRandomNumber(getHeight()));
        }
        
        // Inimigo ZigZag (Muito comum)
        if (Greenfoot.getRandomNumber(100) < 2) { 
            addObject(new InimigoZigZag(), getWidth(), Greenfoot.getRandomNumber(getHeight()));
        }
        
        // Lixo Espacial (Novo obstáculo - Mais comum)
        if (Greenfoot.getRandomNumber(100) < 1) { 
            addObject(new LixoEspacial(), getWidth(), Greenfoot.getRandomNumber(getHeight()));
        }
        
        // Cristais e Moedas (para ajudar a pontuar)
        if (Greenfoot.getRandomNumber(1000) < 15) {
             addObject(new Moeda(), getWidth(), Greenfoot.getRandomNumber(getHeight()));
        }
    }
    
    public void adicionarPontos(int valor)
    {
        pontuacao += valor;
        atualizarPontuacao();
    }

    private void atualizarPontuacao()
    {
        showText("Pontos: " + pontuacao, 80, 25);
    }

    public void pararMusica()
    {
        musicaFundo.stop();
    }
    
    public boolean isJogoAtivo()
    {
        return jogoAtivo;
    }

    public void encerrarJogo()
    {
        jogoAtivo = false;
        pararMusica();
        // Lógica de Game Over igual Fase 1
        showText("GAME OVER - FASE 2", 400, 300);
        addObject(new GameOver(), getWidth()/2, getHeight()/2);
    }
    
    private void verificarVitoria()
    {
        if (pontuacao >= PONTUACAO_VITORIA)
        {
            pararMusica();
            
            // Limpa qualquer texto da tela
            showText("", getWidth() / 2, getHeight() / 2);
            
            // --- MUDANÇA: Chama a transição antes do Chefão ---
            Greenfoot.setWorld(new TelaTransicaoFase3(pontuacao));
            // --------------------------------------------------
        }
    }
}