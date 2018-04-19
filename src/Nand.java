public class Nand {

    int InputCount;
    String[] InputSignal;
    String OutputSignal;

    public Nand(int NandInputs){
        InputCount = NandInputs;
    }

    public void setInput(int Number, String Signal){
        InputSignal[Number] = Signal;
    }

    public void setOutput(String Signal){
        OutputSignal = Signal;
    }
}
