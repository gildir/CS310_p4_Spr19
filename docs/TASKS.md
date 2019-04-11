## Tasks
[back](README.md)

There are **5** tasks in this assignment. It is suggested that you implement these tasks in the given order. 

### Task 1: Examine the JCF Classes (0%)

Read and familiarize yourself with the JCF classes. You may use these classes if you wish (or you are welcome to reuse your previous project code, or "roll your own" versions of these classes). Below is an overview of the most likely classes to be helpful in this assignment:

1. [ArrayList](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html) and [LinkedList](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedList.html) - Java's list classes supported by a dynamic array or a linked structure respectively
2. [ArrayDeque](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayDeque.html) - Java's double ended queue supported by an array, LinkedList is Java's double ended queue supported by a linked list
3. [PriorityQueue](https://docs.oracle.com/javase/8/docs/api/java/util/PriorityQueue.html) - Java's priority queue (supported by a heap)
4. [HashMap](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html) and [HashSet](https://docs.oracle.com/javase/8/docs/api/java/util/HashSet.html) - Java's map and set supported by a hash table
5. [TreeMap](https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html) and [TreeSet](https://docs.oracle.com/javase/8/docs/api/java/util/TreeSet.html) - Java's map and set supported by a red-black tree
6. [Collection](https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html) - All JCF classes implement this generic interface.

Where should you start? The Java Tutorials of course! (If you didn't know, Oracle's official Java documentation includes a set of tutorials.) The [Trail: Collections](https://docs.oracle.com/javase/tutorial/collections/) tutorial will provide you with more than enough information on how to use these classes.

### Task 2: Read the Provided Code Base (0%)

Read and familiarize yourself with the code. This will save you a lot of time later. An overview of the provided code in is given below, but you need to read the code base yourself.

```java

//This class represents a node in a graph.
//It is provided, but you may edit it in some specific ways if you want.
class ThreeTenNode {...}

//This class represents an edge in a graph.
//It is provided, but you may edit it in some specific ways if you want.
class ThreeTenEdge {...}

//This class represents a directed graph.
//You will write 99% of this class, but a template is provided.
class ThreeTenGraph<V,E> implements Graph<V,E>, DirectedGraph<V,E> {...}

//This interface defines an algorithm that can be simulated with the GUI
interface ThreeTenAlg {...}

//You will be writing this algorithm (Dijkstra's shortest path)
class ThreeTenDijkstra implements ThreeTenAlg {...}

//This is the simulator and handles all the graphical stuff, it is provided.
class SimGUI {...}

```

### Task 3: Implement a Directed Graph Class to Support the Simulator (50%)

In order for the simulator to work, you need an internal representation of a graph. The JUNG library provides an interfaces for this: `Graph<V,E>`. You need to implement the directed graph `ThreeTenGraph<V,E>` (in `ThreeTenGraph.java`) which implements the `Graph<V,E>` interface.

Below is a quick overview of the methods you need to support. Note that in the template, actual JavaDoc comments are provided. That said, the JavaDocs are those from the `Graph<>` interface and the `HyperGraph<>` interface in JUNG. They have been copied from that library for your reference, but are otherwise unaltered. Part of this assignment is to practice reading "real" documentation and understanding what to implement based on the library's requirements.

```java

//********************************
// Graph Editing (20%)
//********************************

boolean addEdge(E e, V v1, V v2) {...}
boolean addVertex(V vertex) {...}

boolean removeEdge(E edge) {...}
boolean removeVertex(V vertex) {...}


//********************************
// Graph Information (30%)
//********************************

//For a given graph...

Collection<E> getEdges() {...}
Collection<V> getVertices()  {...}

boolean containsVertex(V vertex) {...}
boolean containsEdge(E edge) {...}

int getEdgeCount() {...}
int getVertexCount() {...}


//For a given vertex in a graph...

int degree(V vertex) {...}
int getNeighborCount(V vertex) {...}

Collection<E> getInEdges(V vertex) {...}
Collection<E> getOutEdges(V vertex) {...}
Collection<E> getIncidentEdges(V vertex)

Collection<V> getPredecessors(V vertex) {...}
Collection<V> getSuccessors(V vertex) {...}
Collection<V> getNeighbors(V vertex) {...}


//Given two verticies in a graph...

boolean isPredecessor(V v1, V v2) {...}
boolean isSuccessor(V v1, V v2) {...}
boolean isNeighbor(V v1, V v2) {...}
E findEdge(V v1, V v2) {...}


//Given an edge in a graph...

V getSource(E directed_edge) {...}
V getDest(E directed_edge) {...}
Pair<V> getEndpoints(E edge) {...}
Collection<V> getIncidentVertices(E edge) {...}


//Given a vertex and an edge in a graph...

boolean isIncident(V vertex, E edge) {...}
 
```

When you are done with this step, you can generate and play with some graphs in the simulator (see the [Examples Page](EXAMPLES.md "")).

_Hints and Notes_
- Read ALL the methods before you decide how to represent your graph, you may need track a lot more things than the simple graphs we covered in class.
- Note that the graph has _Objects_ for verticies and edges... this is not the same as an index! If only there was some way to map one object to a number...
- Note that we cannot test editing a graph or getting information about a graph independently of each other. So you cannot get points for completing only the graph editing or only the graph information parts of this interface, you need everything...

### Task 4: Implement Dijkstra's Shortest Path Algorithm in the Simulator (45%)

Now for the fun part! The simulator need to know what one "step" of Dijkstra's shortest path algorithm looks like. `ThreeTenDijkstra` (in `ThreeTenDijkstra.java`) will provide the steps for the algorithm, but there are two related classes as well:
1. `ThreeTenEdge` (in `ThreeTenEdge.java`) provides a representation of an edge which is used by the simulator. It tracks things like the weight and color of an edge.
2. `ThreeTenNode` (in `ThreeTenNode.java`) provides a representation of a node which is used by the simulator. It tracks things like the text and color of a node.

You may add additional private instance variables to both `ThreeTenEdge` and `ThreeTenNode` _and_ associated public accessors and mutators if you want/need, but you may not break the code that exists and you may not add any public non-accessors/non-mutators methods.

An overview of some key parts of `ThreeTenDijkstra` class is given below to get you started:

```java
//this is the actual "step" method, which calls other methods you will be writing...
boolean step() {
	if(!started) {
		start();
		return true;
	}
	
	cleanUpLastStep();
	if(!setupNextMin()) {
		finish();
		return false;
	}
	doUpdates();
	
	return true;
}

//this code is partially provided for you, but you can add more setup here if you want/need
void start() {...}

//performs any "clean up" in preparation for performing another step of the algorithm
//detailed instructions are provided in the template
void cleanUpLastStep() {...}

//pick the next minimum node to look at for the algorithm and updates the graph accordingly
//detailed instructions are provided in the template
boolean setupNextMin() {...}

//does the neighbor updates from Dijkstra's algorithm
//detailed instructions are provided in the template
void doUpdates() {...}

//this code is partially provided for you, but you can add more tear down here if you want/need
//you also need to complete a helper method for the provided code to work
void finish() {...}

```

When you are done with this step, you can play the algorithm in the simulator (see the [Examples Page](EXAMPLES.md "")).

_Hints and Notes_
- You must complete the JavaDocs for all three of these classes, so you may want to start by doing this to familiarize yourself with the code.
- You are not responsible for making the algorithm work if the user edits the graph while Dijkstra's is running. Just assume all editing will take place before hitting "step" or "play" and that, if they want to do the algorithm again, they will hit "reset" and generate a new graph.

### Task 5: Runtime Write-up (5%)

At the end of your readme.txt file, write a three sentence paragraph:

- Sentence 1 - Describe _how_ you chose to represent your graph internally.
- Sentence 2-3 - Describe _why_ you made your implementation the way you did. Specifically, you must indicate how your decisions were influenced by the Big-O runtime of your implementation.
