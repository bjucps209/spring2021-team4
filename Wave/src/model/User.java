package model;

import java.util.ArrayList;
import java.util.ArrayList;
import model.Enums.ShipSkins;

public class User {
    // contains all necessary info for a Player (coins, currentShip, name)
    private String name;
    private int coins;
    private ShipSkins ship;  // current shipSkin user is using
    private ArrayList<ShipSkins> ownedShipSkins = new ArrayList<ShipSkins>(); // use to record all shipSkins that the user owned
    private boolean isValidUser;

    public User(String name) {
        this.name = name;
        if (name.equals("")) {
            isValidUser = false;
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCoins() {
        return this.coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public ShipSkins getShip() {
        return this.ship;
    }

    public void setShip(ShipSkins ship) {
        this.ship = ship;
    }

    public boolean isIsValidUser() {
        return this.isValidUser;
    }

    public boolean getIsValidUser() {
        return this.isValidUser;
    }

    public void setIsValidUser(boolean isValidUser) {
        this.isValidUser = isValidUser;
    }
}
