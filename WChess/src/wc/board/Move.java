package wc.board;

import wc.piece.Piece;

/**
 * 
 * @author Max (Smugless)
 * 
 *         Very Heavily influenced by amir.afghani's code and youtube series
 *         here:https://youtu.be/h8fSdSUKttk
 */

public abstract class Move {
	final Board board;
	final Piece movedPiece;
	final int destinationCoordinate;

	private Move(final Board board, final Piece movedPiece, int destinationCoordinate) {
		this.board = board;
		this.movedPiece = movedPiece;
		this.destinationCoordinate = destinationCoordinate;
	}

	public static final class MajorMove extends Move {

		public MajorMove(Board board, Piece movedPiece, int destinationCoordinate) {
			super(board, movedPiece, destinationCoordinate);
			// TODO Auto-generated constructor stub
		}

	}

	public static final class CaptureMove extends Move {

		final Piece attackedPiece;

		public CaptureMove(Board board, Piece movedPiece, int destinationCoordinate, Piece attackedPiece) {
			super(board, movedPiece, destinationCoordinate);
			this.attackedPiece = attackedPiece;
		}

	}
}
