import behaviors.Bark;
import behaviors.CanNotSwim;
import behaviors.CanSwim;
import behaviors.NoSpeak;
import behaviors.Quack;
import entities.Animal;
import entities.Dogs.StrayDog;
import entities.Dogs.ToyDog;
import entities.Ducks.MallardDuck;
import entities.Ducks.RedheadDuck;
import entities.Ducks.ToyDuck;

public class ProgramToInterface {
	public static void main(String args[]) {
		Animal animal = new MallardDuck();
		System.out.println("\nClass: " + animal.getClass().getName());
		animal.setBehaviorSpeak(new Quack());
		animal.setBehaviorSwim(new CanSwim());
		animal.speak();
		animal.swim();

		animal = new RedheadDuck();
		System.out.println("\nClass: " + animal.getClass().getName());
		animal.setBehaviorSpeak(new Quack());
		animal.setBehaviorSwim(new CanSwim());
		animal.speak();
		animal.swim();

		animal = new ToyDuck();
		System.out.println("\nClass: " + animal.getClass().getName());
		animal.setBehaviorSpeak(new NoSpeak());
		animal.setBehaviorSwim(new CanNotSwim());
		animal.speak();
		animal.swim();

		animal = new StrayDog();
		System.out.println("\nClass: " + animal.getClass().getName());
		animal.setBehaviorSpeak(new Bark());
		animal.setBehaviorSwim(new CanSwim());
		animal.speak();
		animal.swim();

		animal = new ToyDog();
		System.out.println("\nClass: " + animal.getClass().getName());
		animal.setBehaviorSpeak(new NoSpeak());
		animal.setBehaviorSwim(new CanNotSwim());
		animal.speak();
		animal.swim();
	}
}
