/**
*Codigo realizado por:
*Dana Michelle Constantino Méndez
*Benjamin García López
*Fernando Códova Flores
*Ricargo Gordillo Ibarra
*/

import greenfoot.*;

/**
 * Clase Pez — representa un pez en el escenario.
 * Al crearse, carga una imagen recibida por parámetro y se le asigna al actor.
 */
public class Pez extends Actor {
    
    // Atributo que almacena el nombre del archivo de imagen asignado al pez.
    private String imagenNombre;

    /**
     * Constructor de la clase Pez.
     * Crea un pez con una imagen específica escalada.
     * 
     * @param imagenNombre Nombre del archivo de imagen a asignar al pez.
     */
    public Pez(String imagenNombre) {
        // Guarda el nombre de la imagen en el atributo imagenNombre.
        this.imagenNombre = imagenNombre;

        // Crea nueva imagen con el nombre recibido.
        GreenfootImage imagen = new GreenfootImage(imagenNombre);

        // Ajusta el tamaño de la imagen.
        imagen.scale(110, 110);

        // Asigna la imagen escalada como imagen del actor Pez.
        setImage(imagen);
    }

    /**
     * Método getImagenNombre devuelve el nombre del archivo de la imagen asignado al pez.
     * @return Nombre de la imagen que se asignó al actor pez.
     */
    public String getImagenNombre() {
        return imagenNombre;
    }
}









