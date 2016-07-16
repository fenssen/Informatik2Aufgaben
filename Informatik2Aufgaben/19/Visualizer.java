// file "Visualizer.java"

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * Visualisierung eines int-Arrays, sowie Unterstuetzung
 * fuer die Animation von Sortiervorgaengen im Array.
 * 
 * @author    Daniel Ehlke
 * @version   1.0 (viele Ideen aus einer Klasse von Michael Poldner :-)
 */
public class Visualizer extends Frame {
    
    // Zwei Konstanten zur Anpassung der Klasse
    // an verschiedene Rechner-Umgebungen
    
    // Groesse eines Punktes des grafisch dargestellten Arrays
    private static final int SCALE = 4;
    // Verzoegerungs-Faktor der Animation (1 ist am schnellsten, lieber nicht 0)
    private static final int ANIMATION_DELAY = 1;
    
    /**
     * Grafische Hervorhebung eines Array-Eintrags:
     * keine Hervorhebung
     */
    public static final int NO_HIGHLIGHT = 0;
    
    /**
     * Grafische Hervorhebung eines Array-Eintrags:
     * horizontaler (waagerechter) Balken
     */
    public static final int HORIZONTAL_HIGHLIGHT = 1;
    
    /**
     * Grafische Hervorhebung eines Array-Eintrags:
     * vertikaler (senkrechter) Balken
     */
    public static final int VERTICAL_HIGHLIGHT = 2;
    
    /**
     * Grafische Hervorhebung eines Array-Eintrags:
     * ein Kreuz (kombinierter horizontaler und vertikaler Balken)
     */
    public static final int CROSS_HIGHLIGHT = 4;
    
    // Standardfarbe eines Array-Eintrags vor der Sortierung
    private static final Color POINT_DEFAULT_COLOR = Color.RED;
    
    // Titel des Fensters
    private static final String WINDOW_TITLE =
            "Visualisierung von Sortieralgorithmen";
    
    private String legend = ""; // Legende (Bildunterschrift)
    
    private Graphics graphicsBuffer; // Puffer fuer DoubleBuffering
    private Image imageBuffer; // "off-screen"-Bild fuer DoubleBuffering
    
    // Eigenschaften eines Array-Index (siehe Setter-Methoden)
    private int[]     data;
    private Color[]   color;
    private int[]     highlight;
    private String[]  label;
    
    // Pixel-Koordinaten des Ursprungs
    private int originX;
    private int originY;
    
    //**************************************************************************
    // Konstruktoren
    //**************************************************************************
    
    /**
     * Erzeugt ein neues Visualizer-Objekt und versetzt es in den Grundzustand,
     * d. h. die Array-Eintraege haben keinerlei Hervorhebungen oder Bezeichner
     * (Labels) und sind alle in der gleichen Farbe.
     * 
     * @param a   das Array, das visualisiert werden soll
     * 
     * @throws IllegalArgumentException   falls das uebergebene Array leer ist
     *                                    oder nicht existiert
     */
    public Visualizer(int[] a) {
        this(WINDOW_TITLE, SCALE*a.length+100, SCALE*a.length+100);
        if (a == null || a.length == 0)
            throw new IllegalArgumentException("Array enthaelt keine Daten");
        originX = 49;
        originY = getSize().height-50;
        data = new int[a.length];
        color = new Color[data.length];
        highlight = new int[data.length];
        label = new String[data.length];
        reset();
        setData(a);
        setVisible(true); // Fenster anzeigen
    }
    
    // erzeugt ein schliessbares Fenster in der Mitte des Bildschirms
    private Visualizer(String title, int width, int height) {
        super(title);
        setSize(width, height);
        setResizable(false);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((d.width-width)/2, (d.height-height)/2);
        addWindowListener(new WindowAdapter() {
            public void windowClosing (WindowEvent e) {
                System.exit(0);
            }
        });
    }
    
    //**************************************************************************
    // oeffentliche Setter-Methoden
    //**************************************************************************
    
