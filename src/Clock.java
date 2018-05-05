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

    private void advance(){
        Log.addLogEntry();
        MainTime++;
    }

    public void clockMain(Event e){
        int EventTime = e.getTime();
        while (EventTime > MainTime) {
            this.advance();
        }
        Signal s = e.getSignal();
        boolean v = e.getNewValue();
        s.setValue(v);
    }
}
