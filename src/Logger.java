import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Logger {

    private static ArrayList<boolean[]> Log;
    private static ArrayList<Signal> Signals;

    /*
     * Signal und Clock Klassen werden mit dem Logger verbunden
     */

    public Logger(){
        Log = new ArrayList<>();
        Signals = new ArrayList<>();
        Signal.setLog(this);
        Clock.setLog(this);
    }

    public void addToLog(Signal s){
        Signals.add(s);
    }

    /*
     * Werte sämtlicher Signale werden in Array gespeichert und in den Log eingefügt
     */

    public static void addLogEntry(){
        boolean[] Values = new boolean[Signals.size()];
        for (int i = 0; i < Signals.size(); i++){
            Signal s = Signals.get(i);
            Values[i] = s.getValue();
        }
        Log.add(Values);
    }

    /*
     * Schreibt alle Werte als 1 oder 0 (true/false) mit "," als Seperator in ein String,
     * der als CSV gespeichert wird.
     */

    public static void dumpToCSV() throws IOException {
        BufferedWriter br = new BufferedWriter(new FileWriter("output.csv"));
        StringBuilder sb = new StringBuilder();
        int SignalCount = Signals.size();

        for (Signal s : Signals) {
            sb.append(s.getName());
            sb.append(',');
        }
        sb.setLength(sb.length() - 1);
        br.write(sb.toString());
        br.newLine();
        sb.setLength(0);
        for (boolean[] LogEntry : Log) {
            for (int j = 0; j < SignalCount; j++) {
                if (LogEntry[j]) {
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
