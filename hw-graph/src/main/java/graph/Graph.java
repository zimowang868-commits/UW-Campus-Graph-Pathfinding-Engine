package graph;

import java.util.*;

/**
 * An object that create a multi graph where edges are labeled and make connection between the nodes, and node is for
 * storing the data. The graph can't have multiple nodes with same value in them. No 2 edges with the same parent and
 * child nodes will have the same edge label.
 *
 * @param <T> A set of vertices
 * @param <E> A set of edges
 */
public class Graph<T, E> {

    // AF(this) = a directed graph with labeled edge is represented by
    // a HashMap where key is the nodes in map and the value is a list
    // of edges that pointing from this node to other nodes.
    //
    // Rep invariant: the graph != null, the nodes in graph != null,
    // each edge that connect between nodes != null.

    private final HashMap<Node<T>, List<Edge<T, E>>> graph;

    /**
     * Constructor create an empty graph.
     *
     * @spec.effects create an empty graph where the graph will be added nodes and edges later.
     */
    public Graph() {
        graph = new HashMap<>();
        checkRep();
    }

    /**
     * Return true if node doesn't exist in the graph and successfully add to the graph. Return false
     * if the node already in the graph and can't be added again.
     *
     * @param node The node will be added to the graph
     * @spec.modifies modify the graph by adding the nodes
     * @spec.requires node != null
     * @return return true if node is successfully be added, return false otherwise.
     */
    public boolean addNode(Node<T> node) {
        if (!graph.containsKey(node) && node != null) {
            graph.put(node, new ArrayList<>());
            return true;
        }
        return false;
    }

