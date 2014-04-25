package worms.model;


public class Food {

	
	private double y;
	private double x;
	
	public Food(double x, double y) {
		this.setX(x);
		this.setY(y);
	}
	
	public void setX(double x){
		this.x =x;
	}
	
	public double getX(){
		return this.x;
	}
	
	
	public void setY(double y){
		this.y = y;
	}
	
	public double getY(){
		return y;
	}
	
	private double foodRadius = 0.20;
	public double getFoodRadius(){
		return this.foodRadius;
	}
    
	boolean isActive = true;
	private Worm worm;
	public boolean isActive() {
    if(!this.isActive)
     return false;
		return true;
	}

	

}
