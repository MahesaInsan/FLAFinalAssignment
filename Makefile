r:
	@echo "build..."
	@javac -d out */*.java 
	@echo "Running..."
	@clear
	@java -cp out main.Main
	@clear

.PHONY: r
