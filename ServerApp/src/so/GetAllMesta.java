/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.Mesto;
import domain.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class GetAllMesta extends AbstractSO {

    private List<Mesto> mesta;

    @Override
    protected void executeOperation(OpstiDomenskiObjekat object) throws Exception {

        mesta = new ArrayList<>();
        Mesto pomocni = (Mesto) object;

        List<OpstiDomenskiObjekat> listaObjekata = dbb.getAllObjekti(pomocni);

        for (OpstiDomenskiObjekat odo : listaObjekata) {
            Mesto m = (Mesto) odo;
            mesta.add(m);

        }

    }

    public List<Mesto> getMesta() {
        return mesta;
    }

    @Override
    protected void validate(OpstiDomenskiObjekat object) throws Exception {
        if (!(object instanceof Mesto)) {
            throw new Exception("Object is not valid!");
        }
    }
}
