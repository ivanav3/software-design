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
public class GetKa extends AbstractSO {

    private Kamion kamion;

    @Override
    protected void executeOperation(OpstiDomenskiObjekat object) throws Exception {

        kamion = (Kamion) object;
        kamion = (Kamion) dbb.getODO(kamion);
        

    }

    public Kamion getKamion() {
        return kamion;
    }

    @Override
    protected void validate(OpstiDomenskiObjekat object) throws Exception {
        if (!(object instanceof Kamion)) {
            throw new Exception("Object not valid!");
        }
    }

}
