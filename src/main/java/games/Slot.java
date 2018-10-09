package games;

public class Slot {

	private int amount;

	private final int bet;

	private final int prize;

	private Slot(final int amount, final int bet, final int prize) {
		this.amount = amount;
		this.bet = bet;
		this.prize = prize;
	}

	private static int getEffort() {
		return (int)Math.round(Math.random() * 100);
	}

	private static void printHello() {
		System.out.println("Добро пожаловать в игру \"Однорукий бандит\"!");
	}

	private static void printBye() {
		System.out.println("\n\nОднорукий бандит одной рукой вынул все Ваши деньги.");
		System.out.println("Возвращайтесь, когда найдёте ещё немного денег. Вам обязательно повезёт. Кхе-кхе =)");
	}

	private static void printMoneyStatement(final int amount, final int bet) {
		System.out.println("\nУ Вас $" + amount + ", ставка - $" + bet);
		System.out.println("Крутим барабаны!Розыгрыш принёс следующие результаты:");
	}

	private static void printDrumsResult(final int firstValue, final int secondValue, final int thirdValue) {
		System.out.println("первый барабан - " + firstValue + ", второй - " + secondValue + ", третий - " + thirdValue);
	}

	private static void printWin(final int prize, final int amount) {
		System.out.println("\n!!!!!!!!!!!!!!!!*************************!!!!!!!!!!!!!!!!");
		System.out.println("Вы ВЫИГРАЛИ $" + prize + "!!! Ваш капитал теперь составляет: $" + amount);
		System.out.println("!!!!!!!!!!!!!!!!*************************!!!!!!!!!!!!!!!!");
	}

	private static void printLose(final int bet, final int amount) {
		System.out.println("Проигрыш $" + bet + ", ваш капитал теперь составляет: $" + amount);
	}

	public static void main(String[] args) {
		Slot slot = new Slot(100, 10, 1000);
		Drum drum1 = new Drum();
		Drum drum2 = new Drum();
		Drum drum3 = new Drum();

		printHello();
		while (slot.amount > slot.bet) {
			printMoneyStatement(slot.amount, slot.bet);

			int firstValue = drum1.runDrums(getEffort());
			int secondValue = drum2.runDrums(getEffort());
			int thirdValue = drum3.runDrums(getEffort());

			printDrumsResult(firstValue, secondValue, thirdValue);

			if (firstValue == secondValue && firstValue == thirdValue) {
				slot.amount += slot.prize;
				printWin(slot.prize, slot.amount);
			} else {
				slot.amount -= slot.bet;
				printLose(slot.bet, slot.amount);
			}
		}
		printBye();
	}
}
