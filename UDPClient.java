/*
    Team 6 - John Ta - Chau Trieu
    Professor Luti
    15 November 2019
    Assignment 3: UDP Communication
 */

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class UDPClient
{
    public static void main(String args[])
    {
        // for user input
        Scanner scanner = new Scanner(System.in);

        if (args.length !=2 )
        {
            System.out.println ("Please try java UDPClient [server IP address] [port number] ");

        }
        else
        {
            // args give message contents and server host name
            DatagramSocket aSocket = null;

            try
            {
                // preserve a free local port
                aSocket = new DatagramSocket();

                // declare a IP address to send to server
                InetAddress IPAddress = InetAddress.getByName(args[0]);
                // declare a port number
                int portNumber = Integer.parseInt(args[1]);

                System.out.println("Running client...\n\tIP Address: " + IPAddress + "\n\tPort: " + portNumber + "\n" );

                // run until user terminates program
                while (true)
                {
                    // promt for user input the message.
                    System.out.print("Please enter text message : ");

                    // storing string message from user
                    String message = scanner.nextLine();

                    // convert the string into sequence of bytes, then store it in message array
                    byte [] m = message.getBytes();

                    //Constructs a datagram packet for sending packets of  length of array m with port number on the IPaddress.
                    DatagramPacket request = new DatagramPacket(m, m.length, IPAddress, portNumber);

                    // send datagram packet from aSocket.
                    aSocket.send(request);

                    // buffer array is for holding the datagram.
                    byte[] buffer = new byte[1000];

                    // constructs a reply with datagram packet type to receiving a packets with given length.
                    DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

                    // Received datagram packet from aSocket.
                    aSocket.receive(reply);
                    //
                    System.out.println("Reply: " + new String(reply.getData()) + "\n");

                }


            }
            catch (SocketException e)
            {System.out.println("Socket: " + e.getMessage());}
            catch (IOException e)
            {System.out.println("IO: " + e.getMessage());}
            finally
            {
                if(aSocket != null)
                {
                    aSocket.close();

                }
            }
        }
    }
}