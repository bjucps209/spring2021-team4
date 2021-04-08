package model;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

public class HighScoreTest {
    
    HighScore list = new HighScore(new ArrayList<User>());
        

    User user1 = new User("Bob");
    User user2 = new User("Amanda");
    User user3 = new User("Tim");

    // Initializes the list and objects in that list that I will use for further methods
    @Test
    public void initialize() {
        user1.setCoins(100);
        user2.setCoins(200);
        user3.setCoins(300);

        list.getList().add(user1);
        list.getList().add(user2);
        list.getList().add(user3);
    }

    // tests to see if sort sorted by coins and not name
    @Test
    public void testSort() {
        list.sort();

        assertEquals(300, list.getList().get(0).getCoins());
        assertEquals(200, list.getList().get(1).getCoins());
        assertEquals(100, list.getList().get(2).getCoins());
    }

    @Test
    public void testSave() {
        list.save();

        File f = new File("scores.dat");
        assertEquals(true, f.exists());
    }

    @Test
    public void testLoad() {
        list.save();
        var scoreList = new HighScore(new ArrayList<User>());
        scoreList.load();

        assertEquals("Bob", scoreList.getList().get(0).getName());
        assertEquals(100, scoreList.getList().get(0).getCoins());

        assertEquals("Amanda", scoreList.getList().get(1).getName());
        assertEquals(200, scoreList.getList().get(1).getCoins());

        assertEquals("Tim", scoreList.getList().get(2).getName());
        assertEquals(300, scoreList.getList().get(2).getCoins());
    }

}
