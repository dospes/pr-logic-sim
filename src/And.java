public class And extends Gatter {

    public And (int... i){
        InputSignal = new Signal[i[0]];
        InputSignalValue = new boolean[i[0]];
        if (i.length > 1) {
            Delay = i[1];
        } else {
            Delay = 0;
        }
    }

    public boolean calcOutput(){
        for(Boolean b : InputSignalValue){
            if (!b){
                return false;
            }
        }
        return true;
    }
}
