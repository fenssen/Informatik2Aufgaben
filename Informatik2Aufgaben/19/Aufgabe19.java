// file "Aufgabe19.java"
import java.util.*;
import java.io.*;
import java.awt.Color;

/**
 * Rahmen zur Aufgabe 19.
 * Verwendet ein (privates) Visualizer-Objekt, um die
 * Animation der Sortieralgorithmen darzustellen.
 * 
 * @author    Daniel Ehlke, edited by Aaron Scherzinger
 * 
 * @see       Visualizer
 */
public class Aufgabe19 {

    // statisches Attribut fuer Visualizer-Objekt (fuer die Animation noetig)
    private static Visualizer v = null;
    //statische Variablen fuer Zaehlung
    private static int ccomp = 0,
                       cswap = 0;

    /**
     * Fuellt ein Eingabearray mit Zufallszahlen.
     * @param a das Eingabearray
     */
    private static void fillArray(int[] a) {
        java.util.Random r = new java.util.Random();
        for (int i=0; i<a.length; i++) {
            a[i] = r.nextInt(a.length) + 1; 

            // dafuer sorgen, dass jeder Wert von 1 bis a.length
            // nur genau einmal im Array vorkommt
            for (int k=0; k<i; k++) {
                if (a[i] == a[k]) {
                    i--;
                    break;
                }
            }
        }
    }

    /**
     * Zaehlt einen Countdown herunter und zeigt diesen auch an.
     * Erwartet, dass das Visualizer-Objekt v gesetzt ist.
     * @param seconds Laenge des Countdowns.
     */
    private static void countdown(int seconds) {
        if (v == null)
            throw new IllegalStateException("Visualizer-Objekt v "
                +"existiert nicht!");
        for (int i=seconds; i>0; i--) {
            v.setLegend("Start in "+i+" Sekunden ...");
            v.repaint();
            v.sleepRealtime(1000);
        }
    }


