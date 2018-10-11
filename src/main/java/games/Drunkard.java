package games;

import org.apache.commons.math3.util.MathArrays;

import java.util.Arrays;

class Drunkard {

	private static final int PARS_TOTAL_COUNT = Par.values().length; //кол-во карт в масти

	private static final int CARDS_TOTAL_COUNT = PARS_TOTAL_COUNT * Suit.values().length; //кол-во карт в колоде

	private static int[] deckOfCards = new int[CARDS_TOTAL_COUNT];

	private static int[][] playersCards = new int[2][CARDS_TOTAL_COUNT];

	private static int[] cardsOnTable = new int[2];

	private static int[] playersCardsBeginCursors = {0, 0};

	private static int[] playersCardsEndCursors = {CARDS_TOTAL_COUNT / 2 - 1, CARDS_TOTAL_COUNT / 2 - 1};

	private static Suit getSuit(final int cardNumber) {
		return Suit.values()[cardNumber / PARS_TOTAL_COUNT];
	}

	private static int getParsInt(final int cardNumber) {
		return cardNumber / PARS_TOTAL_COUNT;
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

	private static void shuffleDeck() {
		for (int i = 0; i < deckOfCards.length; i += 1) {
			deckOfCards[i] = i;
		}
		MathArrays.shuffle(deckOfCards);
	}

	private static void dealCards() {
		playersCards[0] = Arrays.copyOfRange(deckOfCards,0,18);
		playersCards[1] = Arrays.copyOfRange(deckOfCards,18,36);
	}

	private static int playerCardsCount(final int playerNumber) {
		return Math.abs(playersCardsBeginCursors[playerNumber] - playersCardsEndCursors[playerNumber]);
	}

	private static void getCardsFromTop() {
		cardsOnTable[0] = playersCards[0][playersCardsBeginCursors[0]];
		cardsOnTable[1] = playersCards[1][playersCardsBeginCursors[1]];
		playersCardsBeginCursors[0] = (playersCardsBeginCursors[0] + 1) % CARDS_TOTAL_COUNT;
		playersCardsBeginCursors[1] = (playersCardsBeginCursors[1] + 1) % CARDS_TOTAL_COUNT;
	}

	private static void putCardsDown(final int playerNumber) {
		playersCards[playerNumber][playersCardsEndCursors[playerNumber]] = cardsOnTable[0];
		playersCardsEndCursors[playerNumber] = (playersCardsEndCursors[playerNumber] + 1) % CARDS_TOTAL_COUNT;
		playersCards[playerNumber][playersCardsEndCursors[playerNumber]] = cardsOnTable[1];
		playersCardsEndCursors[playerNumber] = (playersCardsEndCursors[playerNumber] + 1) % CARDS_TOTAL_COUNT;
	}

	static void main() {
		shuffleDeck();
		dealCards();

		getCardsFromTop();

		while(playerCardsCount(0) != 0 && playerCardsCount(1) != 0) {
			if (getParsInt(playersCards[0][0]) > getParsInt(playersCards[1][0])) {
				System.out.println("Кон выиграл первый игрок");
				putCardsDown(0);
				putCardsDown(0);

			} else if (getParsInt(playersCards[0][0]) < getParsInt(playersCards[1][0])) {
				System.out.println("Кон выиграл второй игрок");
				putCardsDown(1);
				putCardsDown(1);
			} else {
				System.out.println("Хз, кто победил. Набейте друг другу морды, я спать уже хочу");
				putCardsDown(0);
				putCardsDown(1);
			}

		}
		if (playersCards[0].length == 0) {
			System.out.println("Победил первый игрок!");
		} else {
			System.out.println("Победил второй игрок!");
		}

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
