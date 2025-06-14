/**
*Codigo realizado por:
*Dana Michelle Constantino Méndez
*Benjamin García López
*Fernando Códova Flores
*Ricargo Gordillo Ibarra
*/
import greenfoot.*; 
import java.util.HashMap; 
import java.util.Map; 
import java.util.ArrayList; 
import java.util.Stack; 

/**
 * Clase ManglarLimpio — representa un mundo en Greenfoot que muestra
 * el escenario limpio con peces y un conteo de basuras recolectadas.
 */
public class ManglarLimpio extends World {

    private GreenfootSound musicaLimpio = new GreenfootSound("sonido3.mp3");
    private Stack<Basura> pilaOrganica;
    private Stack<Basura> pilaInorganica;

    /**
     * Constructor ManglarLimpio
     * @param totalOrganica total de basura orgánica.
     * @param totalInorganica total de basura inorgánica.
     * @param totalPecesSaludables total de peces saludables.
     * @param pecesSaludables ArrayList con objetos Pez saludables a mostrar.
     * @param pilaOrganica pila de basura orgánica recolectada.
     * @param pilaInorganica pila de basura inorgánica recolectada.
     */
    public ManglarLimpio(HashMap<String, Integer> conteoOrganica, HashMap<String, Integer> conteoInorganica,
                     int totalOrganica, int totalInorganica, int totalPecesSaludables, 
                     ArrayList<Pez> pecesSaludables, HashMap<String, Integer> conteoPeces) {

        // Crea un mundo de 1200x600 píxeles, con tamaño de celda 1x1.
        super(1200, 600, 1);

        // Asigna las pilas recibidas.
        this.pilaOrganica = pilaOrganica;
        this.pilaInorganica = pilaInorganica;

        // Carga la imagen de fondo del manglar limpio.
        GreenfootImage fondo = new GreenfootImage("manglarlimpio.png");
        fondo.scale(1200, 600); // Ajusta su tamaño al del mundo.
        setBackground(fondo); // Establece la imagen de fondo.

        // Muestra los conteos de basura orgánica, inorgánica y peces saludables en pantalla.
        mostrarBasuras(conteoOrganica, 795, 270);
        mostrarBasuras(conteoInorganica, 950, 270);
        mostrarPeces(conteoPeces, 1110, 270);

        // Muestra los totales generales de basuras y peces.
        mostrarTotales(totalOrganica, totalInorganica, totalPecesSaludables);

        // Añade los peces saludables al escenario en sus respectivas posiciones.
        for (Pez pez : pecesSaludables) {
            Pez nuevoPez = new Pez(pez.getImagenNombre());
            addObject(nuevoPez, pez.getX(), pez.getY());
        }

        // Reproduce la música en bucle.
        musicaLimpio.playLoop();
    }
    
    /**
     * Método que se ejecuta automáticamente cuando el escenario comienza.
     * Se encarga de iniciar la música de fondo en bucle.
     */
    public void started() {
        musicaLimpio.playLoop();
    }
    
    /**
     * Método que se ejecuta automáticamente cuando se detiene el escenario.
     * Se encarga de pausar la música de fondo.
     */
    public void stopped() {
        musicaLimpio.pause();
    }

    /**
     * Muestra en pantalla las imágenes de los tipos de basura y su cantidad correspondiente.
     *
     * @param conteo HashMap que contiene como clave el nombre de la imagen (ruta del archivo) 
     *               y como valor la cantidad de ese tipo de basura recolectada.
     * @param x Posición horizontal inicial para mostrar las fichas de basura.
     * @param y Posición vertical inicial para mostrar las fichas de basura.
     */
    private void mostrarBasuras(HashMap<String, Integer> conteo, int x, int y) {
        int offset = 0; // Espaciado vertical entre fichas.
        
        for (Map.Entry<String, Integer> entry : conteo.entrySet()) {
            String imagen = entry.getKey();   // Nombre del archivo de imagen.
            int cantidad = entry.getValue();  // Cantidad de basuras de ese tipo.
    
            // Crea una ficha de fondo semitransparente.
            GreenfootImage fondoFicha = new GreenfootImage(120, 65);
            fondoFicha.setColor(new Color(255, 255, 255, 100)); // Color blanco con transparencia.
            fondoFicha.fill();
            Actor ficha = new Actor() {{ setImage(fondoFicha); }};
            addObject(ficha, x + 30, y + offset);
    
            // Crea y agrega la imagen de la basura.
            GreenfootImage img = new GreenfootImage(imagen);
            img.scale(50, 50); // Ajusta tamaño de imagen.
            Actor imgActor = new Actor() {{ setImage(img); }};
            addObject(imgActor, x + 15, y + offset);
    
            // Crea y agrega la cantidad de esa basura.
            GreenfootImage imgTexto = new GreenfootImage("x" + cantidad, 30, Color.BLUE, new Color(0, 0, 0, 0));
            Actor texto = new Actor() {{ setImage(imgTexto); }};
            addObject(texto, x + 50, y + offset + 2);
    
            offset += 80; // Incrementa posición vertical para siguiente ficha.
        }
    }

