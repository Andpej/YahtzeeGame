import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

public class YatzyGame {

	private Scanner keyboard = new Scanner(System.in);
	private Player[] players;
	private Die[] dice;
	private final int NBROFDICE = 5;

	private String readString() {
		return keyboard.nextLine();
	}

	private int readInt() {
		int i = keyboard.nextInt();
		keyboard.nextLine();
		return i;
	}

	public void commandLoop() {

		while (true) {
			System.out.print("Welcome to Yatzy! What would you like to do?\n\n1. Start new game\n2. " + "Exit game");

			String cmd = readString().toLowerCase();
			switch (cmd) {
			case "1":
				startGame();
				break;
			case "2":
				System.out.println("Game terminated!");
				System.exit(0);

			default:
				System.out.println("\n" + "Wrong command!");
				break;
			}
		}
	}

	public void startGame() {
		addPlayer();

		for (int i = 0; i < players.length; i++) {
			rollDice(i);
			rerollDice(i);
			registerSum(i);
			printPlayerSum(i);
		}
		printScoreboard();
	}

	public void addPlayer() {
		System.out.println("Enter how many players: ");
		int noOfPlayers = readInt();
		while (noOfPlayers < 2) {
			System.out.println("Must be atleast 2 players! Try again:");
			noOfPlayers = readInt();
		}
		players = new Player[noOfPlayers];
		for (int i = 0; i < players.length; i++) {
			System.out.println("Enter name for Player " + (i + 1) + ":");
			String playerName = readString();

			players[i] = new Player(playerName);
		}
		dice = new Die[NBROFDICE];
		for (int a = 0; a < dice.length; a++) {
			dice[a] = new Die();
		}
	}

	public void rollDice(int a) {
		System.out.println(players[a].getPlayerName() + "s turn. press enter to roll dice.");
		String input = readString();
		if (input.equalsIgnoreCase("")) {
			for (int d = 0; d < dice.length; d++) {
				dice[d].roll();
			}	
			}
		}
	

	public void rerollDice(int a) {

		int rollCount = 0;
		while (rollCount < 2) {
			System.out.println("Your dice show " + Arrays.toString(dice) + "\n" + "Do you want to re-roll any dice? Y/N?");

			String input = readString();
			if (input.equalsIgnoreCase("y")) {
				System.out.println("Select which dice you want to roll again. Dice index is 0-4.");
				input = readString();
				String[] inputDice = input.split(",");
				Integer[] convertedDice = new Integer[inputDice.length];
				int i = 0;
				for (String str : inputDice) {
					convertedDice[i++] = Integer.parseInt(str);
				}
				for (int d : convertedDice) {
					dice[d].roll();
				}
				rollCount++;

			} else if (input.equalsIgnoreCase("n")) {
				registerSum(a);
				break;
			}
		}
	}

	public void registerSum(int a) {
		int totalSum = 0;
		for (int i = 0; i < dice.length; i++) {
			totalSum += dice[i].getFaceValue();
		}
		if (checkForYatzy(a)) {
			players[a].addScore(50);
		} else {
			players[a].addScore(totalSum);
		}
	}

	public void printPlayerSum(int a) {

		System.out.println("Your final dice show " + Arrays.toString(dice));

		int totalSum = 0;
		for (int i = 0; i < dice.length; i++) {
			totalSum += dice[i].getFaceValue();
		}
		if (checkForYatzy(a)) {
			System.out.println("YATZY! Your total score is 50 points. Congratulations!" + "\n");
		} else
			System.out.println("Which gives you a final score of: " + totalSum + " points." + "\n");

	}

	public boolean checkForYatzy(int a) {
		int value = dice[0].getFaceValue();
		for (int i = 1; i < dice.length; i++) {
			if (dice[i].getFaceValue() != value) {
				return false;
			}
		}
		return true;
	}

	public void printScoreboard() {

		Arrays.sort(players, new Comparator<Player>() {
			public int compare(Player p1, Player p2) {
				return p2.getScore() - p1.getScore();
			}
		});
		System.out.println("The results for the game are:");
		for (int i = 0; i < players.length; i++) {
			System.out.println(players[i].getPlayerName() + " " + "- " + players[i].getScore() + " points");

		}
	}

	public static void main(String[] args) {
		new YatzyGame().commandLoop();
	}
}
