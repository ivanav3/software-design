/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class KomunalniRadnik implements OpstiDomenskiObjekat, Serializable {

    private Long komunalniRadnikID;
    private String ime;
    private String prezime;
    private Mesto mesto;
    private List<Termin> termini;

    public KomunalniRadnik() {
        termini = new ArrayList<>();

    }

    public KomunalniRadnik(Long komunalniRadnikID, String ime, String prezime, Mesto mesto) {
        this.komunalniRadnikID = komunalniRadnikID;
        this.ime = ime;
        this.prezime = prezime;
        this.mesto = mesto;
        termini = new ArrayList<>();

    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    public Long getKomunalniRadnikID() {
        return komunalniRadnikID;
    }

    public void setKomunalniRadnikID(Long komunalniRadnikID) {
        this.komunalniRadnikID = komunalniRadnikID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    //lista termina-za slozen SK
    public List<Termin> getTermini() {
        return termini;
    }

    public void setTermini(List<Termin> termini) {
        this.termini = termini;
    }

    @Override
    public String toString() {
        return "KomunalniRadnik{" + "komunalniRadnikID=" + komunalniRadnikID + ", ime=" + ime + ", prezime=" + prezime + ", mesto=" + mesto + '}';
    }

    @Override
    public String getTableName() {
        return "komunalni_radnik";
    }

    @Override
    public String getColumnsForInsert() {
        return "ime,prezime,mesto";
    }

    @Override
    public String getParamsForInsert() {
        return "(?,?,?)";
    }

    @Override
    public void setParamsForInsert(PreparedStatement statement, OpstiDomenskiObjekat opstiDomenskiObjekat) throws SQLException {
        KomunalniRadnik kr = (KomunalniRadnik) opstiDomenskiObjekat;
        statement.setString(1, kr.getIme());
        statement.setString(2, kr.getPrezime());
        statement.setLong(3, kr.getMesto().getMestoID());

    }

    @Override
    public Object getFilter() {
        if (mesto != null) {
            return " LOWER(m.naziv) LIKE LOWER('" + getMesto().getNaziv() + "%')";
        }
        return null;
    }

    @Override
    public void setFilter(Object filter) {
        if (filter != null) {
            mesto = new Mesto();
            mesto.setNaziv((String) filter);
        }
    }

    @Override
    public Object createObject() {
        return new KomunalniRadnik();
    }

    @Override
    public void setColumnsFromResultSet(ResultSet rs) throws SQLException {
        setKomunalniRadnikID(rs.getLong("komunalniRadnikID"));
        setIme(rs.getString("ime"));
        setPrezime(rs.getString("prezime"));
        //mesto i drzava
        Mesto m = new Mesto();
        m.setMestoID(rs.getLong("mestoID"));
        m.setNaziv(rs.getString("m.naziv"));
        m.setPtt(rs.getLong("ptt"));
        Drzava d = new Drzava();
        d.setDrzavaID(rs.getLong("drzavaID"));
        d.setNaziv(rs.getString("d.naziv"));
        d.setPozivni(rs.getLong("pozivni"));
        m.setDrzava(d);
        setMesto(m);
////        
////        //termin-za listu
////        Termin t = new Termin();
////        t.setDatum(rs.getDate("datum").toLocalDate());
////        t.setDeponijaOdlazak(rs.getBoolean("deponijaOdlazak"));
////        t.setReciklazaOdlazak(rs.getBoolean("reciklazaOdlazak"));
////        t.setVremePocetka(rs.getTime("vremePocetka").toLocalTime());
////        t.setVremeZavrsetka(rs.getTime("vremeZavrsetka").toLocalTime());
////
////        Kamion ka = new Kamion();
////        ka.setKamionID(rs.getLong("kamionID"));
////        ka.setBrend(rs.getString("brend"));
////        ka.setModel(rs.getString("model"));
////        ka.setNosivost(rs.getLong("nosivost"));
////        Vozac v = new Vozac();
////        v.setVozacID(rs.getLong("vozacID"));
////        v.setIme(rs.getString("ime"));
////        v.setPrezime(rs.getString("prezime"));
////        ka.setVozac(v);
////
////        t.setKomunalniRadnik(this);
////        t.setKamion(ka);
////        
//        for(Termin pomoc:getTermini()){
//            System.out.println("U klasi je termin svaki: "+pomoc);
//        }
//
//        getTermini().add(t);
    }

    @Override
    public String getJoinTableName() {
        return " k JOIN mesto m ON (k.mesto=m.mestoID) JOIN drzava d ON (d.drzavaID=m.drzava)";
        //JOIN termin t ON (k.komunalniRadnikID=t.komunalniRadnikID) JOIN kamion ka ON (ka.kamionID=t.kamionID) "
//                + " JOIN vozac v ON (ka.vozac=v.vozacID) JOIN drzava d ON (d.drzavaID=m.drzava)
    }

    @Override
    public String updateValues() {
        return "ime=('" + getIme() + "'), prezime=('" + getPrezime() + "'), mesto=" + getMesto().getMestoID();
    }

    @Override
    public String updateCondition() {
        return "komunalniRadnikID=" + getKomunalniRadnikID();
    }

    @Override
    public boolean containsAutoIncrementPK() {
        return true;
    }

    @Override
    public void setAutoIncrementPrimaryKey(Long primaryKey) {
        setKomunalniRadnikID(primaryKey);
    }

    @Override
    public Object getIdentifier() {
        return getKomunalniRadnikID();
    }

}
