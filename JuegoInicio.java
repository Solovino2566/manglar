/**
*Codigo realizado por:
*Dana Michelle Constantino Méndez
*Benjamin García López
*Fernando Códova Flores
*Ricargo Gordillo Ibarra
*/
// Importación de librerías necesarias
import java.util.HashMap;            // Para conteo de elementos por clave
import greenfoot.*;                  // Librería para entorno gráfico y de sonido de Greenfoot
import java.util.Stack;              // Para manejo de pilas
import java.util.ArrayList;          // Para manejo de listas dinámicas

/**
 * Clase JuegoInicio
 * 
 * Representa el escenario inicial del juego en el entorno Greenfoot,
 * donde el jugador debe limpiar un manglar contaminado, eliminando basura
 * para generar peces saludables y restaurar el ecosistema.
 * Controla la creación de basura aleatoria, su eliminación, el conteo de estadísticas
 * y la transición hacia el escenario de manglar limpio.
 */
public class JuegoInicio extends World {
    
    // Pilas para almacenar objetos de tipo Basura
    private Stack<Basura> pilaManglar;      // Pila principal de basura en el escenario
    private Stack<Basura> pilaOrganica;     // Pila para basura orgánica eliminada
    private Stack<Basura> pilaInorganica;   // Pila para basura inorgánica eliminada

    // Sonidos del juego
    private GreenfootSound musicaJuego;     // Sonido de fondo ambiental
    private GreenfootSound sonidoPez;       // Sonido al generar un pez saludable

    // Arreglos de nombres de imágenes para las basuras
    private String[] imagenesOrganicas = { "manzana.png", "platano.png", "pizza.png", "huevos.png" };
    private String[] imagenesInorganicas = { "botella.png", "lata.png", "vaso.png", "caja.png" };

    // Variables contadoras para estadísticas en pantalla
    private int totalOrganica = 0;
    private int totalInorganica = 0;
    private int totalPecesSaludables = 0;
    private int contadorEliminados = 0;    // Controla cada cuántas basuras generar un pez

    // Etiquetas para mostrar valores en pantalla
    private Label labelOrganica;
    private Label labelInorganica;
    private Label labelPecesSaludables;

    // HashMaps para llevar conteo detallado por tipo de imagen
    private HashMap<String, Integer> conteoOrganica = new HashMap<>();
    private HashMap<String, Integer> conteoInorganica = new HashMap<>();
    private HashMap<String, Integer> conteoPeces = new HashMap<>();

    // Listas de nombres de peces y objetos Pez generados
    private ArrayList<String> nombresPeces = new ArrayList<>();
    private ArrayList<Pez> pecesSaludables = new ArrayList<>();

    /**
     * Constructor de la clase JuegoInicio.
     * 
     * Inicializa el escenario con un manglar sucio,etiquetas de conteo,uso de las esttucturas de datos,
     * sonidos, imágenes de fondo y genera una cantidad inicial de basura aleatoria.
     * 
     * @param musicaJuego Sonido de fondo que se reproducirá en bucle durante la partida.
     */
    public JuegoInicio(GreenfootSound musicaJuego) {
        super(1200, 600, 1);  // Crea un mundo de 1200x600 píxeles con celda de tamaño 1x1

        // Inicialización de pilas
        pilaManglar = new Stack<>();
        pilaOrganica = new Stack<>();
        pilaInorganica = new Stack<>();

        // Asignación de sonido de fondo
        this.musicaJuego = musicaJuego;

        // Configuración de imagen de fondo escalada
        GreenfootImage imagen = new GreenfootImage("manglarsucio.png");
        imagen.scale(1200, 600);
        setBackground(imagen);

        // Creación y colocación de etiquetas en pantalla
        labelOrganica = new Label(totalOrganica, 30);
        labelInorganica = new Label(totalInorganica, 30);
        labelPecesSaludables = new Label(totalPecesSaludables, 30);
        addObject(labelOrganica, 1055, 372);
        addObject(labelInorganica, 1055, 440);
        addObject(labelPecesSaludables, 1070, 510);

        // Carga de nombres de imágenes de peces disponibles
        nombresPeces.add("sardina.png");
        nombresPeces.add("bacalao.png");
        nombresPeces.add("mojarra.png");
        nombresPeces.add("atun.png");
        nombresPeces.add("salmon.png");

        // Generación inicial de basura aleatoria
        generarBasuraAleatoria(20);

        // Carga de efecto de sonido para aparición de peces
        sonidoPez = new GreenfootSound("sonidopez.mp3");
    }

