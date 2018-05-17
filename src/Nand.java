public class Nand extends Gatter {

    public Nand(int... i){
        InputSignal = new Signal[i[0]];
        InputSignalValue = new boolean[i[0]];
        if (i.length > 1) {
            Delay = i[1];
        } else {
            Delay = 0;
        }
    }

    public boolean calcOutput(){
        boolean o = false;
        for (boolean Value : InputSignalValue) {
            if (!Value) {
                o = true;
                break;
            }
        }
        return o;
    }
}
