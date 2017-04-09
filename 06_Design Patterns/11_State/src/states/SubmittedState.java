package states;

public class SubmittedState implements IState {
	public String toString() {
		return "SUBMITTED";
	}

	public IState nextState() {
		return new ApprovedState();
	}
}
