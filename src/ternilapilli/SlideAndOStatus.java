package ternilapilli;

public class SlideAndOStatus extends Status {

	public Ternilapilli ternilapilli;
	public String status;

	public SlideAndOStatus(Ternilapilli ternilapilli) {
		this.ternilapilli = ternilapilli;
		status = "Slide and O";
	}

	
	public void putXAt(Position position) {
		ternilapilli.FailIfXPutsWhenItsStatusIsWrong(position);
	}

	public void putOAt(Position position) {
		ternilapilli.FailIfOPutsWhenItsStatusIsWrong(position);
	}

	public void slideXFrom(Position token, Position slider) {
		ternilapilli.FailIfXPutsWhenItsOsTurn(slider);	
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
