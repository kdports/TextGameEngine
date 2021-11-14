package client;

import java.awt.*;

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
                textColor = Color.DARK_GRAY;
                backgroundColor = Color.PINK;
                textHoverColor = Color.orange;
                break;
            case "Blue Sky":
                textColor = new Color(255, 255, 255);
                backgroundColor = new Color(159, 208, 255);
                textHoverColor = new Color(22, 158, 40);
                break;
        }
    }
}
