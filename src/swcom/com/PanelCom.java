package swcom.com;

import javax.swing.*;

/**
 * Proporciona un JPanel sin Layout y además añade la propiedad enabledAllCom, que
 * activa o desactiva todos los componentes que contenga el JPanel incluido el mismo.
 */
public class PanelCom extends JPanel {

    /**
     * Contructor.
     */
    public PanelCom() {
        this.setLayout(null);
    }

    /**
     * Activa o desactiva (propiedad enabled) de todos los componentes que contenga el PanelCom.
     * @param enabled true para activar false para desactivar todos sus componentes.
     */
    public void enabledAllCom(boolean enabled) {
        this.setEnabled(enabled);
        for(int i=0; i<this.getComponentCount(); i++)
            this.getComponent(i).setEnabled(enabled);
    }
}