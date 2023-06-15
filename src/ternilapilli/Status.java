package ternilapilli;

public abstract class Status {
	public abstract void putXAt(Position position);
	public abstract void putOAt(Position position);
	public abstract void checkIfOsContainsIt(Position position);
	public abstract void checkIfXsContainsIt(Position position);
}
