import java.util.ArrayList;
import java.util.regex.Pattern;

public class Gatter {

    /*
     * Input- und OutputSignal sind die Signal-Objekte, InputSignalValue die Werte der Signale
     */

    protected Signal[] InputSignal;
    protected Signal OutputSignal;
    protected boolean[] InputSignalValue;
    protected int Delay;
    protected static boolean SetupFlag;
    protected static boolean SetupComplete;

    /*
     * InputSignal und InputSignalValue werden auf die Anzahl der Inputs angepasst
     * Da in TimingSimulator mehrere Argumente weitergegeben werden, wird die Menge hier variabel gehalten
     * i verhält sich wie ein Array
     */

    public Gatter(){
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

    protected void getInputValues(){
        for(int i = 0; i < InputSignal.length; i++){
            InputSignalValue[i] = InputSignal[i].getValue();
        }
    }

    protected void makeOutputEvent(boolean Output) {
        new Event(OutputSignal, Delay, Output, true);
    }

    /*
     * Gatter-Output wird anhand aktualisierter Werte berechnet
     * Output wird an Signal weitergegeben
     * "!" bedeutet Negation
     */

    protected boolean calcOutput(){
        return false;
    }

    protected void checkForSetup(){
        ArrayList<String> Methods = new ArrayList<String>();
        for (StackTraceElement e : Thread.currentThread().getStackTrace()){
            Methods.add(e.getMethodName());
        }
        for (String s : Methods) {
            if (Pattern.compile(Pattern.quote("steady"), Pattern.CASE_INSENSITIVE).matcher(s).find()){
                if (!SetupFlag){
                    SetupComplete = false;
                }
                SetupFlag = true;
                return;
            }
        }
        SetupFlag = false;
        SetupComplete = true;
    }

    public void gatterMain(){
        boolean Output;
        checkForSetup();
        if (!SetupFlag) {
            getInputValues();
            Output = calcOutput();
            if (Delay == 0) {
                OutputSignal.setValue(Output);
            } else {
                makeOutputEvent(Output);
            }
        } else {
            if (!SetupComplete){
                SetupComplete = true;
                getInputValues();
                Output = calcOutput();
                OutputSignal.forceSetValue(Output);
            }
            getInputValues();
            Output = calcOutput();
            OutputSignal.setValue(Output);
        }
    }
}
