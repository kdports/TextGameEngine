package client.DisplayGame;

import client.Theme;
import entities.Player;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import javax.swing.*;

public class CreateMenu {
    GameRenderer gameRenderer;
    Player player;
    Theme theme;
    int animationSpeed;
    Pane pane;
    /**
     * Creates the menu bar at the top of the window.
     *
     */
    CreateMenu(Theme theme, Player player, int animationSpeed, Pane pane, GameRenderer gameRenderer) {
        this.theme =theme;
        this.animationSpeed  = animationSpeed;
        this.player = player;
        this.pane = pane;
        this.gameRenderer = gameRenderer;
    }

    public MenuBar createMenu() {
        MenuBar mb = new MenuBar();
        // Creates the theme dropdown
        Menu themes = new Menu("Themes");
        // Adds the themes from the themeList as a button to allow the user to switch between themes
        for (String t : theme.themeList) {
            MenuItem th = new MenuItem(t);
            th.setOnAction(e -> {
                theme.setTheme(t);
                gameRenderer.display();
            });
            themes.getItems().add(th);
        }
        // Creates the animation dropdown
        Menu animation = createAnimationMenu();

        // Adds the themes dropdown and the animation dropdown to the menu bar
        mb.getMenus().add(themes);
        mb.getMenus().add(animation);
        mb.setStyle("-fx-background-color: #FFFFFF");
        return mb;
    }

    /**
     * Creates the animation menu for the menu bar.
     *
     * @return - The animation menu;
     */
    public Menu createAnimationMenu() {
        // Crates an animation speed dropdown to allow users to switch the animation speed
        // Speed contains the 4 options for the animation speed
        String[] speed = {"off", "fast", "medium", "slow"};
        Menu animation = new Menu("Animation");
        int speed_num = 0;
        for (String s : speed) {
            MenuItem sp = new MenuItem(s);
            int finalSpeed_num = speed_num;
            sp.setOnAction(e -> {
                gameRenderer.animationSpeed = finalSpeed_num;
                gameRenderer.display();
            });
            // Increases the speed with each option
            speed_num += 5;
            speed_num *= 2;
            animation.getItems().add(sp);
        }
        return animation;
    }
}
