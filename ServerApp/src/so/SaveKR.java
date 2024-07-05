/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.KomunalniRadnik;
import domain.OpstiDomenskiObjekat;
import domain.Termin;

/**
 *
 * @author HP
 */
public class SaveKR extends AbstractSO {

    @Override
    protected void executeOperation(OpstiDomenskiObjekat object) throws Exception {

        KomunalniRadnik pomoc = (KomunalniRadnik) object;
        dbb.save(pomoc);
        for(Termin t:pomoc.getTermini()){
            dbb.save(t);
        }
        
//        if (pomoc.getKomunalniRadnikID() == null) {
//            dbb.add(pomoc);
//        } else {
//            dbb.edit(pomoc);
//        }
    }

    @Override
    protected void validate(OpstiDomenskiObjekat object) throws Exception {
        if (!(object instanceof KomunalniRadnik)) {
            throw new Exception("Object is not valid");
        }
        KomunalniRadnik kr = (KomunalniRadnik) object;
        if (kr.getIme().isEmpty()) {
            throw new Exception("Pogresan unos imena!");
        }
        System.out.println("Ime je " + kr.getIme() + " prezime " + kr.getPrezime());
        if (kr.getPrezime().isEmpty()) {
            throw new Exception("Pogresan unos prezimena!");
        }

    }

}
