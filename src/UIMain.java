import ui.custom.frame.MainFrame;
import ui.custom.panel.MainPanel;
import ui.custom.screen.MainScreen;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

//Esse main é possui as ferramentas para rodar um uma tela interativa através do java
public class UIMain {

    public static void main(String[] args) {
        final var gameConfig = Stream.of(args)
                .collect(toMap(k -> k.split(";")[0], v -> v.split(";")[1]));
        var mainsScreen = new MainScreen(gameConfig);
        mainsScreen.buildMainScreen();
        }
    }

