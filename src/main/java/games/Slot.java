package games;

public class Slot {

	private int amount;

	final private int bet;

	final private int prize;

	private int power;

	final static private int NUM_OF_VALUES = 7;

	private Slot(final int amount, final int bet, final int prize) {
		this.amount = amount;
		this.bet = bet;
		this.prize = prize;
	}







	public static void main(String[] args) {
		Slot slot = new Slot(100, 10, 1000);
		Drum drum1 = new Drum(NUM_OF_VALUES);
		Drum drum2 = new Drum(NUM_OF_VALUES);
		Drum drum3 = new Drum(NUM_OF_VALUES);
		System.out.println("Hello, World!");
	}
}

