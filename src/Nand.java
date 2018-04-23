public class Nand {

    int InputCount; //Anzahl der Inputs
    Signal[] InputSignal = new Signal[4]; //Array mit Platz für 4 Signal-Objekte, Eingänge
    Signal OutputSignal; //Signal für Ausgang
    boolean[] InputSignalValue; //Array von boolean für die Inputsignale
    boolean output;

    public Nand(int NandInputs){
        InputCount = NandInputs;
    }

    public void setInput(int n, Signal s){
        InputSignal[n] = s;
        s.setPostSignal(this);
    }

    public void setOutput(Signal s){
        OutputSignal = s;
    }

    public void getInputValues(){
        for(int i = 0; i < InputSignal.length; i++){
            InputSignalValue[i] = InputSignal[i].getValue();
        }
    }

    public void gatterMain(){
        boolean tempOutput = false;
        this.getInputValues();
        for(int i = 0; i < InputSignalValue.length; i++){
            if(InputSignalValue[i]==false){
                tempOutput = true;
                break;
            }
        }
        output = tempOutput;
        OutputSignal.setValue(output);
    }
}
