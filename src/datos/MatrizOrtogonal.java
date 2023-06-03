package datos;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class MatrizOrtogonal {

    private static MatrizOrtogonal instancia;
    private Node<Object> center = new Node<>(null);

    public MatrizOrtogonal() {
    }

    public static MatrizOrtogonal getInstancia() {
        if (instancia == null) {
            instancia = new MatrizOrtogonal();
        }
        return instancia;
    }
    public void insert(Object data, int fila, int columna) {
        Node<Object> node = new Node<>(data);
        Node<Object> currentFila = center;
        Node<Object> currentColumna = center;

        // Avanzar hasta la fila deseada
        for (int i = 1; i <= fila; i++) {
            if (currentFila.getDown() == null) {
                currentFila.setDown(new Node<>(null));
            }
            currentFila = currentFila.getDown();
        }

        // Avanzar hasta la columna deseada
        for (int i = 1; i <= columna; i++) {
            if (currentColumna.getRight() == null) {
                currentColumna.setRight(new Node<>(null));
            }
            currentColumna = currentColumna.getRight();
        }

        // Insertar en la posición deseada
        node.setRight(currentColumna.getRight());
        currentColumna.getRight().setLeft(node);
        currentColumna.setRight(node);
        node.setLeft(currentColumna);
        node.setDown(currentFila.getDown().getRight());
        currentFila.getDown().getRight().setUp(node);
        currentFila.setDown(node);
        node.setUp(currentFila);
    }

    public void imprimir() {
        StringBuilder sb = new StringBuilder();
        Node<Object> currentFila = center.getDown();
        while (currentFila != null) {
            Node<Object> currentColumna = currentFila.getRight();
            while (currentColumna != null) {
                Object data = currentColumna.getData();
                sb.append(data != null ? "1 " : "  ");
                currentColumna = currentColumna.getRight();
            }
            sb.append("\n");
            currentFila = currentFila.getDown();
        }

        JOptionPane.showMessageDialog(null, sb.toString(), "Matriz Ortogonal", JOptionPane.INFORMATION_MESSAGE);
    }

    public List<Object> obtenerDatosMatriz() {
        List<Object> datosMatriz = new ArrayList<>();
        Node<Object> currentFila = center.getDown();
        while (currentFila != null) {
            Node<Object> currentColumna = currentFila.getRight();
            while (currentColumna != null) {
                Object data = currentColumna.getData();
                if (data != null) {
                    datosMatriz.add(data);
                }
                currentColumna = currentColumna.getRight();
            }
            currentFila = currentFila.getDown();
        }
        return datosMatriz;
    }

}
