/*
 * Copyright (C) 2023 Hal Perkins.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Winter Quarter 2023 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

package pathfinder;

import pathfinder.datastructures.Path;
import java.util.Map;
import graph.Graph;
import pathfinder.datastructures.Point;
import pathfinder.parser.CampusBuilding;
import pathfinder.parser.CampusPath;
import pathfinder.parser.CampusPathsParser;
import java.util.HashMap;
import java.util.List;

/**
 * A class represent buildings and paths in campus, where buildings include shortName
 * and longName, and path include starting point, ending point and coordinates.
 */
public class CampusMap implements ModelAPI {

    private final Map<String, String> building;
    private final Graph<Point, Double> campusMap;
    private final Map<String, Point> location;

    /**
     * Constructor load the campus path file and campus building file and give initialization.
     * Build the graph and construct the campus map.
     */
    public CampusMap() {
        building = new HashMap<>();
        campusMap = new Graph<>();
        location = new HashMap<>();
        List<CampusPath> campusPath = CampusPathsParser.parseCampusPaths("campus_paths.csv");
        List<CampusBuilding> campusBuilding = CampusPathsParser.parseCampusBuildings("campus_building.csv");

        for (CampusBuilding build : campusBuilding) {
            building.put(build.getShortName(), build.getLongName());
            location.put(build.getShortName(), new Point(build.getX(), build.getY()));
            campusMap.addNode(new Graph.Node<>(new Point(build.getX(), build.getY())));
        }

        for (CampusPath path : campusPath) {
            Point p1 = new Point(path.getX1(), path.getY1());
            Point p2 = new Point(path.getX2(), path.getY2());
            campusMap.addNode(new Graph.Node<>(p1));
            campusMap.addNode(new Graph.Node<>(p2));
            campusMap.addEdge(new Graph.Node<>(p1), new Graph.Node<>(p2), path.getDistance());
        }
    }

    /**
     * Check whether the given shortName exists in our campus map.
     *
     * @param shortName The short name of a building to query.
     * @return return true if shortName exists in campus, return false otherwise.
     * @throws RuntimeException if the given shortName is null.
     */
    @Override
    public boolean shortNameExists(String shortName) {
        if (shortName == null) {
            throw new RuntimeException("short name can't be null");
        }
        return building.containsKey(shortName);
    }

    /**
     * Find the longName of the building corresponding to the given shortName.
     *
     * @param shortName The short name of a building to look up.
     * @return return the longName of the building match with the given shortName
     * @throws RuntimeException if shortName is not in the campus map.
     */
    @Override
    public String longNameForShort(String shortName) {
        if (!shortNameExists(shortName)) {
            throw new RuntimeException("short name doesn't exist");
        }
        return building.get(shortName);
    }

    /**
     * Find and return the mapping from all the buildings' short names
     * to their long names in this campus map.
     *
     * @return return the mapping from all the buildings' short names
     * to their long names in this campus map.
     */
    @Override
    public Map<String, String> buildingNames() {
        return Map.copyOf(building);
    }

    /**
     * Find and return the shortest path between the given starting building and the ending building.
     *
     * @param startShortName The short name of the building at the beginning of this path.
     * @param endShortName   The short name of the building at the end of this path.
     * @return return the shortest path between the given starting building and the ending building.
     * @throws RuntimeException if given starting building is null or given ending building is null
     * or either starting building or ending building is not in the campus map.
     */
    @Override
    public Path<Point> findShortestPath(String startShortName, String endShortName) {
        if (startShortName == null || endShortName == null) {
            throw new RuntimeException("Short name can't be null");
        }
        if (!shortNameExists(startShortName) || !shortNameExists(endShortName)) {
            throw new RuntimeException("Name doesn't exist in campus");
        }

        return Dijkstra.getPath(campusMap, location.get(startShortName), location.get(endShortName));
    }
}
