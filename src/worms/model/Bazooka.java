package worms.model;

public class Bazooka {
	private final double HOMOGENOUS_DENSITY = 7800;

	public double getMass() {
		return 0.3;
	}

	public double getRadius() {
		// Mass = Density * Volume
		//getmass() / density = volume
		// mass/density = 4/3 pi * r^3
		// volume *3 / 4PI*density = r^3
		double volume = getMass()/HOMOGENOUS_DENSITY;
		double radiuss = volume*3/(4*Math.PI);
		double radius =Math.pow(radiuss, 1.0/3.0);
		return radius;
	}

	public double getForce(double yield) {
		// TODO Auto-generated method stub
		return 2.5+(9.5-2.5)*yield/100;
	}

	public double getHitCost(){
		return 80;
	}
	
	public double getActionCost(){
		return 50;
	}

}
