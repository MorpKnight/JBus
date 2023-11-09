package com.GiovanChristoffelSihombingJBusRS;

import java.util.HashMap;

public class Serializable {
    public final int id;
    private static final HashMap<Class<?>, Integer> mapCounter = new HashMap<Class<?>, Integer>();

    protected Serializable() {
        Integer counter = mapCounter.get(getClass());
        counter = counter == null? 0 : counter + 1;
        mapCounter.put(getClass(), counter);
        this.id = counter;
    }

    public static <T> Integer getLastAssignedId(Class<T> cls) {
        return mapCounter.get(cls);
    }

    public static <T> Integer setLastAssignedId(Class<T> cls, int id) {
        return mapCounter.put(cls, id);
    }

    public int compareTo(Serializable temp){
        return ((Integer)this.id).compareTo(temp.id);
    }

    public boolean equals(Serializable temp){
        return temp.id == this.id;
    }

    public boolean equals(Object object){
        return object instanceof Serializable && ((Serializable) object).id == this.id;
    }
}