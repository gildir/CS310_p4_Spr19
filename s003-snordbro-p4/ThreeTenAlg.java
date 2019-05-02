import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.EdgeType;

interface ThreeTenAlg {
	public EdgeType graphEdgeType();
	public void start();
	public boolean step();
	public void finish();
	public void reset(Graph<ThreeTenNode, ThreeTenEdge> g);
}