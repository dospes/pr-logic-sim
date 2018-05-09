import java.util.ArrayList;

public class Signal {

    /*
     * Name, Wert und nachfolgende Nand-Gatter mitsamt Index für diese
     */

    private String Name;
    private boolean Value;
    private ArrayList<Gatter> postSignal;
    private static Logger Log;

    public Signal(String SignalName){
        Name = SignalName;
        postSignal = new ArrayList<>();
        Log.addToLog(this);
    }

    public static void setLog(Logger log) {
        Log = log;
    }

    /*
     * Wert wird aktualisiert
     * Nachfolgende Nand-Gatter werden angeregt zu aktualisieren
     * Handelt es sich um ein Output-Signal wird ein Konsolen-Output erzeugt
     */

    public void setValue(boolean SignalValue){
        Value = SignalValue;
        for (Gatter g : postSignal) {
            g.gatterMain();
        }
//        if(Name.contains("s")){
//            System.out.println(Name + " = " + Value);
//        }
    }

    public boolean getValue(){
        return Value;
    }

    /*
     * postSignal wird um Nand erweitert
     * Index wird erhöht, um überschreiben zu verhindern
     */

    public void setPostSignal(Gatter gatter){
        postSignal.add(gatter);
    }

    public String getName() {
        return Name;
    }
}
