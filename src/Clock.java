public class Clock {

    static int MainTime;

    public Clock(){
        MainTime = 0;
    }

    public int getTime(){
        return MainTime;
    }

    public void advance(){
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