    /**
     * Setzt die gesamten Array-Daten neu.
     * 
     * @param a   das Array, aus dem die neuen Daten kopiert werden
     * 
     * @throws IllegalArgumentException   falls das uebergebene Array nicht die
     *                                    gleiche Laenge wie das Array bei der
     *                                    Instanzierung des Visualizers hat
     */
    public void setData(int[] a) {
        if (a.length != data.length)
            throw new IllegalArgumentException("Neusetzen der Array-Daten nur "
                    +"fuer ein gleichlanges Array erlaubt.");
        for (int i=0; i<data.length; i++) {
            data[i] = a[i];
        }

    }
    
    /**
     * Setzt die Farbe eines Array-Eintrags, d. h. die Farbe
     * des dargestellten Punktes (eigentlich ein Quadrat).
     * 
     * @param index   der Index des Array-Eintrags
     * @param color   die neue Farbe
     */
    public void setColor(int index, Color color) {
        this.color[index] = color;
    }
    
    /**
     * Setzt eine Hervorhebung eines Array-Eintrags
     * (siehe bei den Hervorhebungs-Konstanten).
     * 
     * @param index   der Index des Array-Eintrags
     * @param state   die neue Hervorhebung
     * 
     * @throws IllegalArgumentException   falls eine unbekannte Hervorhebung
     *                                    (state) uebergeben wurde
     * 
     * @see Visualizer#NO_HIGHLIGHT
     */
    public void setHighlight(int index, int state) {
        switch (state) {
            case NO_HIGHLIGHT:
            case HORIZONTAL_HIGHLIGHT:
            case VERTICAL_HIGHLIGHT:
            case CROSS_HIGHLIGHT:
                highlight[index] = state;
                break;
            default:
                throw new IllegalArgumentException("Unbekannte Hervorhebung");
        }
    }
    
    /**
     * Setzt einen Bezeichner (Label) eines Array-Eintrags.
     * Bezeichner (Labels) werden oberhalb der Grafik
     * ab der x-Koordinate des Array-Eintrags angezeigt.
     * 
     * @param index       der Index des Array-Eintrags
     * @param labeltext   der Text des Bezeichners (Labels)
     */
    public void setLabel(int index, String labeltext) {
        label[index] = labeltext;
    }
    
    /**
     * Setzt die Legende (Bildunterschrift).
     * 
     * @param legend   die neue Legende (Bildunterschrift)
     */
    public void setLegend(String legend) {
        this.legend = legend;
    }
    
    /**
     * Setzt das Visualizer-Objekt in den Grundzustand.
     * In diesem Zustand befindet sich das Objekt automatisch
     * direkt nach seiner Instanzierung mit new.
     * Die Array-Daten werden jedoch nicht veraendert.
     */
    public void reset() {
        resetColors();
        resetHighlights();
        resetLabels();
    }
    
    //**************************************************************************
    // private Hilfsmethoden fuer reset()
    //**************************************************************************
    
    // setzt alle Farben der Array-Eintraege auf die Standardfarbe
    private void resetColors() {
        for (int i=0; i<color.length; i++)
            color[i] = POINT_DEFAULT_COLOR;
    }
    
    // setzt alle Hervorhebungen der Array-Eintraege zurueck
    private void resetHighlights() {
        for (int i=0; i<highlight.length; i++)
            highlight[i] = NO_HIGHLIGHT;
    }
    
    // loescht alle Bezeichner (Label) der Array-Eintraege
    private void resetLabels() {
        for (int i=0; i<label.length; i++)
            label[i] = "";
    }
    
    //**************************************************************************
    // private Hilfsmethoden zum Zeichnen
    //**************************************************************************
    
    // zeichnet die Koordinatenachsen des grafischen Koordinatensystems
    private void drawAxes() {
        graphicsBuffer.setColor(Color.BLACK);
        graphicsBuffer.drawLine(originX, originY, originX,
                                originY-SCALE*data.length);
        graphicsBuffer.drawLine(originX, originY, originX+SCALE*data.length,
                                originY);
    }
    
    // zeichnet die Bildunterschrift
    private void drawLegend() {
        graphicsBuffer.setColor(Color.BLACK);
        graphicsBuffer.drawString(legend, originX, originY+27);
    }
    
    // zeichnet alle Punkte des Arrays
    private void drawAllPoints() {
        for (int i=0; i<data.length; i++) {
            drawHighlight(i, data[i], highlight[i]);
        }
        for (int i=0; i<data.length; i++) {
            graphicsBuffer.setColor(color[i]);
            drawPoint(i, data[i]);
            drawLabel(i, label[i]);
        }
    }
    
