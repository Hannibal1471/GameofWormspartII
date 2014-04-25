package worms.model;

import java.util.ArrayList;
import java.util.ListIterator;

import javax.imageio.IIOException;

import be.kuleuven.cs.som.annotate.Immutable;


/**
 * 
 * @author afraz Salim and Jordy Ottenburgs
 *
 */
public class Worm {
	private double x;
	private World wor;
	private double y;
	private double radius;
	private double direction;
	private String name;
	private int maxActionPoints;
	private double ActionPoints;
	int ActionCost;
	private boolean changeValue = false;
	private double Mass=0;
	private int Point = 0;
	private double density = 1062.0;
	boolean canChangeValue = true;
	private World world;
	private ArrayList<Projectile> NameOfWeapons = new ArrayList<Projectile>();
	private int currentWeapon = 0;
	private ArrayList<Food> delete = new ArrayList<Food>();
	private Food food;



	
	
	/**
	 * Constructor to initialise the variable with their initial value.
	 * @param world 
	 * 
	 * @param rx
	 * 			The position of the worm at x-Axis.
	 * @param ry
	 *          The position of the worm at y-axis.
	 * @param rdirection
	 *          The direction of the worm .
	 * @param rradius
	 *          The radius of the worm.
	 * @param rname
	 *          Name of the worm.
	 * @post...
	 *         The x variable will be initialised with it,s initial value.
	 * @post...
	 *         The Y variable will be initialised with it,s initial value.
	 * @post...
	 *         The variable for direction will be initialised with it,s initial value.
	 * @post...
	 *         The worm will get an initial radius.
	 * @post...
	 *         The worm,s name will be initialised with it,s name.Worm,s get their names.
	 */
	public Worm(World world, double rx, double ry, double rdirection, double rradius, String rname)
	{
		this.x = rx;
		this.y = ry;
		this.direction = rdirection;
		this.radius = rradius;
		this.name = rname;
        this.wor = world;
        food = new Food(rx, ry);
	}
	
	
	
	/**
	 * To get the position of the worm at x-coordinate.
	 * @return
	 *       The position of the worm at x-axis.
	 *       (result = (position at x-axis)).
	 *       |return this.getX()
	 */
	// @Basic
	public double getX()
	{
		return this.x;
	}
	
	/**
	 * To get the position of the worm at Y-coordinate.
	 * @return
	 *         it returns the position of the worm worm at y-axis.
	 *         (result= (position at y-axis))
	 *         |return this.getY()
	 */
	//@Basic
	public double getY()
	{
		return this.y;
	}
	
	
	/**
	 * To set the position of the worm at X-axis.
	 * @param xcoordinate
	 *               To update the value of the variable at x-axis.it updates the value
	 *               of the x-coordinate when the new value is assigned or when any action 
	 *               is performed.
	 *               |this.x = new.xCoordinate
	 */
	public void setX(double xCoordinate)
	{
		this.x = xCoordinate;
	}
	
	
	/**
	 * To set the position of the worm at y-axis.
	 * @param yCoordinate
	 *           To set the value of the variable at y-axis.The value of the y-coordinate will be updated 
	 *           when a new value will be assigned to the worm or when the action will be performed by 
	 *           jumping or moving.
	 *           |this.y = new.yCoordinate;
	 */
	public void setY(double yCoordinate)
	{
		this.y = yCoordinate;
	}
	
	
	
