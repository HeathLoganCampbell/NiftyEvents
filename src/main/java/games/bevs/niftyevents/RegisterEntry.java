package games.bevs.niftyevents;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RegisterEntry
{
    private Object obj;
    private Method method;

    public RegisterEntry(Object obj, Method method)
    {
        this.obj = obj;
        this.method = method;
    }

    public void triggerListener(Event event)
    {
        try {
            this.method.invoke(obj, event);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
