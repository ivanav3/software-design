/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 *
 * @author HP
 */
public class Drzava implements OpstiDomenskiObjekat, Serializable {

    private Long drzavaID;
    private String naziv;
    private Long pozivni;

    public Drzava() {
    }

    public Drzava(Long drzavaID, String naziv, Long pozivni) {
        this.drzavaID = drzavaID;
        this.naziv = naziv;
        this.pozivni = pozivni;
    }

    public Long getDrzavaID() {
        return drzavaID;
    }

    public void setDrzavaID(Long drzavaID) {
        this.drzavaID = drzavaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Long getPozivni() {
        return pozivni;
    }

    public void setPozivni(Long pozivni) {
        this.pozivni = pozivni;
    }

    @Override
    public String getTableName() {
        return "drzava";
    }

    @Override
    public String getJoinTableName() {
        return null;
    }

    @Override
    public String getColumnsForInsert() {
        return null;
    }

    @Override
    public String getParamsForInsert() {
        return null;
    }

    @Override
    public void setParamsForInsert(PreparedStatement statement, OpstiDomenskiObjekat opstiDomenskiObjekat) throws SQLException {

    }

    @Override
    public Object getFilter() {
        return null;
    }

    @Override
    public void setFilter(Object filter) {

    }

    @Override
    public Object createObject() {
        return new Drzava();
    }

    @Override
    public void setColumnsFromResultSet(ResultSet rs) throws SQLException {
        setDrzavaID(rs.getLong("drzavaID"));
        setNaziv(rs.getString("naziv"));
        setPozivni(rs.getLong("pozivni"));

    }

    @Override
    public String updateValues() {
        return null;
    }

    @Override
    public String updateCondition() {
        return null;
    }

    @Override
    public Object getIdentifier() {
        return getDrzavaID();
    }

    @Override
    public boolean containsAutoIncrementPK() {
        return true;
    }

    @Override
    public void setAutoIncrementPrimaryKey(Long primaryKey) {

    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.drzavaID);
        hash = 97 * hash + Objects.hashCode(this.naziv);
        hash = 97 * hash + Objects.hashCode(this.pozivni);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Drzava other = (Drzava) obj;
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        if (!Objects.equals(this.drzavaID, other.drzavaID)) {
            return false;
        }
        return Objects.equals(this.pozivni, other.pozivni);
    }

    @Override
    public String toString() {
        return getNaziv();
    }

}