	/**
	 * To move along the axis with given number of steps and according to the angle.
	 *   
	 * @param nbSteps
	 *            The given number of steps.
	 * @post.... 
	 *            set the new position of the worm at x coordinate.
	 *            |this.setX(nbStepsOnXcoordinate).
	 * @post...
	 *            set the new position of the worm at y-Axis.
	 *            |this.setY(nbStepsOnYcoordinate).
	 * @post ....
	 *            The ActionCost, required to move, will be subtracted from the total ActionPoints.
	 *            |this.setActionPoints(ActionCost).
	 * @effect...
	 *            The worm will move the number of steps, it is given on the x and y axis and the 
	 *            the cost to walk will be decremented from the total action points.
	 */
	public void move(int nbSteps)
	{
		if(this.canMove(nbSteps)){
		double nbStepsOnXcoordinate = this.getX()+nbSteps*this.getRadius()*Math.cos(this.getDirection());
		double nbStepsOnYcoordinate = this.getY()+nbSteps*this.getRadius()*Math.sin(this.getDirection());
		ActionCost = (int) (this.getActionPoints() - nbSteps*(Math.abs((Math.cos(this.getDirection())))+Math.abs(4*Math.sin(this.getDirection()))));
		this.setX(nbStepsOnXcoordinate);
		this.setY(nbStepsOnYcoordinate);
		this.setActionPoints((int)ActionCost);
		}
		else
			throw new ModelException("cant move");
	}
	
	
	
	/**
	 * To set the ActionPoints and to update the action Points.
	 * @param newActionPoints
	 *          The initial Action points are awarded to the worm and the will be 
	 *          updated according to it,s mass.
	 *@post....
	 *         The Action points will be initialised and then updated with the newActionPoints
	 *         |this.ActonPoints  new.ActionPoints.
	 */
	public void setActionPoints(int newActionPoints){
		this.ActionPoints = newActionPoints;
	}

	
	
	/**
	 * set,s the position of the worm on x-axis and y-axis.
	 * @param coo
	 *        An array to save the values of X and Y axis to keep the worm at a certain 
	 *        position after jumping.
	 * @post...
	 *        set,s the new position of the worm at x-axis.When the jump action will be
	 *        performed by the worm.
	 *       |this.setX() = new.coo[0].
	 * @post...
	 *       set,s the new position of the worm at y-axis.When the jump action will be 
	 *       performed by the worm.
	 *       |this.setY() = new.coo[1].
	 */
	public void actionJump(double[] coo)
	{
		this.setX(coo[0]);
		this.setY(coo[1]);
	}
	
	
	/**
	 * To turn in the given direction.
	 * @param angle
	 *         to turn according to this given angle.
	 * @post ...
	 *         set,s the direction of the worm to it,s given angle.
	 *         |this.setDirection(getDirection() + angle)
	 *@post...
	 *         Action points for the turning will be subtracted from ActionPoinst.
	 *         |this.setActionPoints(ActCostTurn).
	 */
	public void turn(double angle)
	{
			this.setDirection(getDirection() + angle);
			double fraction = 2*Math.PI/angle; 
			double ActCostTurn = this.getActionPoints()-(60/Math.abs(fraction));
			this.setActionPoints((int)ActCostTurn);
	}
	
	/**
	 * Get,s the radius of the worm.
	 * @return
	 *        It return the radius of the worm .
	 *        (result = (radius)).
	 *        |return this.radius
	 */
	//@Basic
	public double getRadius(){
		return this.radius;
	}
	
	/**
	 * 
	 * @param Radius
	 *         Parameter to update or set the radius of the worm.
	 * @post ....
	 *         Set,s the radius to the new radius.when the new value
	 *         of the worm will be awarded.
	 *         |this.radius = new.radius.
	 */
	public void setRadius(double Radius)
	{
		this.radius = Radius;
		
	}
	
