package roadgraph;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import geography.GeographicPoint;

/**
 * Represents a node in a {@link MapGraph}.
 */
public class MapNode {

    /** The latitude and longitude of this node */
	private GeographicPoint location;
	
	/** The list of edges out of this node */
	private Set<MapEdge> edges;
	
	public MapNode(GeographicPoint location) {
		this.location = location;
		this.edges = new HashSet<>();
	}
	
	public MapNode(GeographicPoint location, Set<MapEdge> edges) {
        this.location = location;
        this.edges = edges;
    }
	
	public GeographicPoint getLocation() {
		return this.location;
	}
	
	public Set<MapEdge> getEdges() {
		return this.edges;
	}
	
	/**
	 * Adds an edge to this node.
	 * @param edge
	 */
	public void addEdge(MapEdge edge) {
		this.edges.add(edge);
	}
	
	/**
	 * Removes an edge from this node.
	 * Returns boolean stating whether the removal was successful.
	 * @param edge
	 * @return true if the edge was removed, false if the edge is not connected to this node.
	 */
	public boolean removeEdge(MapEdge edge) {
		return this.edges.remove(edge);
	}
	
	/**
	 * Returns the number of edges connected to this node.
	 * @return
	 */
	public int getNumEdges() {
		return this.edges.size();
	}
	
	/**
	 * Returns the list of neighbors of this node represented as GeographicPoints.
	 * @return
	 */
	public Set<MapNode> getNeighbors() {
		return this.edges.stream().map(MapEdge::getEnd).collect(Collectors.toSet());
	}
	
	/**
	 * Returns whether a given map node is a neighbor of this node.
	 * @param other
	 * @return
	 */
	public boolean hasNeighbor(MapNode other) {
	    return !getDistanceToNode(other).equals(Double.POSITIVE_INFINITY);
	}
	
	/**
	 * Returns the distance from this node to another node.
	 * If the other node is unreachable, then distance is set to infinity.
	 * @param other    a MapNode
	 * @return         the distance to the other node
	 */
	public Double getDistanceToNode(MapNode other) {
	    Double d = Double.POSITIVE_INFINITY;
	    for (MapEdge e : this.edges) {
	        if (e.getEnd().equals(other)) {
	            d = e.getLength();
	            break;
	        }
	    }
	    return d;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (o == null || !(o instanceof MapNode)) {
	        return false;
	    }
	    MapNode node = (MapNode) o;
	    return node.location.equals(this.location);
	};
	
	@Override
	public int hashCode() {
	    return this.location.hashCode();
	};
	
	@Override
	public String toString() {
	    String toReturn = "[NODE at location (" + location + ")";
        toReturn += " intersects streets: ";
        for (MapEdge e: edges) {
            toReturn += e.getRoadName() + ", ";
        }
        toReturn += "]";
        return toReturn;
	}
}
