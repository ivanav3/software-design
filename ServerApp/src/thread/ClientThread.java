/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import controller.Controller;
import domain.Drzava;
import domain.Kamion;
import domain.KomunalniRadnik;
import domain.Mesto;
import domain.Termin;
import domain.User;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.Server;

/**
 *
 * @author HP
 */
public class ClientThread extends Thread {

    private Socket clientSocket;
    private Sender sender;
    private Receiver receiver;
    private Controller c;
    private User loginUser;
    private Server server;

    private KomunalniRadnik kr;
    private Kamion ka;
    private Termin t;
    private Mesto m;
    private Drzava d;
    
    private PrintWriter out;

    public ClientThread(Socket clientSocket, Server server) throws IOException {
        this.clientSocket = clientSocket;
        sender = new Sender(clientSocket);
        receiver = new Receiver(clientSocket);
        c = new Controller();
        this.server = server;
//        DateFormat fm=new SimpleDateFormat("yyyyMMddHHmmss");
//        Date d=new Date();
//        out=new PrintWriter(new FileWriter(fm.format(d)+".txt"), true);
        
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            System.out.println("U niti je trenutno");
            if (server.isInterrupted()) {

            }
            try {

                Request request = (Request) receiver.receive();
                Response response = new Response();
//                out.print(request.getOperation());
                try {
                    switch (request.getOperation()) {
                        case LOGIN:
                            User user = (User) request.getArgument();
                            System.out.println("U login u switch-u");

                            if (!server.notLogin(user)) {
                                throw new Exception("User je vec prijavljen.");
                            }
                            //za slucaj da mora odredjeni broj klijenata
//                            if (server.getClients().size() > server.getMax_broj_klijenata()) {
//                                for (ClientThread ct : server.getClients()) {
//                                    System.out.println(ct.getLoginUser());
//                                }
//
//                                throw new Exception("Previse klijenata.");
//                            }
                            response.setResult(c.login(user));
                            loginUser = user;
                            
                            //za table model
                            server.getTm().getUsers().add(c.login(loginUser));
                            server.getTm().fireTableDataChanged();

                            break;

                        case LOGOUT:
                            interrupt();
                            server.getClients().remove(this);
                            //za table model
                            server.getTm().getUsers().remove(c.login(loginUser));
                            server.getTm().fireTableDataChanged();
//                            out.close();
                            break;
                        case CHECK_SERVER:
                            if (server.isInterrupted() || server.getServerSocket().isClosed()) {
                                interrupt();
                                server.getClients().remove(this);
                                response.setResult("Ugasen server");
                            } else {
                                response.setResult("Jos uvek radi server");
                            }
                            break;

                        case GET_ALL_MESTA:
                            m = new Mesto();
                            System.out.println("Ovde je argument " + request.getArgument());
                            m.setFilter(request.getArgument());
                            response.setResult(c.getAllMesta(m));
                            break;
                        case ADD_KR:
                            kr = (KomunalniRadnik) request.getArgument();
                            c.createKR(kr);
                            response.setResult(kr);
                            break;
                        case GET_ALL_KR:
                            //moze da argument bude null ili naziv
                            kr = new KomunalniRadnik();
                            System.out.println("Ovde je argument " + request.getArgument());
                            kr.setFilter(request.getArgument());
                            response.setResult(c.getAllKR(kr));
                            break;
                        case GET_KR:
                            kr = (KomunalniRadnik) request.getArgument();
                            response.setResult(c.getKR(kr));
                            break;
                        case GET_ALL_VOZACI:
                            response.setResult(c.getAllVozaci());
                            break;
                        case ADD_KA:
                            ka = (Kamion) request.getArgument();
                            c.createKa(ka);
                            response.setResult(ka);
                            break;
                        case EDIT_KA:
                            ka = (Kamion) request.getArgument();
                            c.editKa(ka);
                            response.setResult(ka);
                            break;
                        case GET_ALL_KA:

                            ka = new Kamion();

                            ka.setFilter(request.getArgument());
                            response.setResult(c.getAllKa(ka));
                            System.out.println("Trenutno je u klijent threadu za kamion");
                            break;
                        case GET_KA:
                            ka = (Kamion) request.getArgument();
                            response.setResult(c.getKa(ka));
                            break;
                        case DELETE_KA:
                            ka = (Kamion) request.getArgument();
                            response.setResult(c.deleteKa(ka));
                            break;
                        case ADD_T:
                            t = (Termin) request.getArgument();
                            c.createTremin(t);
                            response.setResult(t);
                            break;
                        case GET_ALL_T:
                            //moze da argument bude null ili datum

                            t = new Termin();

                            t.setFilter(request.getArgument());
                            response.setResult(c.getAllTermini(t));
                            break;
                        case GET_ALL_D:
                            d = new Drzava();
                            d.setFilter(request.getArgument());
                            System.out.println("Doslo je do klijent threada");
                            response.setResult(c.getAllDrzave(d));
                            break;
                        case ADD_M:
                            m = (Mesto) request.getArgument();
                            c.createMesto(m);
                            response.setResult(m);
                            break;
                        case EDIT_M:
                            m = (Mesto) request.getArgument();
                            c.editMesto(m);
                            response.setResult(m);
                            break;
                        case GET_M:
                            m = (Mesto) request.getArgument();
                            response.setResult(c.getMesto(m));
                            break;
                        case DELETE_M:
                            m = (Mesto) request.getArgument();
                            response.setResult(c.deleteMesto(m));
                            break;

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    response.setException(e);
                }
                sender.send(response);
//                out.println(" "+response.getResult()+" ");
//                out.flush();

            } catch (Exception ex) {
                try {
                    ex.printStackTrace();

                    //da i kada iskljucimo aplikaciju klijenta se logoutuje
                    interrupt();
                    server.getClients().remove(this);
                    
                    //za table model-moze da prouzrokuje ovaj exception
                    server.getTm().getUsers().remove(c.login(loginUser));
                    server.getTm().fireTableDataChanged();
                } catch (Exception ex1) {
                    ex1.printStackTrace();
                }

            }
        }

    }

    public User getLoginUser() {
        return loginUser;
    }

}
