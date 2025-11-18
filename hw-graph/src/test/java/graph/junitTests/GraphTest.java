package graph.junitTests;

import graph.Graph;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GraphTest {
    private final Graph.Node<String> n1 = new Graph.Node<>("n1");
    private final Graph.Node<String> n2 = new Graph.Node<>("n2");
    private final Graph.Node<String> n3 = new Graph.Node<>("n3");
    private final Graph.Node<String> n4 = new Graph.Node<>("n2");
    private final Graph.Node<String> n5 = new Graph.Node<>("n3");

    @Test
    public void testGetNode(){
        assertEquals("n1", n1.getNode());
        assertEquals("n2", n2.getNode());
        assertEquals("n3", n3.getNode());
    }

    @Test
    public void testEqualNodes() {
        assertEquals(n2, n4);
        assertEquals(n3, n5);
    }

    @Test
    public void testUnequalNodes() {
        assertNotEquals(n1, n2);
        assertNotEquals(n3, n4);
    }

    private final Graph.Edge<String, String> e1 = new Graph.Edge<>(n1, n2, "a");
    private final Graph.Edge<String, String> e2 = new Graph.Edge<>(n2, n3, "b");
    private final Graph.Edge<String, String> e3 = new Graph.Edge<>(n2, n1, "c");
    private final Graph.Edge<String, String> e4 = new Graph.Edge<>(n1, n5, "a");
    private final Graph.Edge<String, String> e5 = new Graph.Edge<>(n5, n3, "c");
    private final Graph.Edge<String, String> e6 = new Graph.Edge<>(n5, n3, "c");
    private final Graph.Edge<String, String> e7 = new Graph.Edge<>(n1, n5, "a");

    @Test
    public void testGetEdge() {
        assertEquals("a", e1.getLabel());
        assertEquals("b", e2.getLabel());
        assertEquals("c", e3.getLabel());
        assertEquals("a", e4.getLabel());
    }

    @Test
    public void testGetSource() {
        assertEquals(n5, e5.getSource());
        assertEquals(n1, e4.getSource());
        assertEquals(n1, e1.getSource());
        assertEquals(n2, e3.getSource());
    }

    @Test
    public void testEqualEdges() {
        assertEquals(e4, e7);
        assertEquals(e5, e6);
    }

    @Test
    public void testUnequalEdges() {
        assertNotEquals(e3, e5);
        assertNotEquals(e1, e4);
        assertNotEquals(e1, e2);
        assertNotEquals(e2, e3);
        assertNotEquals(e4, e5);
    }

    private final Graph<String, String> graph = new Graph<>();

    @Test
    public void testEmpty1() {
        assertTrue(graph.isEmpty());
    }

    @Test
    public void testEmpty2() {
        assertEquals(0, graph.size());
    }

    @Test
    public void tryAddNodes() {
        assertTrue(graph.addNode(n1));
        assertTrue(graph.addNode(n2));
        assertTrue(graph.addNode(n3));
    }

    public void addNodes() {
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
    }

    @Test
    public void addNodeSuccess() {
        addNodes();
        assertFalse(graph.isEmpty());
    }

    @Test
    public void testSize1() {
        addNodes();
        assertEquals(3, graph.size());
    }

    @Test
    public void nodesAreExists() {
        addNodes();
        assertTrue(graph.containsNode(n1));
        assertTrue(graph.containsNode(n2));
        assertTrue(graph.containsNode(n3));
    }

    @Test
    public void tryAddNodeTwice() {
        addNodes();
        assertFalse(graph.addNode(n1));
        assertFalse(graph.addNode(n2));
        assertFalse(graph.addNode(n3));
    }

    @Test
    public void addEdgeBetweenNodes() {
        addNodes();
        assertTrue(graph.addEdge(n1, n2, "e1"));
        assertTrue(graph.addEdge(n1, n3, "e2"));
        assertTrue(graph.addEdge(n2, n3, "e3"));
    }

    @Test
    public void deleteNodes1() {
        addNodes();
        assertTrue(graph.deleteNode(n1));
        assertTrue(graph.deleteNode(n2));
        assertTrue(graph.deleteNode(n3));
    }

    @Test
    public void checkGraph1() {
        deleteNodes1();
        assertTrue(graph.isEmpty());
    }

    @Test
    public void deleteEdge1() {
        addEdgeBetweenNodes();
        assertTrue(graph.deleteEdge(n1, n2, "e1"));
        assertTrue(graph.deleteEdge(n1, n3, "e2"));
        assertTrue(graph.deleteEdge(n2, n3, "e3"));
    }

    @Test
    public void deleteEdge2() {
        addEdgeBetweenNodes();
        assertFalse(graph.deleteEdge(n2, n1, "e1"));
        assertFalse(graph.deleteEdge(n3, n2, "e2"));
        assertFalse(graph.deleteEdge(n1, n3, "e3"));
        assertFalse(graph.deleteEdge(n1, n2, "e2"));
    }

    @Test
    public void findChildren1() {
        addEdgeBetweenNodes();
        List<Graph.Node<String>> children = new ArrayList<>();
        children.add(n2);
        children.add(n3);
        assertEquals(children, graph.getChild(n1));
    }

    @Test
    public void testGetEdgesFrom() {
        addEdgeBetweenNodes();
        List<Graph.Edge<String, String>> edges = new ArrayList<>();
        Graph.Edge<String, String> e1 = new Graph.Edge<>(n1, n2, "e1");
        Graph.Edge<String, String> e2 = new Graph.Edge<>(n1, n3, "e2");
        edges.add(e1);
        edges.add(e2);
        assertEquals(edges, graph.getAllEdgesFrom(n1));
    }

    @Test
    public void testGetEdgesTo() {
        addEdgeBetweenNodes();
        List<Graph.Edge<String, String>> edges = new ArrayList<>();
        Graph.Edge<String, String> e1 = new Graph.Edge<>(n1, n3, "e2");
        Graph.Edge<String, String> e2 = new Graph.Edge<>(n2, n3, "e3");
        edges.add(e2);
        edges.add(e1);
        assertEquals(edges, graph.getAllEdgesTo(n3));
    }
}
