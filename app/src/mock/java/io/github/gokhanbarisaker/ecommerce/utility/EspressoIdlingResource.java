package io.github.gokhanbarisaker.ecommerce.utility;

import android.support.test.espresso.contrib.CountingIdlingResource;
import android.util.Log;

/**
 * Created by gokhanbarisaker on 12/3/15.
 * <p/>
 * Wrapper utility for Espresso idling resource API.
 * Use for synchronizing test cases with asynchronous execution.
 */
public class EspressoIdlingResource {
    private static final CountingIdlingResource idlingResource = new CountingIdlingResource("global");

    public static void increment() {
        idlingResource.increment();
    }

    public static void decrement() {
        idlingResource.decrement();
    }

    public static void lockFor(final long milliseconds) {
        Log.e("EIR", "Locking for " + milliseconds + "ms");
        idlingResource.increment();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    try {
                        wait(milliseconds);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        idlingResource.decrement();
                        Log.e("EIR", "Unlocked");
                    }
                }
            }
        }).run();
    }
}
