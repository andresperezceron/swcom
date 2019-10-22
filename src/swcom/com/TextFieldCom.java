package swcom.com;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * JTextField modificado. Ofrece un evento de focus que nos permite hacer que el
 * texto de TextFielCom haga de label. Además ofrece otro evento de tipo keypress
 * para hacer que TextFeilCom solo sea numérico.
 * Pasar parametro label "" para desactivar el evento de label-titulo.
 * Pasar onlyNumber true para que el texto solo sea numérico.
 */
public class TextFieldCom extends JTextField {
    private boolean onlyNumber;
    private String label;
    private Font font = new Font(Font.DIALOG, Font.ITALIC, getFont().getSize());
    private Font auxFont;
    private Color background = new Color(254, 249, 231);
    private Color foreground = Color.GRAY;

    /**
     * Contructor.
     * @param label texto para el TextFielCom que hará de label del mismo.
     * @param onlyNumber determina si TextFielCom será alfanumérico o solo numérico.
     */
    public TextFieldCom(String label, boolean onlyNumber) {
        this.label = label;
        this.onlyNumber = onlyNumber;
        auxFont = this.getFont();
        if(onlyNumber) addKeyListener(new eKeyOnlyNumber());
        if(!label.equals("")) {
            setText(label);
            setFont(font);
            setBackground(background);
            setForeground(foreground);
            addFocusListener(new eLabel());
        }
    }

    /**
     * Es "un getText" metodo heredado JTextField. Pero mientras que getText retorna
     * siempre un String, getValue retorna un Object casteable a: String si es texto,
     * int si es entero y double si es un valor numerico decimal. Si TextFieldCom está
     * vacio retorna null cuando es onlyNumeric y cadena vacía cuando es String.
     * @return Objector casteable a: String, int o double.
     */
    public Object getValue() {
        String text = getText();
        boolean isDecimal = text.indexOf(".") > 0;
        if(onlyNumber)
            if(isDecimal) return text.equals(label) ? null : Double.parseDouble(text);
            else return text.equals(label) ? null : Integer.parseInt(text);
        else if(text.equals(label)) return "";
        else return text;
    }

    /**
     * Metodo para reiniciar los valores en TextFieldCom.
     */
    public void reset() {
        if(!label.equals("")) {
            setText(label);
            setFont(font);
            setBackground(background);
            setForeground(foreground);
        }else setText("");
    }

    /**
     * Subclase que implementa el evento KeyListener del TextFielCom.
     */
    private class eKeyOnlyNumber implements KeyListener {

        /**
         * Captura del evento keyTyped de TextFielCom.
         * Este evento es agreado a TextFieldCom si se ha instanciado con el parámetro
         * onlyNumber a true y es el encargado de hacer que TextFieldCom solo sea numérico.
         * @param e evente de tipo keyTiped a capturar.
         */
        @Override
        public void keyTyped(@NotNull KeyEvent e) {
            char key = e.getKeyChar();
            String actualText = ((TextFieldCom) e.getSource()).getText();
            if(key == '.' && actualText.indexOf(".") > 0) e.consume();
            if(key == '.' && actualText.length() == 0) e.consume();
            if(!Character.isDigit(key) && key != '.') e.consume();
        }
        public void keyPressed(KeyEvent arg0){}
        public void keyReleased(KeyEvent arg0){}
    }

    /**
     * Subclase que implementa el evento FocusListener del TextFielCom.
     */
    private class eLabel implements FocusListener {

        /**
         * Captura del evento focusGained de TextFielCom.
         * Este evento es agreado a TextFieldCom si se ha instanciado con el parámetro
         * label y es el encargado de hacer que el text de TextFieldCom haga de label.
         * @param e evento de tipo focusGained a capturar.
         */
        @Override
        public void focusGained(FocusEvent e) {
            if(getText().equals(label)) {
                setText("");
                setFont(auxFont);
                setForeground(Color.BLACK);
                setBackground(Color.WHITE);
            }
        }

        /**
         * Captura del evento focusLost de TextFielCom.
         * Este evento es agreado a TextFieldCom si se ha instanciado con el parámetro
         * titulo y es el encargado de hacer que el text de TextFieldCom haga de label.
         * @param e evento de tipo focusLost a capturar.
         */
        @Override
        public void focusLost(FocusEvent e) {
            if(getText().equals("")) {
                setText(label);
                setFont(font);
                setForeground(foreground);
                setBackground(background);
            }
        }
    }
}