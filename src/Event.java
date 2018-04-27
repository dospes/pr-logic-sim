public class Event {

    private Signal Input;
    private int Time;
    private boolean newValue;
    private static EventQueue currentEq;

    public Event(Signal s, int t, boolean v){
        Input = s;
        Time = t;
        newValue = v;
        currentEq.addEvent(this);
    }

    public static void setEventQueue(EventQueue eq){
        currentEq = eq;
    }

    public void propagate(){
        Input.setValue(newValue);
    }
}
