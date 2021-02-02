package wchess.wc.piece;

import java.util.Collection;
import wchess.wc.board.Board;
import wchess.wc.board.BoardUtils;
import wchess.wc.board.Move;

/**
 * 
 * @author Max (Smugless)
 * 
 * Very Heavily influenced by amir.afghani's code and youtube series here:https://youtu.be/h8fSdSUKttk
 */

public abstract class Piece {
	
	protected final int piecePosition;
	protected final Alliance pieceAlliance;
	protected final BoardUtils boardUtils;
	
	//TODO some work here
	protected final boolean isFirstMove = false;
	
	Piece(final int piecePosition, final Alliance pieceAlliance, BoardUtils boardUtils) {
		this.piecePosition = piecePosition;
		this.pieceAlliance = pieceAlliance;
		this.boardUtils = boardUtils;
	}
	
	public abstract Collection<Move> calculateLegalMoves(final Board board);

	public Alliance getPieceAlliance() {
		return this.pieceAlliance;
	}
	
	public boolean isFirstMove() {
		return this.isFirstMove;
	}
	
	public int getPiecePosition() {
		return this.piecePosition;
	}

}
