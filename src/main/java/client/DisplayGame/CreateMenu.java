package client.DisplayGame;

import client.EditorTheme;
import client.ThemeColours;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * The class that creates the menu bar at the top of the Game Renderer Screen
 */
public class CreateMenu {
    GameRenderer gameRenderer;
    ThemeColours theme;

    /**
     * Creates a CreateMenu object and sets the theme and gameRenderer
     *
     * @param theme        - the color theme for the panel
     * @param gameRenderer - the gameRenderer that the menu is on
     */
    CreateMenu(ThemeColours theme, GameRenderer gameRenderer) {
        this.theme = theme;
        this.gameRenderer = gameRenderer;
    }

    /**
     * Creates the menu bar at the top of the window.
     *
     * @return - the menubar that was created
     */
    public MenuBar createMenu() {
        MenuBar mb = new MenuBar();
        // Creates the theme dropdown
        Menu themes = new Menu("Themes");
        // Adds the themes from the themeList as a button to allow the user to switch between themes
        for (EditorTheme t : theme.themes) {
            MenuItem th = new MenuItem(t.name);
            th.setOnAction(e -> {
                theme.active = t;
                gameRenderer.display();
            });
            themes.getItems().add(th);
        }
        // Creates the animation dropdown
        Menu animation = createAnimationMenu();

        // Adds the themes dropdown and the animation dropdown to the menu bar
        mb.getMenus().add(themes);
        mb.getMenus().add(animation);
        mb.setStyle(".label {-fx-background-color:" + theme.active.backgroundColour +
                ";-fx-font-size:16;" + "-fx-text-fill:" + theme.active.textColour + ";}");
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
        String[] speed = {"Off", "Fast", "Medium", "Slow"};
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
