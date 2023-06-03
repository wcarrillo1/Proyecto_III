package log.builder;
import java.awt.event.*;

import javax.swing.*;

import datos.Archivo;
import InterfazGrafica.Main;
import datos.MatrizOrtogonal;

public class Menu extends JMenuBar implements ConstructorAbs {
    private JMenu archivoMenu = new JMenu("Archivo");
    private JMenu ayudaMenu = new JMenu("Ayuda");
    private JMenuItem nuevoItem = new JMenuItem("Nuevo");
    private JMenuItem abrirItem = new JMenuItem("Abrir");
    private JMenuItem guardarItem = new JMenuItem("Guardar");
    public JMenuItem salirItem = new JMenuItem("Salir");
    private JMenuItem creditosItem = new JMenuItem("Creditos");

    private JMenuItem matriz = new JMenuItem("Matriz");

    public Menu() {
        archivoMenu.setMnemonic('a');
        ayudaMenu.setMnemonic('y');
        nuevoItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getInstancia().selecArchivos.archivoActual = null;
                ((Panel) Main.getInstancia().constructor).setAllDataToModel(new String[Main.CANTIDAD_FILAS][Main.CANTIDAD_COLUMNAS]);
            }
        });
        nuevoItem.setMnemonic('n');
        nuevoItem.setToolTipText("Crear una Tabla Vacia");
        archivoMenu.add(nuevoItem);
        archivoMenu.addSeparator();
        abrirItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setMultiSelectionEnabled(false);
                chooser.addChoosableFileFilter(new Archivo());
                int option = chooser.showOpenDialog(Main.getInstancia());
                if (option == JFileChooser.APPROVE_OPTION) {
                    Main.getInstancia().selecArchivos.abrirArchivo(chooser.getSelectedFile());
                }
            }
        });
        abrirItem.setMnemonic('a');
        abrirItem.setToolTipText("Abrir un archivo");
        archivoMenu.add(abrirItem);
        guardarItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Main.getInstancia().selecArchivos.archivoActual != null) {
                    Main.getInstancia().selecArchivos.guardar();
                }
                else {
                    JFileChooser chooser = new JFileChooser();
                    chooser.setMultiSelectionEnabled(false);
                    chooser.addChoosableFileFilter(new Archivo());
                    int option = chooser.showSaveDialog(Main.getInstancia());
                    if (option == JFileChooser.APPROVE_OPTION) {
                        Main.getInstancia().selecArchivos.guardarArchivo(chooser.getSelectedFile());
                    }
                }
            }
        });
        guardarItem.setMnemonic('g');
        guardarItem.setToolTipText("Guardar el archivo actual");
        archivoMenu.add(guardarItem);
        archivoMenu.addSeparator();
        salirItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Main.getInstancia().selecArchivos.archivoActual == null) {
                    int option = JOptionPane.showConfirmDialog(Main.getInstancia(), "El archivo no ha sido guardado. Desea guardarlo ahora", "Guardar...", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (option == JOptionPane.YES_OPTION)
                        guardarItem.doClick();
                    if (option == JOptionPane.NO_OPTION)
                        System.exit(0);
                }
                else
                    System.exit(0);
            }
        });
        salirItem.setMnemonic('s');
        salirItem.setToolTipText("Salir del programa");
        archivoMenu.add(salirItem);

        creditosItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Main.getInstancia(), "\"Hoja de  Calculo\" Desarrollado por:\n" +
                        "Mario Fernando Castañeda Perez - 7690-20-145\n" +
                        "William Josue Carrillo Sandoval - 7690-21-3740\n" +
                        "Marco Amed Marroquin Quiñonez - 7690 -09-7104\n" +
                        "Juan Carlos Alvarado Montes de oca - 7690-18-5031 \n" +
                        "Programacion 3\n" +
                        "Ingenieria de Sistemas\n" +
                        "Universidad Mariano Galvez");
            }
        });
        creditosItem.setMnemonic('c');
        creditosItem.setToolTipText("Mostrar los creditos de la aplicacion");
        ayudaMenu.add(creditosItem);
        add(archivoMenu);
        add(ayudaMenu);

        matriz.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {
                MatrizOrtogonal data = MatrizOrtogonal.getInstancia();
                data.obtenerDatosMatriz();
            }
        });
        creditosItem.setMnemonic('c');
        creditosItem.setToolTipText("matriz");
        ayudaMenu.add(matriz);
        add(archivoMenu);
        add(ayudaMenu);
    }
}