.DEFAULT_GOAL := build-run

build-run: build run

compile: clear
	mkdir -p ./target/classes
	javac -d ./target/classes ./src/main/java/games/Slot.java

run:
	java -jar ./target/Casino.jar

clear:
	rm -rf ./target

build: compile
	jar cfe ./target/Casino.jar games.Slot -C ./target/classes .