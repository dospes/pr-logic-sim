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
        boolean o = true;
        for (int i = 0; i < InputSignal.length - 1; i++){
            if (InputSignalValue[i] != InputSignalValue[i+1]){
                o = false;
                break;
            }
        }
        return o;
    }

}
