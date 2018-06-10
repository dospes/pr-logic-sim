import java.util.ArrayList;
import java.util.Collections;

public class EventQueue{

    private ArrayList<Event> EventList;
    private static Clock c;
    private static boolean setupFlag;


    public EventQueue(){
        EventList = new ArrayList<>();
        c = new Clock();
        new Logger();
        setupFlag = false;
    }

    public void addEvent(Event e){
        EventList.add(e);
    }

    public void removeFirst(){
        EventList.remove(0);
    }

    public void addDelayedEvent(int i, Event e) {
        EventList.add(i, e);
    }

    public void addDelayedEvent(Event e){
        EventList.add(e);
    }

    public ArrayList<Event> getEventList(){
        return EventList;
    }

    private void sortByTime(){
        Collections.sort(EventList);
    }

    public boolean hasMore(){
        if (!setupFlag){
            sortByTime();
        }
        return EventList.size() > 0;
    }

    public Event getFirst(){
        return EventList.get(0);
    }

    public Clock getC(){
        return c;
    }

}