    /**
     * Muestra en pantalla las imágenes de los peces saludables recolectados y su cantidad.
     *
     * @param conteoPeces HashMap que contiene como clave el nombre de la imagen ( laa ruta del archivo)
     *                    y como valor la cantidad de peces saludables generados.
     * @param x Posición horizontal inicial para mostrar las fichas de peces.
     * @param y Posición vertical inicial para mostrar las fichas de peces.
     */
    private void mostrarPeces(HashMap<String, Integer> conteoPeces, int x, int y) {
        int offset = 0; // Espaciado vertical entre fichas.
    
        for (Map.Entry<String, Integer> entry : conteoPeces.entrySet()) {
            String imagen = entry.getKey();   // Nombre del archivo de imagen del pez.
            int cantidad = entry.getValue();  // Cantidad de peces de ese tipo.
    
            // Crea una ficha de fondo transparente.
            GreenfootImage fondoFicha = new GreenfootImage(120, 65);
            fondoFicha.setColor(new Color(255, 255, 255, 100));
            fondoFicha.fill();
            Actor ficha = new Actor() {{ setImage(fondoFicha); }};
            addObject(ficha, x + 17, y + offset);
    
            // Crea y agrega la imagen del pez.
            GreenfootImage img = new GreenfootImage(imagen);
            img.scale(50, 50);
            Actor pezImg = new Actor() {{ setImage(img); }};
            addObject(pezImg, x, y + offset);
    
            // Crea y agrega la cantidad de peces.
            GreenfootImage imgTexto = new GreenfootImage("x" + cantidad, 30, Color.BLUE, new Color(0, 0, 0, 0));
            Actor texto = new Actor() {{ setImage(imgTexto); }};
            addObject(texto, x + 35, y + offset + 2);
    
            offset += 75; // Incrementa posición vertical para siguiente ficha (apartado).
        }
    }

    /**
     * Muestra en pantalla los totales de basura orgánica, inorgánica y de peces saludables recolectados.
     *
     * @param totalOrganica Cantidad total de basura orgánica recolectada.
     * @param totalInorganica Cantidad total de basura inorgánica recolectada.
     * @param totalPecesSaludables Cantidad total de peces saludables generados.
     */
    private void mostrarTotales(int totalOrganica, int totalInorganica, int totalPecesSaludables) {
        // Muestra total de basura orgánica.
        GreenfootImage imgOrganica = new GreenfootImage(String.valueOf(totalOrganica), 30, Color.BLUE, new Color(0, 0, 0, 0));
        Actor totalOrg = new Actor() {{ setImage(imgOrganica); }};
        addObject(totalOrg, 990, 30); // Posición fija en pantalla.
    
        // Muestra total de basura inorgánica.
        GreenfootImage imgInorganica = new GreenfootImage(String.valueOf(totalInorganica), 30, Color.BLUE, new Color(0, 0, 0, 0));
        Actor totalInorg = new Actor() {{ setImage(imgInorganica); }};
        addObject(totalInorg, 1000, 70);
    
        // Muestra total de peces saludables.
        GreenfootImage imgPeces = new GreenfootImage(String.valueOf(totalPecesSaludables), 30, Color.BLUE, new Color(0, 0, 0, 0));
        Actor totalPeces = new Actor() {{ setImage(imgPeces); }};
        addObject(totalPeces, 1050, 107);
    }
}
