/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.OpstiDomenskiObjekat;
import domain.User;

/**
 *
 * @author HP
 */
public class LoginUser extends AbstractSO {

    private User user;

    @Override
    protected void executeOperation(OpstiDomenskiObjekat object) throws Exception {

        user = (User) dbb.getODO((User) object);
        object = user;
        if (user.getUserID() == null) {
            throw new Exception("Korisnik ne postoji");
        }

    }

    public User getUser() {
        return user;
    }

    @Override
    protected void validate(OpstiDomenskiObjekat object) throws Exception {
        if (!(object instanceof User)) {
            throw new Exception("Object is not valid");
        }
    }
}
