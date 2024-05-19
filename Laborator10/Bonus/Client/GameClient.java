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

                if (userInput.startsWith("create") || userInput.startsWith("join") || userInput.startsWith("placeShip") || userInput.startsWith("move") || userInput.startsWith("register") || userInput.startsWith("results") || userInput.startsWith("schedule") || userInput.startsWith("winning_sequence")) {
                    out.println(userInput);

                    if (userInput.startsWith("schedule") || userInput.startsWith("results")) {
                        StringBuilder responseBuilder = new StringBuilder();
                        String responseLine;
                        while ((responseLine = in.readLine()) != null && !responseLine.isEmpty()) {
                            responseBuilder.append(responseLine).append("\n");
                        }
                        System.out.println("Server response: " + responseBuilder.toString());
                    } else {
                        // Single line response for other commands
                        String response = in.readLine();
                        System.out.println("Server response: " + response);
                    }
                } else {
                    System.out.println("Invalid command.");
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
