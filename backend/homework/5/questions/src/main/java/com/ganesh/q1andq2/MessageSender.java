package com.ganesh.q1andq2;

import com.ganesh.LogMaster;
public class MessageSender implements Runnable{
    private int id;
    MessageQueue messageQueue;

    MessageSender(int id, MessageQueue messageQueue){
        this.id = id;
        this.messageQueue = messageQueue;
    }
    public int getId(){
        return id;
    }

    @Override
    public void run() {
        for(int i=0; i<30;i++){
            messageQueue.sendMessages(String.format("Message %d from %d",i,getId()));
        }
        LogMaster.print("Sender {} ending after sending 30 messages",getId());
    }
}
