package roadgraph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import geography.GeographicPoint;

/**
 * Represents a node in a {@link MapGraph}.
 */
public class MapNode {

	private GeographicPoint location;
	private List<MapEdge> edges;
	
	public MapNode(GeographicPoint location) {
		this.location = location;
		this.edges = new ArrayList<>();
	}
	
	public GeographicPoint getLocation() {
		return this.location;
	}
	public void setLocation(GeographicPoint location) {
		this.location = location;
	}
	
	public List<MapEdge> getEdges() {
		return this.edges;
	}
	public void setEdges(List<MapEdge> edges) {
		this.edges = edges;
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
	public List<GeographicPoint> getNeighbors() {
		return this.edges.stream().map(MapEdge::getEnd).collect(Collectors.toList());
	}
	
	public String toString() {
		return "(" + this.location.getX() + "," + this.location.getY() + ")";
	}
}
