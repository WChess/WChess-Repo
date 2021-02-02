/**
 * 
 */
package wchess.wc.piece;

/**
 * @author Max (Smugless)
 * A move pair is used to describe how a piece may move
 */
public class MovePair {
	private final int vertical;
	private final int horizontal;
	private final boolean isJump;
	private final boolean isCapture;
	private final boolean isNotCapture;
	private final boolean isForcedCapture;
	private final boolean isChainCapture;
	
	//constructor for complicated capturing moves, like checkers pieces
	public MovePair(final int vertical, final int horizontal, final boolean isJump, final boolean isCapture, final boolean isNotCapture, final boolean isForcedCapture, final boolean isChainCapture) {
		this.vertical = vertical;
		this.horizontal = horizontal;
		this.isJump = isJump;
		this.isCapture = isCapture;
		this.isNotCapture = isNotCapture;
		this.isForcedCapture = isForcedCapture;
		this.isChainCapture = isChainCapture;
	}
	
	//constructor for moves for a piece where the move is different depending on whether it is a capture or not
	public MovePair(final int vertical, final int horizontal, final boolean isJump, final boolean isCapture, final boolean isNotCapture) {
		this.vertical = vertical;
		this.horizontal = horizontal;
		this.isJump = isJump;
		this.isCapture = isCapture;
		this.isNotCapture = isNotCapture;
		this.isForcedCapture = false;
		this.isChainCapture = false;
	}
}
