package ternilapilli;

public class SlideAndXStatus extends Status {

	public Ternilapilli ternilapilli;
	public String status;

	public SlideAndXStatus(Ternilapilli ternilapilli) {
		this.ternilapilli = ternilapilli;
		status = "Slide and X";
	}

	
	public void putXAt(Position position) {
		ternilapilli.FailIfXPutsWhenItsStatusIsWrong(position);
	}

	public void putOAt(Position position) {
		ternilapilli.FailIfOPutsWhenItsStatusIsWrong(position);
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
