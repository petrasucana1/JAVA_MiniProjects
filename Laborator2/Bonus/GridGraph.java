import java.lang.reflect.Array;
import java.util.*;

/**
 * The GridGraph class represents the graph, and it implements its cost matrix.
 * This graph shows the paths from a vehicle to a client. Always is chosen the shortest path with the help of Dijkstra method.
 */
public class GridGraph {
    private int rows;
    private int cols;
    private int[][] adjacencyMatrix;

    public GridGraph(int rows, int cols){
        this.rows=rows;
        this.cols=cols;
        this.adjacencyMatrix=new int[rows*cols][rows*cols];
        initialiseAdjacencyMatrix();
    }

    public int getCols() {
        return cols;
    }

    public int getRows(){
        return rows;
    }

    /**
     *  This method implements the cost matrix and initializes it with random values. All values in the matrix should be read as the value of the distance from a point of a graph( two coordinates) to another point.Exemple : adjancencyMatrix[0] is the value of the distance between (0,0) and (0,0).
     */
    public void initialiseAdjacencyMatrix() {
        Random random = new Random();
        for (int i = 0; i < rows * cols; i++) {
            for (int j = 0; j < rows * cols; j++) {
                if (i == j)
                    adjacencyMatrix[i][i] = 0;
                else
                    adjacencyMatrix[i][j] = random.nextInt(9) + 1;
            }
        }
    }

    /**
     * This method finds the shortest path between the parameter and the points from the graph. It saves the path in an int array.
     * In our case, this method find the shortest path from a depot to the rest points or from a client to the rest nodes of the grid graph.
     * @param sourceRow The client's or the depot's row from where we want to start to calculate the shortest path.
     * @param sourceCol The client's or the depot's column from where we want to start to calculate the shortest path.
     * @return It returns the shortest path from the coordinates given to all other nodes of the graph.
     */
        public int[] dijkstra ( int sourceRow, int sourceCol){
            //Source node calculation (depending on its coordinates)
            int sourceNode = sourceRow * cols + sourceCol;

            // Array initialization for saving all minimum distances
            int[] distance = new int[cols*cols*rows*rows];
            Arrays.fill(distance, Integer.MAX_VALUE); // All the distances from the array are initialised with max values
            distance[sourceNode] = 0; // The distance from the source to the same source is 0

            // Crearea unui min-heap pentru a extrage nodurile în ordinea distanțelor minime estimate
            //Min-heap creation for node extraction in order of minimum distances
            PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(distance[a], distance[b]));
            minHeap.add(sourceNode);

            // We go throw min-heap until it is empty
            while (!minHeap.isEmpty()) {
                //  We extract the node with the smallest distance
                int currentNode = minHeap.poll();

                // Iterate through all neighbors of the current node in the adjacency matrix
                for (int neighbor = 0; neighbor < rows * cols; neighbor++) {
                    // Calculating the new estimated source-to-neighbor distance
                    int newDistance = distance[currentNode] + adjacencyMatrix[currentNode][neighbor];

                    // Updating the estimated distance if we find a shorter path
                    if (newDistance < distance[neighbor]) {
                        distance[neighbor] = newDistance;
                        minHeap.add(neighbor); // Adding the neighbor to the min-heap to be explored later
                    }
                }
            }

            return distance;
        }

    /**
     * This method prints the cost matrix of the grid graph.
     */
    public void printMatrix () {
            for (int i = 0; i < rows * cols; i++) {
                System.out.println();
                for (int j = 0; j < rows * cols; j++)
                    System.out.print(adjacencyMatrix[i][j] + " ");
            }
        }


    }
