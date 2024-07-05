/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.Kamion;
import domain.OpstiDomenskiObjekat;

/**
 *
 * @author HP
 */
public class SaveKa extends AbstractSO {

    @Override
    protected void executeOperation(OpstiDomenskiObjekat object) throws Exception {

        Kamion pomoc = (Kamion) object;
        dbb.save(pomoc);
//        if (pomoc.getKamionID() == null) {
//            dbb.add(pomoc);
//        } else {
//            dbb.edit(pomoc);
//        }

    }

    @Override
    protected void validate(OpstiDomenskiObjekat object) throws Exception {
        if (!(object instanceof Kamion)) {
            throw new Exception("Object is not valid");
        }
        Kamion kr = (Kamion) object;
        if (kr.getBrend().isEmpty()) {
            throw new Exception("Pogresan unos brenda!");
        }
        if (kr.getModel().isEmpty()) {
            throw new Exception("Pogresan unos modela!");
        }

    }

}
