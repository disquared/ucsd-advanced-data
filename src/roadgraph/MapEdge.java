package roadgraph;

import geography.GeographicPoint;

/**
 * Represents an edge in a {@link MapGraph}.
 */
public class MapEdge {

	private GeographicPoint start;
	private GeographicPoint end;
	private String roadName;
	private String roadType;
	
	public MapEdge(GeographicPoint start, GeographicPoint end, String roadName, String roadType) {
		this.start = start;
		this.end = end;
		this.roadName = roadName;
		this.roadType = roadType;
	}
	
	public GeographicPoint getStart() {
		return this.start;
	}
	public void setStart(GeographicPoint start) {
		this.start = start;
	}
	
	public GeographicPoint getEnd() {
		return this.end;
	}
	public void setEnd(GeographicPoint end) {
		this.end = end;
	}
	
	public String getRoadName() {
		return this.roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	
	public String getRoadType() {
		return this.roadType;
	}
	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}
	
	public String toString() {
		return "start:" + this.start + ", end:" + this.end + ", roadName:" + this.roadName;
	}
}
