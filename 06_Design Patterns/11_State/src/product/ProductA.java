package product;

import states.DraftState;
import states.IState;

public class ProductA {
	private IState state;
	public ProductA() {
		state = new DraftState(); 
	}
	public IState getState() {
		return state;
	}
	public void setState(IState state) {
		this.state = state;
	}
	public void promote() {
		setState(state.nextState());
	}

}
