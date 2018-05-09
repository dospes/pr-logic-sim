public class Buf extends Gatter {

    public Buf(int i){
        Delay = i;
    }

    public boolean calcOutput(){
        return InputSignalValue[0];
    }
}
