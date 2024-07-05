/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.Drzava;
import domain.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class GetAllDrzave extends AbstractSO {

    private List<Drzava> drzave;

    @Override
    protected void executeOperation(OpstiDomenskiObjekat object) throws Exception {

        drzave = new ArrayList<>();
        System.out.println("Trenutno je u so");
        Drzava pomocni = (Drzava) object;
        List<OpstiDomenskiObjekat> listaObjekata = dbb.getAllObjekti(pomocni);
        for (OpstiDomenskiObjekat odo : listaObjekata) {
            Drzava d = (Drzava) odo;
            System.out.println("U sistemskoj operaciji "+d);
            drzave.add(d);

        }

    }

    public List<Drzava> getDrzave() {
        return drzave;
    }

    

    @Override
    protected void validate(OpstiDomenskiObjekat object) throws Exception {
        if (!(object instanceof Drzava)) {
            throw new Exception("Object is not valid!");
        }
    }
}
