/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class Kamion implements OpstiDomenskiObjekat, Serializable {

    private Long kamionID;
    private String brend;
    private String model;
    private Long nosivost;
    private Vozac vozac;

    public Kamion() {
        
    }

    public Kamion(Long kamionID, String brend, String model, Long nosivost, Vozac vozac) {
        this.kamionID = kamionID;
        this.brend = brend;
        this.model = model;
        this.nosivost = nosivost;
        this.vozac = vozac;
    }

    public Vozac getVozac() {
        return vozac;
    }

    public void setVozac(Vozac vozac) {
        this.vozac = vozac;
    }

    public Long getKamionID() {
        return kamionID;
    }

    public void setKamionID(Long kamionID) {
        this.kamionID = kamionID;
    }

    public String getBrend() {
        return brend;
    }

    public void setBrend(String brend) {
        this.brend = brend;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getNosivost() {
        return nosivost;
    }

    public void setNosivost(Long nosivost) {
        this.nosivost = nosivost;
    }

    @Override
    public String toString() {
        return "Kamion{" + "kamionID=" + kamionID + ", brend=" + brend + ", model=" + model + ", nosivost=" + nosivost + ", vozacID=" + vozac + '}';
    }

    @Override
    public String getTableName() {
        return "kamion";
    }

    @Override
    public String getColumnsForInsert() {
        return "brend,model,nosivost,vozac";
    }

    @Override
    public String getParamsForInsert() {
        return "(?,?,?,?)";
    }

    @Override
    public void setParamsForInsert(PreparedStatement statement, OpstiDomenskiObjekat opstiDomenskiObjekat) throws SQLException {
        Kamion ka = (Kamion) opstiDomenskiObjekat;
        statement.setString(1, ka.getBrend());
        statement.setString(2, ka.getModel());
        statement.setLong(3, ka.getNosivost());
        statement.setLong(4, ka.getVozac().getVozacID());
    }

    @Override
    public Object getFilter() {
        if(getNosivost()==null)
            return null;
        return "nosivost>= " + getNosivost();
    }

    @Override
    public Object createObject() {
        return new Kamion();
    }

    
    @Override
    public void setFilter(Object filter) {
        if(filter!=null)
        setNosivost((Long) filter);
    }
    
    @Override
    public void setColumnsFromResultSet(ResultSet rs) throws SQLException {
        setKamionID(rs.getLong("kamionID"));
        setBrend(rs.getString("brend"));
        setModel(rs.getString("model"));
        setNosivost(rs.getLong("nosivost"));

        Vozac v = new Vozac();
        v.setVozacID(rs.getLong("vozacID"));
        v.setIme(rs.getString("ime"));
        v.setPrezime(rs.getString("prezime"));
        
        setVozac(v);

    }

    @Override
    public String getJoinTableName() {
        return " k JOIN vozac v ON (k.vozac=v.vozacID)";
    }

    @Override
    public String updateValues() {
        
        return "brend=('"+getBrend()+"'), model=('"+getModel()+"'), nosivost="+getNosivost();
    }

    @Override
    public String updateCondition() {
        return "kamionID="+getKamionID();
    }

    @Override
    public boolean containsAutoIncrementPK() {
        return true;
    }

    @Override
    public void setAutoIncrementPrimaryKey(Long primaryKey) {
        setKamionID(primaryKey);
    }

    @Override
    public Object getIdentifier() {
        return getKamionID();
    }


}
