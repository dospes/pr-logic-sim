import java.util.ArrayList;

public class Event {

    private Signal Input;
    private int Time;
    private boolean newValue;
    private static EventQueue currentEq;
    private static Clock clock;

    /*
     * Klasse ist overloaded um das Einfügen von verzögerten Events in die Queue zu ermöglichen
     */

    public Event(Signal s, int t, boolean v){
        Input = s;
        Time = t;
        newValue = v;
        currentEq.addEvent(this);
    }

    /*
     * Input und Signal-Wert werden vom Nand-Gatter übernommen, die Zeit des Events wird aus dem Delay und der jetzt-Zeit
     * ermittelt. Die Liste der Events wird geladen um das neu erstellte Event, wenn es
     * früher oder zum gleichen Zeitpunkt ausgeführt werden muss, wie ein Event in der Liste, es genau vor diesem
     * Event einzufügen.
     */

    public Event(Signal s, int t, boolean v, boolean temp) {
        ArrayList<Event> currentEventList = currentEq.getEventList();
        Input = s;
        Time = t + clock.getTime();
        newValue = v;
        for (int i = 0; i < currentEventList.size(); i++) {
            Event e = currentEventList.get(i);
            if (Time <= e.Time) {
                currentEq.addDelayedEvent(i, this);
                break;
            }
        }
    }

    public static void setEventQueue(EventQueue eq){
        currentEq = eq;
        clock = eq.getC();
    }

    public Signal getSignal(){
        return Input;
    }
    public int getTime() {
        return Time;
    }

    public boolean getNewValue() {
        return newValue;
    }


    public void propagate(){
        currentEq.removeFirst();
        clock.clockMain(this);
    }
}
