package wchess.wc.piece;

/**
 * 
 * @author Max (Smugless)
 * 
 * Very Heavily influenced by amir.afghani's code and youtube series here:https://youtu.be/h8fSdSUKttk
 */

public enum Alliance {
	WHITE
 {
		@Override
		public byte getDirection() {
			return -1;
		}
		
		@Override
		boolean isWhite() {
			return true;
		}

		@Override
		public boolean isBlack() {
			return false;
		}
	},
 	BLACK
	
 {
		@Override
		public byte getDirection() {
			return 1;
		}
		
		@Override
		boolean isWhite() {
			return false;
		}

		@Override
		public boolean isBlack() {
			return true;
		}
	};
	
	abstract byte getDirection();
	abstract boolean isBlack();
	abstract boolean isWhite();
}
