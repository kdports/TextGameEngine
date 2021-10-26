package client;

import entities.Studio;

public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Studio studio = new Studio();

        DisplayerBoxScene gui = new DisplayerBoxScene();
        gui.setStudio(studio);

        gui.start(args);
    }
}
