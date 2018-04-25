/**
 * Klasse TimingSimulator ist ein Logiksimulator, der nur 
 * Nand-Gatter simulieren kann und Zeitverzögerungen berücksichtigt.
 * Die zu simulierende Schaltung wird in der Methode
 * <CODE>buildCircuit()</CODE> der
 * Klasse erzeugt.
 * Es handelt sich hierbei um ein einfaches RS-FlipFlop.
 * Nach der Konstruktion der Schaltung muss zunächst der Ruhezustand
 * der Schaltung berechnet werden. Dies übernimmt die Methode
 * <CODE>findSteadyState()</CODE>. Die Eingabe-Events zur Stimulation der Schaltung werden durch die Methode
 * <CODE>setInputEvents()</CODE> erzeugt.
 * Zum Testen Ihrer Klassen <CODE>Nand</CODE>, <CODE>Signal</CODE>,
 * <CODE>Event</CODE>,und <CODE>EventQueue</CODE> müssen Sie
 * einfach nur eine Instanz dieser Klasse erzeugen und dann die Methode
 * <CODE>simulate()</CODE> aufrufen.
 * @author Christian Hochberger, TU Dresden
 * @version 1.0 Erste Fassung
 */
public class TimingSimulator {
    // EventQueue für diesen Simulator, wird im Konstruktor initialisiert
    private EventQueue	queue;
    // Die beiden Eingangssignale
    private Signal	inS,inR;
    // Die beiden Ausgangssignale (werden nur benötigt, wenn mann die Ausgänge
    // zwischendurch auch mal ausgeben möchte)
    private Signal oQ,onQ;

    /**
     * Konstruktor, der die zu simulierende Schaltung aufbaut, den Ruhezustand
     * ermittelt und die Eingabe-Events erzeugt.
     * Simuliert wird ein einfaches RS-FlipFlop.
     */
    public TimingSimulator() {
	// Erzeugt die EventQueue für diesen Simulator
	queue=new EventQueue();

	// Trägt diese EventQueue in ein statisches Feld der Klasse Event ein
	// Dazu muss Event die statische Methode setEventQueue(EventQueue e)
	// besitzen.
	Event.setEventQueue(queue);

	// Schaltung aufbauen
	buildCircuit();

	// Ruhezustand berechnen
	findSteadyState();
	
	// Zum Testen einfach mal den Zustand der Ausgabesignale ausgeben
	System.out.println("Q = "+oQ.getValue());
	System.out.println("nQ = "+onQ.getValue());

	// EventQueue mit Eingabe-Events füllen
	setInputEvents();
    }

    /**
     * Diese Methode konstruiert die Schaltung. Die erzeugten Gatter und die
     * inneren Signale sind nur in dieser Methode bekannt, da sie im Verlauf
     * der Simulation implizit durch die Events, bzw. die Signale angesprochen
     * werden. 
     */
    private void buildCircuit() {
	Signal s1,s2,s3,s4;
	Nand n1,n2,n3,n4,n5,n6;

	// Alle Signale anlegen
	// Der Konstruktor bekommt einen Signalnamen als Parameter
	inS=new Signal("S");
	inR=new Signal("R");
	// Die Signale oQ und onQ sind mit keinen weiteren Gattern verbunden.
	// Sorgen Sie dafür, dass in diesem Fall eine Wertänderung dieser
	// Signale ausgegeben wird.
	oQ=new Signal("Q");
	onQ=new Signal("nQ");
	s1=new Signal("s1");
	s2=new Signal("s2");
	s3=new Signal("s3");
	s4=new Signal("s4");

	// Alle Gatter anlegen
	// Parameter des Konstruktors sind die Anzahl von Eingängen und
	// die Verzögerungszeit
	// Die Inverter sind sozusagen entartete Nand-Gatter (1 Eingang)
	n1=new Nand(1,5);
	n2=new Nand(1,5);
	n3=new Nand(2,10);
	n4=new Nand(2,10);
	n5=new Nand(1,5);
	n6=new Nand(1,5);

	// Inverter mit Ein- und Ausgängen verbinden.
	// Die Methode setInput() des Gatters bekommt die Nummer des Eingangs
	// und das Signal, mit dem dieser Eingang verbunden werden soll.
	n1.setInput(0,inR);
	// Die Methode setOutput() bekommt nur ein Signal, welches durch diesen
	// Ausgang bestimmt wird
	n1.setOutput(s1);
	n2.setInput(0,inS);
	n2.setOutput(s2);

	// Die Nand-Gatter des FlipFlops richtig verbinden
	n3.setOutput(s3);
	n3.setInput(0,s1);
	n3.setInput(1,s4);
	n4.setOutput(s4);
	n4.setInput(0,s2);
	n4.setInput(1,s3);

	// Die Ausgangs-Inverter verbinden.
	n5.setOutput(oQ);
	n5.setInput(0,s3);
	n6.setOutput(onQ);
	n6.setInput(0,s4);
    }

    /**
     * Diese Methode ermittelt den Ruhezustand der Schaltung. Dazu werden
     * vernünftige Initialwerte an die Eingänge angelegt. Diese Initialwerte
     * müssen mindestens einmal durch die Schaltung propagiert werden,
     * bis sich ein stabiler Zustand einstellt. Um das festzustellen gibt
     * es verschiedene Methoden (im Gatter mitzählen, wie oft sich der Wert
     * ändert. Im Signal mitzählen, wie oft es geändert wurde).
     * Bei diesem Propagieren darf natürlich nicht mit den Zeitverzögerungen
     * gearbeitet werden.  Sie können also im Grunde die Wert-Propagierung
     * aus der ersten Teilaufgabe benutzen.
     */
    private void findSteadyState() {
	inS.setValue(false);
	inR.setValue(false);
    }

    /**
     * Diese Methode erzeugt eine Reihe von Eingabe-Events, die dann zur
     * Stimulation der Schaltung dienen.  Die Events werden durch ihren
     * eigenen Konstruktor in die EventQueue eingetragen, so dass hier nur
     * das Erzeugen der Events zu sehen ist.
     * Jedes Event bekommt beim Erzeugen das betroffene Signal, den Zeitpunkt
     * und den <bold>neuen Wert</bold> mit.
     */
    private void setInputEvents() {
	new Event(inS, 10, true);
	new Event(inS, 30, false);
	new Event(inR, 50, true);
	new Event(inR, 100, false);
	new Event(inS, 120, true);
	new Event(inR, 150, true);
	new Event(inS, 170, false);
	new Event(inR, 180, false);
    }

    /**
     * Diese Methode führt die eigentliche Simulation durch. Dazu wird
     * geprüft, ob in der EventQueue noch weitere Events vorhanden sind. Ist
     * dies der Fall, dann wird das nächste anstehende Event behandelt. Dazu
     * muss das Event die Methode propagate() zur Verfügung stellen, die
     * dann das betroffene Signal informiert.
     */
    public void simulate() {
	while (queue.hasMore()) {
	    Event e=queue.getFirst();

	    //System.out.println(e);
	    e.propagate();
	}
    }
    
    /**
     * Main Methode dieser Klasse. Sie müssen das im Moment noch nicht
     * verstehen. Diese Methode wird benötigt, wenn Sie den Simulator ohne
     * BlueJ laufen lassen wollen. Wenn Sie diese Klasse in BlueJ nutzen,
     * dann ignorieren Sie diese Methode einfach.
     */
    static public void main(String[] args) {
	TimingSimulator	t=new TimingSimulator();
	t.simulate();
    }
}
