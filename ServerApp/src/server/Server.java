/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import communication.Receiver;
import communication.Sender;
import components.table.model.UserTableModel;
import controller.Controller;
import domain.User;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.TableModel;
import thread.ClientThread;

/**
 *
 * @author HP
 */
public class Server extends Thread {

    private Sender sender;
    private Receiver receiver;
    private Controller controller;
    private List<ClientThread> clients;
//    private Long max_broj_klijenata;
    private ServerSocket serverSocket;
    private UserTableModel tm;

    public Server(TableModel tm) throws IOException {
        this.controller = new Controller();
        clients = new ArrayList<>();
//        Properties properties = new Properties();
//        try ( FileInputStream input = new FileInputStream("app.config")) {
//            properties.load(input);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        max_broj_klijenata = Long.parseLong(properties.getProperty("max_broj_klijenata"));
//        System.out.println("Procitao iz fajla " + max_broj_klijenata);
        this.tm=(UserTableModel) tm;
        serverSocket = new ServerSocket(9000);
    }

    public void startServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(9000);

            while (true) {

                System.out.println("Cekam klijente...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected!");
                ClientThread clientThread = new ClientThread(clientSocket, this);
                clientThread.start();
                clients.add(clientThread);

                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public UserTableModel getTm() {
        return tm;
    }

    
    @Override
    public void run() {
        try {

            while (!isInterrupted()) {
                
                
                System.out.println("Cekam klijente...");
                Socket clientSocket = serverSocket.accept();

                System.out.println("Cekam klijente...");
                System.out.println("Connected!");
                ClientThread clientThread = new ClientThread(clientSocket, this);
                clientThread.start();
                clients.add(clientThread);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

    public boolean notLogin(User user) {
        for (ClientThread client : clients) {
            if (user.equals(client.getLoginUser())) {
                return false;
            }
        }
        return true;
    }

    public List<ClientThread> getClients() {
        return clients;
    }
//
//    public Long getMax_broj_klijenata() {
//        return max_broj_klijenata;
//    }

    public void stopServer() {
        
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        interrupt();
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
    
    
}
