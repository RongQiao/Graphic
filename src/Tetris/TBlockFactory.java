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
		case ZH:
			blk = new TBlock_ZH(box);
			break;
		case ZHOPPOSITE:
			blk = new TBlock_ZHOpposite(box);
			break;
		case LH:
			blk = new TBlock_LHUp(box);
			break;
		case LHOPPOSITE:
			blk = new TBlock_LHOppositeUp(box);
			break;
		case SQUARE:
			blk = new TBlock_Square(box);
			break;
		case T:
			blk = new TBlock_Tup(box);
			break;
		case STICK:
			blk = new TBlock_StickH(box);
			break;			
		default:
			break;
		}
		blk.setColor(cl);
		return blk;
	}
}
