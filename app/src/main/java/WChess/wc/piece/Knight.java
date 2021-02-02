package wchess.wc.piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;

import wchess.wc.board.Board;
import wchess.wc.board.Move;
import wchess.wc.board.Move.MajorMove;
import wchess.wc.board.Move.CaptureMove;
import wchess.wc.board.Square;
import wchess.wc.board.BoardUtils;

/**
 * 
 * @author Max (Smugless)
 * 
 * Very Heavily influenced by amir.afghani's code and youtube series here:https://youtu.be/h8fSdSUKttk
 */

public class Knight extends Piece {

	private static int[] CANDIDATE_MOVE_COORDINATES = { -17, -15, -10, -6, 6, 10, 15, 17 };

	Knight(int piecePosition, Alliance pieceAlliance, BoardUtils boardUtils) {
		super(piecePosition, pieceAlliance, boardUtils);
	}

	public Collection<Move> calculateLegalMoves(final Board board) {

		int candidateDestinationCoordinate;
		final List<Move> legalMoves = new ArrayList<>();

		for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES) {
			candidateDestinationCoordinate = (int) (this.piecePosition + currentCandidateOffset);
			if (BoardUtils.isValidSquareCoordinate(candidateDestinationCoordinate)) {
				if (isAFileExclusion(this.piecePosition, currentCandidateOffset)
						|| isBFileExclusion(this.piecePosition, currentCandidateOffset)
						|| isGFileExclusion(this.piecePosition, currentCandidateOffset)
						|| isHFileExclusion(this.piecePosition, currentCandidateOffset)) {
					continue;
				}
				final Square candidateDestinationSquare = board.getSquare(candidateDestinationCoordinate);
				if (!candidateDestinationSquare.isSquareOccupied()) {
					legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
				} else {
					final Piece pieceAtDestination = candidateDestinationSquare.getPiece();
					final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
					if (this.pieceAlliance != pieceAlliance) {
						legalMoves.add(
								new CaptureMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
					}
				}
			}
		}

		return ImmutableList.copyOf(legalMoves);
	}

	private static boolean isAFileExclusion(final int currentPosition, final int candidateOffset) {
		return BoardUtils.FILES[0][currentPosition]
				&& (candidateOffset == -17 || candidateOffset == -10 || candidateOffset == 6 || candidateOffset == 15);
	}

	private static boolean isBFileExclusion(final int currentPosition, final int candidateOffset) {
		return BoardUtils.FILES[1][currentPosition] && (candidateOffset == -10 || candidateOffset == 6);
	}

	private static boolean isGFileExclusion(final int currentPosition, final int candidateOffset) {
		return BoardUtils.FILES[6][currentPosition] && (candidateOffset == -6 || candidateOffset == 10);
	}

	private static boolean isHFileExclusion(final int currentPosition, final int candidateOffset) {
		return BoardUtils.FILES[7][currentPosition]
				&& (candidateOffset == -15 || candidateOffset == -6 || candidateOffset == 10 || candidateOffset == 17);
	}

}
