/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components.table.model;

import domain.KomunalniRadnik;
import domain.Mesto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author HP
 */
public class KRTableModel extends AbstractTableModel{

   private List<KomunalniRadnik> komunalniRadnici;
    private List<KomunalniRadnik> obrisani;

    public KRTableModel(List<KomunalniRadnik> komunalniRadnici) {
        this.komunalniRadnici = komunalniRadnici;
        this.obrisani = new ArrayList<>();
    }

    public List<KomunalniRadnik> getKomunalniRadnici() {
        //treba nam zbog sinhronizacije
        return komunalniRadnici;
    }

    @Override
    public int getRowCount() {
        return komunalniRadnici == null ? 0 : komunalniRadnici.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        KomunalniRadnik kr = komunalniRadnici.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return kr.getIme();

            case 1:
                return kr.getPrezime();

            case 2:
                return kr.getMesto();

            default:
                return "n/a";
        }

    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ime";
            case 1:
                return "prezime";
            case 2:
                return "mesto";
            default:
                return "n/a";
        }
    }

    public KomunalniRadnik getRow(int row) {
        return komunalniRadnici.get(row);

    }

    public void addEmptyRow() {
        KomunalniRadnik kr = new KomunalniRadnik();
        komunalniRadnici.add(kr);
        fireTableDataChanged();
    }
//
//    @Override
//    public boolean isCellEditable(int rowIndex, int columnIndex) {
//        return true;
//    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        KomunalniRadnik kr = komunalniRadnici.get(rowIndex);
        switch (columnIndex) {
            case 0:
                kr.setIme(aValue.toString());
                break;
            case 1:
                kr.setPrezime(aValue.toString());
                break;
            case 2:
                kr.setMesto((Mesto) aValue);
                break;

        }
    }

    public void removeRow(int row) {
        if (komunalniRadnici == null) {
            return;
        }
        if (row != -1 && row < komunalniRadnici.size()) {
            KomunalniRadnik komunalniRadnik=komunalniRadnici.get(row);
            if(komunalniRadnik.getKomunalniRadnikID()!=null){
                //brisemo samo ako je postojao u bazi
                obrisani.add(komunalniRadnik);
            }
            komunalniRadnici.remove(row);
            fireTableDataChanged();
        }
    }

    public List<KomunalniRadnik> getObrisani() {
        return obrisani;
    }
}