	/**
	 * 
	 * @return
	 *      The minimal radius of the worm.
	 *      (result = (minimalRadius)).
	 *      |return = minimalRadius.
	 */
	//@Basic
	public double getMinimalRadius()
	{
		double minimalRadius = 0.25;
		return minimalRadius;
	}
	
	
	/**
	 * 
	 * @return
	 *     The direction of the worm.
	 *     (result = (this.direction))
	 *     |return this.direction
	 */
	//@Basic
	public double getDirection()
	{
		return this.direction;
	}
	
	
	/**
	 * 
	 * @param newDirection
	 *          Direction/angle of the worm.
	 * @post....
	 *         it set,s the new direction of the worm according to the 
	 *         given angle.
	 *         |this.Direction = new.newDirection.
	 */
	public void setDirection(double newDirection)
	{
		this.direction = newDirection;
	}
	
	
	/**
	 * 
	 * @return
	 *      Get,s the name of the worm.
	 *      (result = (name))
	 *      |result = (this.name).
	 */
    //@Basic
	public String getName()
	{
		return this.name;
	}
	
	
	/**
	 *  To set the name of the worm.
	 * @param Name
	 *        new Name of the worm. 
	 * @post...
	 *       Replaces the old name with a new name if the new name 
	 *       is a valid name.
	 *       |this.name = new.name.
	 */
	public void setName(String Name)
	{
		this.name = Name;
	}
	
	
	
	

	
	
	
	/**
	 *   The total mass of the worm will be calculated according to it,s radius.
	 * @return
	 *        it return,s the total mass of the worm.
	 *        (result = (mass)).
	 *        |this.getMass() = mass.
	 */
	public double getMass()
	{
		Mass = 1062*((1.33)*Math.PI*Math.pow(this.getRadius(), 3));
		return Mass;
	}
	
	
	/**
	 * Get,s the ActionPoints of the worm.
	 * @return
	 *          It return,s the Action points of the worm.
	 *          (result = (ActionPoints))
	 *          |return ActionPoints.
	 */
	public int getActionPoints()
	{
		return (int) ActionPoints;
	}
	
	
    /**
     * Get,s the initial ActionPoints which are equal to the worm,s mass.
     * @return
     *          it return,s the initialAction points which are equal to the mass of the worm
     *          (result = (ActionPoints))
     *          |return ActionPoints.
     */
	public int getInitialActionPoints()
	{
		int initialpoints = (int) this.getMass();
		return initialpoints;
	}
	
	/**
	 * Computes the force, need a worm to jump according to this force.
	 * @return
	 *      it returns the force required by the worm acording to its Action points and Mass.
	 *      result = (force).
	 *      |return force.
	 */
	public double getForce(){
		double force = (5*this.getActionPoints())+(this.getMass()*9.80665);
		return force;
	}
	
	
	/**
	 * It computes the initial velocity of the worm.
	 * @return
	 *     it return the the initial velocity of the worm.
	 *     result = (velocity).
	 *     |return velocity.
	 */
	public double initialVelocity(){
		double velocity;
		velocity = (this.getForce()/this.getMass())*0.5;
		return velocity;
	}
	
	/**
	 * To compute the distance.
	 * @return
	 *     it returns the distance that the worm will jump.
	 *     |distance = (Math.pow(this.initialVelocity(), 2)*Math.sin(2*this.getDirection()))/gravity.
	 */
	public double getDistance(){
		double distance = (Math.pow(this.initialVelocity(), 2)*Math.sin(2*this.getDirection()))/9.80665;
		return distance;

	}
	
	/**
	 * Compute,s the total time while jumping.
	 * @param timeStep 
	 * @return
	 *       The time of the jump.
	 *       result = (time)
	 *       |return time.
	 */
	public double getJumpTime(double timeStep)
	{
		double	time = this.getDistance()/(this.initialVelocity()*Math.cos(this.getDirection()));
		return time;
	}
	
	

	/**
	 * check,s the remaining ActionPoints are valid are not.
	 * @return
	 *       True if and only if the ActionPoints are greater than zero.if the given number of the action points 
	 *       are less than zero in that case it will be false.
	 *       (result = (true) if AcrtionPoints are greater than 0)
	 *       |this.getActionPoints()>0.
	 */
	//@Basic
	public boolean hasValidActionPoints()
	{
		if (this.getActionPoints()>0)
		return true;
			if(this.getActionPoints()<0)
			this.setActionPoints(0);
		return false;
	}
	
	
	
	

	
	
