package client;

import java.util.ArrayList;

// Each theme is hardcoded into here. the default theme is default but can be changed
// each theme is stored in a list of themes
public class ThemeColours {
    public EditorTheme active;
    public ArrayList<EditorTheme> themes = new ArrayList<>();

    public ThemeColours(){
        EditorTheme allWhite = new EditorTheme("-fx-background-color: #ffffff; ",
                "-fx-background-color: #ffffff; ",
                "-fx-background-color: #ffffff; ",
                "-fx-background-color: #ffffff; ",
                "All White");

        EditorTheme allBlack = new EditorTheme("-fx-background-color: #000000; ",
                "-fx-background-color: #000000; ",
                "-fx-background-color: #000000; ",
                "-fx-background-color: #000000; ",
                "All Black");

        EditorTheme defaultTheme = new EditorTheme("", "", "",
                "", "Default");

        this.themes.add(allWhite);
        this.themes.add(allBlack);
        this.themes.add(defaultTheme);
        this.active = defaultTheme;
    }
}