    /**
     * Das Hauptprogramm fuer die Aufgabe 19
     */
    public static void main(String[] args) {
        int[] A = new int[100];// Erstelle Array a 

        //einlesen der Dateien, falls ein Parameter uebergeben wurde
        if(args.length == 0)
            fillArray(A);
        else if(args[0].equals("absteigend"))
            A = readfile("C:\\Users\\Marcel\\Desktop\\Eingabedateien\\absteigend.txt"); //2mal backslash, da ein backslash eine escape sequenz markiert
        else if(args[0].equals("aufsteigend"))
            A = readfile("C:\\Users\\Marcel\\Desktop\\Eingabedateien\\aufsteigend.txt");   
        else if(args[0].equals("zufall"))
            A = readfile("C:\\Users\\Marcel\\Desktop\\Eingabedateien\\zufall.txt");    
        else
        {
            System.out.println("Der Parameter ist nicht erlaubt! Programm wird beendet.");
            System.exit(0);
        } 

        int[] a = A.clone();
        v = new Visualizer(a); // Visualizer-Objekt erzeugen und v zuweisen,die Animation wird damit moeglich

        countdown(3);          // es spannend machen ;-)
        visualBubbleSort(a);   // visuell sortieren
        v.sleepRealtime(1000); // 1 Sekunde warten

        // Zuruecksetzen fuer den naechsten Sortier-Algorithmus
        a = A.clone();
        v.reset();    // grafischen Visualizer zuruecksetzen
        v.setData(a); // Array-Daten des Visualizers setzen
        v.repaint();  // Neuzeichnen des Fensters
        visualInsertionSort(a);
        v.sleepRealtime(1000); // 1 Sekunde warten

        // Zuruecksetzen fuer den naechsten Sortier-Algorithmus
        a = A.clone();
        v.reset();    // grafischen Visualizer zuruecksetzen
        v.setData(a); // Array-Daten des Visualizers setzen
        v.repaint();  // Neuzeichnen des Fensters
        visualSelectionSort(a);
        v.sleepRealtime(1000); // 1 Sekunde warten

        // Zuruecksetzen fuer den naechsten Sortier-Algorithmus
        a = A.clone();
        v.reset();    // grafischen Visualizer zuruecksetzen
        v.setData(a); // Array-Daten des Visualizers setzen
        v.repaint();  // Neutzeichnen des Fensters
        visualMergeSort(a);
        v.sleepRealtime(1000); // 1 Sekunde warten
         

        // Zuruecksetzen fuer den naechsten Sortier-Algorithmus
        a = A.clone();
        v.reset();    // grafischen Visualizer zuruecksetzen
        v.setData(a); // Array-Daten des Visualizers setzen
        v.repaint();  // Neutzeichnen des Fensters
        visualQuickSort(a,0,a.length-1);
        v.sleepRealtime(1000); // 1 Sekunde warten

        // Zuruecksetzen fuer den naechsten Sortier-Algorithmus
        a = A.clone();
        v.reset();    // grafischen Visualizer zuruecksetzen
        v.setData(a); // Array-Daten des Visualizers setzen
        v.repaint();  // Neutzeichnen des Fensters
        modifiedVisualQuickSort(a,0,a.length-1, 13);
        v.sleepRealtime(1000); // 1 Sekunde warten
        
         // Zuruecksetzen fuer den naechsten Sortier-Algorithmus
        v.reset();    // grafischen Visualizer zuruecksetzen

        
        int ergneu = 0;
        int ergbest = 999999999;
        int aktuellesx = 0;
        int[] e = new int[100];
        v.setLabel(0, "10000 mal wird Sortierung fuer ein X ");
        v.setLegend(" angewendet und Schritte werden summiert" );
        v.repaint();  // Neuzeichnen des Fensters 
        v.sleep(6000);
        for(int anzahlinsertionstart = 1; anzahlinsertionstart <= 40 ; anzahlinsertionstart++)
        {
            ccomp = 0; //zaehler wieder auf Null setzen
            cswap = 0;
            for(int i = 1; i <=10000; i++)
            {
                fillArray(e);//Array erneut fuellen
                v.reset();    // grafischen Visualizer zuruecksetzen
                v.setData(e); // Array-Daten des Visualizers setzen
                v.setLabel(0, "Ab X wird Insertionsort angewendet");
                v.repaint();  // Neuzeichnen des Fensters 

                testerQuickInsertion(e,0,e.length-1,anzahlinsertionstart);
               
                ergneu = ccomp + cswap ;
                v.setLegend("Tester Aufwand:" + ergneu+ " X= " + anzahlinsertionstart );
                v.repaint();
            }
            v.sleep(500);
            if( ergneu < ergbest) //gucken ob das neue ergebnis besser ist
            {
                ergbest = ergneu;
                aktuellesx = anzahlinsertionstart;
            }
            v.setLegend("Am besten ist zurzeit:" + ergbest+ " X= " + aktuellesx );
            v.repaint();
            v.sleep(1000);
        }
    }

     /**
     * liest Textfile ein
     *
     * @param file   Pfad der Textdatei
     * @return ein Array, in der die Daten der Textdatei stecken
     * @throws FileNotFoundException falls File nicht gefunden wurde
     * @throws NoSuchElementException falls kein Int gefunden wird
     */
    private static int[] readfile(String file)
    {
        int[] a = new int[100];
        try (Scanner meinScanner = new Scanner(new File(file)))
        {
            int i = 0;
            while(meinScanner.hasNextInt())
            {
                a[i] = meinScanner.nextInt();
                i++;
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e.getMessage()+ "File nicht gefunden");
            e.printStackTrace();
        }
        catch(NoSuchElementException e) //InputMismatchException ist eine subklasse
        {
            System.out.println(e.getMessage() + "kein Int gefunden");
            e.printStackTrace();
        }
        return a;

    }