    /**
     * Genera basura aleatoria en el escenario y la almacena en la pila del manglar.
     * 
     * @param cantidad Cantidad de objetos Basura a generar.
     */
    public void generarBasuraAleatoria(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            String tipo;
            String imagen;
            int tipoAleatorio = Greenfoot.getRandomNumber(2); // 0: orgánica, 1: inorgánica

            if (tipoAleatorio == 0) {
                tipo = "organica";
                int indice = Greenfoot.getRandomNumber(imagenesOrganicas.length);
                imagen = imagenesOrganicas[indice];
                conteoOrganica.put(imagen, conteoOrganica.getOrDefault(imagen, 0) + 1);
                totalOrganica++;
            } else {
                tipo = "inorganica";
                int indice = Greenfoot.getRandomNumber(imagenesInorganicas.length);
                imagen = imagenesInorganicas[indice];
                conteoInorganica.put(imagen, conteoInorganica.getOrDefault(imagen, 0) + 1);
                totalInorganica++;
            }

            // Creación y colocación de objeto basura
            Basura basura = new Basura(imagen, tipo);
            int x = Greenfoot.getRandomNumber(700);
            int y = Greenfoot.getRandomNumber(getHeight());
            addObject(basura, x, y);

            // Añadir basura a pila principal
            pilaManglar.push(basura);
        }

        // Actualización de etiquetas de conteo
        labelOrganica.setValue(totalOrganica);
        labelInorganica.setValue(totalInorganica);
    }

    /**
     * Elimina una basura seleccionada del escenario y la añade en su pila correspondiente.
     * 
     * Utiliza una pila temporal para conservar el orden de pilaManglar al retirar el objeto.
     * Genera un pez saludable cada 5 basuras eliminadas.
     */
    public void eliminarBasura(Basura basura) {
        Stack<Basura> pilaTemporal = new Stack<>();

        while (!pilaManglar.isEmpty()) {
            Basura actual = pilaManglar.pop();

            if (actual == basura) {
                // Clasificación en pila orgánica o inorgánica
                if (basura.getTipo().equals("organica")) {
                    pilaOrganica.push(basura);
                    totalOrganica--;
                    labelOrganica.setValue(totalOrganica);
                } else {
                    pilaInorganica.push(basura);
                    totalInorganica--;
                    labelInorganica.setValue(totalInorganica);
                }
                removeObject(basura);
                break;
            } else {
                pilaTemporal.push(actual);
            }
        }

        // Restauración de pila principal
        while (!pilaTemporal.isEmpty()) {
            pilaManglar.push(pilaTemporal.pop());
        }

        // Incremento de contador de eliminados
        contadorEliminados++;

        // Cada 5 basuras eliminadas, generar un pez
        if (contadorEliminados == 5) {
            generarPezSaludable();
            contadorEliminados = 0;
        }

        // Cambio de escenario si ya no hay basura
        if (pilaManglar.isEmpty()) {
            musicaJuego.stop();
            int totalOrg = pilaOrganica.size();
            int totalInorg = pilaInorganica.size();
            int totalPeces = pecesSaludables.size();

            ManglarLimpio limpio = new ManglarLimpio(
                conteoOrganica, conteoInorganica, totalOrg, totalInorg, totalPeces, pecesSaludables, conteoPeces
            );
            Greenfoot.setWorld(limpio);
        }
    }

    /**
     * Genera un pez saludable aleatorio en una posición libre del escenario.
     * Actualiza los conteos y reproduce sonido de aparición.
     */
    private void generarPezSaludable() {
        int indice = Greenfoot.getRandomNumber(nombresPeces.size());
        String nombrePez = nombresPeces.get(indice);

        Pez pez = new Pez(nombrePez);

        int x, y;
        boolean espacioLibre;

        // Buscar posición libre para el pez
        do {
            x = Greenfoot.getRandomNumber(700);
            y = Greenfoot.getRandomNumber(getHeight());
            espacioLibre = getObjectsAt(x, y, Basura.class).isEmpty() && getObjectsAt(x, y, Pez.class).isEmpty();
        } while (!espacioLibre);

        addObject(pez, x, y);

        totalPecesSaludables++;
        labelPecesSaludables.setValue(totalPecesSaludables);
        pecesSaludables.add(pez);

        // Actualizar conteo por imagen
        conteoPeces.put(nombrePez, conteoPeces.getOrDefault(nombrePez, 0) + 1);

        // Reproducir sonido de aparición
        sonidoPez.play();
    }

    /**
     * Activa la reproducción en bucle de la música de fondo.
     */
    public void started() {
        musicaJuego.playLoop();
    }

    /**
     * Pausa la reproducción de música de fondo.
     */
    public void stopped() {
        musicaJuego.pause();
    }
}
