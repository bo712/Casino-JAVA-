.DEFAULT_GOAL := compile-run

compile-run: compile run

compile: clear
	mkdir -p ./target/classes
	javac -d ./target/classes ./src/main/java/games/Slot.java

run:
	java -cp ./target/classes games.Slot

clear:
	rm -rf ./target