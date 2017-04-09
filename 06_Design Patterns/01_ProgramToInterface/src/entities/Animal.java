package entities;

import behaviors.BehaviorSpeak;
import behaviors.BehaviorSwim;

public abstract class Animal {
	private BehaviorSpeak behaviorSpeak;
	private BehaviorSwim behaviorSwim;
	public BehaviorSpeak getBehaviorSpeak() {
		return behaviorSpeak;
	}
	public void setBehaviorSpeak(BehaviorSpeak behaviorSpeak) {
		this.behaviorSpeak = behaviorSpeak;
	}
	public BehaviorSwim getBehaviorSwim() {
		return behaviorSwim;
	}
	public void setBehaviorSwim(BehaviorSwim behaviorSwim) {
		this.behaviorSwim = behaviorSwim;
	}
	
	
	public void speak() {
		getBehaviorSpeak().speak();
	}
	public void swim() {
		getBehaviorSwim().swim();
	}
}
