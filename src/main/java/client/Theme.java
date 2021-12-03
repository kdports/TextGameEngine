package client;

import java.awt.*;
import javafx.scene.paint.Color;
/**
 * The Theme class contains multiple color variables that store what color certain things in the GUI should be and can
 * be changed based on the theme.
 */
public class Theme {
    public final String[] themeList = {"Light", "Dark", "Pastel", "Blue Sky"};
    public String textColor;
    public String backgroundColor;
    public String textHoverColor;

    /**
     * Constructs the theme object with light as the default theme
     */
    public Theme() {
        textColor = "#000000";
        backgroundColor =  "#FFFFFF";
        textHoverColor = "#FF0000";
    }

    /**
     * Sets the theme in accordance with the string provided.
     *
     * @param theme - The theme to set.
     */
    public void setTheme(String theme) {
        switch (theme) {
            case "Light":
                textColor = "#000000";
                backgroundColor =  "#FFFFFF";
                textHoverColor = "#FF0000";
                break;
            case "Dark":
                textColor =  "#FFFFFF";
                backgroundColor = "#000000";
                textHoverColor = "#FF0000";
                break;
            case "Pastel":
                textColor ="#A9A9A9";
                backgroundColor =" #FFC0CB";
                textHoverColor = "#FFA500";
                break;
            case "Blue Sky":
                textColor = "#FFFFFF";
                backgroundColor = "#87CEFA";
                textHoverColor = "#008000";
                break;
        }
    }
}
