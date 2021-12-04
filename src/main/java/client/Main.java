package client;

import entities.EditorGame;
import entities.Studio;

public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        RootDisplayer gui = new RootDisplayer();
        gui.begin(args);
    }
}