package ua.ronaldo173.library.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Developer on 08.04.2016.
 */
public class TestSingleton {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

       WeakHashMap<Integer, String>  weakHashMap = new WeakHashMap<>();

        Integer key2 = new Integer(2);

        weakHashMap.put(1, "first");
        weakHashMap.put(key2, "second");

        System.out.println(weakHashMap);

        key2 = null;
        System.gc();
        System.out.println(weakHashMap);
    }

}

class MySingleton implements Serializable {
    private static final long serialVersionUID = -7604766932017737115L;

    private MySingleton() {
    }

    public static MySingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }

    protected Object readResolve() {
        return getInstance();
    }

    private static class SingletonHelper {
        private static final MySingleton INSTANCE = new MySingleton();
    }

}

class Person1 {
    private final Date birthdate = new Date();

    public boolean isInDiapasonBad() {
        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

        gmtCal.set(1950, Calendar.JANUARY, 0,0,0);
        Date start = gmtCal.getTime();
        gmtCal.set(2019,Calendar.JANUARY, 0,0,0);
        Date end = gmtCal.getTime();

        return birthdate.compareTo(start) >= 0 &&
                birthdate.compareTo(end) < 0;
    }
}

class Person2 {
    private static final Date start;
    private static final Date end;

    static {
        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1950, Calendar.JANUARY, 1, 0, 0);
        start = gmtCal.getTime();
        gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
        end = gmtCal.getTime();

    }

    private final Date birthdate = new Date(1955, 1, 1);

    public boolean isInDiapasonGood() {
        return birthdate.compareTo(start) >= 0 &&
                birthdate.compareTo(end) < 0;
    }
}