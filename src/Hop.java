import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Hop {

	public static void main(String[] args) {
		if (0 < args.length) {
			String filename = args[0];
			File file = new File(filename);
			ArrayList<Integer> nums = readNumbers(file);

			// Convert the list to an array
			int[] list = new int[nums.size()];
			for (int i = 0; i < nums.size(); i++) {
				list[i] = nums.get(i);
			}
			ArrayList<Integer> chosen = new ArrayList<Integer>();
			System.out.println(minJumps(list, 0, list.length, chosen));
			System.out.println(chosen);
			
			jumper(list); // Go get the shortest hops
		} else {
			System.out.println("Failure: File not found!");
		}
	}

	/*
	 * Read the passed file and return a list of numbers stored in
	 * that file.
	 */
	public static ArrayList<Integer> readNumbers(File file) {
		ArrayList<Integer> nums = new ArrayList<Integer>();
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;

			while((line = reader.readLine()) != null) {
				nums.add(Integer.parseInt(line));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {

			}
		}
		return nums;
	} 



	// Returns minimum number of jumps to reach arr[source] from arr[dest]
	public static int minJumps(int arr[], int dest, int source, ArrayList<Integer> chosen)
	{
		// when source and dest are same
		if (source == dest)
			return 0;

		// If value is 0, cannot jump, return arbitrary value
		if (arr[dest] == 0)
			return Integer.MAX_VALUE;

		// Traverse through all the points reachable from arr[source]. Recursively
		// get the minimum number of jumps needed to reach arr[dest] from these
		// reachable points.
		int min = Integer.MAX_VALUE; // store minimum number of hops
		for (int i = dest + 1; i <= source && i <= dest + arr[dest]; i++)
		{
			int jumps = minJumps(arr, i, source, chosen);
			if(jumps != Integer.MAX_VALUE && jumps + 1 < min)
				min = jumps + 1;
				chosen.add(i);
		}

		return min;
	}


	public static void jumper(int[] a) {
		List<Integer> chosen = new ArrayList<Integer>();
		jumper(a, 0, chosen, 0);
	}


	private static void jumper(int[] a, int index, List<Integer> chosen, int sum) {

		if (a[index] == 0) {
			// Value is 0, cannot move forward
			return;
		} else if (sum > a.length) {
			// reached end of list, print the array
			chosen.add(index);
			System.out.println(chosen);
		} else {
			int val = a[index]; // value at the index

			// Build an array of all possible "hops" in descending order
			int[] hops = new int[val];
			for (int i = 1; i <= val; i++) {
				hops[i - 1] = i;
			}

			// explore these hops in descending order
			for (int i = hops.length - 1; i > -1; i--) {
				chosen.add(hops[i]);
				jumper(a, index + 1, chosen, sum);
				//chosen.remove(chosen.size() - 1);
			}

		}

	}

}
