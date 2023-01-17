import java.io.*;
import java.util.*;

public class GeneticAlgorithm {
	public static String target;
	public Population population;
	public double mutationRate;
	public int totalPopulation;

	public GeneticAlgorithm() {
		run();
	}
	
	public void run() {
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter population");
		totalPopulation = in.nextInt();
		System.out.println("Please enter mutation rate percentage");
		mutationRate = in.nextInt()/100;
		target = "To be or not to be.";
		System.out.println("Mutation rate: " + mutationRate);
		System.out.println("Population: " + totalPopulation);
		population = new Population(target, mutationRate, totalPopulation);
		while (true) {
			System.out.println("Generation: " + population.getGenerations());
			// Generate mating pool
			population.naturalSelection();
			//Create next generation
			population.generate();
			// Calculate fitness
			population.calcFitness();

			population.evaluate();
			
			population.print();
			
			if(population.isFinished()){
				break;
			}
		}
		System.out.println("Answer: " + population.getBest());
		System.out.println("Average fitness: " + population.getAverageFitness());
	}
}