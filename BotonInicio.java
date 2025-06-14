/**
*Codigo realizado por:
*Dana Michelle Constantino Méndez
*Benjamin García López
*Fernando Códova Flores
*Ricargo Gordillo Ibarra
*/

import greenfoot.*;

/**
 * Clase BotonInicio — representa el botón de inicio de la presentación del juego.
 * Al hacer clic en este botón, se detiene la música del menú, inicia la música del juego de manglar sucio
 * y cambia al mundo JuegoInicio(manglarsucio).
 */
public class BotonInicio extends Actor {  

    private GreenfootSound musicaMenu;

    private int tamañoBase = 130;
    private int tamañoActual = 130;
    private int direccion = 1;

    private int contador = 0;        // contador para controlar la velocidad
    private int velocidadCambio = 5; // cada cuántos acts se cambia tamaño

    public BotonInicio(GreenfootSound musicaMenu) {
        this.musicaMenu = musicaMenu;
        setImage("play.png");
        getImage().scale(tamañoBase, tamañoBase);
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            musicaMenu.stop();
            GreenfootSound musicaJuego = new GreenfootSound("sonido2.mp3");
            musicaJuego.playLoop();
            Greenfoot.setWorld(new JuegoInicio(musicaJuego));
        }
        animarBoton();
    }

    private void animarBoton() {
        // Solo cambia de tamaño cuando el contador alcanza el valor de velocidadCambio
        contador++;
        if (contador >= velocidadCambio) {
            tamañoActual += direccion;

            if (tamañoActual >= tamañoBase + 5 || tamañoActual <= tamañoBase - 5) {
                direccion *= -1;
            }

            GreenfootImage imagen = new GreenfootImage("play.png");
            imagen.scale(tamañoActual, tamañoActual);
            setImage(imagen);

            // Reinicia el contador
            contador = 0;
        }
    }
}


