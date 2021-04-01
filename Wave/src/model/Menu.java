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
    public void initialize() {
        currentUser = new User(""); // create empty user. Not included in user list
        coins = currentUser.getCoins();
    }

    public static Menu getInstance() {
        if (menu == null) {
            menu = new Menu();
        }

        return menu;
    }
}
