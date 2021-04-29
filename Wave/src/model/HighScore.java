//-----------------------------------------------------------
//File:   HighScore.java
//Desc:   represents one individual highscore
//        this is correlated with a user's name attribute
//-----------------------------------------------------------

package model;

// class that associates a username with a score to be displayed in the high scores auxiliary screen
public class HighScore implements Comparable<HighScore> {
    String name;
    int score;

    public HighScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    // will modify the Collections.sort method to sort by score
    @Override
    public int compareTo(HighScore o) {
        return (this.getScore() < o.getScore() ? -1 : (this.getScore() == o.getScore() ? 0 : 1));
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String serialize() {
        return name + "-" + score + "\n";
    }

    public void unSerialize(String line) {
        String[] info = line.split("-");

        name = info[0];
        score = Integer.parseInt(info[1]);
    }
}