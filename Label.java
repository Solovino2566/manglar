/**
*Codigo realizado por:
*Dana Michelle Constantino Méndez
*Benjamin García López
*Fernando Códova Flores
*Ricargo Gordillo Ibarra
*/

import greenfoot.*;

/**
 * Clase Label — representa una etiqueta de texto numérico en pantalla.
 * Permite mostrar y actualizar valores enteros como texto dentro del escenario.
 */
public class Label extends Actor {

    // Atributo que almacena la imagen generada con el valor numérico.
    private GreenfootImage imagen;

    // Atributo que almacena el valor entero actual que muestra la etiqueta.
    private int value;

    /**
     * Constructor de la clase Label.
     * @param value Valor inicial que mostrará la etiqueta.
     * @param fontSize Tamaño de la fuente para mostrar el número.
     */
    public Label(int value, int fontSize) {
        // Asigna el valor inicial al atributo value.
        this.value = value;

        // Crea una nueva imagen con el valor recibido, usando el tamaño de fuente especificado.
        // Color del texto: rojo | Fondo: transparente (RGBA 0,0,0,0)
        imagen = new GreenfootImage("" + value, fontSize, Color.RED, new Color(0,0,0,0));

        // Establece la imagen creada como imagen del actor.
        setImage(imagen);
    }

    /**
     * Método setValue - actualiza el valor mostrado en la etiqueta.
     * Crea una nueva imagen con el nuevo valor y la reemplaza.
     * @param value Nuevo valor entero a mostrar la etiqueta.
     */
    public void setValue(int value) {
        // Asigna el nuevo valor al atributo value.
        this.value = value;
        
        // crea nueva imagen con el valor actualizado.
        // Tamaño de fuente fijo en 30 | Color rojo | Fondo transparente.
        imagen = new GreenfootImage("" + value, 30, Color.RED, new Color(0,0,0,0));
        // nueva imagen generada como imagen del actor.
        setImage(imagen);
    }
}

