import java.io.IOException;
import java.util.ArrayList;

public class Event implements Comparable<Event> {

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
        Time = t + Clock.getTime();
        newValue = v;
        boolean flag = false;
        for (int i = 0; i < currentEventList.size(); i++) {
            Event e = currentEventList.get(i);
            if (Time <= e.Time) {
                currentEq.addDelayedEvent(i, this);
                flag = true;
                break;
            }
        }
        if (!flag) {
            currentEq.addDelayedEvent(this);
        }
    }

    public int compareTo(final Event e){
        return Integer.compare(this.Time, e.Time);
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

    private ArrayList<Event> checkForMultiples(){
        ArrayList<Event> GatterOverlapEvents = new ArrayList<>();
        ArrayList<Event> currentEventList = currentEq.getEventList();
        for (int i = 0; i < currentEventList.size(); i++){
            if (this.getTime() == currentEventList.get(i).getTime()){
                for (int j = 0; j < this.getSignal().getPostSignal().size(); j++){
                    for (int k = 0; k < currentEventList.get(i).getSignal().getPostSignal().size(); k++){
                        if (this.getSignal().getPostSignal().get(j) == currentEventList.get(i).getSignal().getPostSignal().get(k)){
                            GatterOverlapEvents.add(currentEventList.get(i));
                        }
                    }
                }
            }
        }
        for (int i = 0; i < GatterOverlapEvents.size(); i++){
            if (this == GatterOverlapEvents.get(i)){
                GatterOverlapEvents.remove(i);
                break;
            }
        }
        return GatterOverlapEvents;
    }


    /*
     * Event wird aus der Queue entfernt damit es nicht wiederholt aufgerufen werden kann.
     * Die Ausführung des Events wird dann von der Clock übernommen.
     */

    public void propagate(){
        ArrayList<Event> GatterOverlapEvents = this.checkForMultiples();
        currentEq.removeFirst();
        clock.clockMain(this, GatterOverlapEvents);
        if (!currentEq.hasMore()) {
            Logger.addLogEntry();
            try {
                Logger.dumpToCSV();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("output.csv konnte nicht geschrieben werden.");
            }
        }
    }
}
