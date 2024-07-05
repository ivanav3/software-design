/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Drzava;
import domain.Kamion;
import domain.KomunalniRadnik;
import domain.Mesto;
import domain.OpstiDomenskiObjekat;
import domain.Termin;
import domain.User;
import domain.Vozac;
import java.util.List;
import so.DeleteKa;
import so.DeleteMesto;
import so.GetAllDrzave;
import so.GetAllKR;
import so.GetAllKa;
import so.GetAllMesta;
import so.GetAllTermini;
import so.GetAllVozaci;
import so.GetKR;
import so.GetKa;
import so.GetMesto;
import so.LoginUser;
import so.SaveKR;
import so.SaveKa;
import so.SaveMesto;
import so.SaveTermin;

/**
 *
 * @author HP
 */
public class Controller {

    public User login(User user) throws Exception {
        LoginUser lu = new LoginUser();
        lu.execute(user);
        return lu.getUser();
    }

    //kreiranje
    public void createKR(KomunalniRadnik kr) throws Exception {
        SaveKR skr = new SaveKR();
        skr.execute(kr);
    }

    public void createKa(Kamion ka) throws Exception {
        SaveKa ska = new SaveKa();
        ska.execute(ka);
    }

    public void createTremin(Termin t) throws Exception {
        SaveTermin st = new SaveTermin();
        st.execute(t);
    }

    public void createMesto(Mesto m) throws Exception {
        SaveMesto sm = new SaveMesto();
        sm.execute(m);
    }

    //ucitavanje lista
    public List<Mesto> getAllMesta(OpstiDomenskiObjekat odo) throws Exception {
        GetAllMesta gam = new GetAllMesta();
        if (odo != null) {
            gam.execute((Mesto) odo);
        } else {
            gam.execute(new Mesto());
        }
        return gam.getMesta();
    }

    public List<Vozac> getAllVozaci() throws Exception {
        GetAllVozaci gav = new GetAllVozaci();
        gav.execute(new Vozac());
        return gav.getVozaci();
    }

    public List<KomunalniRadnik> getAllKR(OpstiDomenskiObjekat odo) throws Exception {
        //filter je ili null ili String
        GetAllKR gakr = new GetAllKR();
        if (odo != null) {
            gakr.execute((KomunalniRadnik) odo);
        } else {
            gakr.execute(new KomunalniRadnik());
        }

        return gakr.getKomunalniRadnici();
    }

    public List<Kamion> getAllKa(OpstiDomenskiObjekat odo) throws Exception {
        //filter je ili null ili Long
        GetAllKa gak = new GetAllKa();
        if (odo != null) {
            gak.execute((Kamion) odo);
        } else {
            gak.execute(new Kamion());
        }

        return gak.getKamioni();
    }

    public Object getAllTermini(OpstiDomenskiObjekat odo) throws Exception {
        //filter je ili null ili Date

        GetAllTermini gat = new GetAllTermini();
        if (odo != null) {
            gat.execute((Termin) odo);
        } else {
            gat.execute(new Termin());
        }

        return gat.getTermini();
    }

    public Object getAllDrzave(OpstiDomenskiObjekat odo) throws Exception {
        GetAllDrzave gd = new GetAllDrzave();
        if (odo != null) {
            gd.execute((Drzava) odo);
        } else {
            gd.execute(new Drzava());
        }
        return gd.getDrzave();
    }

    //editovanje

    public void editKa(Kamion ka) throws Exception {
        SaveKa ska = new SaveKa();
        ska.execute(ka);
    }

    public void editMesto(Mesto m) throws Exception {
        SaveMesto sm = new SaveMesto();
        sm.execute(m);
    }

    //ucitavanje pojedinacnih objekata
    public KomunalniRadnik getKR(KomunalniRadnik kr) throws Exception {
        GetKR gkr = new GetKR();
        gkr.execute(kr);

        return gkr.getKomunalniRadnik();
    }

    public Object getKa(Kamion ka) throws Exception {
        GetKa gka = new GetKa();
        gka.execute(ka);

        return gka.getKamion();
    }

    public Object getMesto(Mesto m) throws Exception {
        GetMesto gm = new GetMesto();
        gm.execute(m);
        return gm.getMesto();
    }

    //brisanje
    
    public Object deleteKa(Kamion ka) throws Exception {
        DeleteKa dka = new DeleteKa();
        dka.execute(ka);
        return dka.getKamion();
    }

    public Object deleteMesto(Mesto m) throws Exception {
        DeleteMesto dm = new DeleteMesto();
        dm.execute(m);
        return dm.getMesto();
    }

}
