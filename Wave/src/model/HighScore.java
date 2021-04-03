package model;

import java.util.ArrayList;

public class HighScore implements Comparable<User> {
    // List containing player name and score

    private ArrayList<User> list = new ArrayList<User>();

    public HighScore(ArrayList<User> list) {
        this.list = list;
    }

    // will modify the Collections.sort method to sort by score
    @Override
    public int compareTo(User o) {
        return 0;
    }

    // Use collections to properly sort the list
    public ArrayList<User> sort() {
        return null;
    }

    public ArrayList<User> getList() {
        return list;
    }
    
    public void setList(ArrayList<User> list) {
        this.list = list;
    }

    // Save the name and score to a file 
    public void save() {

    }

    // Update the list with information from the save file
    public void load() {

    }


}
