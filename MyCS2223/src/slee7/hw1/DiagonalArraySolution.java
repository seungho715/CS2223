package slee7.hw1;

import algs.hw1.arraysearch.DiagonalArraySearch;

/**
 * Copy this class into your package, which must have USERID has its root.
 */
public class DiagonalArraySolution extends DiagonalArraySearch {

	/** Construct problem solution for given array. Do not modify this method. */
	public DiagonalArraySolution(int[][] a) {
		super(a);
	}
	
	/** 
	 * For this homework assignment, you need to complete the implementation of this
	 * method.
	 */
	@Override
	public int[] locate(int target) {
		//Got the Column
		int low = 0;
		int high = this.length()-1;
		
		while(low<=high) {
			int mid = (low+high)/2;
			int rc = inspect(mid,0) - target;
			if(rc<0) {
				low = mid+1;
			}
			else if(rc>0) {
				high=mid-1;
			}
			else {
                return new int[] {mid,0};
			}
		}
		int left = 0;
		int right = this.length() - high - 1;
		while((left <= right) && (high >= 0) && (high<=this.length()))
		{
			int mid = (left+right)/2;
			int diff = inspect(high + mid, mid) - target;
			if(diff < 0) {
				left = mid + 1;
			}
			else if(diff > 0) {
				right = mid - 1;
			}
			else
				return new int[] {high + mid, mid};
		}
		return null;
	}
	/** Be sure that you call your class constructor. Do not modify this method. */ 
	public static void main (String args[]) {
		int[][] ar = DiagonalArraySearch.create(13);
		new DiagonalArraySolution(ar).trial();
	}
}
