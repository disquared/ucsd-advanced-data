package roadgraph;

/**
 * Represents an edge in a {@link MapGraph}.
 */
public class MapEdge {

    /** THe two end points of the edge */
	private MapNode start;
	private MapNode end;
	
	/** The name of the road */
	private String roadName;
	
	/** The type of the road */
	private String roadType;
	
	/** THe length of the road segment, in km */
	private Double length;
	
	static final Double DEFAULT_LENGTH = 0.01;
	
	public MapEdge(MapNode start, MapNode end, String roadName, String roadType) {
		this(start, end, roadName, roadType, DEFAULT_LENGTH);
	}
	
	public MapEdge(MapNode start, MapNode end,
	        String roadName, String roadType, double length) {
        this.start = start;
        this.end = end;
        this.roadName = roadName;
        this.roadType = roadType;
        this.length = length;
    }
	
	public MapNode getStart() {
		return this.start;
	}
	
	public MapNode getEnd() {
		return this.end;
	}
	
	public String getRoadName() {
		return this.roadName;
	}
	
	public String getRoadType() {
		return this.roadType;
	}
	
	public Double getLength() {
	    return this.length;
	}
	
	public String toString() {
		return "start:" + this.start + ", end:" + this.end + ", roadName:" + this.roadName;
	}
}
