package games;

import org.slf4j.Logger;

import java.io.IOException;

final class BlackJack {

	private static final Logger log = org.slf4j.LoggerFactory.getLogger(BlackJack.class);

	private static final int MAX_VALUE = 21;
	private static final int VALUE_PASS_HUMAN = 20;
	private static final int VALUE_PASS_CPU = 17;

	private static final int MAX_CARDS_COUNT = 8; //максимальное количество карт на руках. Иначе всегда будет перебор.

	private static int[] playersMoney = {100, 100};
	private static int bet = 10;

	private static int[] cards; // Основная колода
	private static int cursor; // Счётчик карт основной колоды

	private static int[][] playersCards; // карты игроков. Первый индекс - номер игрока
	private static int[] playersCursors; // курсоры карт игроков. Индекс - номер игрока
	private static int[] playersPoints = {0, 0}; // количество очков игроков. Индекс - номер игрока

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

	private static int sum(final int player) {
		int result = 0;
		for (int i = 0; i < playersCursors[player]; i += 1) {
			result += value(playersCards[player][i]);
		}
		return result;
	}

	private static int getFinalSum(final int player) {
		int result = sum(player);
		if(result <= MAX_VALUE) {
			return result;
		}
		return 0;
	}

	private static void initRound() {
		log.info("\nУ Вас $" + playersMoney[0] + ", у компьютера - $" + playersMoney[1] + ". Начинаем новый раунд!");
		cards = CardUtils.getShaffledCards();
		cursor = 0;
		playersCards = new int[2][MAX_CARDS_COUNT];
		playersCursors = new int[]{0, 0};
	}

	private static void playersStep(final int player) throws IOException {
		while(playersCursors[player] < 2 ||
				((player == 0 && sum(player) < VALUE_PASS_HUMAN && confirm("Берём ещё?")) ||
						(player == 1 && sum(player) < VALUE_PASS_CPU))) {
			addCard2Player(player);
		}
		playersPoints[player] = getFinalSum(player);
	}

	private static boolean confirm(String message) throws IOException {
		log.info(message + " \"Y\" - Да, {любой другой символ} - нет (Что бы выйти из игры, нажмите Ctrl + C)");
		switch (Choice.getCharacterFromUser()) {
			case 'Y':
			case 'y': return true;
			default: return false;
		}
	}

	private static void printGivenCard(final int player, final int card) {
		if (player == 0){
			log.info("Вам выпала карта " + CardUtils.toString(card));
		} else {
			log.info("Компьютеру выпала карта " + CardUtils.toString(card));
		}

	}

	private static void printRoundResult() {
		log.info("Сумма ваших очков - " + playersPoints[0] + " , компьютера - " + playersPoints[1]);
		if (playersPoints[0] > playersPoints[1]) {
			log.info("Вы выиграли раунд! Получаете $" + bet);
			playersMoney[0] += bet;
			playersMoney[1] -= bet;
		} else if (playersPoints[0] < playersPoints[1]) {
			log.info("Вы проиграли раунд! Теряете $" + bet);
			playersMoney[0] -= bet;
			playersMoney[1] += bet;
		} else {
			log.info("Ничья. Все остаются при своих.");
		}
	}

	static void main() throws IOException {

		while(playersMoney[0] >= bet && playersMoney[1] >= bet){
			initRound();
			playersStep(0);
			playersStep(1);
			printRoundResult();
		}

		if (playersMoney[0] > 0)
			log.info("Вы выиграли! Поздравляем!");
		else
			log.info("Вы проиграли. Соболезнуем...");

	}

}
