package swcom.com;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ComboBoxCom es un JComboBox pero que se le añaden los metodos load(Resultset) y setSelectedIndexByItem(Obj item).
 * load(ResultSet) nos propociona una forma muy fácil de cargar los datos en el ComboBoxCom; además tiene capacidad
 * para cargar "ResusltSet" que contengan más de una columna.
 * setSelectedIndexByItem(Object) proporciona la selección de un elemento de la lista pasando el propio valor de la
 * lista como parámetro.
 * Posee dos constructores: ComboBoxCom() y CoboBoxCom(String label). Este último añade como primer elemento de la
 * lista el valor del parámetro label, haciendo de "label" del JComboBox.
 */
public class ComboBoxCom extends JComboBox<Object> {

    /**
     * Constructor.
     */
    public ComboBoxCom() {}

    /**
     * Constructor
     * @param label el primer elemento que aparecerá en la lista del ComboBoxCom para hacer de label.
     */
    public ComboBoxCom(String label) {
        this.addItem(label);
    }

    /**
     * Carga ComboBoxCom con los datos que contiene el ResultSet.
     * Es capaz de cargar un ResultSet que contengan más de una fila.
     * @param resultset ResultSet obtenido tras la consulta sql.
     */
    public void load(@NotNull ResultSet resultset) {
        try{
            int colum_count = resultset.getMetaData().getColumnCount();
            while(resultset.next()) {
                StringBuilder item = new StringBuilder();
                for(int i=0; i<colum_count; i++)
                    item.append((i + 1 == colum_count) ?
                            resultset.getObject(i + 1) :
                            resultset.getObject(i + 1) + "   -   ");
                this.addItem(item.toString());
            }
            resultset.close();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    /**
     * Método que nos proporciona la posibilidad de hacer setSelectedIndex(index) sobre un elemento que
     * contenga la lista de ComboBoxCom pasando como parámetro el propio valor del item.
     * @param item valor del item de la lista del ComboBoxCom a seleccionar.
     */
    public void setSelectedIndexByItem(Object item) {
        for(int i = 0; i < this.getItemCount(); i++)
            if(this.getItemAt(i).equals(item))
                this.setSelectedIndex(i);
    }
}
