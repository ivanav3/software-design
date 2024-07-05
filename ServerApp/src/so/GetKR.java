/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.KomunalniRadnik;
import domain.OpstiDomenskiObjekat;
import domain.Termin;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class GetKR extends AbstractSO {

    private KomunalniRadnik komunalniRadnik;

    @Override
    protected void executeOperation(OpstiDomenskiObjekat object) throws Exception {

        komunalniRadnik = (KomunalniRadnik) object;
        komunalniRadnik = (KomunalniRadnik) dbb.getODO(komunalniRadnik);
        Termin pomocni=new Termin();
        pomocni.setKomunalniRadnik(komunalniRadnik);
        
         List<OpstiDomenskiObjekat> listaObjekata = dbb.getAllObjekti(pomocni);
         List<Termin> termini=new ArrayList<>();
        for (OpstiDomenskiObjekat odo : listaObjekata) {
            Termin kamion = (Termin) odo;
            termini.add(kamion);

        }
        
        komunalniRadnik.setTermini(termini);
        for(Termin t:komunalniRadnik.getTermini()){
            System.out.println("Termini u SO: "+t);
        }

    }

    public KomunalniRadnik getKomunalniRadnik() {
        return komunalniRadnik;
    }

    @Override
    protected void validate(OpstiDomenskiObjekat object) throws Exception {
        if (!(object instanceof KomunalniRadnik)) {
            throw new Exception("Object not valid!");
        }
    }

}
