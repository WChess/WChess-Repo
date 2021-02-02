package wchess.wc.piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;

import wchess.wc.board.Board;
import wchess.wc.board.BoardUtils;
import wchess.wc.board.Move;
import wchess.wc.board.Move.MajorMove;
import wchess.wc.board.Move.CaptureMove;
import wchess.wc.board.Square;

/**
 * 
 * @author Max (Smugless)
 * 
 * Very Heavily influenced by amir.afghani's code and youtube series here:https://youtu.be/h8fSdSUKttk
 */

public class Bishop extends Piece {

	private static final int[] CANDIDATE_VECTOR_MOVE_COORDINATES = { -9, -7, 7, 9 };

	Bishop(int piecePosition, Alliance pieceAlliance, BoardUtils boardUtils) {
		super(piecePosition, pieceAlliance, boardUtils);
	}

	@Override
	public Collection<Move> calculateLegalMoves(final Board board) {

		final List<Move> legalMoves = new ArrayList<Move>();

		for (final int candidateCoordinateOffset : CANDIDATE_VECTOR_MOVE_COORDINATES) {
			int candidateDestinationCoordinate = this.piecePosition;

			while (BoardUtils.isValidSquareCoordinate(candidateDestinationCoordinate)) {
				if (isAFileExclusion(candidateDestinationCoordinate, candidateCoordinateOffset)
						|| isHFileExclusion(candidateDestinationCoordinate, candidateCoordinateOffset)) {
					break;
				}
					candidateDestinationCoordinate += candidateCoordinateOffset;
				if (BoardUtils.isValidSquareCoordinate(candidateDestinationCoordinate)) {
					final Square candidateDestinationSquare = board.getSquare(candidateDestinationCoordinate);
					if (!candidateDestinationSquare.isSquareOccupied()) {
						legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
					} else {
						final Piece pieceAtDestination = candidateDestinationSquare.getPiece();
						final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
						if (this.pieceAlliance != pieceAlliance) {
							legalMoves.add(new CaptureMove(board, this, candidateDestinationCoordinate,
									pieceAtDestination));
						}
						break;

					}
				}
			}
		}

		return ImmutableList.copyOf(legalMoves);
	}

	private static boolean isAFileExclusion(final int currentPosition, final int candidateOffset) {
		return BoardUtils.FILES[0][currentPosition] && (candidateOffset == -9 || candidateOffset == 7);
	}

	private static boolean isHFileExclusion(final int currentPosition, final int candidateOffset) {
		return BoardUtils.FILES[7][currentPosition] && (candidateOffset == -7 || candidateOffset == 9);
	}

}
