/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import db.DatabaseBroker;

import domain.OpstiDomenskiObjekat;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public abstract class AbstractSO {

    protected DatabaseBroker dbb;

    public AbstractSO() {
        dbb = new DatabaseBroker();
    }

    public void execute(OpstiDomenskiObjekat object) throws Exception {

        try {
            dbb.connect();
        } catch (Exception ex) {
            throw new Exception("Neuspesno povezivanje sa bazom!");
        }
        try {

            validate(object);
            executeOperation(object);
            commit();

        } catch (Exception ex) {
            rollback();
            throw new Exception("Neuspesno prijavljivanje na sistem!");
        } finally {
            dbb.disconnect();
        }

    }

    private void commit() throws SQLException {
        dbb.commit();
    }

    private void rollback() throws SQLException {
        dbb.rollback();
    }

    protected abstract void executeOperation(OpstiDomenskiObjekat object) throws Exception;

    protected abstract void validate(OpstiDomenskiObjekat object) throws Exception;
}
