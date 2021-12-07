package client;

import java.util.ArrayList;

/**
 * Constructor of all themes and make them available for the GUI.
 */
public class ThemeColours {
    public EditorTheme active;
    public ArrayList<EditorTheme> themes = new ArrayList<>();

    public ThemeColours(){
        EditorTheme defaultTheme = new EditorTheme("#ffffff", "#000000", "#e1e1e1",
                "#e1e1e1", "Default");

        EditorTheme Peachy = new EditorTheme("#ff847c", "#2a363b", "#fecea8",
                "#99b898", "Peachy");

        EditorTheme Ocean = new EditorTheme("#78C5DC", "#0D9CBF", "#B7ECEA", "#C5F7EB",
                "Ocean");

        EditorTheme Crimson = new EditorTheme("#132052", "#EDCEA8", "#4D2E5B", "#7F2639",
                "Crimson");

        EditorTheme SeaPort = new EditorTheme("#073b4c", "#748cab", "#f29ca3", "#f4b942",
                "Sea Port");

        EditorTheme Forest = new EditorTheme("#536c45", "#bd9b16", "#244334", "#073630",
                "Forest");

        EditorTheme Tranquil = new EditorTheme("#38a094", "#ffffff", "#33a27f", "#39c7ba",
                "Tranquil");

        EditorTheme Purpleish = new EditorTheme("#735ab3", "#64a7cc", "#9cafff", "#5f6ba4",
                "Purple-ish");

        EditorTheme Boring = new EditorTheme("#875e59", "#f6dca7", "#f4a573", "#864263",
                "Boring");

        this.themes.add(defaultTheme);
        this.themes.add(Peachy);
        this.themes.add(Ocean);
        this.themes.add(Crimson);
        this.themes.add(SeaPort);
        this.themes.add(Forest);
        this.themes.add(Tranquil);
        this.themes.add(Purpleish);
        this.themes.add(Boring);
        this.active = Forest;
    }
}
