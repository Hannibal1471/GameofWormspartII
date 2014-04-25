package worms.model;

public class Rifle {

	private final double HOMOGENOUS_DENSITY = 7800;
	public double getMass() {
		return 0.01;
	}

	public double getRadius() {
		double volume = getMass()/HOMOGENOUS_DENSITY;
		double radiuss = volume*3/(4*Math.PI);
		double radius =Math.pow(radiuss, 1.0/3.0);
		return radius;
	}

	public double getForce() {
		return 1.5;
	}
	
	public double getHitCost(){
		return 20;
	}
	
	public double getActionCost(){
		return 10;
	}


}
