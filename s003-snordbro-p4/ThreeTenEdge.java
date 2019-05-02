import org.apache.commons.collections15.Factory;

import java.awt.Color;

import java.util.*;

class ThreeTenEdge {
	public static int edgeCount = 0;
	private static Random r = new Random(0);
	
	private int id;
	private int weight = 0;
	private Color c = Color.BLACK;
	
	public ThreeTenEdge() { this.id = edgeCount++; this.weight = r.nextInt(10)+1; }
	public ThreeTenEdge(int weight) { this.id = edgeCount++; this.weight = weight; }
	
	public int getWeight() { return weight; }
	public void setWeight(int weight) { this.weight = weight; }
	
	public Color getColor() { return c; }
	public void setColor(Color c) { this.c = c; }
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof ThreeTenEdge) {
			return this.id == ((ThreeTenEdge)o).id;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return id;
	}
	
	public static Factory<ThreeTenEdge> getFactory() { 
		return new Factory<ThreeTenEdge> () {
			public ThreeTenEdge create() {
				return new ThreeTenEdge();
			}
		};
	}
	
	@Override
	public String toString() {
		return ""+weight;
	}
}

