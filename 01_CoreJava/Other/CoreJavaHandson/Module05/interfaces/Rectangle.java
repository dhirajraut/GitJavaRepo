package Module05.interfaces;
class Rectangle implements Graphic {
	private float width, height;
  	
	public Rectangle (float w, float h) {
    			width = w; height = h;
  	}
 	 public float area () {
    			return width * height;
  		}
  	public float periphery () {
    			return 2 * (width + height);
  	}
}

