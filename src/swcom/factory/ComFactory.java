package swcom.factory;

import org.jetbrains.annotations.NotNull;
import swcom.com.ComboBoxCom;
import swcom.com.PanelCom;
import swcom.com.TableCom;
import swcom.com.TextFieldCom;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;

/**
 * Factoria de componentes JSwing y Com (heredean de JSwing), listos para ser añandidos al JFrame.
 * Para ulilizar esta Factoria se han de quitar los layout del JFrame.
 * Todos sus metodos son estáticos.
 */
public class ComFactory {

    /**
     * Propociona un TextFielCom con las posibilidades de utilizar el parámetro "label" y que sea solo numérico.
     * @param bounds rectangulo que forma las dimensiones del componente en el Frame.
     * @param label activa la función label-título; para desactivar pasarle cadena en blanco.
     * @param onlynumber combierte a TextFieldCom para texto solo numérico.
     * @param enabled activar o desactivar el TextFielCom.
     * @return TextFielCom listo para ser agregado al JFrame.
     */
    @NotNull
    public static TextFieldCom TextField(@NotNull Rectangle bounds, String label, boolean onlynumber, boolean enabled) {
        TextFieldCom tfc = new TextFieldCom(label, onlynumber);
        tfc.setBounds(bounds);
        tfc.setEnabled(enabled);
        return tfc;
    }

    /**
     * Proporciona un JButton con imagen de fondo y con Tooltip.
     * @param bounds rectángulo que forma las dimensiones del componente en el JFrame.
     * @param pathImg ruta de del archivo .png
     * @param tooltip mensaje que aperecerá en el tooltip; para sin tooltip pasarle cadena vacía.
     * @param enabled activar o desactivar el JButton.
     * @return JButton con imagen de fondo y un tooltip.
     */
    @NotNull
    public static JButton Button(@NotNull Rectangle bounds, String pathImg, String tooltip, boolean enabled) {
        JButton jButton = new JButton();
        jButton.setBounds(bounds);
        jButton.setIcon(new ImageIcon(pathImg));
        jButton.setToolTipText(tooltip);
        jButton.setEnabled(enabled);
        return jButton;
    }

    /**
     * Proporciona un JButton "normal" con su label.
     * @param bounds rectángulo que forma las dimensiones del componente en el JFrame.
     * @param label título que se mostrará. Pasar "" para no mostrar título.
     * @param enabled activar o desactivar el JButton.
     * @return JButton con su label.
     */
    @NotNull
    public static JButton Button(@NotNull Rectangle bounds, String label, boolean enabled) {
        JButton b = new JButton(label);
        b.setBounds(bounds);
        b.setEnabled(enabled);
        return b;
    }

    /**
     * Proporciona un ComboBoxCom.
     * @param bounds rectángulo que forma las dimensiones del componente en el JFrame.
     * @param label añade un primer elemento a la lista que hará de label. Pasar "" para no utilizar label.
     * @param enabled activar o desactivar el ComboBoxCom.
     * @return ComboBoxCom listo para ser añadido al JFrame.
     */
    @NotNull
    public static ComboBoxCom ComboBox(@NotNull Rectangle bounds, @NotNull String label, boolean enabled) {
        ComboBoxCom cbc = (label.equals("")) ? new ComboBoxCom() : new ComboBoxCom(label);
        cbc.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
        cbc.setBounds(bounds);
        cbc.setEnabled(enabled);
        return cbc;
    }

    /**
     * Se ulitiza el componente JLabel para poner cualquier imagen en el JFrame.
     * @param bounds rectángulo que forma las dimensiones del componente en el JFrame.
     * @param pathImg ruta de del archivo .png
     * @return JLabel con la imagen de fondo y con el borde negro.
     */
    @NotNull
    public static JLabel Label(@NotNull Rectangle bounds, String pathImg) {
        JLabel label = new JLabel(new ImageIcon(pathImg));
        label.setBounds(bounds);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return label;
    }

    /**
     * Proporciona un JLabel.
     * @param bounds rectángulo que forma las dimensiones del componente en el JFrame.
     * @param titulo texto que aparecera en el JLabel.
     * @param font fuente del texto del JLabel.
     * @return JLabel listo parar se añadido al JFrame.
     */
    @NotNull
    public static JLabel Label(@NotNull Rectangle bounds, String titulo, Font font) {
        JLabel label = new JLabel(titulo);
        label.setBounds(bounds);
        label.setText(titulo);
        label.setFont(font);
        return label;
    }

    /**
     * Proporciona un PanelCom.
     * @param bounds rectangulo que forma las dimensiones del componente en el Frame.
     * @param label título para el borde; para sin título pasarle cadena en blanco.
     * @param com componentes que se agregarán al PanelCom.
     * @param enabled activar o desactivar el Panel.
     * @return PanelCom listo para ser añadido al JFrame.
     */
    @NotNull
    public static PanelCom Panel(@NotNull Rectangle bounds, String label, @NotNull Component[] com, boolean enabled) {
        PanelCom panelCom = new PanelCom();
        panelCom.setBounds(bounds.getBounds());
        panelCom.setBorder(BorderFactory.createTitledBorder(label));
        panelCom.setEnabled(enabled);
        for(Component aCom : com) panelCom.add(aCom);
        return panelCom;
    }

    /**
     * Proporciona un JPanel para contener un JTable.
     * @param bounds rectangulo que forma las dimensiones del componente en el Frame.
     * @param label título para el borde; para sin título pasarle cadena en blanco.
     * @param tabla JTable que contendrá el JPanel.
     * @param enabled activar o desactivar el Panel.
     * @return JPanel con las propiedades necesarias para contener un JTable.
     */
    @NotNull
    public static JPanel PanelTable(@NotNull Rectangle bounds, String label, TableCom tabla, boolean enabled) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBounds(bounds.getBounds());
        p.setBorder(BorderFactory.createTitledBorder(label));
        p.setEnabled(enabled);
        p.add(new JScrollPane(tabla), BorderLayout.CENTER);
        return p;
    }

    /**
     * Proporciona un TableCom. Por defecto le seteamos un setRowSorter para poder ordenar los datos de la
     * tabla por las columnas.
     * @param nomColum vector de tipo String con el nombre de las columnas de la tabla.
     * @param tamColum vector de tipo Int con el tamaño de las columnas de la tabla.
     * @return TableCom lista para ser añadido al JFrame.
     */
    @NotNull
    public static TableCom Table(String[] nomColum, int[] tamColum) {
        TableCom t = new TableCom(nomColum, tamColum);
        t.setRowSorter(new TableRowSorter<>(t.getModel()));
        return  t;
    }
}