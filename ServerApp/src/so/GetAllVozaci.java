/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.OpstiDomenskiObjekat;
import domain.Vozac;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class GetAllVozaci extends AbstractSO {

    private List<Vozac> vozaci;

    @Override
    protected void executeOperation(OpstiDomenskiObjekat object) throws Exception {

        vozaci = new ArrayList<>();
        List<OpstiDomenskiObjekat> listaObjekata = dbb.getAllObjekti(new Vozac());

        for (OpstiDomenskiObjekat odo : listaObjekata) {
            Vozac v = (Vozac) odo;
            vozaci.add(v);

        }

    }

    public List<Vozac> getVozaci() {
        return vozaci;
    }

    @Override
    protected void validate(OpstiDomenskiObjekat object) throws Exception {
        if (!(object instanceof Vozac)) {
            throw new Exception("Object is not valid!");
        }
    }
}
