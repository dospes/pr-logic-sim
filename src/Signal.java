import java.util.ArrayList;
import java.util.regex.Pattern;

public class Signal {

    /*
     * Name, Wert und nachfolgende Nand-Gatter mitsamt Index für diese
     */

    private String Name;
    private boolean Value;
    private ArrayList<Gatter> postSignal;
    private Boolean prevValue;
    private static Logger Log;

    public Signal(String SignalName){
        Name = SignalName;
        postSignal = new ArrayList<>();
        Log.addToLog(this);
    }

    public static void setLog(Logger log) {
        Log = log;
    }


    public ArrayList<Gatter> getPostSignal() {
        return postSignal;
    }

    /*
     * Wert wird aktualisiert
     * Nachfolgende Nand-Gatter werden angeregt zu aktualisieren
     * Handelt es sich um ein Output-Signal wird ein Konsolen-Output erzeugt
     */

    public void setValue(boolean SignalValue){
        Value = SignalValue;
        SignalProp();
    }

    private boolean inSetup(){
        ArrayList<String> Methods = new ArrayList<String>();
        for (StackTraceElement e : Thread.currentThread().getStackTrace()){
            Methods.add(e.getMethodName());
        }
        for (String s : Methods) {
            if (Pattern.compile(Pattern.quote("steady"), Pattern.CASE_INSENSITIVE).matcher(s).find()){
                return true;
            }
        }
        return false;
    }

    private void SignalProp(){
        if (prevValue == null) {
            for (Gatter g : postSignal) {
                g.gatterMain();
            }
            prevValue = Value;
            return;
        }
        if (prevValue != Value) {
            for (Gatter g : postSignal) {
                g.gatterMain();
            }
            prevValue = Value;
        }
    }

    public void setValue(boolean SignalValue, ArrayList<Event> Overlap){
        Value = SignalValue;
        if (Overlap == null){
            SignalProp();
        } else {
            for (Event e : Overlap) {
                e.getSignal().setValueNoProp(e.getNewValue());
            }
            SignalProp();
        }
    }

    private void setValueNoProp(boolean SignalValue){
        Value = SignalValue;
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
