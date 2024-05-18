package org.example;

import java.io.*;
import java.net.Socket;

public class GameClient {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private BufferedReader console;

    public GameClient(String host, int port) throws IOException {
        socket = new Socket(host, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        console = new BufferedReader(new InputStreamReader(System.in));
    }

    public void start() {
        String userInput;
        try {
            while ((userInput = console.readLine()) != null) {
                if (userInput.equals("exit")) {
                    out.println(userInput);
                    break;
                }

                // Process specific commands to assist the user
                if (userInput.startsWith("create") || userInput.startsWith("join") || userInput.startsWith("placeShip") || userInput.startsWith("move")) {
                    out.println(userInput);
                    String response = in.readLine();
                    System.out.println("Server response: " + response);
                } else {
                    System.out.println("Invalid command. Use: create, join <gameId>, placeShip <gameId> <x> <y>, move <gameId> <x> <y>, exit");
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

    public static void main(String[] args) throws IOException {
        GameClient client = new GameClient("localhost", 12345);
        client.start();
    }
}
