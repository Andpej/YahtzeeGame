
public class Player {

	private String playerName;
	private int score;

	public Player(String playerName) {
		this.playerName = playerName;
	}

	public String getPlayerName() {
		return playerName;
	}

	public int getScore() {
		return score;
	}

	public void addScore(int totalScore) {
		this.score = totalScore;
	}

	public String toString() {
		return playerName;
	}

}
