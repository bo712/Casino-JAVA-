package games;

import java.io.IOException;

final class BlackJack {

	private static final int MAX_VALUE = 21;
	private static final int MAX_CARDS_COUNT = 8; //максимальное количество карт на руках. Иначе всегда будет перебор.

	private static int[] playersMoney = {100, 100};
	private static int bet = 10;

	private static int[] cards; // Основная колода
	private static int cursor; // Счётчик карт основной колоды

	private static int[][] playersCards; // карты игроков. Первый индекс - номер игрока
	private static int[] playersCursors; // курсоры карт игроков. Индекс - номер игрока
	private static int[] playersPoints; // количество очков игроков. Индекс - номер игрока

	private static int value(final int card) {
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
		playersCards[player][playersCursors[player]] = cards[cursor];
		printGivenCard(player, cards[cursor++]);
		playersCursors[player] += 1;
	}

	static int sum(final int player) {
		int result = 0;
		for (int i = 0; i < playersCursors[player]; i += 1) {
			result += playersCards[player][i];
		}
		return result;
	}

	static int getFinalSum(final int player) {
		int result = sum(player);
		if(result <= 21) {
			return result;
		}
		return 0;
	}

	private static void initRound() {
		System.out.println("\nУ Вас $" + playersMoney[0] + ", у компьютера - $" + playersMoney[1] + ". Начинаем новый раунд!");
		cards = CardUtils.getShaffledCards();
		cursor = 0;
		playersCards = new int[2][MAX_CARDS_COUNT];
		playersCursors = new int[]{0, 0};
	}

	private static void playersStep(final int player) throws IOException {
		addCard2Player(player);
		addCard2Player(player);
		while((player == 0 && sum(player) < 20 && confirm("Берём ещё?")) || (player == 1 && sum(player) < 17)) {
			addCard2Player(player);
		}
		playersPoints[player] = getFinalSum(0);
	}

	static boolean confirm(String message) throws IOException {
		System.out.println(message + " \"Y\" - Да, {любой другой символ} - нет (Что бы выйти из игры, нажмите Ctrl + C)");
		switch (Choice.getCharacterFromUser()) {
			case 'Y':
			case 'y': return true;
			default: return false;
		}
	}

	static void printGivenCard(final int player, final int card) {
		if (player == 0){
			System.out.println("Вам выпала карта " + CardUtils.toString(card));
		} else {
			System.out.println("Компьютеру выпала карта " + CardUtils.toString(card));
		}

	}

	static void main() throws IOException {

		while(playersMoney[0] >= bet && playersMoney[1] >= bet){
			initRound();
			playersStep(0);
			playersStep(1);
		}

		if (playersMoney[0] > 0)
			System.out.println("Вы выиграли! Поздравляем!");
		else
			System.out.println("Вы проиграли. Соболезнуем...");

		initRound();
	}

}
