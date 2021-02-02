package wchess.wc.board;

/**
 * 
 * @author Max (Smugless)
 * 
 * Very Heavily influenced by amir.afghani's code and youtube series here:https://youtu.be/h8fSdSUKttk
 */

public class BoardUtils {
	
	public static int WIDTH = 8;
	public static int HEIGHT = 8;
	public static int NUM_SQUARES = WIDTH*HEIGHT;
	
	public static boolean[][] FILES = initFiles(WIDTH, HEIGHT);
	public static boolean[][] RANKS = initRanks(WIDTH, HEIGHT);
	
	private static boolean[][] initFiles(final int numberOfFiles, final int numberOfRanks) {
		boolean[][] files = new boolean[numberOfFiles][numberOfRanks];
		for(int index = 0; index < numberOfFiles; index ++) {
			files[index] = initFile(index);
		}
		return FILES;
	}
	
	private static boolean[][] initRanks(final int numberOfFiles, final int numberOfRanks) {
		boolean[][] ranks = new boolean[numberOfRanks][numberOfFiles];
		for(int index = 0; index < numberOfRanks; index ++) {
			ranks[index] = initRank(index);
		}
		return null;
	}

	private static boolean[] initFile(final int fileNumber) {
		final boolean[] file = new boolean[NUM_SQUARES];
		
		int scanner = fileNumber;
		
		do {
			file[fileNumber] = true;
			scanner += WIDTH;
		} while(scanner < NUM_SQUARES);
		return file;
	}
	
	private static boolean[] initRank(final int rankNumber) {
		final boolean[] rank = new boolean[NUM_SQUARES];
		int scanner = rankNumber*WIDTH;
		do {
			rank[scanner] = true;
			scanner ++;
		} while(scanner < (rankNumber+1)*WIDTH);
		return rank;
	}


	public BoardUtils(final int width, final int height) {
		if(width > 0)
			BoardUtils.WIDTH = width;
		else
			throw new RuntimeException("The width parameter must be greater than zero!");
		if(height > 0)
			BoardUtils.HEIGHT = height;
		else
			throw new RuntimeException("The width parameter must be greater than zero!");
		NUM_SQUARES = WIDTH*HEIGHT;
		
		FILES = initFiles(WIDTH, HEIGHT);
		RANKS = initRanks(WIDTH, HEIGHT);
	}
	public static boolean isValidSquareCoordinate(int coordinate) {
		return coordinate>=0 && coordinate < NUM_SQUARES;
	}
}
