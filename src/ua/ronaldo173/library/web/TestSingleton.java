package ua.ronaldo173.library.web;

import java.io.*;

/**
 * Created by Developer on 08.04.2016.
 */
public class TestSingleton {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MySingleton instance = MySingleton.getInstance();

        String fileName = "test.ser";
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream(fileName));
        out.writeObject(instance);
        out.close();

        ObjectInput input = new ObjectInputStream(new FileInputStream(fileName));
        MySingleton instance2 = (MySingleton) input.readObject();
        input.close();

        System.out.println(instance);
        System.out.println(instance2);
        System.out.println(instance == instance2);

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

 class test{
    private test(){}
}