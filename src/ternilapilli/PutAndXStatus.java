package ternilapilli;

public class PutAndXStatus extends Status{

	public Ternilapilli ternilapilli;
	public String status;

	public PutAndXStatus(Ternilapilli ternilapilli) {
		this.ternilapilli = ternilapilli;
		status = "Put and X";
	}

	public void putXAt(Position position) {
		ternilapilli.failWhenSpaceAlreadyTakenForX(position);
		ternilapilli.failWhenSpaceAlreadyTakenForO(position);
		ternilapilli.putXWhenItsXsTurn(position);
		ternilapilli.isWinnerX();
	}

	public void putOAt(Position position) {
		ternilapilli.FailIfXPutsWhenItsOsTurn(position);
	} 

	public void slideXFrom(Position token, Position slider) {
		ternilapilli.FailIfXslidesWhenItsStatusIsWrong(slider);
	}

	public void slideOFrom(Position token, Position slider) {
		ternilapilli.FailIfOslidesWhenItsStatusIsWrong(slider);
		
	}

}
