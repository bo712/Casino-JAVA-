package games;

public class Slot {

	private int credit;

	private final int bet;

	private final int prize;

	private Slot(final int credit, final int bet, final int prize) {
		this.credit = credit;
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

	private static void printMoneyStatement(final int credit, final int bet) {
		System.out.println("\nУ Вас $" + credit + ", ставка - $" + bet);
		System.out.println("Крутим барабаны!Розыгрыш принёс следующие результаты:");
	}

	private static void printDrumsResult(final int firstValue, final int secondValue, final int thirdValue) {
		System.out.println("первый барабан - " + firstValue + ", второй - " + secondValue + ", третий - " + thirdValue);
	}

	private static void printWin(final int prize, final int credit) {
		System.out.println("\n!!!!!!!!!!!!!!!!*************************!!!!!!!!!!!!!!!!");
		System.out.println("Вы ВЫИГРАЛИ $" + prize + "!!! Ваш капитал теперь составляет: $" + credit);
		System.out.println("!!!!!!!!!!!!!!!!*************************!!!!!!!!!!!!!!!!");
	}

	private static void printLose(final int bet, final int credit) {
		System.out.println("Проигрыш $" + bet + ", ваш капитал теперь составляет: $" + credit);
	}

	public static void main(String[] args) {
		Slot slot = new Slot(100, 10, 1000);
		Reel reel1 = new Reel();
		Reel reel2 = new Reel();
		Reel reel3 = new Reel();

		printHello();
		while (slot.credit > slot.bet) {
			printMoneyStatement(slot.credit, slot.bet);

			int firstValue = reel1.runReel(getEffort());
			int secondValue = reel2.runReel(getEffort());
			int thirdValue = reel3.runReel(getEffort());

			printDrumsResult(firstValue, secondValue, thirdValue);

			if (firstValue == secondValue && firstValue == thirdValue) {
				slot.credit += slot.prize;
				printWin(slot.prize, slot.credit);
			} else {
				slot.credit -= slot.bet;
				printLose(slot.bet, slot.credit);
			}
		}
		printBye();
	}
}
