/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.Kamion;
import domain.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class GetAllKa extends AbstractSO {

    private List<Kamion> kamioni;

    @Override
    protected void executeOperation(OpstiDomenskiObjekat object) throws Exception {

        kamioni = new ArrayList<>();
        Kamion pomocni = (Kamion) object;
        List<OpstiDomenskiObjekat> listaObjekata = dbb.getAllObjekti(pomocni);

        for (OpstiDomenskiObjekat odo : listaObjekata) {
            Kamion kamion = (Kamion) odo;
            kamioni.add(kamion);

        }

    }

    public List<Kamion> getKamioni() {
        return kamioni;
    }

    @Override
    protected void validate(OpstiDomenskiObjekat object) throws Exception {

    }
}