    /**
     * Sortiert ein int-Array mit dem Bubble-Sort Algorithmus
     * (Code siehe Aufgabe 20) und visualisiert dies mit Hilfe
     * eines (statischen) Visualizer-Objekts v, welches existieren muss.
     *
     * @param a   das zu sortierende Array
     * @throws IllegalStateException falls das Visualizer-Objekt nicht existiert
     */
    public static void visualBubbleSort(int[] a) {
        if (v == null)
            throw new IllegalStateException("Visualizer-Objekt v existiert nicht!");
        v.setLegend("Bubble Sort");
        int hilf = 0;
        boolean vertauscht = false;
        int run = 0;
        do {
            run += 1;
            vertauscht = false;
            for (int i=0; i<a.length-run; i++) {
                v.setLabel(i, "i");
                v.setHighlight(i, v.VERTICAL_HIGHLIGHT);
                if (a[i] > a[i+1]) {
                    hilf = a[i];
                    a[i] = a[i+1];
                    a[i+1] = hilf;
                    vertauscht = true;
                }
                v.setColor(i, Color.RED);
                v.setColor(i+1,Color.ORANGE);
                v.setData(a);
                v.repaint(); // alles neuzeichnen
                v.sleep(5);  // und warten
                v.setLabel(i, "");
                v.setHighlight(i, v.NO_HIGHLIGHT);
                if (i == a.length-run-1)
                    v.setColor(i+1, Color.BLACK);
            }
        } while (vertauscht);
        for (int cnt = a.length-run; cnt >= 0; cnt--)
            v.setColor(cnt, Color.BLACK);
        v.setData(a);
        v.setLegend("Bubble Sort (terminiert)");
        v.repaint();
    }

    /**
     * Sortiert ein int-Array mit dem Insertion-Sort Algorithmus und
     * visualisiert dies mithilfe eines (statischen) Visualizer-Objekts v,
     * welches existieren muss.
     *
     * @param a das zu sortierende Array
     * @throws IllegalStateException falls das Visualizer-Objekt nicht existiert
     */
    public static void visualInsertionSort(int[] a)
    {
        if (v == null)
            throw new IllegalStateException("Visualizer-Objekt v "+"existiert nicht!");
        v.setLegend("InsertionSort");

        for (int i = 1; i < a.length; i++)
        {
            v.setLabel(i, "i");
            v.setHighlight(i, v.VERTICAL_HIGHLIGHT);
            int tmp = a[i];
            int j = i - 1;
            while ((j >= 0) && (tmp < a[j]))
            {
                v.setColor(j,Color.BLUE);
                v.setColor(j+1,Color.RED);

                v.setLabel(j, "j");
                v.setHighlight(j, v.VERTICAL_HIGHLIGHT);
                a[j + 1] = a[j];

                v.setData(a);
                v.repaint(); // alles neuzeichnen
                v.sleep(10);  // und warten

                v.setLabel(j, "j");//j strich
                v.setHighlight(j, v.VERTICAL_HIGHLIGHT);//j strich

                v.setColor(j+1,Color.BLUE);
                v.setColor(j,Color.RED);

                v.setData(a);
                v.repaint(); // alles neuzeichnen
                v.sleep(10);  // und warten

                v.setLabel(j, "");//j strich loeschen
                v.setHighlight(j, v.NO_HIGHLIGHT);//j strich loeschen

                j = j - 1;
            }
            a[j + 1] = tmp;
            v.setColor(j+1,Color.BLUE);
            v.setData(a);
            v.repaint(); // alles neuzeichnen
            v.sleep(50);  // und warten
            v.setLabel(i, "");
            v.setHighlight(i, v.NO_HIGHLIGHT);
        }
        v.setLegend("Insertionsort (terminiert)");
        v.repaint();
    }

