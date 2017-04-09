package states;

public class DraftState implements IState {

	public String toString() {
		return "DRAFT";
	}
	
	public IState nextState(){
		return new SubmittedState();
	}
}
