package ui.custom.button;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Finishbutton extends JButton {

    public Finishbutton(final ActionListener actionListener){
        this.setText("Concluir");
        this.addActionListener(actionListener);
    }
}
