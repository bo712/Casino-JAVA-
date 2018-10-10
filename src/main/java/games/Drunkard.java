package games;

class Drunkard {

	private static final int PARS_TOTAL_COUNT = Par.values().length;

	private static final int CARDS_TOTAL_COUNT = PARS_TOTAL_COUNT * Suit.values().length;

	private static int[] deckOfCards = new int[CARDS_TOTAL_COUNT];

	private static int[][] playersCards = new int[2][CARDS_TOTAL_COUNT];

	private static int[] playersCardsBeginCursors = new int[2];

	private static int[] playersCardsEndCursors = new int[2];

	private static Suit getSuit(int cardNumber) {
		return Suit.values()[cardNumber / PARS_TOTAL_COUNT];
	}

	private static Par getPar(int cardNumber) {
		return Par.values()[cardNumber % PARS_TOTAL_COUNT];
	}

	private static String toString(int cardNumber) {
		return getPar(cardNumber) + " " + getSuit(cardNumber);
	}

	private static int incrementIndex(int i) {
		return (i + 1) % CARDS_TOTAL_COUNT;
	}

	static void main() {
	    System.out.println(toString(CARDS_TOTAL_COUNT - 1));
    }

	enum Suit {
		SPADES, // пики
		HEARTS, // червы
		CLUBS, // трефы
		DIAMONDS // бубны
	}

	enum Par {
		SIX,
		SEVEN,
		EIGHT,
		NINE,
		TEN,
		JACK, // Валет
		QUEEN, // Дама
		KING, // Король
		ACE // Туз
	}
}