    /**
     * Sortiert ein int-Array mit dem Selection-Sort Algorithmus und
     * visualisiert dies mithilfe eines (statischen) Visualizer-Objekts v,
     * welches existieren muss.
     *
     * @param a das zu sortierende Array
     * @throws IllegalStateException falls das Visualizer-Objekt nicht existiert
     */
    public static void visualSelectionSort(int[] a) 
    {
        if (v == null)
            throw new IllegalStateException("Visualizer-Objekt v "+"existiert nicht!");
        v.setLegend("SelectionSort");
        for (int i = 0; i < a.length - 1; i++)
        {
            int minindex = i;

            v.setLabel(i, "i");
            v.setHighlight(i, v.VERTICAL_HIGHLIGHT);

            for (int j = i+1; j < a.length; j++)
            {

                v.setColor(j,Color.GREEN);//zu vergleichende Objekte
                v.setColor(minindex,Color.BLUE);//zu vergleichende Objekte
                v.setLabel(j, "j");//j Strich
                v.setHighlight(j, v.VERTICAL_HIGHLIGHT);//j Strich

                v.setData(a);
                v.repaint(); // alles neuzeichnen
                v.sleep(10);  // und warten

                v.setColor(j,Color.RED);
                v.setLabel(j, "");//j strich entfernen
                v.setHighlight(j, v.NO_HIGHLIGHT);//j strich entfernen

                if (a[j] < a[minindex])
                {
                    v.setHighlight(minindex, v.NO_HIGHLIGHT);//altes entfernen
                    v.setColor(minindex,Color.RED);//Altes min rot setzen
                    minindex = j;

                    v.setColor(minindex,Color.BLUE);//Minimum gesetzt
                    v.setHighlight(minindex, v.HORIZONTAL_HIGHLIGHT);//Min
                    v.setHighlight(i, v.VERTICAL_HIGHLIGHT);

                    v.setLabel(20, "neues Minimum gefunden!");

                    v.setData(a);
                    v.repaint(); // alles neuzeichnen
                    v.sleep(100);  // und warten

                    v.setLabel(20, "");
                }
            }
            swap(a, i, minindex);
            v.setColor(minindex,Color.RED);//Element von i wird nach Tausch auf Rot gesetzt
            v.setColor(i,Color.BLUE);//bearbeitete Zahl
            v.setData(a);
            v.repaint(); // alles neuzeichnen
            v.sleep(50);  // und warten
            v.setHighlight(minindex, v.NO_HIGHLIGHT);
            v.setHighlight(i, v.NO_HIGHLIGHT);
            v.setLabel(i, "");

        }
        v.setColor(a.length-1,Color.BLUE);//letzteZahl blau machen
        v.setLegend("Selectionsort(terminiert)");
        v.repaint();
    }

