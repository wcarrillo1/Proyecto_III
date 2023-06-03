package InterfazGrafica;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import datos.SelecArchivos;
import log.builder.ConstructorAbs;
import log.builder.Menu;
import log.builder.Panel;

public class Main extends JFrame implements WindowListener {
    private static Main instancia;
    private JMenuItem salir;
    public final static int CANTIDAD_FILAS = 100;
    public final static int CANTIDAD_COLUMNAS = 26;
    public ConstructorAbs constructor;
    public SelecArchivos selecArchivos;
    private Main() {
        selecArchivos = new SelecArchivos();
        constructor = new Menu();
        salir = ((Menu) constructor).salirItem;
        setJMenuBar((JMenuBar) constructor);
        constructor = new Panel();
        add((Component) constructor, BorderLayout.CENTER);
        addWindowListener(this);
        setTitle("Hoja de Calculo");
        setLocationByPlatform(true);
        setSize(800,600);
        setVisible(true);
    }

    public static Main getInstancia() {
        if(instancia == null)
            instancia = new Main();
        return instancia;
    }

    public static void main(String[] args) {
        Main.getInstancia();
    }


    public void windowClosing(WindowEvent e) {
        salir.doClick();
    }
    public void windowOpened(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
}