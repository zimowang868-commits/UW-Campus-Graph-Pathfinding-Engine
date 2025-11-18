package marvel.junitTests;

import graph.Graph;
import marvel.MarvelPaths;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MarvelTest {
    private Graph<String, String> graph;

    @Before
    public void build() {
        graph = MarvelPaths.createGraph("soccerPlayersInTeams.csv");
    }

    @Test
    public void testRegular1() {
        List<Graph.Edge<String, String>> path1 = MarvelPaths.shortPath(graph, "Cristiano-Ronaldo", "Karim-Benzema");
        List<Graph.Edge<String, String>> path2 = new ArrayList<>();
        path2.add(new Graph.Edge<String, String>(new Graph.Node<String>("Cristiano-Ronaldo"),
                  new Graph.Node<String>("Karim-Benzema"), "Real-Madrid"));
        assertEquals(path1, path2);
    }

    @Test
    public void testRegular2() {
        List<Graph.Edge<String, String>> path1 = MarvelPaths.shortPath(graph, "Marcus-Rashford", "Karim-Benzema");
        List<Graph.Edge<String, String>> path2 = new ArrayList<>();
        path2.add(new Graph.Edge<String, String>(new Graph.Node<String>("Marcus-Rashford"),
                new Graph.Node<String>("Cristiano-Ronaldo"), "Manchester-United"));
        path2.add(new Graph.Edge<String, String>(new Graph.Node<String>("Cristiano-Ronaldo"),
                new Graph.Node<String>("Karim-Benzema"), "Real-Madrid"));
        assertEquals(path1, path2);
    }

    @Test
    public void testRegular3() {
        List<Graph.Edge<String, String>> path1 = MarvelPaths.shortPath(graph, "Casemiro", "Gareth-Bale");
        List<Graph.Edge<String, String>> path2 = new ArrayList<>();
        path2.add(new Graph.Edge<String, String>(new Graph.Node<String>("Casemiro"),
                new Graph.Node<String>("Cristiano-Ronaldo"), "Manchester-United"));
        path2.add(new Graph.Edge<String, String>(new Graph.Node<String>("Cristiano-Ronaldo"),
                new Graph.Node<String>("Gareth-Bale"), "Real-Madrid"));
        assertEquals(path1, path2);
    }

    @Test
    public void testRegular4() {
        List<Graph.Edge<String, String>> path1 = MarvelPaths.shortPath(graph, "Casemiro", "Robert-Lewandowski");
        List<Graph.Edge<String, String>> path2 = new ArrayList<>();
        path2.add(new Graph.Edge<String, String>(new Graph.Node<String>("Casemiro"),
                new Graph.Node<String>("Cristiano-Ronaldo"), "Manchester-United"));
        path2.add(new Graph.Edge<String, String>(new Graph.Node<String>("Cristiano-Ronaldo"),
                new Graph.Node<String>("Kaka"), "Real-Madrid"));
        path2.add(new Graph.Edge<String, String>(new Graph.Node<String>("Kaka"),
                new Graph.Node<String>("Zlatan-Ibrahimovic"), "AC-Milan"));
        path2.add(new Graph.Edge<String, String>(new Graph.Node<String>("Zlatan-Ibrahimovic"),
                new Graph.Node<String>("Robert-Lewandowski"), "Barcelona"));
        assertEquals(path1, path2);
    }

    @Test
    public void testRegular5() {
        List<Graph.Edge<String, String>> path1 = MarvelPaths.shortPath(graph, "Luka-Modric", "Zlatan-Ibrahimovic");
        List<Graph.Edge<String, String>> path2 = new ArrayList<>();
        path2.add(new Graph.Edge<String, String>(new Graph.Node<String>("Luka-Modric"),
                new Graph.Node<String>("Kaka"), "Real-Madrid"));
        path2.add(new Graph.Edge<String, String>(new Graph.Node<String>("Kaka"),
                new Graph.Node<String>("Zlatan-Ibrahimovic"), "AC-Milan"));
        assertEquals(path1, path2);
    }

    @Test
    public void testGoItself1() {
        List<Graph.Edge<String, String>> path1 = MarvelPaths.shortPath(graph, "Federico-Chiesa", "Federico-Chiesa");
        List<Graph.Edge<String, String>> path2 = new ArrayList<>();
        assertEquals(path1, path2);
    }

    @Test
    public void testGoItself2() {
        List<Graph.Edge<String, String>> path1 = MarvelPaths.shortPath(graph, "Marcus-Rashford", "Marcus-Rashford");
        List<Graph.Edge<String, String>> path2 = new ArrayList<>();
        assertEquals(path1, path2);
    }

    @Test
    public void testNoPath1() {
        List<Graph.Edge<String, String>> path1 = MarvelPaths.shortPath(graph, "Federico-Chiesa", "Christiano-Ronaldo");
        List<Graph.Edge<String, String>> path2 = new ArrayList<>();
        assertEquals(path1, path2);
    }

    @Test
    public void testNoPath2() {
        List<Graph.Edge<String, String>> path1 = MarvelPaths.shortPath(graph, "Dusan-Vlahovic", "Casemiro");
        List<Graph.Edge<String, String>> path2 = new ArrayList<>();
        assertEquals(path1, path2);
    }

    @Test (expected = RuntimeException.class)
    public void testUnknown1() {
        List<Graph.Edge<String, String>> path1 = MarvelPaths.shortPath(graph, "Neymar", "Messi");
        List<Graph.Edge<String, String>> path2 = new ArrayList<>();
        assertEquals(path1, path2);
    }

    @Test (expected = RuntimeException.class)
    public void testUnknown2() {
        List<Graph.Edge<String, String>> path1 = MarvelPaths.shortPath(graph, "Son-Heung-min", "Karim-Benzema");
        List<Graph.Edge<String, String>> path2 = new ArrayList<>();
        assertEquals(path1, path2);
    }

    @Test (expected = RuntimeException.class)
    public void testUnknown3() {
        List<Graph.Edge<String, String>> path1 = MarvelPaths.shortPath(graph, "Pele", "Diego-Maradona");
        List<Graph.Edge<String, String>> path2 = new ArrayList<>();
        assertEquals(path1, path2);
    }

    @Test (expected = RuntimeException.class)
    public void buildNull1() {
        MarvelPaths.createGraph(null);
    }

    @Test (expected = RuntimeException.class)
    public void buildNull2() {
        MarvelPaths.shortPath(null, "Karim-Benzema", "Cristiano-Ronaldo");
    }

    @Test (expected = RuntimeException.class)
    public void buildNull3() {
        MarvelPaths.shortPath(graph, null, "Harry-Kane");
    }

    @Test (expected = RuntimeException.class)
    public void buildNull4() {
        MarvelPaths.shortPath(graph, "Karim-Benzema", null);
    }
}
