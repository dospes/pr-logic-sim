public class Nand {

    /*
     * Input- und OutputSignal sind die Signal-Objekte, InputSignalValue die Werte der Signale
     */

    private Signal[] InputSignal;
    private Signal OutputSignal;
    private boolean[] InputSignalValue;

    /*
     * InputSignal und InputSignalValue werden auf die Anzahl der Inputs angepasst
     * Da in TimingSimulator mehrere Argumente weitergegeben werden, wird die Menge hier variabel gehalten
     * i verhält sich wie ein Array
     */

    public Nand(int... i){
        InputSignal = new Signal[i[0]];
        InputSignalValue = new boolean[i[0]];
    }

    /*
     * Signal wird als Objekt in InputSignal gespeichert
     * Nand Objekt wird in Signal gespeichert
     */

    public void setInput(int n, Signal s){
        InputSignal[n] = s;
        s.setPostSignal(this);
    }

    public void setOutput(Signal s){
        OutputSignal = s;
    }

    /*
     * InputSignalValue wird aktualisiert
     */

    private void getInputValues(){
        for(int i = 0; i < InputSignal.length; i++){
            InputSignalValue[i] = InputSignal[i].getValue();
        }
    }

    /*
     * Gatter-Output wird anhand aktualisierter Werte berechnet
     * Output wird an Signal weitergegeben
     * "!" bedeutet Negation
     */

    public void gatterMain(){
        boolean Output = false;
        this.getInputValues();
        for(int i = 0; i < InputSignalValue.length; i++){
            if(!InputSignalValue[i]){
                Output = true;
                break;
            }
        }
        OutputSignal.setValue(Output);
    }
}