     /**
     *tauscht zwei Arrayelemente im Array
     *
     * @param a das zu sortierende Array
     * @param i Position vom i-ten Element
     * @param j Position vom j-ten Element
     */
    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    /**
     * Sortiert ein int-Array mit dem Merge-Sort Algorithmus und visualisiert
     * dies mithilfe eines (statischen) Visualizer-Objekts v, welches existieren
     * muss.
     *
     * @param a das zu sortierende Array
     * @throws IllegalStateException falls das Visualizer-Objekt nicht existiert
     */
    public static void visualMergeSort(int[] a) {
        if (v == null)
            throw new IllegalStateException("Visualizer object v does"
                + "not exist!");

        v.setLegend("MergeSort");

        /*
         * Hierbei werden zunaechst 2er-Gruppen, dann 4er-Gruppen, 8er-Gruppen
         * usw. geordnet. Jede Gruppe hat zwei geordnete Teile, die mittels
         * MergeSort zusammengefuegt werden.
         */
        for (int k = 1; k <= (int)(Math.log(a.length)/Math.log(2)) + 1; k++)
        {
            // Bestimmung einer Gruppen-/Intervalllaenge
            int intervall_length = (int)Math.pow(2, k);

            for (int l = 0; l <= a.length/intervall_length; l++)
            {
                int bound_left  = l*intervall_length,
                bound_right = (l+1) * intervall_length,
                i           = bound_left,
                j           = bound_left + intervall_length/2;
                if (bound_right >= a.length)
                    bound_right = a.length;

                // Ordnen der Zahlen in dem Intervell.
                for (; i < j && j < bound_right; i++)
                {
                    v.setLabel(i, "i");
                    v.setColor(i, Color.BLUE);
                    v.setHighlight(i, v.VERTICAL_HIGHLIGHT);
                    v.setLabel(j, "j");
                    v.setColor(j, Color.BLUE);
                    v.setHighlight(j, v.VERTICAL_HIGHLIGHT);
                    v.repaint();
                    v.sleep(80);

                    if (a[i] > a[j])
                    {
                        // a[j] verschieben
                        int tmp = a[j];
                        for (int cnt = j; cnt > i; cnt--)
                        {
                            a[cnt] = a[cnt-1];
                        }
                        a[i] = tmp;
                        v.setData(a);
                        v.setLabel(j, "");
                        v.setColor(j, Color.RED);
                        v.setHighlight(j, v.NO_HIGHLIGHT);
                        j++;
                    }

                    v.setLabel(i, "");
                    if (intervall_length >= a.length)
                        v.setColor(i, Color.BLACK);
                    else
                        v.setColor(i, Color.RED);
                    v.setHighlight(i, v.NO_HIGHLIGHT);
                }

                // Redekorieren der noch markierten Eintraege
                for (; i < a.length; i++)
                {
                    v.setLabel(i, "");
                    v.setHighlight(i, v.NO_HIGHLIGHT);
                    if (intervall_length >= a.length)
                        v.setColor(i, Color.BLACK);
                    else
                        v.setColor(i, Color.RED);
                }
            }
        }

        v.setLegend("MergeSort (terminiert)");
        v.repaint();
    }


