// Create the adjacency matrix of a wheel graph Wn.
// Display on the screen a textual representation of the matrix.
// Write an algorithm that finds all the cycles of a wheel graph. Verify that their number is n^2 - 3n + 3.

import java.util.*;

public class Main {
    public static void main(String[] args) {

        if(args.length != 1)
        {
            System.out.println("Error: The argument should be the number of vertices (n).");
            return;
        }

        try{
            int n = Integer.parseInt(args[0]);

            if(n < 3){
                System.out.println("Error: Wheel graph have at least 3 vertices.");
                return;
            }

            int[][] adjacencyMatrix = createAdjacencyMatrix(n);
            System.out.println("The Wheel graph W"+n+" has the following adjacency matrix: ");
            displayMatrix(n, adjacencyMatrix);

            ArrayList<ArrayList<Integer>> cycles = findAllCycles(n,adjacencyMatrix);

            System.out.println("Cycles in the graph wheel: ");
            for(ArrayList<Integer> cycle: cycles){
                System.out.println(cycle);
            }

            int expectedNumberOfCycles = (int) Math.pow(n, 2) - 3 * n + 3;
            System.out.println("Expected number of cycles n^2-3*n+3: " + expectedNumberOfCycles);
            System.out.println("Number of cycles the algorithm found: " + cycles.size());


        } catch(NumberFormatException e){
            System.out.println("Error: Wrong input...");
        }
    }

    static int[][] createAdjacencyMatrix(int n)
    {
        int[][] adjacencyMatrix = new int[n][n];

        for(int i=0;i<n;i++)
            Arrays.fill(adjacencyMatrix[i],0);

        for(int i=0;i<n-1;i++){
            if(i==0){
                adjacencyMatrix[i][i+1]=adjacencyMatrix[i+1][i]=1;
                adjacencyMatrix[i][n-2]=adjacencyMatrix[n-2][i]=1;
                adjacencyMatrix[i][n-1]=adjacencyMatrix[n-1][i]=1;
            }
            else {
                if(i==n-2) {
                    adjacencyMatrix[i][n - 1] = adjacencyMatrix[n - 1][i] = 1;
                }
                else {
                    adjacencyMatrix[i][i+1]=adjacencyMatrix[i+1][i]=1;
                    adjacencyMatrix[i][n-1]=adjacencyMatrix[n-1][i]=1;
                }
            }
        }
        return adjacencyMatrix;
    }

    static void displayMatrix(int n, int[][] matrix)
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
                System.out.print(matrix[i][j]+" ");
            System.out.println();
        }
    }

    static ArrayList<ArrayList<Integer>> findAllCycles(int n, int[][] matrix) {
        ArrayList<ArrayList<Integer>> cycles = new ArrayList<>();

        int cycleDimension = 1;
        while (cycleDimension != n-1) {
            for (int i = 1; i < n ; i++) {
                List<Integer> cycle = new ArrayList<>();
                cycle.add(i);
                cycle.add(n);
                for (int j = 1; j <= cycleDimension; j++) {
                    if(i+j<n)
                        cycle.add(i + j);
                    else
                        cycle.add(j);
                }
                cycle.add(i);
                cycles.add(new ArrayList<>(cycle));
            }
            cycleDimension++;
        }

        //the cycle that doesn't have the central vartex
        List<Integer> cycle = new ArrayList<>();
        for(int i=1;i<n;i++)
            cycle.add(i);
        cycle.add(1);
        cycles.add(new ArrayList<>(cycle));

        return cycles;
        }
    }
