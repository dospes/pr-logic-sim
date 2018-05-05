import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Logger {

    private static ArrayList<boolean[]> Log;
    private static ArrayList<Signal> Signals;

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

//        if(Clock.getTime() == 180){ //TODO so schreiben, dass nicht vor Programmende bekannt sein muss, wann es endet
//            try {
//                this.dumpToCSV();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public static void dumpToCSV() throws IOException {
        BufferedWriter br = new BufferedWriter(new FileWriter("output.csv"));
        StringBuilder sb = new StringBuilder();
        int SignalCount = Signals.size();

        for (int i = 0; i < SignalCount; i++){
            sb.append(Signals.get(i).getName());
            sb.append(',');
        }
        sb.setLength(sb.length() - 1);
        br.write(sb.toString());
        br.newLine();
        sb.setLength(0);
        for (int i = 0; i < Log.size(); i++){
            boolean[] LogEntry = Log.get(i);
            for (int j = 0; j < SignalCount; j++){
                boolean tempValue = LogEntry[j];
                if (tempValue){
                    sb.append('1');
                } else {
                    sb.append('0');
                }
                sb.append(',');
            }
            sb.setLength(sb.length() - 1);
            br.write(sb.toString());
            br.newLine();
            sb.setLength(0);
        }
        br.close();
    }
}
