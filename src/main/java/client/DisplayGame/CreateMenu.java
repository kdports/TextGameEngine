//package client.DisplayGame;
//
//import client.Theme;
//import entities.Player;
//
//import javax.swing.*;
//
//public class CreateMenu {
//    GameRenderer gameRenderer;
//    /**
//     * Creates the menu bar at the top of the window.
//     *
//     */
//    CreateMenu(Theme theme, Player player, int animationSpeed, JFrame frame, GameRenderer gameRenderer) {
//        this.theme =theme;
//        this.animationSpeed  = animationSpeed;
//        this.player = player;
//        this.frame = frame;
//        this.gameRenderer = gameRenderer;
//    }
//
//    public JMenuBar createMenu() {
//        JMenuBar mb = new JMenuBar();
//        // Creates the theme dropdown
//        JMenu themes = new JMenu("Themes");
//        // Adds the themes from the themeList as a button to allow the user to switch between themes
//        for (String t : theme.themeList) {
//            JMenuItem th = new JMenuItem(t);
//            th.addActionListener(e -> {
//                theme.setTheme(t);
//                gameRenderer.display();
//            });
//            themes.add(th);
//        }
//        // Creates the animation dropdown
//        JMenu animation = createAnimationMenu();
//
//        // Adds the themes dropdown and the animation dropdown to the menu bar
//        mb.add(themes);
//        mb.add(animation);
//        return mb;
//    }
//
//    /**
//     * Creates the animation menu for the menu bar.
//     *
//     * @return - The animation menu;
//     */
//    public JMenu createAnimationMenu() {
//        // Crates an animation speed dropdown to allow users to switch the animation speed
//        // Speed contains the 4 options for the animation speed
//        String[] speed = {"off", "fast", "medium", "slow"};
//        JMenu animation = new JMenu("Animation");
//        int speed_num = 0;
//        for (String s : speed) {
//            JMenuItem sp = new JMenuItem(s);
//            int finalSpeed_num = speed_num;
//            sp.addActionListener(e -> {
//                gameRenderer.animationSpeed = finalSpeed_num;
//                gameRenderer.display();
//            });
//            // Increases the speed with each option
//            speed_num += 5;
//            speed_num *= 2;
//            animation.add(sp);
//        }
//        return animation;
//    }
//}
