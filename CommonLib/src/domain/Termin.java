/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 *
 * @author HP
 */
public class Termin implements OpstiDomenskiObjekat, Serializable {

    private Kamion kamion;
    private KomunalniRadnik komunalniRadnik;
    private LocalDate datum;
    private Boolean reciklazaOdlazak;
    private Boolean deponijaOdlazak;
    private LocalTime vremePocetka;
    private LocalTime vremeZavrsetka;

    public Termin() {
    }

    public Termin(Kamion kamionID, KomunalniRadnik komunalniRadnikID, LocalDate datum, Boolean reciklazaOdlazak, 
            Boolean deponijaOdlazak, LocalTime vremePocetka, LocalTime vremeZavrsetka) {
        this.kamion = kamionID;
        this.komunalniRadnik = komunalniRadnikID;
        this.datum = datum;
        this.reciklazaOdlazak = reciklazaOdlazak;
        this.deponijaOdlazak = deponijaOdlazak;
        this.vremePocetka = vremePocetka;
        this.vremeZavrsetka = vremeZavrsetka;
    }

    public LocalTime getVremeZavrsetka() {
        return vremeZavrsetka;
    }

    public void setVremeZavrsetka(LocalTime vremeZavrsetka) {
        this.vremeZavrsetka = vremeZavrsetka;
    }

    public Kamion getKamion() {
        return kamion;
    }

    public void setKamion(Kamion kamion) {
        this.kamion = kamion;
    }

    public KomunalniRadnik getKomunalniRadnik() {
        return komunalniRadnik;
    }

    public void setKomunalniRadnik(KomunalniRadnik komunalniRadnik) {
        this.komunalniRadnik = komunalniRadnik;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public Boolean getReciklazaOdlazak() {
        return reciklazaOdlazak;
    }

    public void setReciklazaOdlazak(Boolean reciklazaOdlazak) {
        this.reciklazaOdlazak = reciklazaOdlazak;
    }

    public Boolean getDeponijaOdlazak() {
        return deponijaOdlazak;
    }

    public void setDeponijaOdlazak(Boolean deponijaOdlazak) {
        this.deponijaOdlazak = deponijaOdlazak;
    }

    public LocalTime getVremePocetka() {
        return vremePocetka;
    }

    public void setVremePocetka(LocalTime vremePocetka) {
        this.vremePocetka = vremePocetka;
    }

    @Override
    public String toString() {
        return "Termin{" + "kamionID=" + kamion + ", komunalniRadnikID=" + komunalniRadnik + ", datum=" + datum + ", reciklazaOdlazak=" + reciklazaOdlazak + ", deponijaOdlazak=" + deponijaOdlazak + ", vremePocetka=" + vremePocetka + ", vremeZavrsetka=" + vremeZavrsetka + '}';
    }

    @Override
    public String getTableName() {
        return "termin";
    }

    @Override
    public String getColumnsForInsert() {
        return "kamionID,komunalniRadnikID,datum,reciklazaOdlazak,deponijaOdlazak,vremePocetka,vremeZavrsetka";
    }

    @Override
    public String getParamsForInsert() {
        return "(?,?,?,?,?,?,?)";
    }

    @Override
    public void setParamsForInsert(PreparedStatement statement, OpstiDomenskiObjekat opstiDomenskiObjekat) throws SQLException {
        Termin t = (Termin) opstiDomenskiObjekat;
        statement.setLong(1, t.getKamion().getKamionID());
        statement.setLong(2, t.getKomunalniRadnik().getKomunalniRadnikID());
        statement.setDate(3, Date.valueOf(t.getDatum()));
        statement.setBoolean(4, t.getReciklazaOdlazak());
        statement.setBoolean(5, t.getDeponijaOdlazak());
        statement.setString(6, String.valueOf(t.getVremePocetka()));
        statement.setString(7, String.valueOf(t.getVremeZavrsetka()));
    }

    @Override
    public Object getFilter() {
        if (komunalniRadnik.getKomunalniRadnikID() != null) {
            return " t.komunalniRadnikID=('" + komunalniRadnik.getKomunalniRadnikID()+ "')";
        } else {
            return " ";
        }

    }

    @Override
    public void setFilter(Object filter) {
        if (filter != null) {
            setDatum((LocalDate) filter);
        }
    }

    @Override
    public Object createObject() {
        return new Termin();
    }

    @Override
    public void setColumnsFromResultSet(ResultSet rs) throws SQLException {
        setDatum(rs.getDate("datum").toLocalDate());
        setDeponijaOdlazak(rs.getBoolean("deponijaOdlazak"));
        setReciklazaOdlazak(rs.getBoolean("reciklazaOdlazak"));
        setVremePocetka(rs.getTime("vremePocetka").toLocalTime());
        setVremeZavrsetka(rs.getTime("vremeZavrsetka").toLocalTime());

        KomunalniRadnik kr = new KomunalniRadnik();
        kr.setKomunalniRadnikID(rs.getLong("komunalniRadnikID"));
        kr.setIme(rs.getString("ime"));
        kr.setPrezime(rs.getString("prezime"));

        Mesto m = new Mesto();
        m.setMestoID(rs.getLong("mestoID"));
        m.setNaziv(rs.getString("naziv"));
        m.setPtt(rs.getLong("ptt"));
        Drzava d = new Drzava();
        d.setDrzavaID(rs.getLong("drzavaID"));
        d.setNaziv(rs.getString("d.naziv"));
        d.setPozivni(rs.getLong("pozivni"));
        m.setDrzava(d);

        kr.setMesto(m);
        setKomunalniRadnik(kr);

        Kamion ka = new Kamion();
        ka.setKamionID(rs.getLong("kamionID"));
        ka.setBrend(rs.getString("brend"));
        ka.setModel(rs.getString("model"));
        ka.setNosivost(rs.getLong("nosivost"));

        Vozac v = new Vozac();
        v.setVozacID(rs.getLong("vozacID"));
        v.setIme(rs.getString("ime"));
        v.setPrezime(rs.getString("prezime"));

        ka.setVozac(v);
        setKamion(ka);
    }

    @Override
    public String getJoinTableName() {
        return " t JOIN komunalni_radnik k ON (t.komunalniRadnikID=k.komunalniRadnikID) JOIN kamion ka ON (t.kamionID=ka.kamionID) "
                + "JOIN mesto m ON (k.mesto=m.mestoID) JOIN drzava d ON (d.drzavaID=m.drzava) JOIN vozac v ON (ka.vozac=v.vozacID)";
    }

    @Override
    public String updateValues() {
        return " t.komunalniRadnikID=" + getKomunalniRadnik().getKomunalniRadnikID();
    }

    @Override
    public String updateCondition() {
        return null;
    }

    @Override
    public boolean containsAutoIncrementPK() {
        return false;
    }

    @Override
    public void setAutoIncrementPrimaryKey(Long primaryKey) {
        //nista posto nije autoIncrement
    }

    @Override
    public Object getIdentifier() {
        return getKomunalniRadnik().getKomunalniRadnikID();
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Termin other = (Termin) obj;
        return Objects.equals(this.datum, other.datum);
    }

}
