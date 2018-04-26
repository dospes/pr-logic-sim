import java.util.ArrayList;

public class EventQueue {

    ArrayList<Event> EventList;

    public EventQueue(){
        EventList = new ArrayList<>();
    }

    public void addEvent(Event e){
        EventList.add(e);
    }

    public boolean hasMore(){
        return EventList.size() > 0;
    }

    public Event getFirst(){
        return EventList.get(0);
    }

}
