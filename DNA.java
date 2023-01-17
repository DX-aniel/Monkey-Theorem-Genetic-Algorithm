import java.io.*;
import java.util.*;

// Constructor (makes a random DNA)
public class DNA {
	public char[] genes;
	public double fitness;
	public String target;
	
	public DNA(int length) {
		// The genetic sequence
		genes = new char[length];
		fitness = 0;
		for (int i = 0; i < length; i++) {
			genes[i] = (char)(Math.random() * 127); // Pick from range of chars
		}
	}
	
	public DNA(char[] newGenes){
		target = GeneticAlgorithm.target;
		for (int i = 0; i < target.length(); i++) {
			genes[i] = newGenes[i];
		}
	}

	// Converts character array to a String
	public String getPhrase() {
		return String.valueOf(genes);
	}

	// Fitness function (returns floating point % of "correct" characters)
	public void calcFitness(String target) {
		int score = 0;
		for (int i = 0; i < genes.length; i++) {
			if (genes[i] == target.charAt(i)) {
				score++;
			}
		}
		//fitness = score / target.length();
		fitness = (score / target.length()) * 100;
	}

	// Crossover
	public DNA crossover(DNA partner) {
		// A new child
		DNA child = new DNA(genes.length);
		
		int midpoint = (int) (Math.random()*genes.length); // Pick a midpoint

		// Half from one, half from the other
		for (int i = 0; i < target.length(); i++) {
			if (i > midpoint){ 
				child.genes[i] = genes[i];
			}
			else{
				child.genes[i] = partner.genes[i];
			}
		}
		
		return child;
	}

	// Based on a mutation probability, picks a new random character
	public void mutate(double mutationRate) {
		for (int i = 0; i < genes.length; i++) {
			if ((int) (Math.random() * (1.0 / mutationRate)) == 0 && genes[i] != (target.charAt(i))) {
				genes[i] = (char)(Math.random() * 127);
			}
		}
	}	
}