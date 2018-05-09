public class Gatter {

    /*
     * Input- und OutputSignal sind die Signal-Objekte, InputSignalValue die Werte der Signale
     */

    protected Signal[] InputSignal;
    protected Signal OutputSignal;
    protected boolean[] InputSignalValue;
    protected int Delay;
    protected static int SetupComplete;
    protected static int SetupCounter;

    /*
     * InputSignal und InputSignalValue werden auf die Anzahl der Inputs angepasst
     * Da in TimingSimulator mehrere Argumente weitergegeben werden, wird die Menge hier variabel gehalten
     * i verhält sich wie ein Array
     */

    public Gatter(int... i){
        SetupComplete = 2;
        SetupCounter = 100;
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

    private void makeOutputEvent(boolean Output) {
        new Event(OutputSignal, Delay, Output, true);
    }

    /*
     * Gatter-Output wird anhand aktualisierter Werte berechnet
     * Output wird an Signal weitergegeben
     * "!" bedeutet Negation
     */

    private boolean calcOutput(){
        return false;
    }

    public void gatterMain(){
        boolean Output;
        if (SetupComplete == 0) {
            this.getInputValues();
            Output = this.calcOutput();
            if (Delay == 0) {
                OutputSignal.setValue(Output);
            } else {
                this.makeOutputEvent(Output);
            }
        } else {
            this.getInputValues();
            Output = this.calcOutput();
            if (SetupCounter > 0) {
                SetupCounter--;
                OutputSignal.setValue(Output);
            } else {
                SetupComplete--;
                if (SetupComplete > 0){
                    SetupCounter = 100;
                }
            }
        }
    }
}
