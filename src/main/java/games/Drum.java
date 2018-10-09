package games;

public class Drum {

	private static final int NUM_OF_VALUES = 7;

	private int value;

	Drum() {
		this.value = (int)Math.round(Math.random() * NUM_OF_VALUES);
	}

	int runDrums(final int effort) {
		return (effort + value) % NUM_OF_VALUES;
    }

}
