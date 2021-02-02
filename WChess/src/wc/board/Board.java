package wc.board;

import java.util.List;
import java.util.Map;

import wc.piece.Alliance;
import wc.piece.Piece;

/**
 * 
 * @author Max (Smugless)
 * 
 * Very Heavily influenced by amir.afghani's code and youtube series here:https://youtu.be/h8fSdSUKttk
 */
public class Board {
	
	private final List<Square> gameBoard;

	
	private Board(Builder builder) {
		this.gameBoard = createGameBoard(builder);
	}

	public Square getSquare(int squareCoordinate) {
		return null;
	}
	
	private List<Square> createGameBoard(Builder builder) {
		final Square[] squares = new Square[BoardUtils.NUM_SQUARES];
		
		return null;
	}
	
	public static class Builder {
				
		Map<Integer, Piece> boardConfig;
		Alliance nextMoveMaker;
		
		public Builder() {
		}
		
		public Builder setPiece(final Piece piece) {
			this.boardConfig.put(piece.getPiecePosition(), piece);
			return this;
		}
		
		public Builder setMoveMaker(final Alliance nextMoveMaker) {
			this.nextMoveMaker = nextMoveMaker;
			return this;
		}
		
		public Board build() {
			return new Board(this);
		}
		
	}

}