	/**
	 * Check,s whether the radius is valid or not.
	 * @param radius
	 *          
	 * @return
	 *  The given radius to check whether it is valid or not.
	 *          Radius should be greater then MinimalRadius.
	 *          True if the radius is valid. 
	 *          |(radius>=this.getMinimalRadius()).
	 */
	// @Basic
	public boolean isValidRadius(double radius)
	{
		if(radius>=this.getMinimalRadius())
		{
		return true;
		}
		return false;
	}
	
	/**
	 * To call the actionJump method with the value,s so that the worm can jum.
	 *@post ...
	 *        The worm will jump.
	 *        |this.actionJump(getJumpStep(getJumpTime()).
	 *@effect...
	 *        The worm will change it,s position by jumping from one place to other,
	 *        with certain force and if the worm has enough Action points to jump.
	 *        It uses a certain number of the steps and time during the jump.
	 */
	public void Jump()
	{
		this.actionJump(getJumpStep(getJumpTime(0.5)));	
	}
	
	
	
	
	
	
	/**
	 * Check,s whether the new name is valid or not.
	 * @param name
	 *        To replace the old name with new name.
	 * @return..
	 *       The name of the worm should not be empty.
	 *       |!name.isEmpty().
	 *       checks, if the name contains any integer.it will than result in false.
	 *       |name.conatains(number).
	 *       The name of the worm should not contain any symbol.
	 *       |(!(name.contains(symbol)).
	 *       Check,s whether the new name contains any space or not.if the name does not contain any 
	 *       space.it will than result in false.
	 *       |(name.contains(" "))
	 *       The first character of the worm,s name should be a Capital.if the first character of the 
	 *       name is not in Upper case the condition will be false.
	 *       |(Character.isUpperCase(name.charAt(0))).
	 */
	 //@Basic
	public boolean isValidName(String name)
	{
		String [] signs = { ":", "%","£","*","^",")","(","+","-","!","'","&","|"};
        String[] num = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };  
		for(int i = 0;i< num.length;i++)
			if(name.contains(num[i])&&(name.contains(num[i]))){
				return false;
			}
		for(int i = 0;i < signs.length;i++){
			if(name.contains(signs[i]))
				return false;
		}
		if(name.isEmpty())
			return false;
		if(!Character.isUpperCase(name.charAt(0)))
			return false;
		if(!name.contains(" "))
			return false;
		
		return true;
	}
	
	/**
	 * Check,s whether the worm can jump or not. It,s given angle is valid or not. 
	 * @return  
	 *    True if and only  if the direction of the worm is valid.If the direction of the worm is 
	 *    towards the down side and if the worm will be prompted to jump it will return false.
	 *    |(this.getDirection()>(0) && this.getDirection()<(Math.PI/2).
	 */
	//@Basic
	public boolean canJump(){
		if(this.getDirection()>0 &&this.getDirection()< Math.PI)
			return true;
		else
		return false;
	  }

	
   /*

public  boolean canFall() {
     if((this.getWorld().isAdjacent(this.getX(),this.getY(),this.getRadius())))
		  return true;
     else
	return false;
	}
	*/

