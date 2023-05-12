package com.example.glukometr.data_layer;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.List;

public class Client {
    Socket socket;
    private BufferedReader in;
    private ObjectOutputStream out;
    public void connectToServer(){
        Thread thread = new Thread(() -> {
            try {
                socket = new Socket("192.168.56.1", 7056);
            }catch (Exception e){
                Log.i("Error","Socket error");
            }
        });
        thread.start();
    }
    public void post(List<Double> measurings){
        try {
            try {
                out = new ObjectOutputStream(socket.getOutputStream());
                out.writeInt(measurings.size());
                out.flush();
                for (double curMeasuring : measurings) {
                    out.writeDouble(curMeasuring);
                    out.flush();
                }
            } catch (IOException e) {
                return;
            } finally {
                out.close();
            }
        }catch (IOException e){
            return;
        }
    }
    public String getPath() {
        String path;
        try {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                path = in.readLine();
            } catch (IOException e) {
                path = "error";
            } finally {
                in.close();
            }
        } catch (IOException e) {
            path = "error";
        }
        return path;
    }
}
