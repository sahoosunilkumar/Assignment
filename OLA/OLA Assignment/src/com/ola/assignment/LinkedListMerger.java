package com.ola.assignment;

import java.util.LinkedList;

public class LinkedListMerger {

	public static void main(String[] args){
		LinkedList<Integer> inputList1 = new LinkedList<Integer>();
		inputList1.add(1);
		inputList1.add(3);
		inputList1.add(5);
		inputList1.add(7);
		LinkedList<Integer> inputList2 = new LinkedList<Integer>();
		inputList2.add(2);
		inputList2.add(4);
		inputList2.add(6);
		inputList2.add(8);
		
		LinkedListMerger merger = new LinkedListMerger();
		LinkedList<Integer> output = merger.merge(inputList1, inputList2);
		System.out.println(output);
	}

	private LinkedList<Integer> merge(LinkedList<Integer> inputList1,
			LinkedList<Integer> inputList2) {
		LinkedList<Integer> output = new LinkedList<>();
		if(inputList1 == null){
			return inputList2;
		}else if(inputList2 == null){
			return inputList1;
		}else{
			int index1 = 0;
			int index2 = 0;
			while(true){
				if((index1>=inputList1.size()) && (index2>=inputList2.size())){
					break;
				} else if(index1>=inputList1.size()){
					output.add(inputList2.get(index2));
					index2++;
				} else if(index2>=inputList2.size()){
					output.add(inputList1.get(index1));
					index1++;
				} else{
					if(inputList1.get(index1) < inputList2.get(index2)){
						output.add(inputList1.get(index1));
						index1++;
					}else{
						output.add(inputList2.get(index2));
						index2++;
					}
				}
			}
		}
		
		return output;
	}
	
	
	class Node{
		int key;
		Node Next;
		Node(int key){
			this.key = key;
		}
	}
	
}