public class FF extends MemSuper {

    private boolean ClockFlag;

    public FF(int i){
        Delay = i;
        ClockFlag = false;
    }

    public boolean calcOutput(){
        if (!InputSignalValue[0]){
            ClockFlag = false;
        } else if (!ClockFlag){
            StoredValue = InputSignalValue[1];
            ClockFlag = true;
        }
        return StoredValue;
    }

}
