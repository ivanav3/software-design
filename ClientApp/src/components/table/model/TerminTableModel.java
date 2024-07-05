/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components.table.model;

import domain.Termin;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author HP
 */
public class TerminTableModel extends AbstractTableModel {

    private List<Termin> termini;
    private List<Termin> obrisani;

    public TerminTableModel(List<Termin> termini) {
        this.termini = termini;
        obrisani = new ArrayList<>();
    }

    public List<Termin> getTermini() {
        return termini;
    }

    @Override
    public int getRowCount() {
        return termini == null ? 0 : termini.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Termin t = termini.get(rowIndex);

        switch (columnIndex) {
//            case 0:
//                return t.getKomunalniRadnik().getIme() + " " + t.getKomunalniRadnik().getPrezime();

            case 0:
                return t.getKamion().getBrend() + " " + t.getKamion().getModel();

            case 1:
                return t.getDatum();

            case 2:
                if (t.getDeponijaOdlazak()) {
                    return "Da";
                } else {
                    return "Ne";
                }

            case 3:
                if (t.getReciklazaOdlazak()) {
                    return "Da";
                } else {
                    return "Ne";
                }
            case 4:
                return t.getVremePocetka();
            case 5:
                return t.getVremeZavrsetka();

            default:
                return "n/a";
        }

    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
//            case 0:
//                return "Komunalni radnik";
            case 0:
                return "Kamion";
            case 1:
                return "Datum";
            case 2:
                return "Deponija";
            case 3:
                return "Reciklaza";
            case 4:
                return "Vreme pocetka";
            case 5:
                return "Vreme zavrsetka";
            default:
                return "n/a";
        }
    }

    public Termin getRow(int row) {
        return termini.get(row);

    }

    public void removeRow(int row) {
        if (termini == null) {
            return;
        }
        if (row != -1 && row < termini.size()) {
            Termin t = termini.get(row);
            if (t.getKomunalniRadnik() != null) {
                obrisani.add(t);
            }
            termini.remove(row);
            fireTableDataChanged();
        }
    }

}
