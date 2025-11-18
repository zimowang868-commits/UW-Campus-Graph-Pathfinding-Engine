# UW-Campus-Graph-Pathfinding-Engine
A full-stack **graph & pathfinding engine** with a **React** frontend and a **Java** backend.
The system models a real campus as a weighted graph, computes optimal walking routes, and exposes them through a clean web UI.

This project combines:
- Custom graph & pathfinding algorithms
- A production-style Java service layer
- A modern React single-page application for map interaction

---

## 🚀 Highlights

- 🧠 **Algorithmic core**: custom graph abstraction, Dijkstra’s shortest path, and flexible routing logic.
- 🏙 **Real-world map modeling**: campus buildings, paths, and intersections encoded as a weighted graph.
- 🌐 **Service-oriented backend**: Java server exposing JSON APIs for route computation.
- ⚛️ **React frontend**: interactive map UI for selecting start/end locations and visualizing computed routes.
- 🧪 **Thorough testing & invariants**: clear representation invariants, defensive checks, and unit tests.

---

## 🧠 Key Capabilities

### Graph & Data Structures  
- Efficient adjacency-list representation for large graphs.  
- Generic design for nodes, edges, weights and labels.  
- Robust invariants and integrity checking built in.  
- Support for weighted, directed, and undirected graphs.

### Pathfinding & Routing  
- Implementation of Dijkstra’s algorithm (and variants) for shortest path.  
- Support for routing on map-derived weighted graphs.  
- Seamless transition from algorithmic layer to service layer for user queries.

### Service & Integration  
- Java backend using Spark framework to handle HTTP requests for routing.  
- JSON payloads for input/output between client and server.  
- Modular service architecture enabling reuse of graph/model logic for different interfaces.

### Client / UX Layer  
- Interactive map visualization: user selects start and end nodes (buildings) and sees computed optimal route.  
- MVC style architecture separation: model (graph + campus data), view (map UI), controller (user interaction & server requests).