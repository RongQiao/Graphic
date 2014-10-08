package Tetris;

public enum TBlkType {
//	ZHRIGHT, 
//	ZHLEFT, 
//	LHRIGHT, 
//	LHLEFT, 
//	SQUARE, 
//	TUP, 
//	STICKH;
	ZHRIGHT(1,1), 
	ZHLEFT(2,1), 
	LHRIGHT(3,1), 
	LHLEFT(4,1), 
	SQUARE(5,1), 
	TUP(6,1), 
	STICKH(7,1);
	
	private int id;
	private int subId;
	TBlkType(int id, int subId) {
		this.id = id;
		this.subId = subId;
	}
}
