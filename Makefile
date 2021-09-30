
all:
	find * -name "*.java" > sources.txt
	javac @sources.txt

re: fclean all

run: all
	cd ./srcs && java fr.kronx12.simulator.Simulator ../scenario.txt

run_md5: all
	cd ./srcs && java fr.kronx12.simulator.Simulator ../scenario_md5.txt

clean:
	rm -rf sources.txt

fclean: clean
	find * -name "*.class" -exec rm -rf {} \;
	rm -f ./srcs/simulation.txt

.PHONY: all re clean fclean run run_md5