/**
 * To check whether a worm can move or not.
 * @param nbSteps
 *          Number of the steps to move.
 * @return
 *     True if and only  if the number of action points are greater than than action cost to move.
 *     |this.getActionPoints()-nbSteps > 0
 */
	public boolean canMove(int nbSteps){
		if(this.getActionPoints()-this.getActionCost(nbSteps) > 0)
			return true;
		return false;
	}

	
	/**
	 * Computes the total action coost required to move.
	 * @param nbsteps
	 *        number of the steps to move.
	 * @return
	 *       The total action Cost to move.
	 *       |return (int) (this.getActionPoints() - (nbsteps*(Math.abs((Math.cos(this.getDirection())))+Math.abs(4*Math.sin(this.getDirection())))));

	 */
	public int getActionCost(double nbsteps){
		return (int) (nbsteps*(Math.abs((Math.cos(this.getDirection())))+Math.abs(4*Math.sin(this.getDirection()))));
	}
	
	
	
	
	/**
	 * To check that a worm can move or not.
	 * True if and only if the both conditions are true.
	 * if the world, at given coordinates, is adjacent and the required action cost is less than the current actionpoints.
	 * |if(this.canMove(nbSteps) &&(world.isAdjacent(this.getX()+this.getRadius(),this.getY()+this.getRadius(),this.getRadius())))
	 */
	/*public boolean canMoveUp(int nbSteps){
		if(this.canMove(nbSteps)&&(world.isAdjacent(this.getX()+Math.cos(this.getDirection()*0.1)+this.getRadius(),this.getY()+Math.sin(this.getDirection()*0.1)+this.getRadius(),this.getRadius())))
			return true;
		return false;
	}
	
	
	
/**
 * The worm will fall if it is moves or jumps to the position which is not adjacent.
 * @pre...
 *     The worm will fall if and only if the worm changes its position by moving or jumping to the position which is not 
 *     adjacent.
 *    |if(!(world.isAdjacent(this.getX(),this.getY(),this.getRadius()))
 * @post...
 *     The worm will fall until it reaches a place which is adjacent.
 *   | while(!(world.isAdjacent(this.getX(),this.getY(),this.getRadius())))){
 *   |	 this.setY(this.getY()-0.1);
 *  @post..
 *     If the worm reaches at 0 (the Y coordinate = height) than the worm will be removed.
 *      | if (this.getY()==0)
 *      | this.remove(this)
 *     
 *//*
	public void fall() {
     if(this.canFall()){
    	 double k = this.getY();
    	while(!(world.isAdjacent(this.getX(),this.getY(),this.getRadius()))){
    		 this.setY(this.getY()-1);
    		 if(this.getY()== 0)
    			 break;
    	}
    	}
     }*/
		


	
	
	
	
	
	
	
	
	/**
	 * 
	 * @param Time
	 *         The time of the jump step.
	 * @return
	 *       The total velocity of the worm will be computed then this velocity
	 *       will be used by a worm to calculate its jump steps at a certain
	 *       distance at x and y coordinate.
	 *       (result = (Array containing the value s of x and y coordinate.))
	 *       |return Array.
	 */
	public double[] getJumpStep(double Time)
	{
		double velocityx = this.initialVelocity() * Math.cos(this.getDirection());
		double velocityy = this.initialVelocity()* Math.sin(this.getDirection());
		double xCoValue = this.getX()+ (velocityx*Time);
	    double yCoValue = this.getY()+ (velocityy*Time-(0.5)*9.80665*Math.pow(Time, 2));
		double[] Array = new double[2];
		Array[0] = xCoValue;
		Array[1] = yCoValue;
		return Array;
	}
	
	
	
	
	

	public void move() {
		this.move(1);
	double s = 0.7875;
	double divergence = Math.abs(this.getDirection());
		
	}

	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	/**
	 * it computes the total mass of the worm.
	 * @param radius
	 *          The radus of the worm.
	 * @pre ...
	 *      The computed mass should be a valid mass.
	 *      |(isValidMass(Mass)
	 * @post...
	 *      id the computed mass is a valid mass then the computed mass of the worm
	 *      will be awarded to worm.    
	 *      |this.Mass = mass;
	 * @throws ModelException
	 *      if the computed mass is not valid mass then it throws the modelException.
	 *      |throw new ModelException("Mass is not valid");
	 */
	public void wormMass(double radius) throws ModelException{
		double mass = density*(4.0/3.0*Math.PI*Math.pow(radius,3));
		if(isValidMass(Mass)){
			this.Mass = mass;
			this.setMaxHitPoint(this.getMass());
		}
		else
			throw new ModelException("Mass is not valid");
	}
	
	
	
	
	
	
	
	
	
