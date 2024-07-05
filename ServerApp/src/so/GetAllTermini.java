/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.OpstiDomenskiObjekat;
import domain.Termin;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class GetAllTermini extends AbstractSO {

    private List<Termin> termini;

    @Override
    protected void executeOperation(OpstiDomenskiObjekat object) throws Exception {

        termini = new ArrayList<>();
        Termin pomocni = (Termin) object;
        List<OpstiDomenskiObjekat> listaObjekata = dbb.getAllObjekti(pomocni);

        for (OpstiDomenskiObjekat odo : listaObjekata) {
            Termin kamion = (Termin) odo;
            termini.add(kamion);

        }

    }

    public List<Termin> getTermini() {
        return termini;
    }

    @Override
    protected void validate(OpstiDomenskiObjekat object) throws Exception {

    }
}
