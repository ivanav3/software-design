/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.Mesto;
import domain.OpstiDomenskiObjekat;

/**
 *
 * @author HP
 */
public class SaveMesto extends AbstractSO {

    @Override
    protected void executeOperation(OpstiDomenskiObjekat object) throws Exception {

        Mesto pomoc = (Mesto) object;
        dbb.save(pomoc);
//        if (pomoc.getKamionID() == null) {
//            dbb.add(pomoc);
//        } else {
//            dbb.edit(pomoc);
//        }

    }

    @Override
    protected void validate(OpstiDomenskiObjekat object) throws Exception {
        if (!(object instanceof Mesto)) {
            throw new Exception("Object is not valid");
        }
        

    }

}
