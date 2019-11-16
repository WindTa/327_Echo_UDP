/*
    Team 6 - John Ta - Chau Trieu
    Professor Luti
    15 November 2019
    Assignment 3: UDP Communication
 */

import java.net.*;
import java.io.*;

/**
 * This function will take a command line argument as a port number, to receive and display a message from a client with the
 * corresponding port.
 */
public class UDPServer {
    public static void main(String args[]) {
        // Checks that the correct number of command line arguments was received
        if (args.length != 1) {
            System.out.println("Please try java UDPServer [port number]");
        } else {
            // Initializes a socket to send and receive data
            DatagramSocket aSocket = null;
            try {
                // Binds aSocket to the port specified by args[0]
                int serverPort = Integer.parseInt(args[0]);
                aSocket = new DatagramSocket(serverPort);

                System.out.println("Running server...\n\tPort: " + serverPort + "\n");
                while(true) {
                    // Constructs a buffer to specify a DatagramPacket's length.
                    byte[] buffer = new byte[1000];
                    DatagramPacket request = new DatagramPacket(buffer, buffer.length);

                    // aSocket receives a "request" from the corresponding port the client sent a message to
                    aSocket.receive(request);

                    System.out.println("Message received: " + new String(request.getData()) + "\n");

                    // Constructs and sends a DatagramPacket "reply" back through the port from which the message was received from
                    DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(), request.getPort());
                    aSocket.send(reply);
                }
            } catch (SocketException e) {
                System.out.println("Socket: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("IO: " + e.getMessage());
            } finally {
                if (aSocket != null) {
                    aSocket.close();
                }
            }
        }
    }
}