package com.example.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Hashtable;

public class Client {
    public static void main (String[] args){
        try {
            Socket socket = new Socket("localhost", 6500);
            InputStream in = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            final Hashtable<String, String> message = (Hashtable) ois.readObject();
            System.out.println(message.get("test"));


        } catch(IOException err) {
            err.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
