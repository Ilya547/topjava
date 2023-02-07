package ru.javawebinar.topjava;

import ru.javawebinar.topjava.storage.Storage;

/**
 * @see <a href="http://topjava.herokuapp.com">Demo application</a>
 * @see <a href="https://github.com/JavaOPs/topjava">Initial project</a>
 */
public class Main {
    public static void main(String[] args) {
        Storage storage = Config.get().getStorage();
        System.out.println(storage.size());
    }
}
