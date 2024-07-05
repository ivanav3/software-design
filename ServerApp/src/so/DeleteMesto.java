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
public class DeleteMesto extends AbstractSO {

    private Mesto mesto;

    @Override
    protected void executeOperation(OpstiDomenskiObjekat object) throws Exception {

        mesto = (Mesto) object;
        dbb.delete(mesto);
        

    }

    public Mesto getMesto() {
        return mesto;
    }

    
    @Override
    protected void validate(OpstiDomenskiObjekat object) throws Exception {
        if (!(object instanceof Mesto)) {
            throw new Exception("Object is not valid");
        }

    }

}
