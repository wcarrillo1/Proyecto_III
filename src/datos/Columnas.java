package datos;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
public class Columnas extends DefaultTableColumnModel {
    private boolean primera = true;
    public void addColumn(TableColumn tc) {
        if (primera)
            primera = false;
        else {
            tc.setMinWidth(150);
            super.addColumn(tc);
        }
    }
}
