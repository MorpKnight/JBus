package GiovanChristoffelSihombingJBusRS;

import java.util.HashMap;

public class Serializable {
    public final int id;
    private static final HashMap<Class<?>, Integer> mapCounter = new HashMap<Class<?>, Integer>();

    protected Serializable() {
//        Integer counter = mapCounter.get(getClass());
//        if (counter == null) {
//            counter = 0;
//        }
//        mapCounter.put(getClass(), counter + 1);
//        this.id = counter;

        Class<?> classs = getClass();
        Integer counter = mapCounter.get(classs);
        if(counter == null){
            counter = 0;
            mapCounter.put(classs, counter);
        } else {
            mapCounter.put(classs, counter + 1);
        }

        this.id = counter;
    }

    public static <T> Integer getLastAssignedId(Class<T> cls) {
        return mapCounter.get(cls);
    }

    public static <T> Integer setLastAssignedId(Class<T> cls, int id) {
        return mapCounter.put(cls, id);
    }

    public boolean equals(Serializable obj) {
        return obj != null && obj.getClass() == getClass() && obj.id == id;
    }

    public boolean compareTo(Serializable obj) {
        return obj != null && obj.getClass() == getClass() && obj.id == id;
    }

    public boolean equals(Object obj) {
        return obj != null && obj.getClass() == getClass() && ((Serializable) obj).id == id;
    }
}