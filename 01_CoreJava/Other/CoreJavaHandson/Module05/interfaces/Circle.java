package Module05.interfaces;

class Circle implements Graphic {
	private float radius;
	
	public Circle (float r) {
    	radius = r;
  	}
  	public float area () {
    	return PI * radius * radius;
  	}
  	public float periphery () {
    	return 2 * PI * radius;
  	}
}

