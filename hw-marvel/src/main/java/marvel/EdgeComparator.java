package marvel;

import graph.Graph;
import java.util.Comparator;

/**
 * A class that implements on comparator where comparing the two edges.
 */
public class EdgeComparator implements Comparator<Graph.Edge<String, String>> {

    /**
     * Compare the two edges where first comparing the nodes in alphabetical order
     * then comparing the edges label in alphabetical order.
     *
     * @param edge1 the first edge to be compared
     * @param edge2 the second edge to be compared.
     * @return return negative number if edge1 is edge1 behind the edge2,
     * return positive number is edge1 is in front of edge2.
     */
    @Override
    public int compare(Graph.Edge<String, String> edge1, Graph.Edge<String, String> edge2) {
        if (edge1 == null || edge2 == null) {
            throw new RuntimeException("The edge can't be null");
        } else if (!edge1.getDes().equals(edge2.getDes())) {
            return edge1.getDes().getNode().compareTo(edge2.getDes().getNode());
        }
        return edge1.getLabel().compareTo(edge2.getLabel());
    }
}
