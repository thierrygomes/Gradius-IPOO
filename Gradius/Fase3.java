import greenfoot.*;
import java.util.ArrayList; // Importante para o Requisito I5.1

public class Fase3 extends World
{
    private int pontuacao;
    private boolean jogoAtivo = true;
    private GreenfootSound musicaBoss;

    public Fase3(int pontuacaoAnterior)
    {    
        super(800, 600, 1); 
        this.pontuacao = pontuacaoAnterior;

        // Fundo parado (pode ser o mesmo da fase 2 ou um novo)
        setBackground("fundo_fase2.png"); 

        // Adiciona Nave e Chefão
        addObject(new NaveJogador(), 100, 300);
        addObject(new Chefao(), 700, 300); // Chefão na direita

        showText("Fase Final - DERROTE O CHEFE!", 400, 100);
        showText("Pontos: " + pontuacao, 80, 25);

        musicaBoss = new GreenfootSound("musica_boss.mp3"); // Música tensa!
        musicaBoss.playLoop();
    }

    public void act()
    {
        // Se o jogo acabou, não faz nada
    }

    public boolean isJogoAtivo()
    {
        return jogoAtivo;
    }

    public void adicionarPontos(int valor)
    {
        // Se quiser dar pontos na fase 3 (ex: se o boss spawnar minions)
        pontuacao += valor;
        showText("Pontos: " + pontuacao, 80, 25);
    }

    public void encerrarJogo()
    {
        jogoAtivo = false;
        musicaBoss.stop();
        addObject(new GameOver(), 400, 300);
        showText("GAME OVER - O Chefão venceu...", 400, 500);
    }

    /**
     * Chamado pelo Chefão quando ele morre
     */
    /**
     * Chamado pelo Chefão quando ele morre.
     * ATUALIZADO: Usa ArrayList para gerar o relatório final (Requisito I5.1).
     */
    public void vitoriaFinal()
    {
        jogoAtivo = false;
        musicaBoss.stop();

        // 1. Limpa a tela (remove todos os atores, inclusive o Chefão)
        removeObjects(getObjects(null)); 

        // 1. Carrega a imagem na memória primeiro
        GreenfootImage fundoVitoria = new GreenfootImage("fundo_tela_inicial.png");
        
        // 2. Redimensiona para o tamanho exato do mundo (800x600)
        fundoVitoria.scale(getWidth(), getHeight());
        
        // 3. Define a imagem já ajustada como fundo
        setBackground(fundoVitoria);
        

        // --- USO DE ARRAYLIST (Requisito I5.1) ---
        // Cria uma lista para guardar as mensagens do relatório final
        ArrayList<String> relatorio = new ArrayList<String>();

        // Adiciona dados na coleção
        relatorio.add("MISSÃO CUMPRIDA!");
        relatorio.add("O Chefão foi derrotado.");
        relatorio.add("Galáxia Salva.");
        relatorio.add("----------------");
        relatorio.add("PONTUAÇÃO FINAL: " + pontuacao);

        // Usa um loop para exibir a coleção na tela
        int y = 200; // Altura inicial do texto

        for (String mensagem : relatorio)
        {
            showText(mensagem, 400, y);
            y += 50; // Desce 50 pixels para a próxima linha
        }
        // ------------------------------------------

        // Toca som de vitória se tiver
        // Greenfoot.playSound("fanfarra.mp3"); 
    }
}