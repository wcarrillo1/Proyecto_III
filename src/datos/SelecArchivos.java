package datos;
import java.io.*;
import InterfazGrafica.Main;
import log.builder.Panel;

public class SelecArchivos {
    public File archivoActual;
    public void abrirArchivo(File archivo) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
            ((Panel) Main.getInstancia().constructor).setAllDataToModel((String[][]) ois.readObject());
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        archivoActual = archivo;
    }

    public void guardarArchivo(File archivo) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo));
            String[][] datos = ((Panel) Main.getInstancia().constructor).getAllDataFromModel();
            oos.writeObject(datos);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        archivoActual = archivo;
    }

    public void guardar() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoActual));
            String[][] datos = ((Panel) Main.getInstancia().constructor).getAllDataFromModel();
            oos.writeObject(datos);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}