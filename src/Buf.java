public class Buf extends Gatter {

    public Buf(int i){
        Delay = i;
        InputSignal = new Signal[1];
        InputSignalValue = new boolean[1];
    }

    public boolean calcOutput(){
        return InputSignalValue[0];
    }
}
