package client;

import java.util.ArrayList;

// Each theme is hardcoded into here. the default theme is default but can be changed
// each theme is stored in a list of themes
public class ThemeColours {
    public EditorTheme active;
    public ArrayList<EditorTheme> themes = new ArrayList<>();

    public ThemeColours(){
        EditorTheme allWhite = new EditorTheme("#ffffff", "#000000", "#ffffff",
                "#ffffff", "All White");

        EditorTheme allBlack = new EditorTheme("#000000", "#ffffff", "#000000",
                "#000000", "All Black");

        EditorTheme defaultTheme = new EditorTheme("", "#000000", "#ffffff",
                "#e1e1e1", "Default");

        EditorTheme theme1 = new EditorTheme("#ff847c", "#000000", "#fecea8",
                "#99b898", "Theme1");

        this.themes.add(allWhite);
        this.themes.add(allBlack);
        this.themes.add(defaultTheme);
        this.themes.add(theme1);
        this.active = theme1;
    }
}
