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
public class Vozac implements OpstiDomenskiObjekat, Serializable  {

    private Long vozacID;
    private String ime;
    private String prezime;

    public Vozac() {
    }

    public Vozac(Long vozacID, String ime, String prezime) {
        this.vozacID = vozacID;
        this.ime = ime;
        this.prezime = prezime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Long getVozacID() {
        return vozacID;
    }

    public void setVozacID(Long vozacID) {
        this.vozacID = vozacID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.vozacID);
        hash = 59 * hash + Objects.hashCode(this.ime);
        hash = 59 * hash + Objects.hashCode(this.prezime);
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
        final Vozac other = (Vozac) obj;
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        return Objects.equals(this.vozacID, other.vozacID);
    }

    @Override
    public String toString() {
        //return "Vozac{" + "vozacID=" + vozacID + ", ime=" + ime + ", prezime=" + prezime + '}';
        return ime + " " + prezime;
    }

    @Override
    public String getTableName() {
        return "vozac";
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
        return new Vozac();
    }

    @Override
    public void setColumnsFromResultSet(ResultSet rs) throws SQLException {
        setVozacID(rs.getLong("vozacID"));
        setIme(rs.getString("ime"));
        setPrezime(rs.getString("prezime"));

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
    public boolean containsAutoIncrementPK() {
        return true;
    }

    @Override
    public void setAutoIncrementPrimaryKey(Long primaryKey) {
        setVozacID(primaryKey);
    }

    @Override
    public Object getIdentifier() {
        return getVozacID();
    }
}
