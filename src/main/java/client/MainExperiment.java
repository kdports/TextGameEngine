package client;

import entities.EditorGame;
import entities.Studio;
import javafx.application.Application;

public class MainExperiment {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Studio studio = new Studio();
        EditorGame editorGame = new EditorGame();

        RootDisplayerExperiment gui = new RootDisplayerExperiment();
        gui.begin(args);
    }
}