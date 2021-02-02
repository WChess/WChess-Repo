package wc.piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;

import wc.board.Board;
import wc.board.BoardUtils;
import wc.board.Move;
import wc.board.Move.MajorMove;
import wc.board.Move.CaptureMove;

/**
 * 
 * @author Max (Smugless)
 * 
 * Very Heavily influenced by amir.afghani's code and youtube series here:https://youtu.be/h8fSdSUKttk
 */


public class Pawn extends Piece{

	private final static int[] CANDIDATE_MOVE_COORDINATE = { 7, 8, 9 };
	
	Pawn(int piecePosition, Alliance pieceAlliance, BoardUtils boardUtils) {
		super(piecePosition, pieceAlliance, boardUtils);
	}
	@Override
	public Collection<Move> calculateLegalMoves(final Board board) {
		
		final List<Move> legalMoves = new ArrayList<>();
		
		for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {
			
			final byte candidateDestinationCoordinate = (byte) (this.piecePosition + currentCandidateOffset*this.getPieceAlliance().getDirection());
			
			if(!BoardUtils.isValidSquareCoordinate(candidateDestinationCoordinate)) {
				continue;
			}
			
			if(currentCandidateOffset == 8 && !board.getSquare(candidateDestinationCoordinate).isSquareOccupied()) {
				//TODO we need to deal with promotions!
				legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
			} else if (currentCandidateOffset == 16
					&& ((BoardUtils.RANKS[6][this.piecePosition] && this.pieceAlliance.isWhite())
					|| (BoardUtils.RANKS[1][this.piecePosition] && this.pieceAlliance.isBlack()))) {
				final byte behindCandidateDestinationCoordinate = (byte) (this.piecePosition + this.pieceAlliance.getDirection()*8);
				
				if(!board.getSquare(behindCandidateDestinationCoordinate).isSquareOccupied()
						&& !board.getSquare(candidateDestinationCoordinate).isSquareOccupied()) {
					legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
				}
			} else if (currentCandidateOffset ==7
					&& !((BoardUtils.FILES[7][piecePosition]) && this.pieceAlliance.isWhite()
					|| (BoardUtils.FILES[0][piecePosition] && this.pieceAlliance.isBlack()))) {
				if(board.getSquare(candidateDestinationCoordinate).isSquareOccupied()) {
					final Piece pieceOnCandidateDestination = board.getSquare(candidateDestinationCoordinate).getPiece();
					if(this.pieceAlliance != pieceOnCandidateDestination.pieceAlliance) {
						//TODO More work to do here!!!
						legalMoves.add(new CaptureMove(board, this, candidateDestinationCoordinate, pieceOnCandidateDestination));
					}
				}
				
			} else if (currentCandidateOffset ==9
					&& !((BoardUtils.FILES[0][piecePosition]) && this.pieceAlliance.isWhite()
							|| (BoardUtils.FILES[7][piecePosition] && this.pieceAlliance.isBlack()))) {
				if(board.getSquare(candidateDestinationCoordinate).isSquareOccupied()) {
					final Piece pieceOnCandidateDestination = board.getSquare(candidateDestinationCoordinate).getPiece();
					if(this.pieceAlliance != pieceOnCandidateDestination.pieceAlliance) {
						//TODO More work to do here!!!
						legalMoves.add(new CaptureMove(board, this, candidateDestinationCoordinate, pieceOnCandidateDestination));
					}
				}
				
			}
		}
		
		return ImmutableList.copyOf(legalMoves);
	}

}
