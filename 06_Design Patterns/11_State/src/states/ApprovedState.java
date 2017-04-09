package states;

public class ApprovedState implements IState {
	public String toString() {
		return "APPROVED";
	}
	public IState nextState() {
		return new ClosedState();
	}
}
