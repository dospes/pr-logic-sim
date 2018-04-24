public class Signal {

    /*
     * Name, Wert und nachfolgende Nand-Gatter mitsamt Index für diese
     */

    private String Name;
    private boolean Value;
    private int postSignalIndex = 0;
    private Nand[] postSignal;

    public Signal(String SignalName){
        Name = SignalName;
        postSignal = new Nand[10]; //TODO statt Array ArrayListe verwenden um diese dynamisch erweitern zu können
    }

    /*
     * Wert wird aktualisiert
     * Nachfolgende Nand-Gatter werden angeregt zu aktualisieren
     * Handelt es sich um ein Output-Signal wird ein Konsolen-Output erzeugt
     */

    public void setValue(boolean SignalValue){
        Value = SignalValue;
        for(int i = 0; i < postSignalIndex; i++){
            postSignal[i].gatterMain();
        }
        if(Name.contains("s")){
            System.out.println(Name + " = " + Value);
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
        postSignal[postSignalIndex] = nand;
        postSignalIndex++;
    }
}
