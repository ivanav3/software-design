/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components.table.model;

import domain.Mesto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author HP
 */
public class MestoTableModel extends AbstractTableModel {

    private List<Mesto> mesta;
    private List<Mesto> obrisana;

    public MestoTableModel(List<Mesto> mesta) {
        this.mesta = mesta;
        obrisana=new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return mesta != null ? mesta.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Mesto m = mesta.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return m.getNaziv();

            case 1:
                return m.getPtt();

            case 2:
                return m.getDrzava().getNaziv();

            default:
                return "n/a";
        }

    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Naziv";
            case 1:
                return "Ptt";
            case 2:
                return "Drzava";
            default:
                return "n/a";
        }
    }

    public Mesto getRow(int row) {
        return mesta.get(row);
    }

    public void addEmptyRow(){
        Mesto m=new Mesto();
        mesta.add(m);
        fireTableDataChanged();
    }
    
    public Mesto removeRow(int row){
        Mesto m=mesta.get(row);
        obrisana.add(m);
        mesta.remove(row);
        fireTableDataChanged();
        return m;
    }

    public List<Mesto> getObrisana() {
        return obrisana;
    }
    
    
    
}
