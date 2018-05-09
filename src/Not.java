public class Not extends Gatter {

    public Not(int i){
        InputSignal = new Signal[1];
        InputSignalValue = new boolean[1];
        Delay = i;
    }

    private boolean calcOutput() {
        boolean o;
        o = !InputSignalValue[0];
        return o;
    }
}
