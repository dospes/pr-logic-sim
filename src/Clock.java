public class Clock {

    private static int MainTime;
    private static Logger Log;

    public Clock(){
        MainTime = 0;
    }

    public static void setLog(Logger log){
        Log = log;
    }

    public static int getTime(){
        return MainTime;
    }

    /*
     * Vor jedem Zeitschritt wird das Log aktualisiert.
     */

    private void advance(){
        Log.addLogEntry();
        MainTime++;
    }

    /*
     * Es werden Zeitschritte durchgeführt, bis das aktuelle Event ausgeführt werden muss.
     */

    public void clockMain(Event e){
        int EventTime = e.getTime();
        while (EventTime > MainTime) {
            this.advance();
        }
        Signal s = e.getSignal();
        s.setValue(e.getNewValue());
    }
}
