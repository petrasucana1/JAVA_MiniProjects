package org.example;

import java.io.*;
import java.net.Socket;

public class ClientThread implements Runnable {
    private Socket socket;
    private GameManager gameManager;
    private BufferedReader in;
    private PrintWriter out;

    public ClientThread(Socket socket, GameManager gameManager) {
        this.socket = socket;
        this.gameManager = gameManager;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String request;
            while ((request = in.readLine()) != null) {
                if (request.equals("stop")) {
                    out.println("Server stopped");
                    break;
                } else {
                    String response = gameManager.processRequest(request);
                    out.println(response);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
