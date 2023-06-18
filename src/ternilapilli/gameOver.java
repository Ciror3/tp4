package ternilapilli;

public class gameOver extends Status{
	public Ternilapilli ternilapilli;
	public String status;
	public gameOver(Ternilapilli ternilapilli) {
		this.ternilapilli = ternilapilli;
        status = "Game Over";
	}
	
	public void putXAt(Position position) {
		throw new RuntimeException(ternilapilli.cannotPlayWhenGameIsOver);
	}

	public void putOAt(Position position) {
		throw new RuntimeException(ternilapilli.cannotPlayWhenGameIsOver);
	} 

	public void slideXFrom(Position token, Position slider) {
		throw new RuntimeException(ternilapilli.cannotPlayWhenGameIsOver);
	}

	public void slideOFrom(Position token, Position slider) {
		throw new RuntimeException(ternilapilli.cannotPlayWhenGameIsOver);
	}
}
