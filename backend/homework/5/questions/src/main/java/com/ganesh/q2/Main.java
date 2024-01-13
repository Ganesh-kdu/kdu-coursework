package com.ganesh.q2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
    public static void main(String [] args) throws InterruptedException {
        MessageQueue messageQueue = new MessageQueue();
        Thread queue = new Thread(messageQueue);
        queue.start();

        ExecutorService senders = Executors.newFixedThreadPool(3);
        ExecutorService receivers = Executors.newFixedThreadPool(3);


        for (int i=0;i<3;i++){
            MessageSender sender = new MessageSender(i+1,messageQueue);
            MessageReceiver receiver = new MessageReceiver(i+1,messageQueue);
            senders.execute(sender);
            receivers.execute(receiver);
        }
        senders.shutdown();
        receivers.shutdown();
        queue.interrupt();
        queue.join();

    }
}
