public class Nor extends Gatter {

    public Nor(int... i){
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
        for (boolean b : InputSignalValue){
            if (b){
                o = true;
                break;
            }
        }
        return !o;
    }

}
