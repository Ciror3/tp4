package ternilapilli;

import java.util.Optional;

public class TurnX extends Status {
	public Ternilapilli ternilapilli;
	public String status;

	public TurnX(Ternilapilli ternilapilli) {
		this.ternilapilli = ternilapilli;
		status = "X";
	}

	public void putXAt(Position position) {
		ternilapilli.failWhenSpaceAlreadyTakenForX(position);
		ternilapilli.failWhenSpaceAlreadyTakenForO(position);
		ternilapilli.putXWhenItsXsTurn(position);
		ternilapilli.allTokensOnBoard(position);
		if (ternilapilli.isWinnerX()) {
			ternilapilli.xWon();
			ternilapilli.gameFinished();
		}
	}

	public void putOAt(Position position) {
		ternilapilli.FailIfXPutsWhenItsOsTurn(position);
	}

	public void slideXFrom(Position token, Position slider) {
		ternilapilli.failWhenSpaceAlreadyTakenForX(slider);
		ternilapilli.failWhenSpaceAlreadyTakenForO(slider);
		ternilapilli.failWhenSpaceIsTooFarAway(token, slider);
		ternilapilli.slideXWhenItsXsTurn(token, slider);
		if (ternilapilli.isWinnerX()) {
			ternilapilli.xWon();
			ternilapilli.gameFinished();
		}
	}

	public void slideOFrom(Position token, Position slider) {
		ternilapilli.FailIfXPutsWhenItsOsTurn(slider);		
	}

}
