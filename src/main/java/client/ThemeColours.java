package client;

import java.util.ArrayList;

// Each theme is hardcoded into here. the default theme is default but can be changed
// each theme is stored in a list of themes
public class ThemeColours {
    public EditorTheme active;
    public ArrayList<EditorTheme> themes = new ArrayList<>();

    public ThemeColours(){
        EditorTheme defaultTheme = new EditorTheme("#ffffff", "#000000", "#ffffff",
                "#e1e1e1", "Default");

        EditorTheme Peachy = new EditorTheme("#ff847c", "#2a363b", "#fecea8",
                "#99b898", "Peachy");

        EditorTheme Ocean = new EditorTheme("#78C5DC", "#0D9CBF", "#B7ECEA", "#C5F7EB",
                "Ocean");

        EditorTheme Crimson = new EditorTheme("#132052", "#EDCEA8", "#4D2E5B", "#7F2639",
                "Crimson");

        this.themes.add(defaultTheme);
        this.themes.add(Peachy);
        this.themes.add(Ocean);
        this.themes.add(Crimson);
        this.active = Ocean;
    }
}
