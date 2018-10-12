package games;

final class BlackJack {

	private static final int MAX_VALUE = 21;
	private static final int MAX_CARDS_COUNT = 8; //максимальное количество карт на руках. Иначе всегда будет перебор.

	private static int[] playersMoney = {100, 100};

	private static int[] cards; // Основная колода
	private static int cursor; // Счётчик карт основной колоды

	private static int[][] playersCards; // карты игроков. Первый индекс - номер игрока
	private static int[] playersCursors; // курсоры карт игроков. Индекс - номер игрока

	private static int value(int card) {
		switch (CardUtils.getPar(card)) {
			case JACK: return 2;
			case QUEEN: return 3;
			case KING: return 4;
			case SIX: return 6;
			case SEVEN: return 7;
			case EIGHT: return 8;
			case NINE: return 9;
			case TEN: return 10;
			case ACE:
			default: return 11;
		}
	}

	private static void addCard2Player(final int player) {
		playersCards[player][playersCursors[player]] = cards[cursor++];
		playersCursors[player] += 1;
	}

	private static void initRound() {
		System.out.println("\nУ Вас $" + playersMoney[0] + ", у компьютера - $" + playersMoney[1] + ". Начинаем новый раунд!");
		cards = CardUtils.getShaffledCards();
		cursor = 0;
		playersCards = new int[2][MAX_CARDS_COUNT];
		playersCursors = new int[]{0, 0};
	}

	static void main() {
		initRound();
	}

}
