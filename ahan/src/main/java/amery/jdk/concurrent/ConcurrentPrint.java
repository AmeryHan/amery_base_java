package amery.jdk.concurrent;

import java.util.concurrent.RecursiveAction;

class ConcurrentPrint extends RecursiveAction {
    protected void compute() {
    	/*
        CyclicBarrier b = new CyclicBarrier() {
            protected boolean terminate(int cycle, int registeredParties) {
                System.out.println("Cycle is " + cycle + ";"
                        + registeredParties + " parties");
                return cycle >= 10;
            }
        };
        int n = 3;

        CyclicAction[] actions = new CyclicAction[n];
        for (int i = 0; i < n; ++i) {
            final int index = i;
            actions[i] = new CyclicAction(b) {
                protected void compute() {
                    System.out.println("I'm working " + getCycle() + " "
                            + index);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
        }
        for (int i = 0; i < n; ++i)
            actions[i].fork();
        for (int i = 0; i < n; ++i)
            actions[i].join();*/
    }
}