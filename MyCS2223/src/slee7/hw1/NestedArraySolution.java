package slee7.hw1;

import algs.hw1.arraysearch.NestedArraySearch;

/**
 * Copy this class into your package, which must have USERID has its root.
 */
public class NestedArraySolution extends NestedArraySearch {

	/** Construct problem solution for given array. Do not modify this method. */
	public NestedArraySolution(int[][] a) {
		super(a);
	}
	
	/** 
	 * For this homework assignment, you need to complete the implementation of this
	 * method.
	 */
	@Override
	public int[] locate(int target) {
		/*
		int n = this.length();
		int num_T = 1 + Math.floorDiv(n-1, 3);
		
		int min_c = 0;
		int max_c = num_T - 1;
		
		while(min_c <= max_c) {
			int mid_c = (min_c + max_c) / 2;
			int rc = inspect(2 * mid_c, mid_c) - target;
			if(rc < 0) {
				min_c = mid_c + 1;
			}
			else if(rc > 0) {
				max_c = mid_c - 1;
			}
			else {
				int a = 2 * mid_c;
				return new int[] {2 * mid_c, mid_c};
			}
		}
		if(max_c == -1)
			return null;
		
		int min_r = 2 *max_c;
		int max_r = n - 1 - max_c;
		int side = max_r - min_r;
		int min_v = max_c;
		int max_v = min_v + side;
		
		int checkP = inspect(max_r, max_v);
		int checkP2 = inspect(max_r, min_v);
		
		if(target <= checkP) {
			if(target == checkP)
				return new int[] {max_r, max_v};
		int min = min_v + 1;
		int max = max_v - 1;
		while(min <= max) {
			int mid = (min+max)/2;
			int rc = mid - min_v;
			int val = inspect(min_r + rc, mid) - target;
			if(val < 0) {
				min = mid +1;
			}
			else if(val > 0) {
				max = mid - 1;
			}
			else {
				int a = min_r + rc;
				return new int[] {min_r + rc, mid};
			}
		}
		}
		else if(target <= checkP2) {
			if(target == checkP2) {
				return new int[] {max_r, min_v};
			}
			int max = max_v - 1;
			int min = min_v + 1;
			while(min <= max) {
				int mid = (min+max) / 2;
				int rc = inspect(max_r, mid) - target;
				if(rc > 0) {
					min = mid + 1;
				}
				else if(rc < 0) {
					max = mid - 1;
				}
				else
					return new int[] {max_r, mid};
			}
		}
		else {
			int min = min_r + 1;
			int max = max_r - 1;
			while(min <= max) {
				int mid = (min + max) / 2;
				int rc = inspect() - target;
			}
		}*/
		int n = this.length();
        int total = n * (n + 1) / 2;

        int low = 0;
        int high = total - 1;

        while(low<=high) {
			int mid = (low+high)/2;
			int [] curr = IndexHelper(mid, n);
			int diff = inspect(curr[0], curr[1]) - target;
			if (diff < 0) {
				low = mid+1;
			} else if (diff > 0) {
				high = mid-1;
			} else {
				return IndexHelper(mid, n);
			}
		}
        return null;
	}
	public int[] IndexHelper(int index, int numRows) {
    	int n = numRows;
        int currentTri = 0;
        int numInTri = 0;
        int indexWithinTri = 0;
        int indexCounter = 0;
        int row = 0;
        int col = 0;
        while (currentTri < (int) (1 + Math.floor((n - 1) / 3.0))) {
            row = currentTri * 2;
            col = currentTri;
            if (3 * (n - 1 - 3 * currentTri) == 0) {
                numInTri = 1;
            } else {
                numInTri = 3 * (n - 1 - 3 * currentTri);
            }
            while (indexWithinTri < numInTri) {
                if (index == indexCounter) {
                    return new int[] { row, col };
                }
                indexCounter++;
                indexWithinTri++;
                if (indexWithinTri < n - 3 * currentTri) {
                    row++;
                    col++;
                } else if (indexWithinTri < (2 * n - 6 * currentTri - 1)) {
                    col--;
                } else {
                    row--;
                }
            }
            indexWithinTri = 0;
            currentTri++;
        }
        return new int[] { row, col };
    }
	
	/** Be sure that you call your class constructor. Do not modify this method. */ 
	public static void main (String args[]) {
		int[][] ar = NestedArraySearch.create(13);
		new NestedArraySolution(ar).trial();
	}
}
