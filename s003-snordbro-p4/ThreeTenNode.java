import org.apache.commons.collections15.Factory;

import java.awt.Color;

import java.util.*;

class ThreeTenNode implements Comparable<ThreeTenNode> {
	public static int nodeCount = 0;
	
	private int id;
	private String text;
	private Color c = Color.WHITE;
	
	public ThreeTenNode() { this.id = nodeCount++; this.text = ""+this.id; }
	
	public Color getColor() { return c; }
	public void setColor(Color c) { this.c = c; }
	
	public String getText() { return text; }
	public void setText(String text) { this.text = text; }
	
	@Override
	public String toString() {
		return ""+text;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof ThreeTenNode) {
			return this.id == ((ThreeTenNode)o).id;
		}
		return false;
	}
	
	@Override
	public int compareTo(ThreeTenNode n) {
		return this.id-n.id;
	}
	
	@Override
	public int hashCode() {
		return id;
	}
	
	public static Factory<ThreeTenNode> getFactory() { 
		return new Factory<ThreeTenNode> () {
			public ThreeTenNode create() {
				return new ThreeTenNode();
			}
		};
	}
}
