/*
 * Copyright (C) 2022 Kevin Zatloukal.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Spring Quarter 2022 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

package campuspaths;

import campuspaths.utils.CORSFilter;
import com.google.gson.Gson;
import pathfinder.CampusMap;
import spark.Spark;

/**
 * Main class that sets up the campus path server, which allows users to read information about the campus map
 */
public class SparkServer {

    /**
     * main method to run the server and read information about the campus map
     * @param args running main
     */
    public static void main(String[] args) {
        CORSFilter corsFilter = new CORSFilter();
        corsFilter.apply();

        CampusMap map = new CampusMap();

        Spark.get("/findShortPath", (req, res) -> {
            res.type("text/PathPoint");
            String startName = req.queryParams("startName");
            String endName = req.queryParams("endName");
            if (startName == null) {
                res.status(400);
                return "startName missing";
            }
            if (endName == null) {
                res.status(400);
                return "endName missing";
            }
            Gson gson = new Gson();
            String jsonPath = gson.toJson(map.findShortestPath(startName, endName));
            return jsonPath;
        });

        Spark.get("/allShortNames", (req,res) -> {
            res.type("list");
            Gson gson = new Gson();
            String jsonShortNameSet = gson.toJson(map.buildingNames().keySet());
            return jsonShortNameSet;
        });

        Spark.get("/allLongNames", (req,res) -> {
            res.type("list");
            Gson gson = new Gson();
            String jsonLongNameSet = gson.toJson(map.buildingNames().values());
            return jsonLongNameSet;
        });
    }
}
