package games;

import org.apache.commons.math3.util.MathArrays;

class Drunkard {

	private static final int PARS_TOTAL_COUNT = Par.values().length;

	private static final int CARDS_TOTAL_COUNT = PARS_TOTAL_COUNT * Suit.values().length;

	private static int[] deckOfCards = new int[CARDS_TOTAL_COUNT];

	private static int[][] playersCards = new int[2][CARDS_TOTAL_COUNT];

	private static int[] cardsOnTable = new int[2];

	private static int[] playersCardsBeginCursors = {0, 0};

	private static int[] playersCardsEndCursors = {CARDS_TOTAL_COUNT / 2 - 1, CARDS_TOTAL_COUNT / 2 - 1};

	private static Suit getSuit(final int cardNumber) {
		return Suit.values()[cardNumber / PARS_TOTAL_COUNT];
	}

	private static int getParsInt(final int cardNumber) {
		return cardNumber % PARS_TOTAL_COUNT;
	}

	private static Par getPar(int cardNumber) {
		return Par.values()[cardNumber % PARS_TOTAL_COUNT];
	}

	private static String toString(int cardNumber) {
		return getPar(cardNumber) + " " + getSuit(cardNumber);
	}

	private static void shuffleDeck() {
		for (int i = 0; i < deckOfCards.length; i += 1) {
			deckOfCards[i] = i;
		}
		MathArrays.shuffle(deckOfCards);
	}

	private static void dealCards() {
		System.arraycopy (deckOfCards, 0, playersCards[0], 0, CARDS_TOTAL_COUNT / 2);
		System.arraycopy (deckOfCards, CARDS_TOTAL_COUNT / 2, playersCards[1], 0, CARDS_TOTAL_COUNT / 2);
	}

	private static int playerCardsCount(final int playerNumber) {
		if (playersCardsBeginCursors[playerNumber] <= playersCardsEndCursors[playerNumber]) {
			return playersCardsEndCursors[playerNumber] - playersCardsBeginCursors[playerNumber] + 1;
		}
		return playersCardsEndCursors[playerNumber] + CARDS_TOTAL_COUNT - playersCardsBeginCursors[playerNumber] + 1;
	}

	private static void getCardsFromTop() {
		cardsOnTable[0] = playersCards[0][playersCardsBeginCursors[0]];
		cardsOnTable[1] = playersCards[1][playersCardsBeginCursors[1]];
		playersCardsBeginCursors[0] = (playersCardsBeginCursors[0] + 1) % CARDS_TOTAL_COUNT;
		playersCardsBeginCursors[1] = (playersCardsBeginCursors[1] + 1) % CARDS_TOTAL_COUNT;
	}

	private static void putCardsDown(final int playerNumber) {
		playersCardsEndCursors[playerNumber] = (playersCardsEndCursors[playerNumber] + 1) % CARDS_TOTAL_COUNT;
		playersCards[playerNumber][playersCardsEndCursors[playerNumber]] = cardsOnTable[0];
		playersCardsEndCursors[playerNumber] = (playersCardsEndCursors[playerNumber] + 1) % CARDS_TOTAL_COUNT;
		playersCards[playerNumber][playersCardsEndCursors[playerNumber]] = cardsOnTable[1];
	}

	private static void completeStep(final int playerNumber) {
		putCardsDown(playerNumber);
		String winner = "\tВыиграл игрок " + (playerNumber + 1) + "!\t";
		printResult(winner);
	}

	private static void putCardsBack() {
		playersCardsEndCursors[0] = (playersCardsEndCursors[0] + 1) % CARDS_TOTAL_COUNT;
		playersCards[0][playersCardsEndCursors[0]] = cardsOnTable[0];
		playersCardsEndCursors[1] = (playersCardsEndCursors[1] + 1) % CARDS_TOTAL_COUNT;
		playersCards[1][playersCardsEndCursors[1]] = cardsOnTable[1];
	}

	private static void printResult(String winner) {
		System.out.println("Игрок №1 карта:\t" + toString(cardsOnTable[0]) + ";\tигрок №2 карта:\t" + toString(cardsOnTable[1]) + winner + "\tУ игрока №1 карт: " + playerCardsCount(0) + ", у игрока №2 карт: " + (CARDS_TOTAL_COUNT - playerCardsCount(0)));
	}

	static void main() {
		shuffleDeck();
		dealCards();

		while(playerCardsCount(0) != CARDS_TOTAL_COUNT && playerCardsCount(1) != CARDS_TOTAL_COUNT) {
			getCardsFromTop();
			if ((getParsInt(cardsOnTable[0]) == 0 && getParsInt(cardsOnTable[1]) == PARS_TOTAL_COUNT - 1) || getParsInt(cardsOnTable[0]) > getParsInt(cardsOnTable[1])) {
				completeStep(0);
			} else if ((getParsInt(cardsOnTable[0]) == PARS_TOTAL_COUNT - 1 && getParsInt(cardsOnTable[1]) == 0) || getParsInt(cardsOnTable[0]) < getParsInt(cardsOnTable[1])) {
				completeStep(1);
			} else {
				putCardsBack();
				String winner = "\tСпор - каждый при своих!";
				printResult(winner);
			}

		}
		if (playerCardsCount(0) == CARDS_TOTAL_COUNT) {
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
