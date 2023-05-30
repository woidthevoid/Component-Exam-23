package dk.sdu.mmmi.cbse.common.events;

import dk.sdu.mmmi.cbse.common.data.Entity;
import java.io.Serializable;

/**
 *
 * @author Mads
 */
public class Event implements Serializable{
    private final Entity source;
    private EventType eventType;

    public Event(Entity source, EventType eventType) {
        this.source = source;
        this.eventType = eventType;
    }

    public Entity getSource() {
        return source;
    }

    public EventType getEventType() {
        return eventType;
    }
}
