public class Exor extends Gatter {

    public Exor(int... i){
        InputSignal = new Signal[i[0]];
        InputSignalValue = new boolean[i[0]];
        if (i.length > 1) {
            Delay = i[1];
        } else {
            Delay = 0;
        }
    }

    public boolean calcOutput(){
        boolean o = InputSignalValue[0] ^ InputSignalValue[1];
        if (InputSignal.length > 2) {
            for (int i = 2; i < InputSignal.length; i++) {
                o = o ^ InputSignalValue[i];
            }
        }
        return o;
    }

}
