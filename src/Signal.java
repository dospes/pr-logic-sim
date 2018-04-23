public class Signal {

    String Name;
    boolean Value;
//    Nand preSignal;
    int postSignalIndex = 0;
    Nand[] postSignal = new Nand[4];

    public Signal(String SignalName){
        Name = SignalName;
    }

    public void setValue(boolean SignalValue){
        Value = SignalValue;
        for(int i = 0; i < postSignalIndex; i++){
            postSignal[i].gatterMain();
        }
    }

    public boolean getValue(){
        return Value;
    }

//    public void setPreSignal(Nand nand){
//        preSignal = nand;
//    }

    public void setPostSignal(Nand nand){
        postSignal[postSignalIndex] = nand;
        postSignalIndex++;
    }
}
