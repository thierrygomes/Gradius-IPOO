import greenfoot.*;

public class Cristal extends Item 
{
    public Cristal()
    {
        setImage("cristal.png");
        
        // --- CÓDIGO DE REDIMENSIONAR ---
        GreenfootImage imagem = getImage();
        // Redimensiona para 30x30 pixels (Cristal pequeno e valioso)
        imagem.scale(30, 30); 
        setImage(imagem);
        // -------------------------------
    }
}