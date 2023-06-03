package datos;
import java.util.List;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import InterfazGrafica.Main;
import log.ExpresionMath;
import log.Formula;

public class Mtabla extends AbstractTableModel {
    private Vector encabezados = new Vector();

    private String datos[][] = new String[Main.CANTIDAD_FILAS][Main.CANTIDAD_COLUMNAS+1];


    private ExpresionMath demultiplexorExpresion;
    private Formula demultiplexorFormula;

    public Mtabla() {
        char letra = 'A';
        encabezados.add(Character.toString('#'));
        for(int i = 0; i < Main.CANTIDAD_COLUMNAS; i++) {
            encabezados.add(Character.toString(letra));
            letra++;
        }
        for(int i = 0; i < Main.CANTIDAD_FILAS; i++) {
            datos[i][0] = Integer.toString(i);
        }
        fireTableStructureChanged();
    }

    public int getRowCount() {
        return datos.length;
    }

    public int getColumnCount() {
        return encabezados.size();
    }

    public String getColumnName(int column) {
        return (String) encabezados.elementAt(column);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0)
            return Integer.toString(rowIndex+1);
        else
            return datos[rowIndex][columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0)
            return false;
        else
            return true;
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(aValue != null && ((String) aValue).length() != 0) {
            if(((String) aValue).startsWith("=")) {
                demultiplexorFormula = new Formula(this, (String) aValue);
                datos[rowIndex][columnIndex] = String.valueOf(demultiplexorFormula.getValor());
            }
            else if((Character.isDigit(((String) aValue).charAt(0)))) {
                MatrizOrtogonal matriz = new MatrizOrtogonal();
                //guarda datos en la matriz
                demultiplexorExpresion = new ExpresionMath((String) aValue);
                datos[rowIndex][columnIndex] = String.valueOf(demultiplexorExpresion.getValor());

                matriz.insert(datos[rowIndex][columnIndex],rowIndex,columnIndex);
                List<Object> data = matriz.obtenerDatosMatriz();
            }
            else if((Character.isLetter(((String) aValue).charAt(0)))) {
                datos[rowIndex][columnIndex] = (String) aValue;
            }
        }
        else
            datos[rowIndex][columnIndex] = null;
        fireTableDataChanged();
    }
    public void setAllData(String[][] str) {
        // Crea una instancia de la matriz ortogonal
        MatrizOrtogonal matriz = new MatrizOrtogonal();

        // Guarda los datos en la matriz ortogonal
        for (int i = 0; i < Main.CANTIDAD_FILAS; i++) {
            for (int j = 0; j < Main.CANTIDAD_COLUMNAS + 1; j++) {
                matriz.insert(str[i][j],i, j );
            }
        }

        // Obtén todos los datos de la matriz ortogonal
        List<Object> datos = matriz.obtenerDatosMatriz();

        // Imprime los datos obtenidos
        for (Object dato : datos) {
            System.out.println(dato);
        }
        fireTableDataChanged();
    }



    public String[][] getAllData() {
        return datos;
    }
}