package datos;
import java.io.File;
import javax.swing.filechooser.FileFilter;

public class Archivo extends FileFilter {
    public boolean accept(File f) {
        if(f.isDirectory())
            return true;
        if (f.getName().endsWith(".cam"))
            return true;
        return false;
    }
    public String getDescription() {
        return "\"*.cam\" Archivo de Hoja de Calculo - Proyecto III UMG";
    }
}
