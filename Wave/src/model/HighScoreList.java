package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class HighScoreList {
    // List containing player name and score

    private ArrayList<HighScore> list = new ArrayList<HighScore>();

    public HighScoreList() {
        
    }

    // Use collections to properly sort the list
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

    // Save the name and score to a file 
    public void save() {
        try (var writer = new PrintWriter(new FileWriter("scores.txt"))) {
            for (HighScore o : list) {
                writer.write(o.serialize());
            } 
        } catch (IOException e) {
            System.out.println("Error writing scores file");
        }
    }

    // Update the list with information from the save file
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
