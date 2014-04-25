package worms.model;

public class Projectile {
	private double x;
	private double y;
	private double radius;
	private double mass;
	private double force;
	private double yield;
	private double direction;
	private Worm worm;
		public Projectile(Worm worm,double x,double y, double direction, String type)
		{
			this.worm  = worm;
			setX(x);
			setY(y);
			setRadius(radius);
			setDirection(direction);
			if(type == "Rifle" )
			{
				Rifle rifle = new Rifle();
				setMass(rifle.getMass());
				setRadius(rifle.getRadius());
				setForce(rifle.getForce());
			}
			if(type== "Bazooka")
			{
				Bazooka bazooka = new Bazooka();
				setMass(bazooka.getMass());
				setRadius(bazooka.getRadius());
				setForce(bazooka.getForce(yield));
			}
			
		}
		
		
		
		
		
		public double[] getJumpStep(double t) {
			return null;
		}

		public double getJumpTime(double timeStep) {
			return 0;
		}
		
		public void setDirection(double newDirection)
		{
			this.direction = newDirection;
		}

		public double getDirection()
		{
			return this.direction;
		}
		public double getRadius() {
			return this.radius;
		}

		public double getX() {
			return this.x;
		}

		public double getY() {
			return this.y;
		}
		
		public void setForce(double newForce){
			this.force = newForce;
		}
		
		public double getForce()
		{
			return this.force;
		}
		public double getMass()
		{
			return this.mass;
		}
		
		public void shoot(double yield)
		{
			this.yield = yield;
		}
		public void setMass(double newMass){
			this.mass = newMass;
		}
		
		public void setX(double newX)
		{
			this.x = newX;
		}
		
		
		public void setY(double newY)
		{
			this.y=newY;
		}
		
		public void setRadius(double newRadius)
		{
			this.radius = newRadius;
		}
		
		public boolean isActive() {
			return true;
		}

		public void jumpp(double timeStep){
			double tempY = 0;
		   double tempX = 0;
			double time1 = (0.1*this.getRadius())/worm.initialVelocity();
			do
			{
			    time1 += timeStep;
				double[] tempCoordinates = getJumpStep(time1);
				tempX = tempCoordinates[0];
				tempY = tempCoordinates[1];
			}		while (!(this.worm.getWor().isImpassable(tempX,tempY,this.getRadius())) && (!this.worm.getWor().isValidLocation(tempX,tempY,this.getRadius()) && this.worm.getWor().isPassable(tempX, tempY)));
			
			if( this.worm.getWor().isValidLocation(tempX, tempY,getRadius()))
				if (this.worm.getWor().totalDistance(getX(),getY(),tempX,tempY) > getRadius()){
					setX(tempX);
					setY(tempY);
				}
			}
		public double[] getJumpStepProjectile(double Time) {
			
				double velocityx = worm.initialVelocity() * Math.cos(this.getDirection());
				double velocityy = worm.initialVelocity()* Math.sin(this.getDirection());
				double xCoValue = this.getX()+ (velocityx*Time);
			    double yCoValue = this.getY()+ (velocityy*Time-(0.5)*9.80665*Math.pow(Time, 2));
				double[] Array = new double[2];
				Array[0] = xCoValue;
				Array[1] = yCoValue;
				return Array;
			}
		
}
