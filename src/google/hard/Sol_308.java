package google.hard;

public class Sol_308 {
	
	public static void main(String[] a) {
    	System.out.println(problem);
    }
	
	public static final String problem = "Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).\n" + 
			"\n" + 
			"  [3, 0, 1, 4, 2],\n" + 
			"  [5, 6, 3, 2, 1],\n" + 
			"  [1, 2, 0, 1, 5],\n" + 
			"  [4, 1, 0, 1, 7],\n" + 
			"  [1, 0, 3, 0, 5]" + 
			"The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.\n" + 
			"\n" + 
			"Example:\n" + 
			"\n" + 
			"Given matrix = [\n" + 
			"  [3, 0, 1, 4, 2],\n" + 
			"  [5, 6, 3, 2, 1],\n" + 
			"  [1, 2, 0, 1, 5],\n" + 
			"  [4, 1, 0, 1, 7],\n" + 
			"  [1, 0, 3, 0, 5]\n" + 
			"]\n" + 
			"\n" + 
			"sumRegion(2, 1, 4, 3) -> 8\n" + 
			"update(3, 2, 2)\n" + 
			"sumRegion(2, 1, 4, 3) -> 10\n" + 
			"Note:\n" + 
			"\n" + 
			"The matrix is only modifiable by the update function.\n" + 
			"You may assume the number of calls to update and sumRegion function is distributed evenly.\n" + 
			"You may assume that row1 ≤ row2 and col1 ≤ col2.";
}
