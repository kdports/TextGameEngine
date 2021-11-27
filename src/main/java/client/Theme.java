package client;

import java.awt.*;
import javafx.scene.paint.Color;
/**
 * The Theme class contains multiple color variables that store what color certain things in the GUI should be and can
 * be changed based on the theme.
 */
public class Theme {
    public final String[] themeList = {"Light", "Dark", "Pastel", "Blue Sky"};
    public Color textColor;
    public Color backgroundColor;
    public Color textHoverColor;

    /**
     * Constructs the theme object with light as the default theme
     */
    public Theme() {
        textColor = Color.BLACK;
        backgroundColor = Color.WHITE;
        textHoverColor = Color.RED;
    }

    /**
     * Sets the theme in accordance with the string provided.
     *
     * @param theme - The theme to set.
     */
    public void setTheme(String theme) {
        switch (theme) {
            case "Light":
                textColor = Color.BLACK;
                backgroundColor = Color.WHITE;
                textHoverColor = Color.RED;
                break;
            case "Dark":
                textColor = Color.WHITE;
                backgroundColor = Color.BLACK;
                textHoverColor = Color.RED;
                break;
            case "Pastel":
                textColor = Color.DARKGRAY;
                backgroundColor = Color.PINK;
                textHoverColor = Color.ORANGE;
                break;
            case "Blue Sky":
                textColor = Color.WHITE;
                backgroundColor = Color.LIGHTSKYBLUE;
                textHoverColor = Color.GREEN;
                break;
        }
    }
}
