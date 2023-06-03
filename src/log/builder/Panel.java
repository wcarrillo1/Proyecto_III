package log.builder;
import java.awt.*;

import javax.swing.*;

import datos.Columnas;
import datos.Tablas;
import datos.Mtabla;
import InterfazGrafica.Main;

public class Panel extends JPanel implements ConstructorAbs {
    private Mtabla modelo;
    private Columnas columnasTabla;
    private Tablas filasTabla;
    public  JTable tabla;
    private JTable tablaAux;
    private JViewport viewPort;
    private JScrollPane scrollPane;
    private int filaSeleccionada = -1;
    private int columnaSeleccionada = -1;
    private String dataBackup[][] = new String[Main.CANTIDAD_FILAS][Main.CANTIDAD_COLUMNAS+1];
    public Panel() {
        modelo = new Mtabla();
        columnasTabla = new Columnas();
        tabla = new JTable(modelo, columnasTabla);
        tablaAux = new JTable(modelo, filasTabla);
        tabla.createDefaultColumnsFromModel();
        tablaAux.createDefaultColumnsFromModel();
        tabla.setColumnSelectionAllowed(true);
        tabla.setRowSelectionAllowed(true);
        tablaAux.setSelectionModel(tabla.getSelectionModel());
        tablaAux.setMaximumSize(new Dimension(40, 10000));
        tablaAux.setBackground(new Color(238,238,238));
        tablaAux.setEnabled(false);
        tablaAux.setColumnSelectionAllowed(false);
        tablaAux.setCellSelectionEnabled(false);
        viewPort = new JViewport();
        viewPort.setView(tablaAux);
        viewPort.setPreferredSize(tablaAux.getMaximumSize());
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tablaAux.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        scrollPane = new JScrollPane(tabla);
        scrollPane.setRowHeader(viewPort);
        scrollPane.setPreferredSize(new Dimension(790,500));
        add(scrollPane);
    }

    public void setAllDataToModel(String[][] dat) {
        modelo.setAllData(dat);
    }
    public String[][] getAllDataFromModel() {
        return modelo.getAllData();
    }
}