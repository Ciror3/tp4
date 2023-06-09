package ternilapilli;

public class PutAndOStatus extends Status {

	public Ternilapilli ternilapilli;
	public String status;

	public PutAndOStatus(Ternilapilli ternilapilli) {
		this.ternilapilli = ternilapilli;
		status = "Put and O";
	}

	public void putXAt(Position position) {
		ternilapilli.FailIfOPutsWhenItsXsTurn(position);
	}

	public void putOAt(Position position) {
		ternilapilli.failWhenSpaceAlreadyTakenForX(position);
		ternilapilli.failWhenSpaceAlreadyTakenForO(position);
		ternilapilli.putOWhenItsOsTurn(position);
		ternilapilli.transitionFromPutOToSlideX(position);
		ternilapilli.isWinnerO();
	}

	public void slideXFrom(Position token, Position slider) {
		ternilapilli.FailIfXslidesWhenItsStatusIsWrong(slider);
	}

	public void slideOFrom(Position token, Position slider) {
		ternilapilli.FailIfOslidesWhenItsStatusIsWrong(slider);
		
	}

}
