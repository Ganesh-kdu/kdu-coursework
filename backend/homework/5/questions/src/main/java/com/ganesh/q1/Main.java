package com.ganesh.q1;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String [] args) throws InterruptedException {
        AtomicInteger endSignal = new AtomicInteger(0);

        MessageQueue messageQueue = new MessageQueue();
        Thread queue = new Thread(messageQueue);
        queue.start();
        Thread[] senders = new Thread[3];
        Thread[] receivers = new Thread[3];
        for (int i=0;i<3;i++){
            senders[i] = new Thread(new MessageSender(i+1,messageQueue));
            receivers[i] = new Thread(new MessageReceiver(i+1,messageQueue));
            senders[i].start();
            receivers[i].start();
        }
        for (int i=0;i<3;i++){
            senders[i].join();
        }
        for (int i=0;i<3;i++){
            senders[i].join();
        }
        queue.interrupt();
        queue.join();

    }
}
