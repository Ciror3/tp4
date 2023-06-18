package ternilapilli;

import java.util.Optional;

public class TurnO extends Status {
	public Ternilapilli ternilapilli;
	public String status;

	public TurnO(Ternilapilli ternilapilli) {
		this.ternilapilli = ternilapilli;
		status = "O";
	} 

	public void putXAt(Position position) {
		ternilapilli.FailIfOPutsWhenItsXsTurn(position);
	}

	public void putOAt(Position position) {
		ternilapilli.failWhenSpaceAlreadyTakenForX(position);
		ternilapilli.failWhenSpaceAlreadyTakenForO(position);
		ternilapilli.putOWhenItsOsTurn(position);
		ternilapilli.allTokensOnBoard(position);
		if (ternilapilli.isWinnerO()) {
			ternilapilli.oWon();
			ternilapilli.gameFinished();
		} 
	}

	public void slideXFrom(Position token, Position slider) {
		ternilapilli.FailIfOPutsWhenItsXsTurn(slider);
	}

	public void slideOFrom(Position token, Position slider) {
		ternilapilli.failWhenSpaceAlreadyTakenForX(slider);
		ternilapilli.failWhenSpaceAlreadyTakenForO(slider); 
		ternilapilli.failWhenSpaceIsTooFarAway(token, slider);
		ternilapilli.slideOWhenItsOsTurn(token, slider);
		if (ternilapilli.isWinnerO()) {
			ternilapilli.oWon();
			ternilapilli.gameFinished();
		}

	}
}