    // zeichnet einen einzelnen Punkt eines Array-Eintrags
    private void drawPoint(int index, int value) {
        graphicsBuffer.fillRect(originX+index*SCALE+1, originY-value*SCALE,
                                SCALE, SCALE);
    }
    
    // zeichnet eine Hervorhebung eines Array-Eintrags (Balken)
    private void drawHighlight(int index, int value, int state) {
        switch (state) {
            case NO_HIGHLIGHT:
                break;
            case HORIZONTAL_HIGHLIGHT:
                graphicsBuffer.setColor(Color.LIGHT_GRAY);
                graphicsBuffer.fillRect(originX+1, originY-value*SCALE,
                                        data.length*SCALE, SCALE);
                break;
            case VERTICAL_HIGHLIGHT:
                graphicsBuffer.setColor(Color.LIGHT_GRAY);
                graphicsBuffer.fillRect(originX+index*SCALE+1,
                                        originY-data.length*SCALE,
                                        SCALE, data.length*SCALE);
                break;
            case CROSS_HIGHLIGHT:
                graphicsBuffer.setColor(Color.LIGHT_GRAY);
                graphicsBuffer.fillRect(originX+1, originY-value*SCALE,
                                        data.length*SCALE, SCALE);
                graphicsBuffer.fillRect(originX+index*SCALE+1,
                                        originY-data.length*SCALE, SCALE,
                                        data.length*SCALE);
                break;
            default:
                throw new IllegalArgumentException("Unbekannte Hervorhebung");
        }
    }
    
    // zeichnet einen Bezeichner(Label) eines Array-Eintrags oberhalb der Grafik
    private void drawLabel(int index, String labeltext) {
        graphicsBuffer.setColor(Color.BLACK);
        graphicsBuffer.drawString(labeltext, originX+index*SCALE+1,
                                  originY-data.length*SCALE-5);
    }
    
    // loescht den Hintergrund mit der Farbe weiss
    private void clearBackground() {
        graphicsBuffer.setColor(Color.WHITE);
        graphicsBuffer.fillRect(0, 0, getSize().width, getSize().height);
    }
    
    //**************************************************************************
    // Ueberschriebene Methoden der Basisklasse
    //**************************************************************************
    
    /**
     * Der gesamte Inhalt des Fensters wird neugezeichnet.
     * Von aussen kann statt dieser die parameterlose Methode
     * repaint() aufgerufen werden.
     * 
     * @param g   der grafische Kontext des Frames
     */
    public void paint(Graphics g) {
        super.paint(g);
        if (imageBuffer == null) {
            imageBuffer = createImage(getSize().width, getSize().height);
            graphicsBuffer = imageBuffer.getGraphics();
            graphicsBuffer.setFont(new Font("SansSerif", Font.BOLD, 20));
        } else {
            clearBackground();
            drawAxes();
            drawLegend();
            drawAllPoints();
            g.drawImage(imageBuffer, 0, 0, this);
        }
    }
    
    /**
     * Ruft nur die paint-Methode auf.
     * Wird selbst von der repaint-Methode aufgerufen.
     * 
     * @param g   der grafische Kontext des Frames
     */
    public void update(Graphics g) {
        paint(g);
    }
    
    //**************************************************************************
    // oeffentliche Hilfsmethoden fuer die zeitliche Steuerung der Animation
    //**************************************************************************
    
    /**
     * Den Thread einige Zeit anhalten (warten).
     * 
     * @param millis   Wartezeit in Millisekunden (1000 Millisek. = 1 Sek.)
     */
    public static void sleepRealtime(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ie) { }
    }
    
    /**
     * Den Thread einige Zeit anhalten, um die Animation zu verlangsamen.
     * Die Zeitspanne kann ueber die Konstante ANIMATION_DELAY angepasst werden.
     * 
     * @param time   Zeitspanne, die proportional zur Wartezeit ist
     */
    public static void sleep(long time) {
        try {
            Thread.sleep(time*ANIMATION_DELAY);
        } catch (InterruptedException ie) { }
    }
    
} // class Visualizer
