package pathfinder;

import graph.Graph;
import pathfinder.datastructures.Path;
import java.util.*;

/**
 * A class for Dijkstra algorithm where finding the minimum-cost path
 * between two given nodes in a graph with all non-negative edge weights.
 */
public class Dijkstra {

    /**
     * Apply the Dijkstra algorithm to find the shortest path and traveling
     * weight from the given starting node to given ending node in the given graph
     *
     * @param graph the graph that our Dijkstra algorithm will be applied on
     * @param start the starting node for the Dijkstra algorithm
     * @param dest the ending node for the Dijkstra algorithm
     * @return return null if no path found from starting node to ending node,
     *         otherwise, return list of paths and total cost for traveling.
     * @throws RuntimeException if graph or nodes are null or given nodes are not in the given graph.
     */
    public static <T> Path<T> getPath(Graph<T, Double> graph, T start, T dest) {
        if (graph == null || start == null || dest == null) {
            throw new RuntimeException("The input graph or start or dest can't be null");
        } else if (!graph.containsNode(new Graph.Node<>(start)) || !graph.containsNode(new Graph.Node<>(dest))) {
            throw new RuntimeException("The node start or dest or both are not in the graph");
        }

        PriorityQueue<Path<T>> active = new PriorityQueue<>(new PathComparator());
        Set<T> finished = new HashSet<>();
        active.add(new Path<>(start));

        while (!active.isEmpty()) {
            Path<T> minPath = active.remove();
            T minDest = minPath.getEnd();
            if (minDest.equals(dest)) {
                return minPath;
            }
            if (!finished.contains(minDest)) {
                for(Graph.Edge<T, Double> edge : graph.getAllEdgesFrom(new Graph.Node<>(minDest))) {
                    if (!finished.contains(edge.getDes())) {
                        active.add(minPath.extend(edge.getDes().getNode(), edge.getLabel()));
                    }
                }
            }
            finished.add(minDest);
        }
        return null;
    }
}
