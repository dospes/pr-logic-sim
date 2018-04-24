public class Signal {

    String Name;
    boolean Value;
//    Nand preSignal;
    int postSignalIndex = 0;
    Nand[] postSignal;

    public Signal(String SignalName){
        Name = SignalName;
        postSignal = new Nand[10]; //Größe kann erweitert werden
    }

    public void setValue(boolean SignalValue){
        Value = SignalValue;
        for(int i = 0; i < postSignalIndex; i++){
            postSignal[i].gatterMain();
        }
        if(Name.contains("s")){
            System.out.println(Name + " = " + Value);
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
