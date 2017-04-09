package states;

public class ClosedState implements IState {
	public String toString() {
		return "CLOSED";
	}

	public IState nextState() {
		return null;
	}
}
