package com.ola.assignment;

import java.util.Arrays;

public class FindSumMatchProgram {
	
	public static void main(String[] args){
		FindSumMatchProgram program = new FindSumMatchProgram();
		
		int array[] = {2,4,10,5,6,9,14,3};
		int matchNumber = 15;
		program.findMatch(array, matchNumber);
	}
	
	private void findMatch(int[] array, int match){
		//sort the array if not sorted
		Arrays.sort(array);
		int startIndex =0;
		int endIndex = array.length-1;
		while(startIndex < endIndex){
			if(array[startIndex] + array[endIndex] == match){
				System.out.println("Match Found for : "+array[startIndex]+" and "+array[endIndex]);
				startIndex++;
			}else if (array[startIndex] + array[endIndex] < match) {
				startIndex++;
			} else {
				endIndex--;
			}
		}
	}

}
