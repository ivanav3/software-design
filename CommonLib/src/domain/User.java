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
public class User implements OpstiDomenskiObjekat, Serializable  {

    private Long userID;
    private String ime;
    private String prezime;
    private String username;
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(Long userID, String ime, String prezime, String username, String password) {
        this.userID = userID;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final User other = (User) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }

    @Override
    public String toString() {
        return "User{" + "userID=" + userID + ", ime=" + ime + ", prezime=" + prezime + ", username=" + username + ", password=" + password + '}';
    }

    @Override
    public String getTableName() {
        return "user";
    }

    @Override
    public String getJoinTableName() {
        return "";
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
        return new User();
    }

    @Override
    public void setColumnsFromResultSet(ResultSet rs) throws SQLException {
        setUserID(rs.getLong("userID"));
        setIme(rs.getString("ime"));
        setPrezime(rs.getString("prezime"));
        setUsername(rs.getString("username"));
        setPassword(rs.getString("password"));

    }

    @Override
    public String updateValues() {
        return null;
    }

    @Override
    public String updateCondition() {
        //password zbog hash
        return " username=('" + getUsername() + "') AND password=PASSWORD('" + getPassword() + "')";
    }

    @Override
    public boolean containsAutoIncrementPK() {
        return true;
    }

    @Override
    public void setAutoIncrementPrimaryKey(Long primaryKey) {
        setUserID(primaryKey);
    }

    @Override
    public Object getIdentifier() {
        return getUserID();
    }

}
