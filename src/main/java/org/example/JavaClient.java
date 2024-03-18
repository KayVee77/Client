package org.example;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class JavaClient {

        private Socket clientSocket;

        public void startConnection(String ip, int port) throws IOException {
            clientSocket = new Socket(ip, port);
            System.out.println("Connected to server.");
            receiveFile("Movie.xml");
        }

        public void stopConnection() {
            try {
                if (clientSocket != null && !clientSocket.isClosed()) {
                    clientSocket.close();
                }
            } catch (IOException e) {
                System.out.println("Exception occurred while closing resources: " + e.getMessage());
            }
        }

        public void receiveFile(String filePath) {
            try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
                 InputStream socketInputStream = clientSocket.getInputStream()) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = socketInputStream.read(buffer)) != -1) {
                    bos.write(buffer, 0, bytesRead);
                }
                System.out.println("File received.");
            } catch (IOException e) {
                System.out.println("Exception occurred: " + e.getMessage());
            } finally {
                stopConnection();
            }
        }

//       public static void main(String[] args) {
//            JavaClient client = new JavaClient();
//            try {
//                client.startConnection("localhost", 7777);
//            } catch (IOException e) {
//                System.out.println("Exception occurred: " + e.getMessage());
//            }
//        }
    }

