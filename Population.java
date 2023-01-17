import java.io.*;
import java.util.*;

public class Population {
	public DNA [] population; // Array to hold the current population
	public ArrayList<DNA> matingPool; // ArrayList which we will use for our "mating pool"
	public int generations; // Number of generations
	public boolean finished; // Are we finished evolving?
	public String target; // Target phrase
	public double mutationRate; // Mutation rate
	public int perfectScore;
	public String best = "";

	public Population(String target, double mutationRate, int populationSize) {
		generations = 0; // Number of generations
		finished = false; // Are we finished evolving?
		this.target = target; // Target phrase
		this.mutationRate = mutationRate; // Mutation rate
		perfectScore = 1;
		best = "";

		population = new DNA[populationSize];
		for (int i = 0; i < populationSize; i++) {
			population[i] = new DNA(target.length());
		}
		matingPool = new ArrayList<DNA>();
		calcFitness();
	}

	// Fill our fitness array with a value for every member of the population
	public void calcFitness() {
		for (int i = 0; i < population.length; i++) {
			population[i].calcFitness(target);
		}
	}

	// Generate a mating pool
	public void naturalSelection() {
		// Clear the ArrayList
		matingPool.clear();

		//int maxFitness = 0;
		int[] fitness = new int[population.length];
		for (int i = 0; i < population.length; i++){
			//if (population[i].fitness > maxFitness){
				//maxFitness = population[i].fitness;
			//}
			fitness[i] = (int) (population[i].fitness);
			for (int j = 0; j < fitness[i]; j++) {
				// and pick two random numbers
				matingPool.add(population[i]);
			}
		}
		
	}

	// Create a new generation
	public void generate() {
		// Refill the population with children from the mating pool
		for (int i = 0; i < population.length; i++) {
			int a = (int) (Math.random() * matingPool.size());
			int b = (int) (Math.random() * matingPool.size());
			if(matingPool.size() > 0){
				DNA partnerA = matingPool.get(a);
				DNA partnerB = matingPool.get(b);
				DNA child = partnerA.crossover(partnerB);
				child.mutate(mutationRate);
				population[i] = child;
			}
		}
		generations++;
	}

	public String getBest() {
		return best;
	}

	// Compute the current "most fit" member of the population
	public void evaluate() {
		double worldrecord = 0.0;
		int index = 0;
		for (int i = 0; i < population.length; i++) {
			if (population[i].fitness > worldrecord) {
				index = i;
				worldrecord = population[i].fitness;
			}
		}

		best = population[index].getPhrase();
		if (worldrecord == perfectScore) {
			finished = true;
		}
	}

	public boolean isFinished() {
		return finished;
	}

	public int getGenerations() {
		return generations;
	}

	// Compute average fitness for the population
	public double getAverageFitness() {
		int total = 0;
		for (int i = 0; i < population.length; i++) {
			total += population[i].fitness;
		}
		return total/population.length;
	}
	
	public void print() {
		for (int i = 0; i < population.length; i++) {
			for (int j = 0; j < population[i].genes.length; j++) {
				System.out.print(population[i].genes[j]);
			}
			System.out.print("\n");
		}
	}
}