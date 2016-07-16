import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
    //Zaehlvariablen
    private static int cswap = 0;
    private static int ccomp = 0;
    private static int best = 999999;
    private static int bestprogramnumber = 0;
    private static int programnumber = 0;
    // statisches Attribut fuer Visualizer-Objekt (fuer die Animation noetig)
    private static Visualizer v = null;

    //Gibt an, ob Zufallsgenerator benutzt, oder die Werte aus der Datei eingelesen werden sollen
    private static boolean useRNG;

    //Pfad der Datei, die eingelesen werden soll
    private static String fileName;

    //Zeit, die zur Visulisierung gewartet werden soll
    private static int sleepTime;

    /**
     * Fuellt ein Eingabearray mit Zufallszahlen oder liest Datei mit Zahlen ein.
     * @param a das Eingabearray
     */
    private static void fillArray(int[] a) {
        if(useRNG) {
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

        //Falls die Zahlen aus der Datei gelesen werden sollen
        else {
            if(! fileName.endsWith(".txt")) {
                fileName += ".txt";
            }
            try {
                Scanner sc = new Scanner(new File(fileName));
                int i=0;
                //Fuellt das Array mit den Zahlen
                while(sc.hasNextInt()) {
                    a[i] = sc.nextInt();
                    i++;
                }
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage() + "Dateiname/pfad falsch geschrieben?");
                System.exit(1);
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
        //Wenn ein Argument uebergeben wurde, wird dieses als Dateipfad interpretiert
        if(args.length > 0) {
            fileName = args[0];
        }
        else {
            useRNG = true;
        }

        sleepTime = 1;

        //useRNG = false;
        //fileName = "src/Aufgabe19/sol/res/zufall";

        /**
         * Test fuer ein passendes m fuer den HybridSort
         * Bei meinen Durchlaeufen kam fast immer ein Wert um 5 heraus
         *
         * sleepTime = 0;
         * int bestM = findGoodM(5000);
         * System.out.println("Bestes m: "+bestM);
         */

        int[] c = new int[100];// Erstelle Array a 
        fillArray(c);          // Fuelle das Array mit Zufallszahlen
        
        int[] a = c.clone();

        v = new Visualizer(a); // Visualizer-Objekt erzeugen und v zuweisen
        // die Animation wird damit moeglich

        countdown(2);
        visualHeapSort(a); 
        isbest(cswap,ccomp);
        // Zuruecksetzen fuer den naechsten Sortier-Algorithmus
        cswap = 0;
        ccomp = 0;
        v.reset();    // grafischen Visualizer zuruecksetzen
        a = c.clone();// Array mit neuen Zahlen fuellen
        v.setData(a); // Array-Daten des Visualizers setzen
        v.repaint();  // Neuzeichnen des Fensters
        countdown(3);          // es spannend machen ;-)
        visualBubbleSort(a);   // visuell sortieren
        isbest(cswap,ccomp);
        v.sleepRealtime(5000); // 5 Sekunden warten

        // Zuruecksetzen fuer den naechsten Sortier-Algorithmus
        cswap = 0;
        ccomp = 0;
        v.reset();    // grafischen Visualizer zuruecksetzen
        a = c.clone();// Array mit neuen Zahlen fuellen
        v.setData(a); // Array-Daten des Visualizers setzen
        v.repaint();  // Neuzeichnen des Fensters

        countdown(3);
        visualInsertionSort(a, 0, a.length-1);
        isbest(cswap,ccomp);
        v.sleepRealtime(5000);

        // Zuruecksetzen fuer den naechsten Sortier-Algorithmus
        cswap = 0;
        ccomp = 0;
        v.reset();    // grafischen Visualizer zuruecksetzen
        a = c.clone(); // Array mit neuen Zahlen fuellen
        v.setData(a); // Array-Daten des Visualizers setzen
        v.repaint();  // Neuzeichnen des Fensters

        countdown(3);
        visualSelectionSort(a);
        isbest(cswap,ccomp);
        v.sleepRealtime(5000);

        // Zuruecksetzen fuer den naechsten Sortier-Algorithmus
        cswap = 0;
        ccomp = 0;
        v.reset();    // grafischen Visualizer zuruecksetzen
        a = c.clone(); // Array mit neuen Zahlen fuellen
        v.setData(a); // Array-Daten des Visualizers setzen
        v.repaint();  // Neuzeichnen des Fensters

        countdown(3);
        visualMergeSort(a, 0, a.length);
        isbest(cswap,ccomp);
        v.sleepRealtime(5000);

        // Zuruecksetzen fuer den naechsten Sortier-Algorithmus
        cswap = 0;
        ccomp = 0;
        v.reset();    // grafischen Visualizer zuruecksetzen
        a = c.clone(); // Array mit neuen Zahlen fuellen
        v.setData(a); // Array-Daten des Visualizers setzen
        v.repaint();  // Neuzeichnen des Fensters

        countdown(3);
        visualQuickSort(a, 0, a.length-1);
        isbest(cswap,ccomp);
        v.sleepRealtime(5000);

        // Zuruecksetzen fuer den naechsten Sortier-Algorithmus
        cswap = 0;
        ccomp = 0;
        v.reset();    // grafischen Visualizer zuruecksetzen
        a = c.clone(); // Array mit neuen Zahlen fuellen
        v.setData(a); // Array-Daten des Visualizers setzen
        v.repaint();  // Neuzeichnen des Fensters

        countdown(3);
        //m ist hier auf 5 gesetzt, man kann mit verschiedenen Werten experimentieren
        visualHybridSort(a, 0, a.length-1, 5);
        isbest(cswap,ccomp);
        v.sleepRealtime(5000);
        
        //Endergebnis
        v.reset();
        v.clearBackground();
        v.setLabel(0,"Der beste Algorithmus im Test war:");
        v.setLegend( bestprogramnumber + " " +getProgramName(bestprogramnumber) + " mit " + best + " Operationen!");
        v.repaint();  // Neuzeichnen des Fensters
        v.sleepRealtime(10000);

        
        programnumber = 0;
    }

    /**
     * aktualisiert Daten auf Bildschirm
     */
    public static void refresh(int[] a)
    {
        v.setData(a);
        v.repaint(); // alles neuzeichnen
        v.sleep(50);  // und warten
    }

    /**
     * ueberprueft, ob gegebene Messwerte besser sind als die vorherigen und speichert das Beste Ergebnis
     * @param swapcounter Tauschcounter
     * @param compcounter Vergleichecounter
     */
    public static void isbest(int swapcounter, int compcounter)
    {
        int totalcount = swapcounter + compcounter;
        programnumber++;
        if(totalcount < best)
        {
            best = totalcount;
            bestprogramnumber = programnumber;
        }
        
    }

    /**
     * gibt das zur Programmnummer zugehoerige Programm zurueck
     * @param number Nummer des Programms
     * @return Programmnamen
     */
    public static String getProgramName(int number)
    {
        String programname;
        switch (number) {
            case 1:  programname = "HeapSort";
            break;
            case 2:  programname = "BubbleSort";
            break;
            case 3:  programname = "InsertionSort";
            break;
            case 4:  programname = "SelectionSort";
            break;
            case 5:  programname = "MergeSort";
            break;
            case 6:  programname = "QuickSort";
            break;
            case 7:  programname = "HybridSort";
            break;
            default: programname = "Something went wrong";
            break;
        }
        return programname;
    }

    /**
     * Baut einen Heap auf und sortiert das Array dann mit Heap-Sort
     * und visualisiert dies mit Hilfe
     * eines (statischen) Visualizer-Objekts v, welches existieren muss.
     * 
     * @param a   das zu sortierende Array
     * 
     * @throws IllegalStateException falls das Visualizer-Objekt nicht existiert
     */
    public static void visualHeapSort(int[] a)
    {
        v.setLegend("Heap wird erstellt. V= Vaterk. K= groesseres Kind");
        for (int i = (a.length - 1) / 2; i >= 0; i--)//heap erstellen
        {

            restore(a, i, a.length - 1);
        }
        v.setLegend("Heap wird sortiert");
        for (int i = a.length - 1; i > 0; i--)//heap sortieren
        {
            cswap += 2;
            swap(a, i, 0);

            v.setHighlight(i, v.VERTICAL_HIGHLIGHT);
            v.setLabel(i, "i");

            restore(a, 0, i-1);
            v.setHighlight(i, v.NO_HIGHLIGHT);
            v.setLabel(i, "");
            v.setHighlight(0, v.NO_HIGHLIGHT);
        }
        v.setLegend("Heap wurde sortiert");
        v.repaint();
        v.sleep(500);
        v.setLegend("Vergleiche:" + ccomp + " Vertauschungen:" + cswap/2);
        v.repaint();
        v.sleep(1500);
    }
    /**
     * Restored Baum
     * @param h Array in dem sich der Baum befindetn
     * @param L left
     * @param R right
     */
    private static void restore(int[] h,int L, int R)
    {
        int j;
        int i = L;
        while (i <= R / 2) {
            v.setColor(2*i+1, Color.BLACK);             //Kinder
            v.setColor(2*i, Color.BLACK);
            v.setHighlight(2*i+1, v.VERTICAL_HIGHLIGHT);
            v.setHighlight(2*i, v.VERTICAL_HIGHLIGHT);
            refresh(h);
            
            ccomp++;
            if ((2*i < R) && (h[2*i+1] > h[2*i]))
            {
                v.setColor(2*i+1, Color.RED);
                v.setColor(2*i, Color.RED);

                j = 2*i+1;

                v.setHighlight(2*i, v.NO_HIGHLIGHT);
                v.setColor(j, Color.CYAN);
                v.setLabel(j, "K");//groesserer Kinderknoten
                refresh(h);
            }
            else
            {
                v.setColor(2*i+1, Color.RED);
                v.setColor(2*i, Color.RED);

                j = 2*i;

                v.setHighlight(2*i+1, v.NO_HIGHLIGHT);
                v.setColor(j, Color.CYAN);
                v.setLabel(j, "K");//groesserer Kinderknoten
                refresh(h);
            }
            v.setColor(i, Color.BLUE);//Vater
            v.setHighlight(i, v.VERTICAL_HIGHLIGHT);
            v.setLabel(i, "V");
            refresh(h);

            ccomp++;
            if (h[j] > h[i]) 
            {
                cswap +=2;
                swap(h, i, j);

                v.setColor(j, Color.BLUE);//Farben umdrehen
                v.setColor(i, Color.CYAN);
                refresh(h);
                v.setHighlight(i, v.NO_HIGHLIGHT);//Vaterdarstellung loeschen
                v.setHighlight(j, v.NO_HIGHLIGHT);//groesseres Kind Darstellung loeschen
                v.setLabel(i, "");

                v.setColor(i, Color.RED);//Farben wieder rot machen
                v.setColor(j, Color.RED);
                v.setLabel(j, "");//groesserer Kinderknoten Label entfernen

                i = j;

            } else
            {
                v.setHighlight(i, v.NO_HIGHLIGHT);//Vaterdarstellung loeschen
                v.setLabel(i, "");
                v.setHighlight(j, v.NO_HIGHLIGHT);//groesseres Kind Darstellung loeschen
                v.setLabel(j, "");//groesserer Kinderknoten Label entfernen
                v.setColor(i, Color.RED);
                v.setColor(j, Color.RED);
                break;
            }
        }
    }
    /**
     * tauscht Elemente i j im Array
     * @param a Array
     * @param i erste Zahl
     * @param j zweite Zahl
     */
    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    /**
     * Sortiert ein int-Array mit dem Bubble-Sort Algorithmus
     * (Code siehe Aufgabe 20) und visualisiert dies mit Hilfe
     * eines (statischen) Visualizer-Objekts v, welches existieren muss.
     * 
     * @param a   das zu sortierende Array
     * 
     * @throws IllegalStateException falls das Visualizer-Objekt nicht existiert
     */
    public static void visualBubbleSort(int[] a) {
        if (v == null)
            throw new IllegalStateException("Visualizer-Objekt v "
                +"existiert nicht!");
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
                
                ccomp++;
                if (a[i] > a[i+1]) {
                    cswap += 2;
                    
                    hilf = a[i];
                    a[i] = a[i+1];
                    a[i+1] = hilf;
                    vertauscht = true;
                }
                v.setColor(i, Color.RED);
                v.setColor(i+1,Color.ORANGE);
                v.setData(a);
                v.repaint(); // alles neuzeichnen
                v.sleep(sleepTime);  // und warten
                v.setLabel(i, "");
                v.setHighlight(i, v.NO_HIGHLIGHT);
                if (i == a.length-run-1)
                    v.setColor(i+1, Color.BLACK);
            }
        } while (vertauscht);
        v.setData(a);
        v.setLegend("Vergleiche:" + ccomp + " Vertauschungen:" + cswap/2);
        v.repaint();
        v.sleep(1500);
    }

    /**
     * Sortiert ein Array mit InsertionSort entsprechend der VL
     * Die beiden Schleifen (i und j) werden visualisiert.
     * 
     * Wenn das ganze Array sortiert werden soll, muss L=0 und R=a.length-1
     * 
     * @param a das zu sortierende Array
     * @param L linke Grenze (inklusiv), ab der sortiert wird (wichtig fuer HybridSort)
     * @param R Rechte Grenze (inklusiv), bis zu der sortiert wird (wichtig fuer HybridSort)
     */
    public static void visualInsertionSort(int[] a, int L, int R) {
        if (v == null)
            throw new IllegalStateException("Visualizer-Objekt v "
                +"existiert nicht!");
        if(L==0 && R==a.length-1) {
            v.setLegend("Insertion Sort");
        }
        //Aeussere Schleife
        for(int i=L+1; i<=R; i++) {
            v.setLabel(i, "i");
            v.setHighlight(i, v.VERTICAL_HIGHLIGHT);
            int tmp = a[i];
            int j = i-1;
            //Innere Schleife
            ccomp++;
            while((j>=L) && (tmp<a[j])) {
                ccomp++;
                v.setLabel(j, "j");
                v.setHighlight(j, v.VERTICAL_HIGHLIGHT);

                v.setColor(i, Color.RED);
                v.setColor(j,Color.ORANGE);
                v.setData(a);
                v.repaint(); // alles neuzeichnen
                v.sleep(sleepTime);  // und warten
                v.setLabel(j, "");
                v.setHighlight(j, v.NO_HIGHLIGHT);
                cswap += 2;
                a[j+1] = a[j];
                v.setColor(j, Color.BLACK);
                j=j-1;
            }
            v.setLabel(i, "");
            v.setHighlight(i, v.NO_HIGHLIGHT);
            a[j+1] = tmp;
        }
        v.setData(a);
        if(L==0 && R==a.length-1) {
            v.setLegend("Vergleiche:" + ccomp + " Vertauschungen:" + cswap/2);
        }
        v.repaint();
    }

    /**
     * Sortiert ein Array mit SelectionSort entsprechend der VL
     * Die innere Schleife (j) und das jeweils kleinste Element werden visualisiert. 
     * 
     * @param a das zu sortierende Array
     */
    public static void visualSelectionSort(int[] a) {
        if (v == null)
            throw new IllegalStateException("Visualizer-Objekt v "
                +"existiert nicht!");
        v.setLegend("Selection Sort");
        int tmp;
        for(int i=0; i<a.length -1; i++) {
            int minindex = i;
            for(int j = i+1; j<a.length; j++) {
                v.setLabel(j, "j");
                v.setHighlight(j, v.VERTICAL_HIGHLIGHT);

                v.setColor(minindex,Color.ORANGE);
                v.setData(a);
                v.repaint(); // alles neuzeichnen
                v.sleep(sleepTime);  // und warten
                v.setLabel(j, "");
                v.setHighlight(j, v.NO_HIGHLIGHT);
                v.setColor(minindex,Color.RED);
                ccomp++;
                if(a[j] < a[minindex]) {
                    minindex = j;
                }
            }
            //Tauschen
            cswap += 2;
            tmp = a[i];
            a[i] = a[minindex];
            a[minindex] = tmp;
            v.setColor(i, Color.BLACK);
        }
        v.setData(a);
        v.setLegend("Vergleiche:" + ccomp + " Vertauschungen:" + cswap/2);
        v.repaint();
    }

    /**
     * Sortiert ein Array mit MergeSort entsprechend der VL
     * Die Schleife, die 2 Teile zusammenfuegt (k) wird visualisiert
     * Wenn das ganze Array sortiert werden soll, muss L=0 und R=a.length
     * 
     * @param a das zu sortierende Array
     * @param L linke Grenze (inklusiv), ab der sortiert wird
     * @param R Rechte Grenze (exklusiv), bis zu der sortiert wird
     */
    public static void visualMergeSort(int[] a, int L, int R) {
        if (v == null)
            throw new IllegalStateException("Visualizer-Objekt v "
                +"existiert nicht!");
        v.setLegend("Merge Sort");

        int M = (L+R)/2;
        if(L < M) 
            visualMergeSort(a, L, M);
        if(M+1 < R)
            visualMergeSort(a, M+1, R);
        merge(a, L, M, R);

        v.setData(a);
        if(L==0 && R==a.length) {
            v.setLegend("Vergleiche:" + ccomp + " Vertauschungen:" + cswap/2);
        }
        v.repaint();
    }

    /**
     * Fuegt die zwei Teile (angegeben durch L und R) zusammen
     * @param a Das Array auf dem gearbeitet wird
     * @param L Die linke Grenze
     * @param M Die Mitte: (L+R)/2
     * @param R Die rechte Grenze
     */
    public static void merge(int[] a, int L, int M, int R) {    
        int tmp =0;
        int[] b = new int[R-L+1];
        int i = L;
        int j = M + 1;
        int k;
        for (k = L; k <= R; k++) {
            ccomp++;
            if ((j<a.length) && ((i > M) || ((j <= R) && (a[j] < a[i])))) {
                cswap++; //ist im strengen Sinne keine Vertauschung, aber das Einfuegen sollte beachtet werden
                b[k-L] = a[j];
                j = j + 1;
            } else {
                if(i<a.length) {
                    cswap++;
                    b[k-L] = a[i];
                    i = i + 1;
                }
            }       
        }

        for (k = L; k <= R; k++) {
            if(k<a.length) {
                cswap++;
                a[k] = b[k-L];
                v.setLabel(k, "k");
                v.setHighlight(k, v.VERTICAL_HIGHLIGHT);
            }

            v.setData(a);
            v.repaint(); // alles neuzeichnen
            v.sleep(sleepTime);  // und warten

            if(k<a.length) {
                v.setLabel(k, "");
                v.setHighlight(k, v.NO_HIGHLIGHT);
            }
        }   
    }

    /**
     * Sortiert ein Array mit QuickSort entsprechend der VL
     * 
     * Damit das komplette Array sortiert wird muss L=0 und R=a.length-1
     * 
     * Es werden die linke und rechte Grenze, sowie das Pivot-Element und die zwei Elemente
     * die jeweils vertauscht werden visualisiert
     * 
     * @param a Das zu sortierende Array
     * @param L Die linke Grenze (inklusive)
     * @param R Die rechte Grenze (inklusive)
     */
    public static void visualQuickSort(int[] a, int L, int R) {
        if (v == null)
            throw new IllegalStateException("Visualizer-Objekt v "
                +"existiert nicht!");
        v.setLegend("Quick Sort");

        //Wenn das Array zufaellig gefuellt ist, wahlt man hier auch ein zufaelliges Element als Pivot,
        //wenn man die absteigende oder aufsteigende Befuellung waehlt hat man sogar tatsaechlich den Median als Pivot
        int pivotIndex = (L+R)/2;
        int pivot = a[pivotIndex];
        int i=L;
        int j=R;
        int tmp;
        v.setLabel(L, "L");
        v.setHighlight(L, v.VERTICAL_HIGHLIGHT);
        v.setLabel(R, "R");
        v.setHighlight(R, v.VERTICAL_HIGHLIGHT);
        v.repaint();
        v.setHighlight(pivotIndex, v.HORIZONTAL_HIGHLIGHT);
        v.setColor(pivotIndex, Color.BLUE);
        while(i<=j) {
            ccomp++;
            while(a[i]<pivot) {
                ccomp++;
                i++;
            }
            ccomp++;
            while(pivot<a[j]){
                ccomp++;
                j--;
            }
            if(i<=j) {
                v.setColor(i, Color.ORANGE);
                v.setColor(j, Color.ORANGE);
                v.setData(a);
                v.repaint();
                //Damit man was erkennt
                v.sleep(5*sleepTime);
                v.setColor(i, Color.RED);
                v.setColor(j, Color.RED);

                //Tauschen
                cswap += 2;
                tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;

                i++;
                j--;        
            }
        }

        //Zuruecksetzen der Visualisierung
        v.setLabel(L, "");
        v.setHighlight(L, v.NO_HIGHLIGHT);
        v.setLabel(R, "");
        v.setHighlight(R, v.NO_HIGHLIGHT);
        v.setHighlight(pivotIndex, v.NO_HIGHLIGHT);
        v.setColor(pivotIndex, Color.RED);
        v.repaint();
        //Rekursiver Aufruf auf Teile
        if(L<j) visualQuickSort(a, L, j);
        if(i<R) visualQuickSort(a, i, R);
        v.setData(a);
        if(L==0 && R==a.length-1) {
            v.setLegend("Vergleiche:" + ccomp + " Vertauschungen:" + cswap/2);
        }
        v.repaint();
    }

    /**
     * Sortiert ein Array mit QuickSort, bzw wenn das rekursiv zu sortierende Array 
     * kleiner als m ist stattdessen mit InsertionSort.
     * 
     * Damit das komplette Array sortiert wird muss L=0 und R=a.length-1
     * 
     * Der Code und die Visualisierung sind genauso wie beim QuickSort
     * 
     * @param a Das zu sortierende Array
     * @param L Die linke Grenze (inklusive)
     * @param R Die rechte Grenze (inklusive)
     * @param m Die groesse des Teilarrays, unter der InsertionSort aufgerufen wird
     * m=0 entspricht komplett QuickSort
     * m=a.length entspricht komplett InsertionSort
     */
    public static void visualHybridSort(int[] a, int L, int R, int m) {
        if (v == null)
            throw new IllegalStateException("Visualizer-Objekt v "
                +"existiert nicht!");
        v.setLegend("Hybrid Sort");

        if(R-L < m) {
            visualInsertionSort(a, L, R);
        }
        else {
            int pivotIndex = (L+R)/2;
            int pivot = a[pivotIndex];
            int i=L;
            int j=R;
            int tmp;
            v.setLabel(L, "L");
            v.setHighlight(L, v.VERTICAL_HIGHLIGHT);
            v.setLabel(R, "R");
            v.setHighlight(R, v.VERTICAL_HIGHLIGHT);
            v.repaint();
            v.setHighlight(pivotIndex, v.HORIZONTAL_HIGHLIGHT);
            v.setColor(pivotIndex, Color.BLUE);
            while(i<=j) {
                ccomp++;
                while(a[i]<pivot) {
                    ccomp++;
                    i++;
                }
                ccomp++;
                while(pivot<a[j]){
                    ccomp++;
                    j--;
                }
                if(i<=j) {
                    v.setColor(i, Color.ORANGE);
                    v.setColor(j, Color.ORANGE);
                    v.setData(a);
                    v.repaint();
                    //Damit man was erkennt
                    v.sleep(5*sleepTime);
                    v.setColor(i, Color.RED);
                    v.setColor(j, Color.RED);
                    cswap += 2;
                    tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;

                    i++;
                    j--;        
                }
            }
            v.setLabel(L, "");
            v.setHighlight(L, v.NO_HIGHLIGHT);
            v.setLabel(R, "");
            v.setHighlight(R, v.NO_HIGHLIGHT);
            v.setHighlight(pivotIndex, v.NO_HIGHLIGHT);
            v.setColor(pivotIndex, Color.RED);
            //Hier wird auch statt QuickSort wieder HybridSort aufgerufen
            if(L<j) visualHybridSort(a, L, j, m);
            if(i<R) visualHybridSort(a, i, R, m);
            v.setData(a);
            if(L==0 && R==a.length-1) {
                v.setLegend( "Vergleiche:" + ccomp + " Vertauschungen:" + cswap/2);
            }
            v.repaint();
        }
    }

    /**
     * Findet ein passendes m für den HybridSort
     * Testet jedes m numTests Mal und nimmt die durchschnittliche Zeit
     * 
     * Die Variable sleepTime sollte auf 0 gesetzt werden!
     * 
     * @param numTests Anzahl der Tests mit einem bestimmten m
     * Groessere Werte machen das Ergebnis genauer, dauern aber laenger
     * @return Der index m, bei dem es am schnellsten war
     */
    public static int findGoodM(int numTests) {
        int[] a = new int[100];
        v = new Visualizer(a);

        long startTime=0;
        long endTime=0;
        long time=0;
        long averageTime=0;
        long shortestTime=Long.MAX_VALUE;
        int bestM = -1;

        //Iteriert ueber die m's
        //Nach 20 wurden die Zeiten nur noch laenger, man kann aber bis zu 100 testen
        for(int m=0; m<20; m++) {
            //Testet das ganze numTests mal
            for(int i=0; i<numTests; i++) {
                //Wurde mit dem Zufallszahlengenerator getestet (Also useRNG=true)
                fillArray(a);
                //Startet den timer
                startTime = System.nanoTime();
                //Sortiert
                visualHybridSort(a, 0, a.length-1, m);
                //Beendet den Timer
                endTime = System.nanoTime();
                time += (endTime - startTime);
            }
            //Nimmt den Durchschitt aller Tests
            averageTime = time/numTests;
            if(averageTime<shortestTime) {
                shortestTime = averageTime;
                bestM = m;
            }
            //Setzt time zurueck
            time = 0;
            //System.out.println("m: " + m + " braucht: "+averageTime);
        }
        return bestM;   
    }
}
