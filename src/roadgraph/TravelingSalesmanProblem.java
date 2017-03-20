package roadgraph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import geography.GeographicPoint;
import util.GraphLoader;

public class TravelingSalesmanProblem {

    /**
     * A brute-force solution to the Traveling Salesperson Problem using recursion.
     * Returns null if there is no solution.
     * @param nodes
     * @return
     */
    public static List<GeographicPoint> travelingSalespersonProblem(Collection<MapNode> nodes) {
        List<GeographicPoint> solution = null;
        // base cases
        if (nodes == null) {
            solution = new ArrayList<>();
        }
        else if (nodes.size() <= 1) {
            solution = nodes.stream()
                    .map(node -> node.getLocation()).collect(Collectors.toList());
        }
        else {
            Set<MapNode> nodesCopy = new HashSet<>(nodes);
            // call recursive helper function using backtrack to find solution
            List<GeographicPoint> candidateSolution = tspHelper(nodes, new ArrayList<>());
            if (candidateSolution.size() == nodesCopy.size()) {
                solution = candidateSolution;
            }
        }
        printTSPSolution(solution);
        return solution;
    }
 
    private static List<GeographicPoint> tspHelper(
            Collection<MapNode> nodesNotVisited, List<MapNode> route) {
        // still some nodes unvisited
        if (!nodesNotVisited.isEmpty()) {
            for (Iterator<MapNode> i = nodesNotVisited.iterator(); i.hasNext(); ) {
                MapNode node = i.next();
                // if the route is empty, or
                // if there is an edge from the last node in the route to the current node 
                if (route.isEmpty() || route.get(route.size() - 1).hasNeighbor(node)) {
                    // add the node to the end of the route
                    nodesNotVisited.remove(node);
                    route.add(new MapNode(node.getLocation(), node.getEdges()));
                    List<MapNode> routeCopy = new ArrayList<>(route);
                    
                    return tspHelper(nodesNotVisited, routeCopy);
                }
            }
        }
        // all nodes visited, just need a path from last node to first node
        else if (route.get(route.size() - 1).hasNeighbor(route.get(0))) {
            
        }
        return route.stream().map(node -> node.getLocation()).collect(Collectors.toList());
    }

    private static void printTSPSolution(List<GeographicPoint> solution) {
        if (solution != null) {
            StringBuilder sb = new StringBuilder();
            for (GeographicPoint p : solution) {
                sb.append("(" + p.getX() + "," + p.getY() + ") -> ");
            }
            sb.append("(" + solution.get(0).getX() + "," + solution.get(0).getY() + ")");
            System.out.println("Solution to TSP: " + sb.toString());
        }
        else {
            System.out.println("No TSP solution");
        }
    }
    
    public static void main(String[] args)
    {
        // a simple graph with three edges and vertices.
        // Should have a solution.
        MapGraph tspMap = new MapGraph();
        GraphLoader.loadRoadMap("data/testdata/tsptest.map", tspMap);
        travelingSalespersonProblem(tspMap.getMapNodes());
        
        // a modification of simpletest.map with some edges removed and only 6 vertices.
        // Should have a solution.
        MapGraph tspMap2 = new MapGraph();
        GraphLoader.loadRoadMap("data/testdata/tsptest2.map", tspMap2);
        travelingSalespersonProblem(tspMap2.getMapNodes());
        
        // a modification of tsptest2.map with one critical edge removed.
        // Should have no solution.
        MapGraph tspMap3 = new MapGraph();
        GraphLoader.loadRoadMap("data/testdata/tsptest3.map", tspMap3);
        travelingSalespersonProblem(tspMap3.getMapNodes());
    }
}
