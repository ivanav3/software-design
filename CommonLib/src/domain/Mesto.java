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
public class Mesto implements OpstiDomenskiObjekat, Serializable {

    private Long mestoID;
    private String naziv;
    private Long ptt;
    private Drzava drzava;

    public Mesto() {
    }

    public Mesto(Long mestoID, String naziv, Long ptt, Drzava drzava) {
        this.mestoID = mestoID;
        this.naziv = naziv;
        this.ptt = ptt;
        this.drzava = drzava;
    }

    

    public Long getPtt() {
        return ptt;
    }

    public void setPtt(Long ptt) {
        this.ptt = ptt;
    }

    public Long getMestoID() {
        return mestoID;
    }

    public void setMestoID(Long mestoID) {
        this.mestoID = mestoID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava = drzava;
    }

    @Override
    public String toString() {
        //return "Mesto{" + "mestoID=" + mestoID + ", naziv=" + naziv + ", ptt=" + ptt + '}';
        return naziv;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.mestoID);
        hash = 43 * hash + Objects.hashCode(this.naziv);
        hash = 43 * hash + Objects.hashCode(this.ptt);
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
        final Mesto other = (Mesto) obj;
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        if (!Objects.equals(this.mestoID, other.mestoID)) {
            return false;
        }
        return Objects.equals(this.ptt, other.ptt);
    }

    @Override
    public String getTableName() {
        return "mesto";
    }

    @Override
    public String getJoinTableName() {
        return " m JOIN drzava d ON (m.drzava=d.drzavaID)";
    }

    @Override
    public String getColumnsForInsert() {
        return "naziv,ptt,drzava";
    }

    @Override
    public String getParamsForInsert() {
        return "(?,?,?)";

    }

    @Override
    public void setParamsForInsert(PreparedStatement statement, OpstiDomenskiObjekat opstiDomenskiObjekat) throws SQLException {

        Mesto m = (Mesto) opstiDomenskiObjekat;
        statement.setString(1, m.getNaziv());
        statement.setLong(2, m.getPtt());
        statement.setLong(3, m.getDrzava().getDrzavaID());
    }

    @Override
    public Object getFilter() {
        if (drzava != null) {
            return " LOWER(d.naziv) LIKE LOWER('" + getDrzava().getNaziv() + "%')";
        }
        return null;
    }

    @Override
    public void setFilter(Object filter) {
        if (filter != null) {
            drzava = new Drzava();
            drzava.setNaziv((String) filter);
        }
    }

    @Override
    public Object createObject() {
        return new Mesto();
    }

    @Override
    public void setColumnsFromResultSet(ResultSet rs) throws SQLException {

        setMestoID(rs.getLong("mestoID"));
        setNaziv(rs.getString("naziv"));
        setPtt(rs.getLong("ptt"));

        Drzava d = new Drzava();

        d.setDrzavaID(rs.getLong("drzavaID"));
        d.setNaziv(rs.getString("d.naziv"));
        d.setPozivni(rs.getLong("pozivni"));

        setDrzava(d);

    }

    @Override
    public String updateValues() {
        return "naziv=('" + getNaziv()+ "'), ptt="+getPtt()+", drzava=" + getDrzava().getDrzavaID();

    }

    @Override
    public String updateCondition() {
        return "mestoID="+getMestoID();
    }

    @Override
    public boolean containsAutoIncrementPK() {
        return true;
    }

    @Override
    public void setAutoIncrementPrimaryKey(Long primaryKey) {
        setMestoID(primaryKey);
    }

    @Override
    public Object getIdentifier() {
        return getMestoID();
    }
}
