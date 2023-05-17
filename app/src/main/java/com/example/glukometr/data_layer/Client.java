package com.example.glukometr.data_layer;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.List;

public class Client {
    Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    public boolean connectToServer(){
        try {
            socket = new Socket("192.168.5.105", 7056);
            return true;
        }catch (Exception e){
            Log.d("Error","error connect to server");
            return false;
        }
    }
    public void post(List<Double> measurings){
        try {
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            int size = measurings.size();
            out.write(String.valueOf(size));
            out.flush();
            for (double curMeasuring : measurings) {
                out.write(new DecimalFormat("#00.0").format(curMeasuring));
                out.flush();
            }

        } catch (IOException e) {
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
                path = "error while getting path";
            } finally {
                in.close();
            }
        } catch (IOException e) {
            path = "error while getting path";
        }
        return path;
    }
}
