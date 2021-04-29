//-----------------------------------------------------------
//File:   HighScoreList.java
//Desc:   holds a list of all high scores
//        
//-----------------------------------------------------------

package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

// class that holds a list of instances of the HighScore class
public class HighScoreList {

    private ArrayList<HighScore> list = new ArrayList<HighScore>();

    public HighScoreList() {

    }

    /**
     * Use Collections to properly sort the list of highscores
     * 
     * @param none
     * @return list of sorted high scores
     */
    public ArrayList<HighScore> sort() {
        Collections.sort(list);
        Collections.reverse(list);
        return list;
    }

    public ArrayList<HighScore> getList() {
        return list;
    }

    public void setList(ArrayList<HighScore> list) {
        this.list = list;
    }

    /**
     * Save the name and score to scores.txt
     * 
     * @param none
     * @return none
     */
    public void save() {
        try (var writer = new PrintWriter(new FileOutputStream( new File("scores.txt"), true  ))) {
            for (HighScore o : list) {
                writer.append(o.serialize());
            }
            
        } catch (IOException e) {
            System.out.println("Error writing scores file");
        }
    }

    /**
     * Update the list of highscores with information from the save file
     * 
     * @param none
     * @return none
     */
    public void load() {
        try (var reader = new BufferedReader(new FileReader("scores.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                var object = new HighScore("", 0);
                object.unSerialize(line);
                list.add(object);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error reading from scores file");
        }
    }

}
