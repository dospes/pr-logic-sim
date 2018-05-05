import java.util.ArrayList;

public class Logger {

    private ArrayList<Object> Log;
    private ArrayList<Signal> Signals;

    public Logger(){
        Log = new ArrayList<>();
        Signals = new ArrayList<>();
        Signal.setLog(this);
        Clock.setLog(this);
    }

    public void addToLog(Signal s){
        Signals.add(s);
    }

    public void addLogEntry(){
        boolean[] Values = new boolean[Signals.size()];
        for (int i = 0; i < Signals.size(); i++){
            Signal s = Signals.get(i);
            Values[i] = s.getValue();
        }
        Log.add(Values);

        //TODO Log-Dump
    }
}
