package ui.custom.button;

import javax.swing.*;
import java.awt.event.ActionListener;

public class buttomCheckGameStatus extends JButton {

    public buttomCheckGameStatus(final ActionListener actionListener){
        this.setText("Verificar jogo");
        this.addActionListener(actionListener);
    }
}
