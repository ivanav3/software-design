/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.OpstiDomenskiObjekat;
import domain.Termin;

/**
 *
 * @author HP
 */
public class SaveTermin extends AbstractSO {

    @Override
    protected void executeOperation(OpstiDomenskiObjekat object) throws Exception {
        
        Termin pomoc = (Termin) object;
        dbb.save(pomoc);

    }

    @Override
    protected void validate(OpstiDomenskiObjekat object) throws Exception {
        if (!(object instanceof Termin)) {
            throw new Exception("Object is not valid");
        }

    }

}