    /**
     * Sortiert ein int-Array mit dem Quicksort-Sort Algorithmus und
     * visualisiert dies mithilfe eines (statischen) Visualizer-Objekts v,
     * welches existieren muss.
     *
     * @param a das zu sortierende Array
     * @throws IllegalStateException falls das Visualizer-Objekt nicht existiert
     */
    public static void  visualQuickSort(int[] a, int L, int R)
    {
        if (v == null)
            throw new IllegalStateException("Visualizer object v does"
                + "not exist!");

        v.setLegend("QuickSort");

        int m = a[(L + R) / 2]; //erste Methode der Vorlesung, Element in   fixer Position
        int i = L;
        int j = R;

        while (i <= j) {
            for(int z=0; z<a.length;z++)//m suchen und markieren
            {
                if(a[z]==m)
                {
                    v.setLabel(z, "m");
                    v.setColor(z, Color.CYAN);
                    v.setHighlight(z, v.VERTICAL_HIGHLIGHT);
                } 
            }
            //solange das i-te Element kleiner m ist i erhoehen
            while (a[i] < m)
            {
                i = i + 1;
                v.setLabel(i, "i");
                v.setHighlight(i, v.VERTICAL_HIGHLIGHT);
                v.setLabel(j, "j");
                v.setHighlight(j, v.VERTICAL_HIGHLIGHT);
                v.setData(a);
                v.repaint(); // alles neuzeichnen
                v.sleep(10);  // und warten
                v.setLabel(j, "");//loeschen
                v.setHighlight(j, v.NO_HIGHLIGHT);
                v.setLabel(i, "");//loeschen
                v.setHighlight(i, v.NO_HIGHLIGHT);
            }
            //solange das j-te Element kleiner m ist j erniedrigen
            while (m < a[j])
            {
                j = j - 1;
                v.setLabel(i, "i");v.setHighlight(i, v.VERTICAL_HIGHLIGHT);v.setLabel(j, "j");v.setHighlight(j, v.VERTICAL_HIGHLIGHT);
                v.setData(a);
                v.repaint(); // alles neuzeichnen
                v.sleep(10);  // und warten
                v.setLabel(j, "");v.setHighlight(j, v.NO_HIGHLIGHT);v.setLabel(i, "");v.setHighlight(i, v.NO_HIGHLIGHT);
            }
            //i-tes und j-tes Element tauschen
            if (i <= j)
            {
                try
                {
                    v.setColor(j,Color.GREEN);v.setColor(i,Color.BLUE);
                    v.setLabel(i, "i");v.setHighlight(i, v.VERTICAL_HIGHLIGHT);v.setLabel(j, "j");v.setHighlight(j, v.VERTICAL_HIGHLIGHT);

                    v.setData(a);
                    v.repaint(); // alles neuzeichnen
                    v.sleep(10);  // und warten*/

                    swap(a, i, j);

                    v.setColor(i,Color.GREEN);
                    v.setColor(j,Color.BLUE);
                    v.setData(a);
                    v.repaint(); // alles neuzeichnen
                    v.sleep(10);  // und warten
                    v.setLabel(j, "");v.setHighlight(j, v.NO_HIGHLIGHT);v.setLabel(i, "");v.setHighlight(i, v.NO_HIGHLIGHT);

                    v.setColor(i,Color.RED);
                    v.setColor(j,Color.RED);

                    i = i + 1;
                    j = j - 1;

                    v.setLabel(i, "i");v.setHighlight(i, v.VERTICAL_HIGHLIGHT);v.setLabel(j, "j");v.setHighlight(j, v.VERTICAL_HIGHLIGHT);
                    v.setData(a);
                    v.repaint(); // alles neuzeichnen
                    v.sleep(50);  // und warten*/
                    v.setLabel(j, "");v.setHighlight(j, v.NO_HIGHLIGHT);v.setLabel(i, "");v.setHighlight(i, v.NO_HIGHLIGHT);
                }
                catch (ArrayIndexOutOfBoundsException e) {}
            }
            v.reset();
        }
        for(int z=0; z<a.length;z++)//markierungen loeschen
        {
            if(a[z]==m)
            {
                v.setLabel(z, "");
                v.setHighlight(z, v.NO_HIGHLIGHT); 
                v.setColor(z, Color.RED);
            } 
        }

        if (L < j) visualQuickSort(a, L, j);
        if (i < R)  visualQuickSort(a, i, R);

        v.setLegend("QuickSort (terminiert)");
    }

    
    /**
     * Aktualisiert Daten, zeichnet neu und wartet
     *
     * @param array das zu sortierende Array
     */
    private static void refresh(int[] array)
    {
        v.setData(array);
        v.repaint(); // alles neuzeichnen
        v.sleep(10);  // und warten*/
    }

    // Aufgabe21b-------------Vorgehensweise------------------------
    /*
     * Wir haben zuerst die Methode modifiedVisualQuickSort kopiert und die neue
     * Methode testerQuickInsertion erstellt, die dasselbe Sortierverfahren
     * anwendet, wie in modifiedVisualQuickSort, allerdings die grafische
     * Darstellung vernachlaessigt. Wir haben alle Visualisierungselemente
     * enfernt, ausser der Datenaktualisierung in der Insertionsortschleife.
     * Dann haben wir zwei Counter erstellt. ccomp ist der Counter fuer die
     * Vergleiche und cswap ist der Counter fuer die Vertauschungen. Nun haben
     * wir die Counter jeweils bei jeder Vergleichs- und Vertauschungsoperation
     * erhoeht, sodass man nach jedem Terminieren vom modifizierten QuickSort
     * die benoetigte Anzahl an Operationen abfragen kann.
     * Um die Effizienz fuer verschiedene Werte m zu pruefen, haben wir fuer
     * alle 1 <= m <= 40 getestet, wieviele Operationen benoetigt wurden, um
     * 10000 zufaellig generierte Arrays zu sortieren (40 deswegen, da die
     * Effizienz mit groesserem m sinkt, wie man feststellt).
     * Das Ergebnis(nach mehrmaligem Ausfuehren des Tests) ist, dass fuer m=9 die beste Effizienz erreicht wird, d.h.
     * dass die geringste Anzahl an Operationen benoetigt wird.
     */
   
