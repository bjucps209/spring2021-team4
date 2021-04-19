package model;

import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Enums.ShipSkins;

public class User {
    // contains all necessary info for a Player (coins, currentShip, name)
    private String name;
    private int coins;
    private ShipSkins ship; // current shipSkin user is using
    private ArrayList<ShipSkins> ownedShipSkins = new ArrayList<ShipSkins>(); // use to record all shipSkins that the
                                                                              // user owned
    private boolean isValidUser;

    // This constructor should only be called in Load / Save user profile.
    public User(){

    }
    public User(String name) {
        this.name = name;
        if (name.equals("")) {
            isValidUser = false;
        }
    }
    
    /**
     * This function will return a string value correspond to the saving format in serialization design
     * @return String value
     */
    public String serialization(){
        return name+";"+coins+";"+ship.toString()+";"+ ownedShipSkins.toString().replace("[", "").replace("]", "").replaceAll(" ", "");
    }
    /**
     * 
     * @param information- a String array consist of userName,coins, currentShip, ownedShipSkin
     * @return true if successfully load the information into User
     *          false if error occurs in loading or converting the information
     */
    public boolean deserialization(String [] information){
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
            //means error in converting file
            return false;
        }
        

    }

    // method to buy a ship skin from the shop. used in mainwindow's onSkinClicked method
    public void buy(ShipSkins skin) {
        for (ShipSkins s : ownedShipSkins) {
            if (skin.equals(s)) {
                var alert = new Alert(AlertType.INFORMATION, "You already own that skin.");
                alert.show();
                return;
            }
        }
        System.out.println("bought" + coins);
        coins -= 100;
        System.out.println(coins);
        ownedShipSkins.add(skin);

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
