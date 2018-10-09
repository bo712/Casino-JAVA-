package games;

class Reel {

	private static final int NUM_OF_VALUES = 7;

	private int value;

	Reel() {
		this.value = (int)Math.round(Math.random() * NUM_OF_VALUES);
	}

	int runReel(final int effort) {
		return (effort + value) % NUM_OF_VALUES;
    }

}
