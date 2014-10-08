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
		case ZHLEFT:
			blk = new TBlock_ZHLeft(box);
			break;
		default:
			break;
		}
		blk.setColor(cl);
		return blk;
	}
}
