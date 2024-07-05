/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import domain.OpstiDomenskiObjekat;
import domain.Termin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class DatabaseBroker {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void connect() throws SQLException {
        try {

            String url = "jdbc:mysql://localhost:3306/komunalno_preduzece";
            String user = "root";
            String password = "";
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Konekcija sa bazom podataka uspesno uspostavljena!");

            //iskljucujemo automatsko potvrdjivanje transakcije nakon svakog upita
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            System.out.println("Greska! Konekcija sa bazom nije uspesno uspostavljena!\n" + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }
    }

    public void disconnect() throws SQLException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Konekcija sa bazom uspesno raskinuta!");
            }
        } catch (SQLException ex) {
            System.out.println("Greska! Konekcija sa bazom nije uspesno raskinuta!\n" + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }
    }

    public void commit() throws SQLException {
        try {
            connection.commit();
            System.out.println("Transakcija uspesno potvrdjena!");
        } catch (SQLException ex) {
            System.out.println("Transakcija nije potvrdjena!\n" + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }
    }

    public void rollback() throws SQLException {
        try {
            connection.rollback();
            System.out.println("Transakcija uspesno ponistena!");
        } catch (SQLException ex) {
            System.out.println("Transakcija nije ponistena!\n" + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }
    }

    //cuvanje
    public void save(OpstiDomenskiObjekat opstiDomenskiObjekat) throws SQLException {
        try {
            String query = "";
            if (opstiDomenskiObjekat.getIdentifier() == null || opstiDomenskiObjekat instanceof Termin) {
                query = "INSERT INTO " + opstiDomenskiObjekat.getTableName()
                        + " (" + opstiDomenskiObjekat.getColumnsForInsert() + ") VALUES " + opstiDomenskiObjekat.getParamsForInsert();
                //Pravljenje objekta koji je odgovoran za izvrsavanje upita
                PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                //postavljanje vrednosti parametara
                opstiDomenskiObjekat.setParamsForInsert(statement, opstiDomenskiObjekat);

                //izvsi upit
                int result = statement.executeUpdate();

                System.out.println("Objekat uspesno dodat u bazu!");

                //pristup generisanom kljucu
                if (opstiDomenskiObjekat.containsAutoIncrementPK()) {
                    ResultSet rsID = statement.getGeneratedKeys();
                    if (rsID.next()) {
                        //person.setPersonID(rsID.getLong(1));
                        opstiDomenskiObjekat.setAutoIncrementPrimaryKey(rsID.getLong(1));
                    }
                    rsID.close();
                } else if (result > 0) {
                    System.out.println("Uspesno je ubacen objekat u bazu: " + opstiDomenskiObjekat.getClass());
                }
                statement.close();
            } else {
                query = "UPDATE " + opstiDomenskiObjekat.getTableName() + " SET "
                        + opstiDomenskiObjekat.updateValues() + " WHERE " + opstiDomenskiObjekat.updateCondition();
                Statement statement = connection.createStatement();
                System.out.println("Upit za update je: " + query);
                int result = statement.executeUpdate(query);

                statement.close();
                System.out.println("Uspesna izmena Objekta");
            }

        } catch (SQLException ex) {
            System.out.println("Neuspesno dodavanje objekta u bazu!\n" + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }
    }

    //ucitavanje i filter
    public List<OpstiDomenskiObjekat> getAllObjekti(OpstiDomenskiObjekat odo) throws Exception {
        List<OpstiDomenskiObjekat> objekti = new ArrayList<>();
        try {

            String query = "SELECT * FROM " + odo.getTableName();
            if (odo.getJoinTableName() != null) {
                query += odo.getJoinTableName();
                System.out.println("Trenutni query " + query);
            }
            if (odo.getFilter() != null) {
                System.out.println("Query sa filterom " + query);
                query += " WHERE " + odo.getFilter();

            }
            System.out.println("Upit za ucitavanje liste je: " + query);

            PreparedStatement ps = connection.prepareStatement(query);
            System.out.println("U db brokeru je odo " + odo + " a klasa " + odo.getClass());

            ResultSet rs = ps.executeQuery();
            System.out.println("Pre while petlje");
            while (rs.next()) {

                OpstiDomenskiObjekat objekat = (OpstiDomenskiObjekat) odo.createObject();
                System.out.println("Posle createObject " + objekat);
                objekat.setColumnsFromResultSet(rs);
                System.out.println("Konacni objekat " + objekat);
                objekti.add(objekat);
            }
            rs.close();
            ps.close();
            System.out.println("Uspesno ucitavanje Objekta iz baze!");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Sistem ne moze da ucita objekte!" + ex.getMessage());
        }
        System.out.println("Doslo je do vracanja liste objekata");
        return objekti;

    }

    //ucitavanje pojedinacnog objekta
    public OpstiDomenskiObjekat getODO(OpstiDomenskiObjekat odo) {
        OpstiDomenskiObjekat objekat = (OpstiDomenskiObjekat) odo.createObject();
        System.out.println("U dbbrokeru je odo " + odo);
        try {
            String query = "SELECT * FROM " + odo.getTableName() + odo.getJoinTableName() + " WHERE " + odo.updateCondition();

            System.out.println("Upit za ucitavanje je: " + query);

            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                objekat.setColumnsFromResultSet(rs);

            }
            rs.close();
            ps.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Sistem ne moze da ucita Objekat! " + ex.getMessage());
        }
        return objekat;
    }

    //brisanje
    public void delete(OpstiDomenskiObjekat odo) throws Exception {
        try {

            String query = "DELETE FROM " + odo.getTableName() + " WHERE " + odo.updateCondition();
            Statement s = connection.createStatement();

            int result = s.executeUpdate(query);
            s.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Sistem ne moze da obrise objekte!" + ex.getMessage());

        }

    }

}