    /**
     * Sortiert ein int-Array mit dem Quicksort-Insertionsort(ab einer
     * bestimmten Intervallgroesse wird Insertionsort ausgefuehrt) Algorithmus
     * und visualisiert dies mithilfe eines (statischen) Visualizer-Objekts v,
     * welches existieren muss.
     *
     * @param a das zu sortierende Array
     * @param L linkes Arrayende
     * @param R rechtes Arrayende
     * @param m  gibt an, ab wann insertionsort angewendet wird
     * @throws IllegalStateException falls das Visualizer-Objekt nicht existiert
     */
    public static void  modifiedVisualQuickSort(int[] a, int L, int R, int m) 
    {
        if (v == null)
            throw new IllegalStateException("Visualizer object v does"
                + "not exist!");

        v.setLegend("QuickSort modifiziert");

        int middle= a[(L + R) / 2]; //erste Methode, Element in fixer Position
        int i = L;
        int j = R;

        while (i <= j) {
            for(int z=0; z<a.length;z++)
            {
                if(a[z]==middle)
                {
                    v.setLabel(z, "m");
                    v.setColor(z, Color.CYAN);
                    v.setHighlight(z, v.VERTICAL_HIGHLIGHT);
                } 
            }
            ccomp++;//oben

            ccomp++;
            while (a[i] < middle)
            {
                ccomp++;
                i = i + 1;
                v.setLabel(i, "i");
                v.setHighlight(i, v.VERTICAL_HIGHLIGHT);
                v.setLabel(j, "j");
                v.setHighlight(j, v.VERTICAL_HIGHLIGHT);
                v.setData(a);
                v.repaint(); // alles neuzeichnen
                v.sleep(0);  // und warten
                v.setLabel(j, "");//loeschen
                v.setHighlight(j, v.NO_HIGHLIGHT);
                v.setLabel(i, "");//loeschen
                v.setHighlight(i, v.NO_HIGHLIGHT);
            }

            ccomp++;
            while (middle< a[j])
            {
                ccomp++;
                j = j - 1;
                v.setLabel(i, "i");v.setHighlight(i, v.VERTICAL_HIGHLIGHT);v.setLabel(j, "j");v.setHighlight(j, v.VERTICAL_HIGHLIGHT);
                v.setData(a);
                v.repaint(); // alles neuzeichnen
                v.sleep(10);  // und warten
                v.setLabel(j, "");v.setHighlight(j, v.NO_HIGHLIGHT);v.setLabel(i, "");v.setHighlight(i, v.NO_HIGHLIGHT);
            }
            ccomp++;
            if (i <= j)
            {
                try
                {
                    v.setColor(j,Color.GREEN);v.setColor(i,Color.BLUE);
                    v.setLabel(i, "i");v.setHighlight(i, v.VERTICAL_HIGHLIGHT);v.setLabel(j, "j");v.setHighlight(j, v.VERTICAL_HIGHLIGHT);
                    v.setData(a);
                    v.repaint(); // alles neuzeichnen
                    v.sleep(0);  // und warten*/

                    cswap++;

                    swap(a, i, j);

                    v.setColor(i,Color.GREEN);
                    v.setColor(j,Color.BLUE);
                    v.setData(a);
                    v.repaint(); // alles neuzeichnen
                    v.sleep(10);  // und warten
                    v.setLabel(j, "");v.setHighlight(j, v.NO_HIGHLIGHT);v.setLabel(i, "");v.setHighlight(i, v.NO_HIGHLIGHT);

                    v.setColor(i,Color.RED);
                    v.setColor(j,Color.RED);

                    i = i + 1;
                    j = j - 1;

                    v.setLabel(i, "i");v.setHighlight(i, v.VERTICAL_HIGHLIGHT);v.setLabel(j, "j");v.setHighlight(j, v.VERTICAL_HIGHLIGHT);
                    v.setData(a);
                    v.repaint(); // alles neuzeichnen
                    v.sleep(10);  // und warten*/
                    v.setLabel(j, "");v.setHighlight(j, v.NO_HIGHLIGHT);v.setLabel(i, "");v.setHighlight(i, v.NO_HIGHLIGHT);

                }
                catch (ArrayIndexOutOfBoundsException e) {}
            }
            v.reset();
        }
        for(int z=0; z<a.length;z++)
        {
            if(a[z]==middle)
            {
                v.setLabel(z, "");
                v.setHighlight(z, v.NO_HIGHLIGHT); 
                v.setColor(z, Color.RED);
            } 
        }

        if(R-L <= m)
        {
            for(int q=1+L; q < R+1; q++)
            {
                v.setColor(q, Color.BLUE);
                int tmp = a[q];
                int p = q - 1;
                ccomp += 2;//es wird zweimal verglichen

                while ((p >= L) && (tmp < a[p]))
                {
                    ccomp += 2;//es wird zweimal verglichen

                    refresh(a);
                    cswap++;
                    a[p + 1] = a[p];
                    p = p - 1;
                }
                cswap++;
                a[p + 1] = tmp;
                refresh(a);
            }
        }
        else
        {

            if (L < j) modifiedVisualQuickSort(a, L, j, m);
            if (i < R)  modifiedVisualQuickSort(a, i, R, m);
        }

        v.setLegend("QuickSort modifiziert (terminiert)");
    }
    
