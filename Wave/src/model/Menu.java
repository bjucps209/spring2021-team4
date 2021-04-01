package model;

public class Menu {
    // Class that contains variables and methods for the main menu

    // Singleton variable
    private static Menu menu = null;
    
    // Instance variables
    private User currentUser;
    private int coins;

    // Constructor
    private Menu() {

    }

    // Initializes main screen info and prepares game
    public User initialize() {
        return new User(); // create empty user. Not included in user list
    }

    public static Menu getInstance() {
        if (menu == null) {
            menu = new Menu();
        }

        return menu;
    }
}
