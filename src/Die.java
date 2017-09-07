import java.util.Random;

public class Die {

	private final int MAX_VALUE = 6;
	private int faceValue;
	Random rnd = new Random();

	public Die() {
		faceValue = 1;
	}

	public int roll() {
		faceValue = rnd.nextInt(MAX_VALUE) + 1;
		return faceValue;
	}

	public int getFaceValue() {
		return faceValue;

	}

	@Override
	public String toString() {
		return Integer.toString(faceValue);
	}

}
