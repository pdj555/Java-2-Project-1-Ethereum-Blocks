import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Blocks {
	private int number;
	private String miner;
	private static ArrayList<Blocks> blocks = new ArrayList<Blocks>();
	
	
	/**
	 * This constructs a Blocks object without initiating the number 
	 * and miner variables.
	 */
	public Blocks() {}
	
	
	/**
	 * This constructs a Blocks object. It initiates the number variable but
	 * does not initiate the miner variable
	 * @param number
	 */
	public Blocks(int number) {
		this.number = number;
	}
	
	
	/**
	 * This constructs a Blocks object and initiates the variables
	 * number and miner. 
	 * @param number The number that identifies which block we are referring to
	 * @param miner The address of the block
	 */
	public Blocks(int number, String miner) {
		this.number = number;
		this.miner = miner;
	}
	
	
	/**
	 * Returns the number associated with a block object.
	 * @return The number used to identify a block
	 */
	public int getNumber() {
		return number;
	}
	
	
	/**
	 * Returns the miner associated with a block.
	 * @return The miner address
	 */
	public String getMiner() {
		return miner;
	}
	
	
	/**
	 * Creates and returns a copy of the ArrayList blocks
	 * @return A copy of blocks ArrayList
	 */
	public static ArrayList<Blocks> getBlocks() {
		ArrayList<Blocks> copy = new ArrayList<Blocks>();
		
		for (int i = 0; i < blocks.size(); ++i) {
			copy.add(blocks.get(i));
		}
		
		return copy;
	}
	
	
	/**
	 * Counts the number of unique miner addresses and prints the frequency that
	 * that each miner address occurs in the data file.
	 */
	public static void calUniqMiners() {
		ArrayList<String> uMiners = new ArrayList<String>();
		ArrayList<Integer> count = new ArrayList<Integer>();
		String miner;
		
		for (int i = 0; i < blocks.size(); ++i) {
			miner = blocks.get(i).getMiner();
			
			// If miner address is in the uMiners list than it has already been read in
			
			// Correlate the index of the miner address in uMiners to the frequency of 
			// that address in count 
			
			if (uMiners.contains(miner)) {
				count.set(uMiners.indexOf(miner), count.get(uMiners.indexOf(miner)) + 1);
			}
			else {
				uMiners.add(miner);
				count.add(1);
			}
		}
		
		System.out.println("Number of unique Miners: " + uMiners.size());
		System.out.println();
		System.out.println("Each unique Miner and its frequency:");
		
		for (int i = 0; i < uMiners.size(); ++i) {
			System.out.println("Miner Address: " + uMiners.get(i));
			System.out.println("Miner Frequency: " + count.get(i));
			System.out.println();
		}
		
		
	}
	
	
	/**
	 * Returns the difference between two block numbers.
	 * @param A Block A
	 * @param B Block B
	 * @return The int value of the difference between Block A's number and Block B's number
	 */
	public static int blockDiff(Blocks A, Blocks B) {
		return A.number - B.number;
	}
	
	
	/**
	 * Returns the block associated with the number used as a parameter.
	 * @param num Number of a block
	 * @return The block with the parameter number
	 */
	public static Blocks getBlockByNumber(int num) {
		for (int i = 0; i < blocks.size(); i++) {
			if (blocks.get(i).getNumber() == num) {
				return blocks.get(i);
			}
		}
		return null;
	}
	
	
	/**
	 * Outputs the information stored on a block.
	 */
	public String toString() {
		if (getMiner() == null && getNumber() == 0) {
			return "Empty Block";
		}
		else if (getMiner() == null && getNumber() != 0) {
			return "Block Number: " + getNumber();
		}
		else {
			return "Block Number: " + getNumber() + " Miner Address: " + getMiner();
		}
	}
	
	
	/**
	 * Reads and stores the 1st and 10th columns of a file. 
	 * @param filename File that you want to be read
	 * @throws NumberFormatException 
	 * @throws IOException
	 */
	public static void readFile(String filename) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line;
		
		while ((line = br.readLine()) != null) {
			String[] numAndMiner = line.split(",");
			
			Blocks nB = new Blocks(Integer.parseInt(numAndMiner[0]), numAndMiner[9]);
			blocks.add(nB);
		}
		br.close();
	}
}
