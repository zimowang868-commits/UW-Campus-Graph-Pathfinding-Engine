# UW-Campus-Graph-Pathfinding-Engine
An end-to-end Java graph and path-finding engine built for real-world map data and routing services. 
This project demonstrates a full stack of algorithmic rigor, modular software design, and service-oriented architecture.

---

## 🚀 Project Highlights

- **Graph ADT and algorithm suite**: Custom graph abstraction, BFS/DFS, Dijkstra’s shortest-path, weighted graphs.  
- **Map modelling & routing**: Real-world campus map translated into a weighted graph; shortest path queries between buildings.  
- **Service layer**: Java backend (Spark framework) exposing REST endpoints for path-finding and map queries.  
- **Frontend integration**: UI/client component interacts with backend to display routes, leveraging MVC pattern and JSON communication.  
- **Engineering discipline**: Modular package architecture, representation invariants, defensive programming, unit and integration tests.

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