private int MaxHitPoints = 0;



 /**
  *Sets the maxHitPoints according to the mass of the worm.
  * @param mass
  *        The mass of the worm.
  * @pre...
  *   if the mass <=0 the the MaxHitPoints will 0.
  *   |if(mass <= 0){
	  |this.MaxHitPoints = 0;
  * @post...
  *    if the mass is a valid mass or greater than 0 then 
  *    the HitPoints will be equal to the mass of the worm.
  *    |MaxHitPoints = (int) mass;
  *    |this.setActionPoints((int)this.getMass());
  */
	public void setMaxHitPoint(double mass) {
		if(mass <= 0){
			this.MaxHitPoints = 0;
		}
		else
		 MaxHitPoints = (int) mass;
		 this.setActionPoints((int)this.getMass());
	}

	
	
	
	
	
	
	
	/**
	 * Return the MaxHitPoints.
	 * @return
	 *   Returns the MaxHitPoints.
	 *   |return this.MaxHitPoints;
	 */
	public double getMaxHitPoints(){
		return this.MaxHitPoints;
	}
	
	
	
	
	
	public World getWorld(){
		return this.world;
	}
	
	
	
	
	
	
	
/**
 * checks of the mass, ot be awarded to the worm, is valid mass or not.
 * @param mass
 *         Parameter of the computed mass.
 * @return
 *     True if and only if the mass is greater than 0 and not is a (NAN) not a number.
 *     |if(mass > 0)
 *         return true;
 *     |if(!(Double.isNaN(mass)))
 *     	 return true;
 */
	private boolean isValidMass(double mass) {
         if(mass > 0)
        	 return true;
         if(!(Double.isNaN(mass)))
        	 return true;
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Checks whether a worm is alive or not.
	 * @return
	 *   True if and only if the HitPoints of the worms are greater than 0.
	 *   |(this.getHitPoints()>0)
	 */
	public boolean isAlive() {
	if(this.getHitPoint()>0)
			return true ;
			else
				return false;
	}

	
	
	
	/**
	 * Sets the currentHitPoints os the worm.
	 * @post....
	 * The Hit points will be awarded only one time.
	 * |this.currentHitPoints = (int)this.getMaxHitPoints();
	 */
	@Immutable
	public void setHitPoints(int points){
		      this.Point = points;
	}
	
	
	/**
	 * It returns the hitPoints of the worm.
	 * @return
	 *    The hitPoints of the worm.
	 *    |return this.currentHitPoints;
	 */
	public double getHitPoint(){
		return this.Point;
	}
	
	
	/**
	 * It computes the total cost to move.
	 * @param angle
	 *        angle of the worm in which direction it will move.
	 * @return
	 *     The total cost to move.
	 *     |Cost = Math.abs(4*Math.sin(angle)+Math.abs(Math.cos(angle)));
	 */
	public double CostToMove(double angle){
		double Cost;
		Cost = Math.abs(4*Math.sin(angle)+Math.abs(Math.cos(angle)));
		return Cost;
	}
	
	
	
	/**
	 * Checks whether a worm canJump or not.
	 * if the worm is not in the direction which is greater than 0 and less than 180 degree then it will be false.
	 * |if(this.getDirection()>0 &&this.getDirection()< Math.PI)
	 * if the remaining actionPoints are less or equal to zero it will not jump.
	 * |if(this.getActionPoints()<= 0)
			return false;
	 * @return
	 *    True if and only if the both pre-conditions are true.
	 *    |if((this.canJump()))
	 *		return false;
	 *	  |if(this.getActionPoints()> 0)
	 *		return false;
	 * 
	 */
	public boolean canJump1(){
		if(!(this.canJump()))
			return false;
		if(this.getActionPoints()<= 0)
			return false;
		return true;
	}

	
	


public boolean isValidYield(int yield){
	if(yield > 0 && yield < 100)
		return true;
	return false;
}
	
	
   


public World getWor(){
	return wor;
}
	
	
	
	
	
	public double getTime(double timeStep) {
		double[] jumpStep = new double[2];
		double totalJumpTime = this.calculation(timeStep,jumpStep);
		return totalJumpTime;
	}

	
	
	public double calculation(double timeStep,double[] jumpStep){
		double xCo;
		double yCo ;
		double totalJumpTime = 0;
		do {
			jumpStep = this.getJumpStep(totalJumpTime);
			xCo = jumpStep[0];
			yCo = jumpStep[1];
			totalJumpTime += timeStep;
		} while (this.getRadius() >= Math.sqrt(Math.pow(this.getX() - xCo, 2)+ Math.pow(this.getY() - yCo, 2))|| !(this.deterMineDestination(xCo, yCo)));
		return totalJumpTime;
	}
	
	
	
	private boolean deterMineDestination(double newX, double newY) {
		boolean newDestiNationIsInWorld = this.isInWorld(newX,newY);
		boolean OnTheAdjacentPlace = this.isNewPlaceAdjacent(newX,newY);
		return (!newDestiNationIsInWorld|| OnTheAdjacentPlace);
	}
	
	
	
	
	
	public boolean isInWorld(double x, double y){
		return this.getWor().isValidLocation(x,y,this.getRadius());
	}
	
	public boolean isNewPlaceAdjacent(double x, double y){
		return this.getWor().isAdjacent(x,y, this.getRadius());
	}

	  
	public double getIntialJumptime(){
	 double jumpTime = (this.getRadius()*0.1)/this.initialVelocity();
		return jumpTime;
	  }
	

	

	public void jumpp(double timeStep){
		double tempY = 0;
	   double tempX = 0;
		double time1 = (0.1*this.getRadius())/initialVelocity();
		do
		{
		    time1 += timeStep;
			double[] tempCoordinates = getJumpStep(time1);
			tempX = tempCoordinates[0];
			tempY = tempCoordinates[1];
			this.foodToEat();
		}		while (!(this.getWor().isAdjacent(tempX,tempY,this.getRadius())) && (!this.getWor().isValidLocation(tempX,tempY,this.getRadius()) && this.getWor().isPassable(tempX, tempY)));
		if( this.getWor().isValidLocation(tempX, tempY,getRadius()))
			setHitPoints(0);
		else {
			if (this.getWor().totalDistance(getX(),getY(),tempX,tempY) > getRadius()){
				setX(tempX);
				setY(tempY);
			}
		}
	}
	
	
	
	
	
	
	

	public void foodToEat(){
		for (ListIterator<Food> iter = this.getWor().coll.listIterator(); iter.hasNext(); ) {
		    Food food = iter.next();
		    double value =this.getWor().Difference(food.getX(),food.getY(),this.getX(),this.getY());
		    if(value < this.getRadius()+food.getFoodRadius())
		    	delete.add(food);
		    this.DeleteFromTheWorld();
		}
	}
	
	
		public void DeleteFromTheWorld(){
			for (ListIterator<Food> iter = this.delete.listIterator(); iter.hasNext(); ) {
			    Food food = iter.next();
			    this.setRadius(this.getRadius()*.1);
			    this.getWor().removeFromTheWorld(food);
			   food.isActive = false;
		}
	  
		}


		public boolean canFall() {
        if(!(this.getWor().isAdjacent(this.getX(),this.getY(),this.getRadius()) &&(this.getWor().isValidLocation(this.getX(),this.getY(),this.getRadius()))))
			return true;
			return false;
		}

		
		public void Fall(){
			if(!this.canFall()){
				try {
					throw new illegalPlaceToFallException();
				} catch (illegalPlaceToFallException e) {
					e.printStackTrace();
				}
				do{
					this.setY(this.getY()-0.1);
				}while(this.canFall());
				
				
			}
			
			
		
		}


         private int counter = -1;
		public Projectile selectNextWeapon() {
			this.getWor().getActiveProjectil();
		if(counter == this.getWor().projectil.size()-1)
			counter = 0;
		counter = 0;
		Projectile weapon  = this.getWor().projectil.get(counter);
		return weapon;
     }



		public String getSelectedWeapon() {
			String Bazooka = "Bazooka";
			return Bazooka;
		}
}

	

