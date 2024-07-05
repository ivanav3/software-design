/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components.table.model;

import domain.Kamion;
import domain.Vozac;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author HP
 */
public class KaTableModel extends AbstractTableModel {

    private List<Kamion> kamioni;
    private List<Kamion> obrisani;

    public KaTableModel(List<Kamion> kamioni) {
        this.kamioni = kamioni;
        this.obrisani = new ArrayList<>();
    }

    public List<Kamion> getKamioni() {
        return kamioni;
    }

    @Override
    public int getRowCount() {
        return kamioni == null ? 0 : kamioni.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Kamion ka = kamioni.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return ka.getBrend();

            case 1:
                return ka.getModel();

            case 2:
                return ka.getNosivost();

            case 3:
                return ka.getVozac();

            default:
                return "n/a";
        }

    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Brend";
            case 1:
                return "Model";
            case 2:
                return "Nosivost";
            case 3:
                return "Vozac";
            default:
                return "n/a";
        }
    }

    public Kamion getRow(int row) {
        return kamioni.get(row);

    }

    public void addEmptyRow() {
        Kamion ka = new Kamion();
        kamioni.add(ka);
        fireTableDataChanged();
    }
//
//    @Override
//    public boolean isCellEditable(int rowIndex, int columnIndex) {
//        return true;
//    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Kamion ka = kamioni.get(rowIndex);
        switch (columnIndex) {
            case 0:
                ka.setBrend(aValue.toString());
                break;
            case 1:
                ka.setModel(aValue.toString());
                break;
            case 2:
                ka.setNosivost(Long.parseLong(aValue.toString()));
                break;
            case 3:
                ka.setVozac((Vozac) aValue);
                break;

        }
    }

    public void removeRow(int row) {
        if (kamioni == null) {
            return;
        }
        if (row != -1 && row < kamioni.size()) {
            Kamion ka = kamioni.get(row);
            if (ka.getKamionID() != null) {
                //brisemo samo ako je postojao u bazi
                obrisani.add(ka);
            }
            kamioni.remove(row);
            fireTableDataChanged();
        }
    }

    public List<Kamion> getObrisani() {
        return obrisani;
    }

}
