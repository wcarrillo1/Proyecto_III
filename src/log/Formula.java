package log;
import java.util.StringTokenizer;
import javax.swing.table.AbstractTableModel;
public class Formula {
    private String cadena;
    private String fila;
    private String columna;
    private StringTokenizer strToken;
    private String datoCelda;
    private boolean isCelda = false;
    private ExpresionMath expresionMath;
    public Formula(AbstractTableModel tabla, String cad) {
        strToken = new StringTokenizer(cad, "=+*-/^() ", true);
        cadena = new String();
        fila = new String();
        columna = new String();

        while(strToken.hasMoreTokens()) {
            String g = strToken.nextToken();
            if(!g.equals("=") && !g.equals(" ")) {
                char a = 'A';
                for(int i=0; i < 26; i++) {
                    if(g.startsWith(Character.toString(a))) {
                        isCelda = true;
                        StringTokenizer str = new StringTokenizer(g, Character.toString(a), true);
                        if(str.countTokens() == 2) {
                            columna = str.nextToken();
                            fila = str.nextToken();
                            try {
                                datoCelda = (String) tabla.getValueAt(Integer.valueOf(fila).intValue()-1, validarColumna(columna));
                            } catch (NumberFormatException e) {
                                System.err.println("Celda no valida");
                                return;
                            }
                            if(datoCelda == null) {
                                System.err.println("La Celda " + columna + fila + " no contiene ningun valor");
                                return;
                            }
                            cadena += datoCelda;
                        }
                        else {
                            System.err.println("Celda no valida");
                            return;
                        }
                    }
                    a++;
                }
                if(!isCelda)
                    cadena += g;
                isCelda = false;
            }
        }
        expresionMath = new ExpresionMath(cadena);
    }
    public double getValor() {
        // Si ocurre un error en la evaluacion de la expresion
        if(expresionMath == null)
            return 0;
        else
            return expresionMath.getValor();
    }
    private int validarColumna(String s) {
        char c = 'A';

        for(int i=0; i < 26; i++) {
            if(s.charAt(0) == c)
                return i+1;
            c++;
        }
        return 0;
    }
}