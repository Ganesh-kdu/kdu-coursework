package com.ganesh.q1;

import com.ganesh.LogMaster;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageQueue implements Runnable{
    private Queue<String> messages;
    public MessageQueue() {
        this.messages = new ConcurrentLinkedQueue<>();
    }
    public String get(){
        String message = messages.peek();
        messages.poll();
        return message;
    }

    public void sendMessages(String message){
        messages.add(message);
    }
    @Override
    public void run() {
        LogMaster.print("Message Queue Closed");
        while(!Thread.currentThread().isInterrupted()){
            //do stuff
            try{
                Thread.sleep(500);
            }catch(InterruptedException e){
                Thread.currentThread().interrupt(); //propagate interrupt
            }
        }
        LogMaster.print("Message Queue Closed");
    }
}
