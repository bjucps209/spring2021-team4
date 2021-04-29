//-----------------------------------------------------------
//File:   User.java
//Desc:   represents a user who has coins and lists of owned
//        ship skins
//-----------------------------------------------------------

package model;

import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Enums.ShipSkins;

// class that is created when someone creates an account in the game. holds variables such as coins, a name, and owned skins
public class User {
    // contains all necessary info for a Player (coins, currentShip, name)
    private String name;  // user namee
    private int coins; // The total coins user owns
    private ShipSkins ship; // current shipSkin user is using
    private ArrayList<ShipSkins> ownedShipSkins = new ArrayList<ShipSkins>(); // use to record all shipSkins that the
                                                                              // user owned
    private boolean isValidUser;

    // This constructor should only be called in Load / Save user profile.
    public User() {

    }

    public User(String name) {
        this.name = name;
        if (name.equals("")) {
            isValidUser = false;
        } else {
            isValidUser = true;
        }
        ownedShipSkins.add(ShipSkins.SHIP1);
        ship = ShipSkins.SHIP1;
    }

    /**
     * This function will return a string value correspond to the saving format in
     * serialization design
     * 
     * @param none
     * @return String value
     */
    public String serialization() {
        return name + ";" + coins + ";" + ship.toString() + ";"
                + ownedShipSkins.toString().replace("[", "").replace("]", "").replaceAll(" ", "");
    }

    /**
     * 
     * @param information- a String array consist of userName,coins, currentShip,
     *                     ownedShipSkin
     * @return true if successfully load the information into User false if error
     *         occurs in loading or converting the information
     */
    public boolean deserialization(String[] information) {
        try {
            String userName = information[0];
            int coins = Integer.parseInt(information[1]);
            ShipSkins currentShip = ShipSkins.valueOf(information[2]);

            ArrayList<ShipSkins> ownedSkins = new ArrayList<ShipSkins>();
            for (String skins : information[3].split(",")) {
                ownedSkins.add(ShipSkins.valueOf(skins));
            }

            this.name = userName;
            this.coins = coins;
            this.ship = currentShip;
            this.ownedShipSkins = ownedSkins;
            return true;
        } catch (Exception e) {
            // means error in converting file
            return false;
        }

    }

    /**
     * method to load our list of default levels and return them in an arraylist
     * 
     * @param skin the type of skin the user wants to buy
     * @return true if the user has bought the skin, false if the user does not have
     *         enough coins
     */
    public boolean buy(ShipSkins skin) {
        for (ShipSkins s : ownedShipSkins) {
            if (skin.equals(s)) {
                ship = skin;
                // exception to be caught in MainWindow, indicates that the user owns this skin
                throw new IllegalArgumentException();
            }
        }
        if (coins >= 1000) {
            coins -= 1000;
            ownedShipSkins.add(skin);
            return true;
        } else {
            var alert = new Alert(AlertType.ERROR, "You do not have enough coins.");
            alert.show();
            return false;
        }
    }

    public boolean isIsValidUser() {
        return this.isValidUser;
    }

    // --- getter --
    public ArrayList<ShipSkins> getOwnedShipSkins() {
        return ownedShipSkins;
    }

    public boolean getIsValidUser() {
        return this.isValidUser;
    }

    public ShipSkins getShip() {
        return this.ship;
    }

    public String getName() {
        return this.name;
    }

    public int getCoins() {
        return this.coins;
    }

    // --- setter ---
    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShip(ShipSkins ship) {
        this.ship = ship;
    }

    public void setOwnedShipSkins(ArrayList<ShipSkins> ownedShipSkins) {
        this.ownedShipSkins = ownedShipSkins;
    }

    public void setIsValidUser(boolean isValidUser) {
        this.isValidUser = isValidUser;
    }

    public void setValidUser(boolean isValidUser) {
        this.isValidUser = isValidUser;
    }

}
