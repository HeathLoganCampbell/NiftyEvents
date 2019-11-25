package games.bevs.niftyevents;

import games.bevs.niftyevents.exceptions.EventException;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class EventManager
{
    private HashMap<Class<? extends Event>, List<RegisterEntry>> eventListenerFunc = new HashMap();

    public void registerEventListeners(Object listeners)
    {

        for (Method method : listeners.getClass().getDeclaredMethods())
        {
            //Check if function has annotion
            Annotation annotation = method.getAnnotation(EventListener.class);
            if(annotation == null) continue;

            try {
                int args = method.getParameterCount();
                if (args == 0)
                    throw new EventException("Method arguments most contain event class");
                Class<? extends Event> eventClazz = (Class<? extends Event>) method.getParameterTypes()[0];
                //TODO: CHECK IF eventClazz IS A CHILD OF EVENT


                if (args > 1)
                    throw new EventException("Method arguments most contain only event class");

                List<RegisterEntry> listenerFuncs = eventListenerFunc.computeIfAbsent(eventClazz, k -> new ArrayList<RegisterEntry>());
                listenerFuncs.add(new RegisterEntry(listeners, method));
                this.eventListenerFunc.put(eventClazz, listenerFuncs);
            }
            catch (EventException e)
            {
                e.printStackTrace();
            }
        }
    }


    /**
     * Trigger all events and their parents
     * Example
     * CatEvent extends PetEvent Extends Event
     * would trigger CatEvent then, PetEvent and then evnet
     * @param event
     */
    public void triggerEvents(Event event)
    {
        Class eventClazz = event.getClass();
        while (eventClazz != null) {
            this.triggerEventsOneByOne(eventClazz, event);
            eventClazz = eventClazz.getSuperclass();
        }
    }

    /**
     * Will trigger the functions with {@code EventListener} on them
     * with the same paramter as the eventClazz
     * @param eventClazz
     * @param event
     */
    private void triggerEventsOneByOne(Class<? extends Event> eventClazz, Event event)
    {
        List<RegisterEntry> listeners = this.eventListenerFunc.get(eventClazz);
        if(listeners == null) return;

        for (RegisterEntry methodListener : listeners)
        {
            methodListener.triggerListener(event);
        }
    }
}
