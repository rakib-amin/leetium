package solutions;


public class Sol_683 {
	
	public static void main(String[] a) {
    	System.out.println(problem);
    }
	
	public static final String problem = "You have N bulbs in a row numbered from 1 to N. Initially, all the bulbs are turned off. We turn on exactly one bulb everyday until all bulbs are on after N days.\n" + 
			"\n" + 
			"You are given an array bulbs of length N where bulbs[i] = x means that on the (i+1)th day, we will turn on the bulb at position x where i is 0-indexed and x is 1-indexed.\n" + 
			"\n" + 
			"Given an integer K, find out the minimum day number such that there exists two turned on bulbs that have exactly K bulbs between them that are all turned off.\n" + 
			"\n" + 
			"If there isn't such day, return -1.\n" + 
			"\n" + 
			" \n" + 
			"\n" + 
			"Example 1:\n" + 
			"\n" + 
			"Input: \n" + 
			"bulbs: [1,3,2]\n" + 
			"K: 1\n" + 
			"Output: 2\n" + 
			"Explanation:\n" + 
			"On the first day: bulbs[0] = 1, first bulb is turned on: [1,0,0]\n" + 
			"On the second day: bulbs[1] = 3, third bulb is turned on: [1,0,1]\n" + 
			"On the third day: bulbs[2] = 2, second bulb is turned on: [1,1,1]\n" + 
			"We return 2 because on the second day, there were two on bulbs with one off bulb between them.\n" + 
			"Example 2:\n" + 
			"\n" + 
			"Input: \n" + 
			"bulbs: [1,2,3]\n" + 
			"K: 1\n" + 
			"Output: -1\n" + 
			" \n" + 
			"\n" + 
			"Note:\n" + 
			"\n" + 
			"1 <= N <= 20000\n" + 
			"1 <= bulbs[i] <= N\n" + 
			"bulbs is a permutation of numbers from 1 to N.\n" + 
			"0 <= K <= 20000\n";
	
	public int kEmptySlots(int[] bulbs, int K) {
        int[] days = new int[bulbs.length];
        for (int i = 0; i < bulbs.length; i++) {
            days[bulbs[i] - 1] = i + 1;
        }

        int ans = Integer.MAX_VALUE;
        int left = 0, right = K+1;

        search: while (right < days.length) {
            for (int i = left+1; i < right; ++i) {
                if (days[i] < days[left] || days[i] < days[right]) {
                    left = i;
                    right = i + K + 1;
                    continue search;
                }
            }
            ans = Math.min(ans, Math.max(days[left], days[right]));
            left = right;
            right = left + K + 1;
        }

        return ans < Integer.MAX_VALUE ? ans : -1;
    }
	

}
