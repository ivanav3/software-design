/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.controller;

import communication.Operation;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import domain.Drzava;
import domain.Kamion;
import domain.KomunalniRadnik;
import domain.Mesto;
import domain.Termin;
import domain.User;
import domain.Vozac;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author HP
 */
public class Controller {

    Socket socket;
    private static Controller instance;
    Sender sender;
    Receiver receiver;

    private Controller() throws IOException {
        socket = new Socket("127.0.0.1", 9000);
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    public static Controller getInstance() throws Exception {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public User login(User user) throws Exception {
        Request request = new Request(Operation.LOGIN, user);
        System.out.println("Na strani klijenta kontroler " + request.getArgument());
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (User) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public void logout(User user) throws Exception {
        Request request = new Request(Operation.LOGOUT, user);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {

        } else {
            throw response.getException();
        }
    }

    public Boolean checkServerShutDown() throws Exception {
        try {
            Request request = new Request(Operation.CHECK_SERVER, null);
            sender.send(request);
            Response response = (Response) receiver.receive();
            System.out.println("Proverava server");
            return (response.getResult() != null && response.getResult().equals("Ugasen server"));
        } catch (Exception e) {

            return true;
        }

    }

    //kreiranje
    public void createKR(KomunalniRadnik kr) throws Exception {
        Request request = new Request(Operation.ADD_KR, kr);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            kr.setKomunalniRadnikID(((KomunalniRadnik) response.getResult()).getKomunalniRadnikID());
        } else {
            throw response.getException();
        }
    }

    public void createKa(Kamion ka) throws Exception {
        Request request = new Request(Operation.ADD_KA, ka);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            ka.setKamionID(((Kamion) response.getResult()).getKamionID());
        } else {
            throw response.getException();
        }
    }

    public void createTermin(Termin t) throws Exception {
        Request request = new Request(Operation.ADD_T, t);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {

        } else {
            throw response.getException();
        }
    }

    public void createMesto(Mesto m) throws Exception {
        Request request = new Request(Operation.ADD_M, m);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {

        } else {
            throw response.getException();
        }
    }

    //editovanje
    

    public void editKa(Kamion ka) throws Exception {
        Request request = new Request(Operation.EDIT_KA, ka);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {

        } else {
            throw response.getException();
        }
    }

    public void editMesto(Mesto m) throws Exception {
        Request request = new Request(Operation.EDIT_M, m);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {

        } else {
            throw response.getException();
        }
    }

    //ucitavanje pojedinacnih objekata
    public KomunalniRadnik getKR(KomunalniRadnik kr) throws Exception {
        Request request = new Request(Operation.GET_KR, kr);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
            return (KomunalniRadnik) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public Kamion getKa(Kamion ka) throws Exception {
        Request request = new Request(Operation.GET_KA, ka);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
            return (Kamion) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public Mesto getMesto(Mesto m) throws Exception {
        Request request = new Request(Operation.GET_M, m);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
            return (Mesto) response.getResult();
        } else {
            throw response.getException();
        }
    }

    //ucitavanje lista
    public List<Mesto> getAllMesta() throws Exception {
        Request request = new Request(Operation.GET_ALL_MESTA, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Mesto>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<KomunalniRadnik> getAllKomunalniRandnici() throws Exception {
        Request request = new Request(Operation.GET_ALL_KR, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<KomunalniRadnik>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<Vozac> getAllVozaci() throws Exception {
        Request request = new Request(Operation.GET_ALL_VOZACI, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Vozac>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<Kamion> getAllKamioni() throws Exception {
        Request request = new Request(Operation.GET_ALL_KA, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Kamion>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<Termin> getAllTermini() throws Exception {
        Request request = new Request(Operation.GET_ALL_T, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Termin>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<Drzava> getAllDrzave() throws Exception {
        Request request = new Request(Operation.GET_ALL_D, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Drzava>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    //brisanje

    public Kamion deleteKa(Kamion ka) throws Exception {
        Request request = new Request(Operation.DELETE_KA, ka);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (Kamion) response.getResult();

        } else {
            throw response.getException();
        }
    }

    public Mesto deleteMesto(Mesto m) throws Exception {
        System.out.println("Kontroler klijenta "+m);
        Request request = new Request(Operation.DELETE_M, m);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (Mesto) response.getResult();

        } else {
            throw response.getException();
        }
    }

//pretraga
    public List<KomunalniRadnik> searchKR(String naziv) throws Exception {
        Request request = new Request(Operation.GET_ALL_KR, naziv);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<KomunalniRadnik>) response.getResult();
        } else {
            throw response.getException();
        }

    }

    public List<Kamion> searchKa(Long nosivost) throws Exception {
        Request request = new Request(Operation.GET_ALL_KA, nosivost);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Kamion>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<Termin> searchTermini(LocalDate datum) throws Exception {
        Request request = new Request(Operation.GET_ALL_T, datum);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Termin>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<Mesto> searchMesta(String naziv) throws Exception {
        Request request = new Request(Operation.GET_ALL_MESTA, naziv);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Mesto>) response.getResult();
        } else {
            throw response.getException();
        }
    }
    
//    public void update(List<Mesto> mesta,List<Mesto>obrisana){
//        Request request=new Request(Operation.UPDATE_ALL_MESTA,mesta);
//        sender.send(request);
//        Response response=(Response) receiver.receive();
//        if(response.getException()!=null){
//           throw response.getException();
//        }
//        request=new Request(Operation.REMOVE_ALL_MESTA,mesta);
//        sender.send(request);
//        response=(Response) receiver.receive();
//        if(response.getException()!=null){
//           throw response.getException();
//        }
//        
//    }

}
