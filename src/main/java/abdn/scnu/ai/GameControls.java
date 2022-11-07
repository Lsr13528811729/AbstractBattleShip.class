package abdn.scnu.ai;

public interface GameControls {

	public void playRound (String input);
	
	public boolean checkVictory ();
	
	public void exitGame (String input);
	
	public PlayerGameGrid getPlayersGrid ();
	
	public OpponentGameGrid getOpponentsGrid();
}
