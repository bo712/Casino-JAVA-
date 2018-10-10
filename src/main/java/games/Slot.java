package games;

public class Slot {

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
        String message = "Вы ВЫИГРАЛИ $" + prize + "!!! Ваш капитал теперь составляет: $" + credit;

        System.out.println("\n!!!!!!!!!!!!!!!!*************************!!!!!!!!!!!!!!!!");
        System.out.println("Вы ВЫИГРАЛИ $" + prize + "!!! Ваш капитал теперь составляет: $" + credit);
        System.out.println("!!!!!!!!!!!!!!!!*************************!!!!!!!!!!!!!!!!");
    }

    private static void printLose(final int bet, final int credit) {
        System.out.println("Проигрыш $" + bet + ", ваш капитал теперь составляет: $" + credit);
    }

    public static void main(String[] args) {

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
