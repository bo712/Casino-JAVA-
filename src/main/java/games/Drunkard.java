package games;

import org.apache.commons.math3.util.MathArrays;

import java.util.Arrays;

class Drunkard {

	private static final int PARS_TOTAL_COUNT = Par.values().length; //кол-во карт в масти

	private static final int CARDS_TOTAL_COUNT = PARS_TOTAL_COUNT * Suit.values().length; //кол-во карт в колоде

	private static int[] deckOfCards = new int[CARDS_TOTAL_COUNT];

	private static int[][] playersCards = new int[2][CARDS_TOTAL_COUNT];

	private static int[] playersCardsBeginCursors = new int[2];

	private static int[] playersCardsEndCursors = new int[2];

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

	static void main() {
		shuffleDeck();
		dealCards();
		while(playersCards[0].length != 0 && playersCards[1].length !=0) {
			if (getParsInt(playersCards[0][0]) > getParsInt(playersCards[1][0])) {
				System.out.println("Кон выиграл первый игрок");
				//тут надо ещё карты в колоду первому пихнуть
			} else if (getParsInt(playersCards[0][0]) < getParsInt(playersCards[1][0])) {
				System.out.println("Кон выиграл второй игрок");
				//тут надо ещё карты в колоду первому пихнуть
			} else {
				System.out.println("Хз, кто победил. Набейте друг другу морды, я спать уже хочу");
				//тут надо ещё реализовать возврат карт игрокам
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
