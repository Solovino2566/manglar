/**
*Codigo realizado por:
*Dana Michelle Constantino Méndez
*Benjamin García López
*Fernando Códova Flores
*Ricargo Gordillo Ibarra
*/

import greenfoot.*; 

/**
 * Clase Basura — representa un objeto de basura.
 * Cada objeto de basura tiene una imagen y un tipo (orgánica o inorgánica).
 */
public class Basura extends Actor {
    private String tipo;
    public Basura(String imagen, String tipo) {
        GreenfootImage img = new GreenfootImage(imagen);
        img.scale(70, 70);
        setImage(img);
        this.tipo = tipo;
    }
    public String getTipo() {
        return tipo;
    }

    /**
     * Método act — se ejecuta en cada ciclo del escenario.
     * Detecta si se hizo clic sobre el objeto de basura y i es así,
     * llama al método eliminarBasura del mundo (JuegoInicio) para eliminarla.
     */
    public void act() {
        // Verifica si se hizo clic con el mouse sobre este objeto.
        if (Greenfoot.mouseClicked(this)) {
            // Llama al método eliminarBasura del mundo actual, pasándole este objeto.
            ((JuegoInicio) getWorld()).eliminarBasura(this);
        }
    }
}

