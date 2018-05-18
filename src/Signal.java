import java.util.ArrayList;

public class Signal {

    /*
     * Name, Wert und nachfolgende Nand-Gatter mitsamt Index für diese
     */

    private String Name;
    private boolean Value;
    private Boolean prevValue;
    private ArrayList<Nand> postSignal;
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
        if (prevValue == null){
            for(int i = 0; i < postSignal.size(); i++){
                postSignal.get(i).gatterMain();
            }
            prevValue = Value;
            return;
        }
        if (prevValue != Value) {
            for (int i = 0; i < postSignal.size(); i++) {
                postSignal.get(i).gatterMain();
            }
            prevValue = Value;
        }
    }

    public boolean getValue(){
        return Value;
    }

    /*
     * postSignal wird um Nand erweitert
     * Index wird erhöht, um überschreiben zu verhindern
     */

    public void setPostSignal(Nand nand){
        postSignal.add(nand);
    }

    public String getName() {
        return Name;
    }
}
