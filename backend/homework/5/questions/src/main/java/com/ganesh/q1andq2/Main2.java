package com.ganesh.q1andq2;

import com.ganesh.q2.MessageQueue;
import com.ganesh.q2.MessageReceiver;
import com.ganesh.q2.MessageSender;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main2 {
    public static void main(String [] args) throws InterruptedException {
        com.ganesh.q2.MessageQueue messageQueue = new MessageQueue();
        Thread queue = new Thread(messageQueue);
        queue.start();

        ExecutorService senders = Executors.newFixedThreadPool(3);
        ExecutorService receivers = Executors.newFixedThreadPool(3);


        for (int i=0;i<3;i++){
            com.ganesh.q2.MessageSender sender = new MessageSender(i+1,messageQueue);
            com.ganesh.q2.MessageReceiver receiver = new MessageReceiver(i+1,messageQueue);
            senders.execute(sender);
            receivers.execute(receiver);
        }
        senders.shutdown();
        receivers.shutdown();
        queue.interrupt();
        queue.join();

    }
}
