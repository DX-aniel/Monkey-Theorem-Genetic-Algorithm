# Monkey-Theorem-Genetic-Algorithm

An exploration of evolutionary programming. 

Implemented a genetic algorithm to solve the Infinite Monkey Theorem optimization problem. Given that it is a non-linear and non-differentiable problem, a genetic algorithm is an astute optimization technique to use. The idea is the reach a specific phrase starting with an initial generation of completely random characters through selection, crossover, and mutation. Selection is where each individual is given a fitness score based on it's similarity to the target phrase and individuals with higher fitness scores are favored for reproduction. The process of reproduction is created using crossover techniques. These crossover techniques (e.g. ordered, partially mapped, cyclic, etc.) seek to keep similarities between parents in reproduction assuming that those similarities contribute to their high fitness scores. Finally, mutation is implemented to avoid the risk of the algorithm being stuck at a local minimum of the solution.

This exploration was conducted in preparation for the M22 IB HL ICS Paper 3 Exam. 
