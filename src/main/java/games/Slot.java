package games;

import org.slf4j.Logger;

final class Slot {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Slot.class);

    private static int[] reel = {getValueOfReel(), getValueOfReel(), getValueOfReel()}; //барабаны

    private static final int NUM_OF_VALUES = 7;

    private static int getValueOfReel() {
        return (int)Math.round(Math.random() * NUM_OF_VALUES);
    }

    private static int getEffort() {
        return (int)Math.round(Math.random() * 100);
    }

    private static void runReel(final int [] reel) {
        for(int i = 0; i < reel.length; i += 1) {
            reel[i] = (getEffort() + reel[i]) % NUM_OF_VALUES;
        }
    }

    private static void printHello() {
	    log.info("Добро пожаловать в игру \"Однорукий бандит\"!");
    }

    private static void printBye() {
	    log.info("\n\nОднорукий бандит одной рукой вынул все Ваши деньги.");
	    log.info("Возвращайтесь, когда найдёте ещё немного денег. Вам обязательно повезёт. Кхе-кхе =)");
    }

    private static void printMoneyStatement(final int credit, final int bet) {
	    log.info("\nУ Вас $" + credit + ", ставка - $" + bet);
	    log.info("Крутим барабаны!Розыгрыш принёс следующие результаты:");
    }

    private static void printDrumsResult(final int firstValue, final int secondValue, final int thirdValue) {
	    log.info("первый барабан - " + firstValue + ", второй - " + secondValue + ", третий - " + thirdValue);
    }

    private static void printWin(final int prize, final int credit) {
	    log.info("\n!!!!!!!!!!!!!!!!*************************!!!!!!!!!!!!!!!!");
	    log.info("Вы ВЫИГРАЛИ $" + prize + "!!! Ваш капитал теперь составляет: $" + credit);
	    log.info("!!!!!!!!!!!!!!!!*************************!!!!!!!!!!!!!!!!");
    }

    private static void printLose(final int bet, final int credit) {
	    log.info("Проигрыш $" + bet + ", ваш капитал теперь составляет: $" + credit);
    }

    public static void main() {

        int credit = 100;
        int bet = 10;
        int prize = 1000;

        printHello();
        while (credit >= bet) {
            printMoneyStatement(credit, bet);
            runReel(reel);

            printDrumsResult(reel[0], reel[1], reel[2]);

            if (reel[0] == reel[1] && reel[0] == reel[2]) {
                credit += prize;
                printWin(prize, credit);
            } else {
                credit -= bet;
                printLose(bet, credit);
            }
        }
        printBye();
    }
}
