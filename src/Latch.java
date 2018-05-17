public class Latch extends MemSuper{

    public Latch(int i){
        Delay = i;
    }

    public boolean calcOutput(){
        if (InputSignalValue[0]){
            StoredValue = InputSignalValue[1];
        }
        return StoredValue;
    }
}
