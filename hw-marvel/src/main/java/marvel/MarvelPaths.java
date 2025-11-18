package marvel;

import graph.Graph;
import java.util.*;

/**
 * A class that build a directed graph contains nodes and edges by referring a given data set.
 * The nodes represent the characters in Marvel comic. The edges represent the books.
 */

public class MarvelPaths {

    /**
     * Create and return a new graph with nodes and labeled edges where the nodes represent the
     * characters in Marvel comic and the edges represent the books. The nodes connect with the
     * book are the characters are in the same book.
     *
     * @param fileName the file where the graph will create data from it.
     * @spec.modifies modify the graph by adding nodes and edges.
     * @return return the graph we create from the given file.
     * @throws RuntimeException if the given file is null
     */
    public static Graph<String, String> createGraph(String fileName) {
        if (fileName == null) {
            throw new RuntimeException("The fileName can't be null");
        }
        Map<String, List<String>> comics = MarvelParser.parseData(fileName);
        Graph<String,String> graph = new Graph<>();
        for (String comic : comics.keySet()) {
            for (int i = 0; i < comics.get(comic).size() - 1; i++) {
                Graph.Node<String> node1 = new Graph.Node<>(comics.get(comic).get(i));
                graph.addNode(node1);
                for (int j = i + 1; j < comics.get(comic).size(); j++) {
                    Graph.Node<String> node2 = new Graph.Node<>(comics.get(comic).get(j));
                    graph.addNode(node2);
                    graph.addEdge(node1, node2, comic);
                    graph.addEdge(node2, node1, comic);
                }
            }
        }
        return graph;
    }

    /**
     * Find the shortest path between the two given nodes. If the character pairs is  connected
     * by multiple shortest paths with the same length, return the alphabetically least path.
     *
     * @param graph the graph where contains the nodes we need to find the shortest path between them
     * @param start the starting node
     * @param end the ending node
     * @return return a list of edges contains the shortest path between two given nodes.
     * @throws  RuntimeException if the graph == null  or start node == null or end node == null.
     */
    public static List<Graph.Edge<String, String>> shortPath (Graph<String, String> graph, String start, String end) {
        if (graph == null || start == null || end == null) {
            throw new RuntimeException("Parameters can't be null");
        }
        Queue<String> nodes = new LinkedList<>();
        Map<String, List<Graph.Edge<String, String>>> visitedNodes = new HashMap<>();
        nodes.add(start);
        visitedNodes.put(start, new ArrayList<>());
        while (!nodes.isEmpty()) {
            String node = nodes.remove();
            if (node.equals(end)) {
                return new ArrayList<>(visitedNodes.get(node));
            }
            List<Graph.Edge<String, String>> edges = new ArrayList<>(graph.getAllEdgesFrom(new Graph.Node<>(node)));
            edges.sort(new EdgeComparator());
            for (Graph.Edge<String, String > edge : edges) {
                if (!visitedNodes.containsKey(edge.getDes().getNode())) {
                    List<Graph.Edge<String, String>> p = new ArrayList<>(visitedNodes.get(node));
                    List<Graph.Edge<String, String>> pPrime = new ArrayList<>(p);
                    pPrime.add(edge);
                    visitedNodes.put(edge.getDes().getNode(), pPrime);
                    nodes.add(edge.getDes().getNode());
                }
            }
        }
        return visitedNodes.get(start);
    }
}
