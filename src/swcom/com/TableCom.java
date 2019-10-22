package swcom.com;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.sql.ResultSet;

/**
 * TableCom es un JTable pero con el método getModel() sobrecargado para poder usar nuestro TableModelCom.
 * Además proporciona el método getSelectedValueAt(int colum).
 */
public class TableCom extends JTable {
    private TableModelCom tm;

    /**
     * Constructor.
     * @param nom_col nombres de la columnas que harán de cabeceras en el TableCom.
     * @param tam_col el tamaño de las columnas.
     */
    public TableCom(@NotNull String[] nom_col, int[] tam_col) {
        tm = new TableModelCom();
        for(String s : nom_col) tm.addColumn(s);
        this.setModel(tm);
        for(int i=0; i<tam_col.length; i++)
            this.getColumnModel().getColumn(i).setMaxWidth(tam_col[i]);
    }

    /**
     * Con este método obtenemos el TableModel del JTable. Notesé que se sobreescribe el método para recibir
     * un TableModelCom que es el que utilizamos.
     * @return TableModelCom.
     */
    public TableModelCom getModel() {
        return tm;
    }

    /**
     * Carga la tabla con los datos que contenga el ResultSet.
     * @param resultset cargado con los datos de la consulta sql. Observar que se utiliza TableModelCom.
     */
    public void load(ResultSet resultset) {
        tm.load(resultset);
    }

    /**
     * Carga la tabla con los datos que contenga la matriz de objetos.
     * @param data matriz de tipo Object con los datos a insertar. Observar que se ulitiza TableModelCom.
     */
    public void load(Object[][] data) {
        tm.load(data);
    }

    /**
     * Obtenemos un valor de la tabla según nos indique la fila en selección y el parámetro colum nos indica
     * la celda concreta de la fila en selección. Una mezcla de getValuAt(fila, colum), pero de la fila en
     * selección.
     * @param colum indica el valor de la celda dentro de la fila en selección.
     * @return valor de la celda. Tipo Object.
     */
    public Object getSelectdValueAt(int colum) {
        return this.getValueAt(this.getSelectedRow(), colum);
    }
}