    /**
     * Return true if node exist in the graph and successfully delete it from graph. Return false
     * if the node doesn't in the graph, and we can't remove it.
     *
     * @param node The node will be deleted from the graph.
     * @spec.modifies  modify the graph by removing the nodes
     * @return return true if the node is removed successfully from the graph, return false otherwise
     * @spec.requires node != null
     */
    public boolean deleteNode(Node<T> node) {
        if (node != null && graph.containsKey(node)) {
            graph.remove(node);
            for (Node<T> childNode : graph.keySet()) {
                for (Edge<T, E> edge : graph.get(childNode)) {
                    if (edge.getDes().equals(node)) {
                        this.deleteEdge(childNode, node, edge.getLabel());
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Add an edge labeled wth name that connect the start node and the end node.
     * Return true if successfully add the edge. If the graph is empty or there
     * the duplicates edge will be added between nodes, return false.
     *
     * @param start The starting node
     * @param end The ending node
     * @spec.modifies  modify the graph by adding the edges between nodes
     * @param labelNum the edge with a name that connect with starting and ending node
     * @return Return true if successfully add the edge, return false otherwise. If the
     * graph is empty or there the duplicates edge will be added between nodes, return false.
     * @spec.requires start != null, labelNum != null, end != null.
     */
    public boolean addEdge(Node<T> start, Node<T> end, E labelNum) {
        if (!graph.containsKey(start) || !graph.containsKey(end) || start == null || end == null || labelNum == null) {
            return false;
        }
        for (Edge<T, E> edge : graph.get(start)) {
            if (edge.getDes() == end && edge.getLabel() == labelNum) {
                return false;
            }
        }
        graph.get(start).add(new Edge<>(start, end, labelNum));
        return true;
    }

    /**
     * Remove an edge labeled wth name that connect the start node and the end node.
     *
     * @param start The starting node
     * @param end The ending node
     * @param labelNum the edge with a name that connect with starting and ending node
     * @spec.modifies  modify the graph by removing the edges between the nodes
     * @spec.requires start != null, labelNum != null, end != null.
     * @return return true if successfully remove the edge, return false otherwise.
     */
    public boolean deleteEdge(Node<T> start, Node<T> end, E labelNum) {
        if (!graph.containsKey(start) || !graph.containsKey(end) || start == null || end == null || labelNum == null) {
            return false;
        }
        for (Edge<T, E> edge : graph.get(start)) {
            if (edge.getDes() == end && edge.getLabel() == labelNum) {
                graph.get(start).remove(new Edge<>(start, end, labelNum));
                return true;
            }
        }
        return false;
    }

    /**
     * Get and return all the child nodes of the given node.
     *
     * @param node the given node
     * @return Return all the child nodes of the given node.
     */
    public List<Node<T>> getChild(Node<T> node) {
        if (node == null) {
            throw new RuntimeException("Node can't be null");
        }
        List<Node<T>> children = new ArrayList<>();
        for (Edge<T, E> edge : graph.get(node)) {
            children.add(edge.getDes());
        }
        return children;
    }

    /**
     * Get and return all the edges that pointing out from the given node
     *
     * @param node the given node
     * @throws RuntimeException if the node is not in the graph.
     * @return a list of edges where all those edges are pointing out from the given node
     */
    public List<Edge<T, E>> getAllEdgesFrom(Node<T> node) {
        checkRep();
        if (!graph.containsKey(node)) {
            throw new RuntimeException("The given node is not in the graph");
        }
        return new ArrayList<>(graph.get(node));
    }

    /**
     * Get and return all the edges that pointing towards to the given node.
     *
     * @param node the given node
     * @throws RuntimeException if the node is null.
     * @return a list of edges where all those edges are pointing towards to the given node
     */
    public List<Edge<T, E>> getAllEdgesTo(Node<T> node) {
        if (node == null) {
            throw new RuntimeException("Node can't be null");
        }
        List<Edge<T, E>> edges = new ArrayList<>();
        for (Node<T> currNode : graph.keySet()) {
            for (Edge<T, E> edge : graph.get(currNode)) {
                if (edge.getDes().equals(node)) {
                    edges.add(edge);
                }
            }
        }
        return edges;
    }

    /** Get and return all the nodes in the graph as list
     *
     * @return return all the nodes as list in the graph.
     */
    public List<Node<T>> allNodes() {
        return new ArrayList<>(graph.keySet());
    }

    /**
     * Check whether the graph contain the node.
     *
     * @param node The node will be checked
     * @return Return true if the graph contain the node, return false otherwise.
     */
    public boolean containsNode(Node<T> node) {
        return graph.containsKey(node);
    }

    /**
     * Return number of nodes in the graph, that is the size of the graph.
     *
     * @return Return the number of nodes in the graph.
     */
    public int size() {
        return graph.size();
    }

    /**
     * Check whether the graph is empty.
     *
     * @return Return true is the graph is empty, return false otherwise.
     */
    public boolean isEmpty() {
        return graph.size() == 0;
    }

    /**
     * Check if rep invariant holds.
     *
     * @throws RuntimeException if violate rep invariant
     */
    private void checkRep() {
        if (graph == null) {
            throw new RuntimeException("The graph can't be null");
        }
        for (Node<T> node : graph.keySet()) {
            if (node == null) {
                throw new RuntimeException("The node can't be null");
            }
            for (Edge<T, E> edge : graph.get(node)) {
                if (edge == null) {
                    throw new RuntimeException("The node can't be null");
                }
            }
        }
    }

    /**
     * Represent a node to store any type of the data.
     */
    public static class Node<T> {

        // AF(this) = a node in graph that storing a type of data.
        //
        // Rep invariant: the data != null.

        private final T val;

        /**
         * Constructor create a node and name it.
         *
         * @param val Node will be created.
         * @spec.requires node != null.
         */
        public Node(T val) {
            this.val = val;
            checkRep();
        }

        /**
         * Return the data in the node
         *
         * @return the data stored in the node
         */
        public T getNode() {
            return val;
        }

        /**
         * Check whether other object same as this object.
         *
         * @param obj the object will be checked
         * @return Return true if other object equals this object, return false otherwise.
         */
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Graph.Node<?>)) {
                return false;
            }
            Graph.Node<?> node = (Graph.Node<?>) obj;
            return this.getNode().equals(node.getNode());
        }

        /**
         * Return hashcode value.
         *
         * @return Return hashcode value.
         */
        @Override
        public int hashCode() {
            checkRep();
            return 5 * this.getNode().hashCode();
        }

        /**
         * Check if invariant holds.
         */
        private void checkRep() {
            if (this.val == null) {
                throw new RuntimeException("The data can't be null");
            }
        }
    }

    /**
     * Represent am Edge that is labeled and with a child node.
     */
    public static class Edge<T, E> {

        // AF(this) = a labeled edge that connect with source node and dest node.
        // Through this edge, the node start will point to the node end.
        //
        // Rep invariant: the source != null, the dest != null, the labelNum != null.

        private final Node<T> source;
        private final Node<T> dest;
        private final E labelNum;

        /**
         * Constructor create a labeled edge that connect to the source node and dest node.
         *
         * @param source Source node that the edge connect to.
         * @param dest Destination node that the edge connect to.
         * @param labelNum The edge name where the edge connect source node and dest node.
         */
        public Edge(Node<T> source, Node<T> dest, E labelNum) {
            this.source = source;
            this.dest = dest;
            this.labelNum = labelNum;
            checkRep();
        }

        /**
         * Get and return the source node the edge connect to
         *
         * @return the source node the edge connect to
         */
        public Node<T> getSource() {
            return source;
        }

        /**
         * Get and return the destination node the edge connect to
         *
         * @return the destination node the edge connect to
         */
        public Node<T> getDes() {
            return dest;
        }

        /**
         * Return the edge label.
         *
         * @return the label of the edge.
         */
        public E getLabel() {
            return labelNum;
        }

        /**
         * Check whether other object same as this object.
         *
         * @param obj the object will be checked
         * @return Return true if other object equals this object, return false otherwise.
         */
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Graph.Edge<?, ?>)) {
                return false;
            }
            Graph.Edge<?, ?> edge = (Graph.Edge<?, ?>) obj;
            return this.getSource().equals(edge.getSource()) &&
                    this.getDes().equals(edge.getDes()) && this.getLabel().equals(edge.getLabel());
        }

        /**
         * Return hashcode value.
         *
         * @return Return hashcode value.
         */
        @Override
        public int hashCode() {
            checkRep();
            return 5 * this.getSource().hashCode() + 7 * this.getDes().hashCode() + 11 * this.getLabel().hashCode();
        }

        /**
         * Check if invariant holds.
         */
        private void checkRep() {
            if (source == null || dest == null || labelNum == null) {
                throw new RuntimeException("The nodes or edge can't be null");
            }
        }
    }
}