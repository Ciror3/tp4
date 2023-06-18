package ternilapilli;

public abstract class Status {
	public abstract void putXAt(Position position);
	public abstract void putOAt(Position position);
	public abstract void slideXFrom(Position token, Position slider);
	public abstract void slideOFrom(Position token, Position slider);
}
