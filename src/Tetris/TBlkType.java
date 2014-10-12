package Tetris;

public enum TBlkType {
//	ZHRIGHT, 
//	ZH, 
//	LHRIGHT, 
//	LHLEFT, 
//	SQUARE, 
//	TUP, 
//	STICKH;
	ZHOPPOSITE(1,1), 
	ZH(2,1), 
	LH(3,1), 
	LHOPPOSITE(4,1), 
	SQUARE(5,1), 
	T(6,1), 
	STICK(7,1);
	
	private int id;
	private int subId;
	TBlkType(int id, int subId) {
		this.id = id;
		this.subId = subId;
	}
}