package com.badoo.assignment.productviewer.controller;

import android.util.Log;

import com.badoo.assignment.productviewer.model.Currency;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;


public class CurrencyConverterNew {
	final private static int QUEUE_INITIAL_CAPACITY = 10;

	ArrayList<GraphNode> nodeList;

	public CurrencyConverterNew() {
		nodeList = new ArrayList<GraphNode>();
	}

	public void addNode(String id) {
		GraphNode node = new GraphNode(id);
		nodeList.add(node);
	}

	public void addEdge(Currency currency) {
		
		int i = 0;
		for (i = 0; i < nodeList.size(); i++) {
			if (nodeList.get(i).nodeId.equals(currency.getFrom())) {
				break;
			}
		}
		if (i == nodeList.size()) {
			return;
		}
		GraphNode node1 = nodeList.get(i);
		GraphNode node2 = new GraphNode(currency.getTo(), Float.parseFloat(currency.getRate()));
		node2.next = node1.next;
		node1.next = node2;
	}

	private GraphNode findGraphNode(String currQueueNodeId) {
		for (int i = 0; i < nodeList.size(); i++) {
			if (nodeList.get(i).nodeId.equals(currQueueNodeId)) {
				return nodeList.get(i);
			}
		}
		return null;
	}

	public void printGraph() {
		for (int i = 0; i < nodeList.size(); i++) {
			GraphNode curr = nodeList.get(i);
			while (curr != null) {
				System.out.print(
						curr.nodeId + "(" + curr.parentDist + ")" + "->");
				curr = curr.next;
			}
			System.out.print("Null");
			System.out.println();
		}
	}

	private void updateQueue(PriorityQueue queue, String nodeId, float oldDist,
			float newDist) {

		Iterator<QueueNode> queueItr = queue.iterator();
		boolean queueUpdated = false;
		while (queueItr.hasNext()) {
			QueueNode tempNode = queueItr.next();
			if (tempNode.nodeId.equals(nodeId)) {
				queueUpdated = true;
				tempNode.distFromSrc = newDist;
				break;
			}
		}
		if (!queueUpdated) {
			queue.add(new QueueNode(nodeId, newDist));
		}
	}

	public HashMap<String, Float> findShortestDijkstra(String srcId) {
		Comparator<QueueNode> comparator = new QueueNodeComparator();
		PriorityQueue<QueueNode> queue = new PriorityQueue<QueueNode>(
				QUEUE_INITIAL_CAPACITY, comparator);
		HashMap<String, Boolean> unvisited = new HashMap<>(nodeList.size());
		HashMap<String, String>  parent = new HashMap<>(nodeList.size());
//		int[] distance = new int[nodeList.size()];
		HashMap<String, Float> distance = new HashMap<>(nodeList.size());
//		for (int i = 0; i < nodeList.size(); i++) {
//			unvisited[i] = true;
//			parent[i] = -1;
//			distance[i] = Integer.MAX_VALUE;
//		}
		queue.add(new QueueNode(srcId, 1.0f));
		while (!queue.isEmpty()) {
			QueueNode currQueueNode = queue.remove();
			unvisited.put(currQueueNode.nodeId,false);
			distance.put(currQueueNode.nodeId, currQueueNode.distFromSrc);
			GraphNode currGraphNode = findGraphNode(currQueueNode.nodeId);
			GraphNode neighborNode = (currGraphNode == null) ? null
					: currGraphNode.next;
			while (neighborNode != null) {
				if (unvisited.get(neighborNode.nodeId) != null) {
					if ((distance.get(currQueueNode.nodeId)
							+ neighborNode.parentDist) < (distance.get(neighborNode.nodeId) != null ? distance.get(neighborNode.nodeId) : Integer.MAX_VALUE))

					{
						float oldDistance = distance.get(neighborNode.nodeId);
						float newDistance = distance.get(currQueueNode.nodeId)
								* neighborNode.parentDist;
						distance.put(neighborNode.nodeId, newDistance);
						parent.put(neighborNode.nodeId, currQueueNode.nodeId);
						updateQueue(queue, neighborNode.nodeId, oldDistance,
								newDistance);
					}
				}
				neighborNode = neighborNode.next;
			}
		}
		return distance;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nodeList.toString();
	}

//	public static void main(String[] args) {
////		{"from":"GBP","rate":"0.83","to":"AUD"},{"from":"AUD","rate":"1.2","to":"GBP"}]
//		CurrencyConverterNew graphObj = new CurrencyConverterNew();
//		graphObj.addNode("GBP");
//		graphObj.addNode("USD");
//		graphObj.addNode("CAD");
//		graphObj.addNode("AUD");
//		graphObj.addEdge("GBP", "USD", 1.3f);
//		graphObj.addEdge("USD", "GBP", 0.77f);
//		graphObj.addEdge("USD", "CAD", 1.09f);
//		graphObj.addEdge("CAD", "USD", 0.92f);
//		graphObj.addEdge("GBP", "AUD", 0.83f);
//		graphObj.addEdge("AUD", "GBP", 1.2f);
//		System.out.println(graphObj);
//		HashMap<String, Float> distance = graphObj.findShortestDijkstra("GBP");
//		Log.d("Sunil", ""+distance);
////		for (int i = 0; i < distance.length; i++) {
////			if (distance[i] == Integer.MAX_VALUE) {
////				System.out.println(
////						"vertex \'" + i + "\' is unreachable from vertex '0'");
////
////			} else {
////				System.out.println("distance of vertex \'" + i
////						+ "\' from vertex '0' is " + distance[i]);
////			}
////		}
//	}
}

class GraphNode {
	String nodeId;
	GraphNode next;
	float parentDist;

	GraphNode(String id) {
		nodeId = id;
		next = null;
		System.out.println("graph constructor : " + id);
	}

	GraphNode(String id, float parentDist) {
		nodeId = id;
		next = null;
		this.parentDist = parentDist;
		System.out.println("graph constructor : " + id + " dist : " + parentDist);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nodeId + " : " + parentDist + " next: " + next;
	}
}

class QueueNode {
	String nodeId;
	float distFromSrc;

	public QueueNode(String id, float dist) {
		nodeId = id;
		distFromSrc = dist;
	}
}

class QueueNodeComparator implements Comparator<QueueNode> {
	@Override
	public int compare(QueueNode x, QueueNode y) {
		if (x.distFromSrc < y.distFromSrc) {
			return -1;
		}
		if (x.distFromSrc > y.distFromSrc) {
			return 1;
		}
		return 0;
	}
}
