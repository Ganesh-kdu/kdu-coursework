package com.ganesh.q1andq2;

import com.ganesh.LogMaster;

import java.util.concurrent.atomic.AtomicInteger;

public class MessageReceiver implements Runnable {
    private int id;
    private int count;
    private static AtomicInteger totalReceived = new AtomicInteger(0);
    private MessageQueue messageQueue;

    MessageReceiver(int id, MessageQueue messageQueue){
        this.id = id;
        this.count = 0;
        this.messageQueue = messageQueue;
    }
    public int getId(){
        return id;
    }

    @Override
    public void run() {
        while (totalReceived.getAndIncrement()<90){
            String message = messageQueue.get();
            if(message == null){
                totalReceived.getAndDecrement();
                continue;
            }
            LogMaster.print("{} || Received by receiver {}",message,id);
            this.count++;
        }
        LogMaster.print("Receiver {} ending after receiving {} messages",getId(),count);
    }
}
