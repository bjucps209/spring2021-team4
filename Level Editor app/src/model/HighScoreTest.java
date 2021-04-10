package model;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

public class HighScoreTest {
    
    ArrayList<HighScore> list = new ArrayList<HighScore>();
    

    HighScore user1 = new HighScore("Bob", 100);
    HighScore user2 = new HighScore("Amanda", 300);
    HighScore user3 = new HighScore("Tim", 200);

    // Initializes the list and objects in that list that I will use for further methods
    @Test
    public void initialize() {

        list.add(user1);
        list.add(user2);
        list.add(user3);
    }

    // tests to see if sort sorted by coins and not name
    @Test
    public void testSort() {
        initialize();
        var realList = new HighScoreList(list);
        realList.sort();

        assertEquals(300, realList.getList().get(0).getScore());
        assertEquals(200, realList.getList().get(1).getScore());
        assertEquals(100, realList.getList().get(2).getScore());
    }

    @Test
    public void testSave() {
        initialize();
        var realList = new HighScoreList(list);
        realList.save();

        File f = new File("scores.txt");
        assertEquals(true, f.exists());
    }

    @Test
    public void testLoad() {
        var scoreList = new HighScoreList(new ArrayList<HighScore>());
        scoreList.load();

        assertEquals("Bob", scoreList.getList().get(0).getName());
        assertEquals(100, scoreList.getList().get(0).getScore());

        assertEquals("Amanda", scoreList.getList().get(1).getName());
        assertEquals(300, scoreList.getList().get(1).getScore());

        assertEquals("Tim", scoreList.getList().get(2).getName());
        assertEquals(200, scoreList.getList().get(2).getScore());
    }

}