    /**
     * Sortiert ein int-Array mit dem Quicksort-Insertionsort(ab einer bestimmten Intervallgroesse wird Insertionsort ausgefuehrt) Algorithmus und
     * visualisiert dies mithilfe eines (statischen) Visualizer-Objekts v, 
     * welches existieren muss. Wobei Animationen weggelassen wurden.
     *
     * @param a das zu sortierende Array
     * @param L linkes Arrayende
     * @param R rechtes Arrayende
     * @param m  gibt an, ab wann insertionsort angewendet wird
     * @throws IllegalStateException falls das Visualizer-Objekt nicht existiert
     */
     public static void  testerQuickInsertion(int[] a, int L, int R, int x) //x gibt an, ab wann insertionsort angewendet wird
    {
        if (v == null)
            throw new IllegalStateException("Visualizer object v does"
                + "not exist!");

                
        int m = a[(L + R) / 2]; //erste Methode, Element in fixer Position
        int i = L;
        int j = R;
        
        ccomp++;
        while (i <= j) {
            ccomp++;//oben
            
            ccomp++;
            while (a[i] < m)
            {
                ccomp++;
                i = i + 1;
            }
            
            
            ccomp++;
            while (m < a[j])
            {
                ccomp++;
                j = j - 1;
            }
            ccomp++;
            if (i <= j)
            {
                cswap++;
                swap(a, i, j);
                i = i + 1;
                j = j - 1;
            }
        }
        
        v.setData(a);
        v.repaint();
                
        // InsertionSort beginnt
        if(R-L <= x)
        {
            for(int q=1+L; q < R+1; q++)
            {
                int tmp = a[q];
                int p = q - 1;
                ccomp += 2;//es wird zweimal verglichen
                
                while ((p >= L) && (tmp < a[p]))
                {
                    ccomp += 2;//es wird zweimal verglichen
                    cswap++;
                    a[p + 1] = a[p];
                    p = p - 1;
                    v.setData(a);
                    v.repaint();
                }
                cswap++;
                a[p + 1] = tmp;
            }
        }
        else
        {
            if (L < j) testerQuickInsertion(a, L, j,x);
            if (i < R)  testerQuickInsertion(a, i, R, x);
        }
    }
} // class Aufgabe19
