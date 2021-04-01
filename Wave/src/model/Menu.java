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

    /**
    * Returns void. 
    * <p>
    * This method starts the menu with an empty User (name = ""). 
    */
    public void initialize() {
        currentUser = new User(""); // create empty user. Not included in user list
    }

    // Load User and set as current user
    public void loadUser() {
        coins = currentUser.getCoins();
    }

    public static Menu getInstance() {
        if (menu == null) {
            menu = new Menu();
        }

        return menu;
    }
}
