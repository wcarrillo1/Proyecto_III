package datos;
import javax.swing.table.*;

public class Tablas extends DefaultTableColumnModel {

    private boolean first = true;

    public void addColumn(TableColumn tc) {
        // Solo nos interesa la primera, el resto no importa
        if(first) {
            tc.setMaxWidth(25);
            super.addColumn(tc);
            first = false;
        }
    }
}