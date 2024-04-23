package com.example.socket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

public class Server {
    public static void main (String [] args){
        try {
            ServerSocket s = new ServerSocket(6500);
            System.out.println("Witing for client ...");
            Socket client = s.accept();
            Hashtable<String, String> message = new Hashtable<String, String>();
            message.put("test", "Hello i'm the client and here is my message ....");
            //System.out.print((message.get("test")));
            OutputStream out = client.getOutputStream();
            ObjectOutputStream oos =new ObjectOutputStream(out);
            oos.writeObject(message);
            client.close();


        }
        catch(IOException err){
            err.printStackTrace();
        }


    }
}
