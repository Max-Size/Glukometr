package com.example.glukometr;

import android.util.Log;
import java.net.Socket;

public class Client {
    public static void connectToServer(){
        Thread thread = new Thread(() -> {
            try {
                Socket socket = new Socket("192.168.56.1", 7056);
            }catch (Exception e){
                Log.i("Error","Socket error");
            }
        });
        thread.start();
    }
}
