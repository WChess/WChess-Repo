package wc.piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;

import wc.board.Board;
import wc.board.BoardUtils;
import wc.board.Move;
import wc.board.Square;
import wc.board.Move.CaptureMove;
import wc.board.Move.MajorMove;

/**
 * 
 * @author Max (Smugless)
 * 
 * Very Heavily influenced by amir.afghani's code and youtube series here:https://youtu.be/h8fSdSUKttk
 */

public class King extends Piece {

	private final static int[] CANDIDATE_MOVE_COORDINATE = { -9, -8, -7, -1, 1, 7, 8, 9 };

	King(int piecePosition, Alliance pieceAlliance, BoardUtils boardUtils) {
		super(piecePosition, pieceAlliance, boardUtils);
	}

	@Override
	public Collection<Move> calculateLegalMoves(Board board) {
		
		int candidateDestinationCoordinate;
		final List<Move> legalMoves = new ArrayList<>();
		
		for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {
			candidateDestinationCoordinate = (int) (this.piecePosition + currentCandidateOffset);
			if(isAFileExclusion(this.piecePosition, currentCandidateOffset)
					|| isHFileExclusion(this.piecePosition, currentCandidateOffset)) {
				continue;
			}
			if(BoardUtils.isValidSquareCoordinate(candidateDestinationCoordinate)) {
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
				&& (candidateOffset == -9 || candidateOffset == -1 || candidateOffset == 7);
	}

	private static boolean isHFileExclusion(final int currentPosition, final int candidateOffset) {
		return BoardUtils.FILES[7][currentPosition] && (candidateOffset == -7 || candidateOffset == 1 || candidateOffset == 9);
	}

}
