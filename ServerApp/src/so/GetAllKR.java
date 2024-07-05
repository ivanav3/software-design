/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.KomunalniRadnik;
import domain.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class GetAllKR extends AbstractSO {

    private List<KomunalniRadnik> komunalniRadnici;

    @Override
    protected void executeOperation(OpstiDomenskiObjekat object) throws Exception {

        komunalniRadnici = new ArrayList<>();
        //filter je ili null ili unet naziv
        KomunalniRadnik pomocni = (KomunalniRadnik) object;
        List<OpstiDomenskiObjekat> listaObjekata = dbb.getAllObjekti(pomocni);
        for (OpstiDomenskiObjekat odo : listaObjekata) {
            KomunalniRadnik kr = (KomunalniRadnik) odo;
            komunalniRadnici.add(kr);

        }

    }

    public List<KomunalniRadnik> getKomunalniRadnici() {
        return komunalniRadnici;
    }

    @Override
    protected void validate(OpstiDomenskiObjekat object) throws Exception {

    }
}
