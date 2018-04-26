public class Event {

    Signal Input;
    int Time;
    boolean newValue;
    static EventQueue currentEq;

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
