package Tetris;

import java.awt.Color;

/*
 * Singleton
 */
public class TBlockFactory {
	private static TBlockFactory instance = null;
	private TBlockFactory() {
		
	}
	public static TBlockFactory getInstance() {
		if (instance == null) {
			instance = new TBlockFactory();
		}
		return instance;
	}
	public TBlock createBlock(TBlkType type, Color cl, TBlockBox box) {
		TBlock blk = null;
		switch (type) {
		case Z:
			blk = new TBlock_Z(box);
			break;
		case ZOPPOSITE:
			blk = new TBlock_ZOpposite(box);
			break;
		case L:
			blk = new TBlock_L(box);
			break;
		case LOPPOSITE:
			blk = new TBlock_LOpposite(box);
			break;
		case SQUARE:
			blk = new TBlock_Square(box);
			break;
		case T:
			blk = new TBlock_T(box);
			break;
		case STICK:
			blk = new TBlock_Stick(box);
			break;			
		default:
			break;
		}
		blk.setColor(cl);
		return blk;
	}
}
