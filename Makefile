
all:
	find * -name "*.java" > sources.txt
	javac @sources.txt

re: fclean all

run: all
	java srcs.simulator.Simulator scenario.txt

run_md5: all
	java srcs.simulator.Simulator scenario_md5.txt

clean:
	rm -rf sources.txt

fclean: clean
	find * -name "*.class" -exec rm -rf {} \;
	rm -rf simulation.txt

.PHONY: all re clean fclean run run_md5
