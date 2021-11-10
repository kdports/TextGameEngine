package client;

import java.awt.*;

public class Theme {
    public final String[] themeList = {"Light", "Dark", "Pastel"};
    public Color textColor;
    public Color backgroundColor;
    public Color textHoverColor;
    public Theme(){
        textColor = Color.BLACK;
        backgroundColor = Color.WHITE;
        textHoverColor = Color.RED;
    }
    public void setTheme(String theme){
        switch (theme){
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
        }
    }
}
