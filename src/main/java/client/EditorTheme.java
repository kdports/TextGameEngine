package client;

import java.util.ArrayList;

// This is what a theme is comprised of. 4 colours and a name
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
