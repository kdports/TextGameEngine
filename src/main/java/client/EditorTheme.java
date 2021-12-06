package client;

/**
 * The attributes of a theme that are used throughout the GUI
 */
public class EditorTheme {
    public String backgroundColour;
    public String textColour;
    public String slideColour;
    public String sidebarColour;
    public String name;

    public EditorTheme(String backgroundColour, String textColour,
                       String slideColour, String sidebarColour, String name){
        this.backgroundColour = backgroundColour;
        this.sidebarColour = sidebarColour;
        this.slideColour = slideColour;
        this.textColour = textColour;
        this.name = name;
    }
}
