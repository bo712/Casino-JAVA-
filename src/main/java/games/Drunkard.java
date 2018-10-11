package games;

import org.apache.commons.math3.util.MathArrays;

import java.util.Arrays;

class Drunkard {

	private static final int PARS_TOTAL_COUNT = Par.values().length; //кол-во карт в масти

	private static final int CARDS_TOTAL_COUNT = PARS_TOTAL_COUNT * Suit.values().length; //кол-во карт в колоде

	private static int[] deckOfCards = new int[CARDS_TOTAL_COUNT]; //колода карт

	private static int[][] playersCards = new int[2][CARDS_TOTAL_COUNT]; //2-й массив - карты игроков

	private static int[] cardsOnTable = new int[2]; //карты на столе

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
		for(int i = 0; i < 18; i++){
			playersCards[0][i] = deckOfCards[i];
		}
		for(int i = 18; i < 36; i++){
			playersCards[1][i - 18] = deckOfCards[i];
		}
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

	private static void putCardsBack() {
		playersCardsEndCursors[0] = (playersCardsEndCursors[0] + 1) % CARDS_TOTAL_COUNT;
		playersCards[0][playersCardsEndCursors[0]] = cardsOnTable[0];
		playersCardsEndCursors[1] = (playersCardsEndCursors[1] + 1) % CARDS_TOTAL_COUNT;
		playersCards[1][playersCardsEndCursors[1]] = cardsOnTable[1];
	}

	private static void printResult(String winner) {
		System.out.println("Игрок №1 карта:\t" + toString(cardsOnTable[0]) + ";\tигрок №2 карта:\t" + toString(cardsOnTable[1]) + winner + "У игрока №1 карт: " + playerCardsCount(0) + ", у игрока №2 карт: " + + playerCardsCount(1));
	}

	static void main() {
		shuffleDeck();
		dealCards();

		while(playerCardsCount(0) != 0 && playerCardsCount(1) != 0) {
			getCardsFromTop();
			if (getParsInt(cardsOnTable[0]) > getParsInt(cardsOnTable[1])) {
				putCardsDown(0);
				String winner = "\tВыиграл игрок 1!\t";
				printResult(winner);
			} else if (getParsInt(cardsOnTable[0]) < getParsInt(cardsOnTable[1])) {
				putCardsDown(1);
				String winner = "\tВыиграл игрок 2!\t";
				printResult(winner);
			} else {
				putCardsBack();
				String winner = "\tСпор - каждый остаётся при своих!\t";
				printResult(winner);
			}

		}
		if (playerCardsCount(0) == 0) {
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
