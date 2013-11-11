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
			//ArrayList<Integer> chosen = new ArrayList<Integer>();
			//System.out.println(minJumps(list, 0, list.length, chosen));
			//System.out.println(chosen);

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

	public static void jumper(int[] list) {
		List<Integer> chosen = new ArrayList<Integer>();
		jumper(list, 0, list.length, chosen);
	}

	private static void jumper(int[] a, int start, int end, List<Integer> chosen) {

		if (a[start] == 0) {
			// Value is 0, cannot move forward
			return;
		} else if (start == end) {
			// start and end indices same. 
			return;
		} else {
			int val = a[start]; // value at the index

			// Build an array of all possible "hops" in descending order
			int[] hops = new int[val];
			for (int i = 1; i <= val; i++) {
				hops[i - 1] = i;
			}

			// explore these hops in descending order
			for (int i = hops.length - 1; i > -1; i--) {
				chosen.add(hops[i]);
				jumper(a, start + 1, a[start] + a[i], chosen);
				//chosen.remove(chosen.size() - 1);
			}

		}

	}

}
