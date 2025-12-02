import greenfoot.*;

public class Moeda extends Item 
{
    public Moeda()
    {
        setImage("moeda.png");
        
        // --- CÓDIGO DE REDIMENSIONAR ---
        GreenfootImage imagem = getImage();
        // Redimensiona para 40x40 pixels (ajuste esse número se quiser maior/menor)
        imagem.scale(40, 40); 
        setImage(imagem);
        // -------------------------------
    }
}