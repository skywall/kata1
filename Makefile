all: install build run

install:
	brew install kotlin
	kotlinc *.kt -d puzzle.jar

build:
	kotlinc *.kt -d puzzle.jar

run:
	@kotlin -classpath puzzle.jar cz.skywall.kata1.Main
