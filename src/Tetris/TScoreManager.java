package Tetris;

public class TScoreManager {
	private boolean scoredFlag;
	private int scoreFactor;	//M:for one line, 1-10
	private int scoredLineCnt;
	private int scroe;
	private int level;
	private int levelGate;		//N:number of rows required for each level of difficulty range: 20-50
	private double speedFactor;	//S:0.1 - 1.0
	private double speed;
	
	public TScoreManager() {
		this.scoredFlag = true;
		this.setScoreFactor(1);
		this.setScoredLineCnt(0);
		this.setScroe(0);
		this.setLevel(1);
		this.setLevelGate(20);
		this.setSpeedFactor(0.1);
		this.setSpeed(1.0);
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getSpeedFactor() {
		return speedFactor;
	}

	public void setSpeedFactor(double speedFactor) {
		this.speedFactor = speedFactor;
	}

	public int getScoreFactor() {
		return scoreFactor;
	}

	public void setScoreFactor(int scoreFactor) {
		this.scoreFactor = scoreFactor;
	}

	public int getLevelGate() {
		return levelGate;
	}

	public void setLevelGate(int levelGate) {
		this.levelGate = levelGate;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getScore() {
		return scroe;
	}

	public void setScroe(int scroe) {
		this.scroe = scroe;
	}

	public int getScoredLineCnt() {
		return scoredLineCnt;
	}

	public void setScoredLineCnt(int scoredLineCnt) {
		this.scoredLineCnt = scoredLineCnt;
	}

	public boolean needScore() {
		return scoredFlag;
	}

	/*
	 * line = line + lineCnt
	 * score = score + level * M * lineCnt
	 */
	public void calculateScore(int lineCnt) {
		int currentScore = this.getLevel() * this.getScoreFactor() * lineCnt;
		int newScore = this.getScore() + currentScore;
		this.setScroe(newScore);
	}

	public void update(int lineCnt) {
		int newLineCnt = this.getScoredLineCnt() + lineCnt;
		this.setScoredLineCnt(newLineCnt);
		calculateScore(lineCnt);
		if (reachNextLevel()) {
			int newLevel = this.getLevel() + 1;
			this.setLevel(newLevel);
			double newSpeed = this.getSpeed() * (1 + this.getLevel() * this.getSpeedFactor());
			this.setSpeed(newSpeed);
		}
	}

	private boolean reachNextLevel() {
		int lines4NextLevel = this.getLevelGate() * (this.getLevel() + 1);
		boolean ret = (this.getScoredLineCnt() >= lines4NextLevel);
		return ret;
	}

}
