package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    private ServerSocket serverSocket;
    private GameManager gameManager;
    private boolean isRunning;

    public GameServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            isRunning = true;
            gameManager = new GameManager();
            System.out.println("Server started on port " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        while (isRunning) {
            try {
                System.out.println("Waiting for clients...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                ClientThread clientThread = new ClientThread(clientSocket, gameManager);
                new Thread(clientThread).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        isRunning = false;
        try {
            serverSocket.close();
            System.out.println("Server stopped");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int port = 12345;
        GameServer server = new GameServer(port);
        server.start();
    }
}
