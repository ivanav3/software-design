/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components.table.model;

import domain.User;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author HP
 */
public class UserTableModel extends AbstractTableModel {

    private List<User> users;

    public UserTableModel(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    @Override
    public int getRowCount() {
        return users != null ? users.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Username";
            case 1:
                return "Ime i prezime";
            default:
                return "n/a";
        }

    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User u = users.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return u.getUsername();
            case 1:
                return u.getIme() + " " + u.getPrezime();
            default:
                return "n/a";
        }

    }

}
