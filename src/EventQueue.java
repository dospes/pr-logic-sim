import java.util.ArrayList;

public class EventQueue {

    private ArrayList<Event> EventList;
    private static Clock c;


    public EventQueue(){
        EventList = new ArrayList<>();
        c = new Clock();
        new Logger();
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

    public ArrayList<Event> getEventList(){
        return EventList;
    }

    public boolean hasMore(){
        return EventList.size() > 0;
    }

    public Event getFirst(){
        return EventList.get(0);
    }

    public Clock getC(){
        return c;
    }

}
