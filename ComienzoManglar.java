/**
*Codigo realizado por:
*Dana Michelle Constantino Méndez
*Benjamin García López
*Fernando Códova Flores
*Ricargo Gordillo Ibarra
*/
import greenfoot.*; 
public class ComienzoManglar extends World {
    GreenfootSound musicaMenu = new GreenfootSound("sonido1.mp3"); //agrega el sonido de inicio
    
    public ComienzoManglar() { //agrega la imagen del escenario de la presentacion
     super(1200, 600, 1); 
    GreenfootImage imagen = new GreenfootImage("presentacion.png");
    imagen.scale(1200, 600); //cambia la escala (tamaño) de la imagen.
    setBackground(imagen);
    
    BotonInicio BotonInicio = new BotonInicio(musicaMenu); 
    addObject(BotonInicio, 600,460);
     
    }
    
    public void started(){ // reproduce sonido al momento de compilar 
        musicaMenu.playLoop();
    }
    public void stopped() { //detiene el sonido al pausar el juego
    musicaMenu.stop();
    }
}
 