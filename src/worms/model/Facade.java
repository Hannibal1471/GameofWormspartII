package worms.model;

import java.util.Collection;
import java.util.Random;

/**
 * 
 *
 */
public class Facade implements IFacade{
	/**
	 * 
	 * @param x 
	 * 			 The intial X-coordinate of the created worm.
	 * @param y
	 * 			 The intial Y-coordinate of the created worm.
	 * @param direction
	 * 			 The initial direction of the created worm.
	 * @param radius
	 * 			 The initial radius of the created worm.
	 * @param name
	 * 			 The initial name of the created worm.
	 * @return 
	 * 			 The  created worms with the intial action points.
	 * 			 |	Worm worm = new Worm(x,y,direction,radius,name)
	 * 			 | 	worm.setActionPoints(worm.getInitialActionPoints())
	 */
	public Worm createWorm(double x, double y, double direction, double radius,
			String name) {
		Worm worm = new Worm(null, x,y,direction,radius,name);
		worm.setActionPoints(worm.getInitialActionPoints());
        worm.setActionPoints((int) worm.getMass());
		return worm;
	}

	/**
	 * 
	 * @param 	worm
	 *          parameter of the Worm.
	 * @param nbSteps
	 * 			| Number of steps to move
	 * 
	 * @return 	True if worm is going to be able to move, false if the give actionpoints are less than the required actionpoints.
	 * 			| result == (givenActionPoints > requiredActionPoints)
	 * 			| (worm.getActionPoints() - nbSteps) > 0
	 */
	 //@Basic
	public boolean canMove(Worm worm, int nbSteps) {
		return worm.canMove(nbSteps);
	}

	
	
	
/**
 * 
 * @param 	worm
 *           Parameter of the Worm.
 * @param 	nbSteps
 * 		  	Number of steps to change the position of the worm.
 * @pre		The worm should have sufficient action points in order to perform the move. 
 * 			| Worm.getActionPoints()>0
 * @throws ModelException if the number of ActionPoints reach zero or less
 *          | !this.canMove()
 * @post	The worm moves the number of given steps
 * 			| Worm.move(nbSteps)	
 */
	public void move(Worm worm, int nbSteps)  {
		if(canMove(worm,nbSteps)==true){
			worm.move(nbSteps);}
		    else{
	        throw new ModelException("Can,t move.");}
	}

	/**
	 * 
	 * @param worm...
	 *          Parameter of the Worm.
	 * @param angle...
	 * 			angle the worm is supposed to turn.
	 * @return....
	 * 			true if and only if current actionpoints are greater than the required actionpoints to turn.
	 * 			| result == ( requiredActionPoints>0)
	 * 
	 */
   //@Basic
	public boolean canTurn(Worm worm, double angle) {
		double angleInRadiants = (angle);
		double fraction = 2*Math.PI/angleInRadiants; 
		int requiredActionPoints =(int) Math.ceil(worm.getActionPoints()-(60/(fraction)));
		if(worm.getActionPoints()>0) 
		{	
			return true;
		}
		
		return false;
	}

	/**
	 * 
	 * @param worm
	 * @param angle
	 * 				 angle the worm is supposed to turn in
	 * @pre 		
	 * 				 if the given angle is valid, than the worm turns in the given angle.
	 * 				|	canTurn(worm,angle)
	 * @post		
	 * 				 the worm changes its direction according to the given angle.
	 * 				|	worm.turn(angle)
	 */
	public void turn(Worm worm, double angle) {
		if(canTurn(worm,angle)==true)
		{
		worm.turn(angle);	
		}
		else
		{
			throw new ModelException("Not Enough Action points");
		}
	}

	
	
	/**
	 * 
	 * @param worm
	 *        The parameter of the worm class.
	 * @pre...
	 *      Worms should have valid action points to jump.
	 *      |worm.hasValidActionPoints().
	 * @pre...
	 *      Worm should jump jump in a legal direction.
	 *      |(worm.canJump().
	 * @post...
	 *      Worms will change their position,s by jumping from one place to other.
	 *      |worm.Jump().
	 * @post...
	 *      if the worms jump the action point will be consumed.
	 *      |worm.setActionPoints(jumpCost).
	 * @throws...
	 *      if worms do not have enough action points are if they have an illegal angle 
	 *      it throws a new Model Exception.
	 *      |throw new ModelException("Action points are zero").
	 *  
	 */
	public void jump(Worm worm) {		
		if( worm.hasValidActionPoints()&&(worm.canJump())){
		worm.Jump();
		int jumpCost = worm.getActionPoints()-worm.getActionPoints();
		worm.setActionPoints(jumpCost);
		}
		else
		{
			throw new ModelException("Action points are invalid");
		}
	}
	
	
	/**
	 * It gives the jump time.
	 * @post...
	 *     return the jumpTime of the worm.
	 *     |return worm.getJumpTime().
	 */
	public double getJumpTime(Worm worm) 
	{
		if(worm.canJump())
          return worm.getJumpTime(1);
		return 0;
	}

	
	/**
	 *  Returns the jump steps.
	 *  @pre...
	 *        The worm should have the valid action points.
	 *        |worm.hasValidActionPoints()
	 *  @post...
	 *        The worm will return,s the jump steps back.
	 *        |result = (worm.getJumpStep(t).
	 * @throws...
	 *        if the worms do not have valid action point it will throw a new ModelException.
	 *        |throw new ModelException(" invalid Action Points");
	 */
	public double[] getJumpStep(Worm worm, double t) {
			System.out.println(worm.getHitPoint());
			System.out.println(worm.getActionPoints());
		return worm.getJumpStep(t);
	
	
	
	}
	
	
/**
 *  Get,s the current position in the x-direction of the given worm
 * @param worm
 * @return.....
 * 			return the X-coordinate of the worm
 * 			| worm.getX()
 */
	public double getX(Worm worm) {
		return worm.getX();
	}

	/**
	 * get the current position in the y-direction of the given worm
	 * @param worm
	 * @return
	 * 				return the y-coordinate of the worm
	 * 				| worm.getY()
	 */
	public double getY(Worm worm) {
		return worm.getY();
	}

	
	
	
	/**
	 * To get the current orientation of the given worm
	 * @param worm
	 * @return
	 * 	    return the current direction of the worm according to the angle.
	 * 	    | worm.getDirection()
	 */
	public double getOrientation(Worm worm) {
		return worm.getDirection();
	}

	
	
	
	/**
	 * 
	 * @param worm
	 * @return
	 * 				The radius of the worm
	 * 				| worm.getRadius()
	 */
	public double getRadius(Worm worm) {
		return worm.getRadius();
	}
	
	
	

	/**
	 * 
	 * @param worm
	 * @param newRadius
	 * 			Set the current radius to the given radius.
	 * @pre			
	 * 			checks if the given radius is valid,
	 * 			|worm.isValidRadius(newRadius)
	 * @post
	 * 		    the radius will be updated to the given radius if the pre-condition is true
	 *          |worm.setRadius(newRadius)
	 * @throws ...
	 *          if the precondition is not true then it throws the model exception
	 *          |throw new ModelException("Invalid Radius").
	 */					
	public void setRadius(Worm worm, double newRadius) {
	if(worm.isValidRadius(newRadius))
		worm.setRadius(newRadius);
	else
		throw new ModelException("Invalid Radius");
	}

	
	
	
	
	/**
	 * To get the minimal radius.
	 * @param worm
	 * @return   The minimal radius of the worm.
	 *            |minimalRadius >=0.25.
	 *      
	 */
	public double getMinimalRadius(Worm worm) {
		double minimalRadius = 0.25;
		return minimalRadius;
	}

	
	
	
   /**
    * To get the curent actionpoints of the worm.
    * @param worm
    * @return
    *       The Action poits of the worm.
    *       | worm.getActionPoints().
    */
	public int getActionPoints(Worm worm) {
		return worm.getActionPoints();
	}
	
	
	

	/**
	 * To get the maximum action points according to the worm,s mass.
	 * @param worm
	 * @return
	 *       The maximum Action points of the worm.
	 *       |worm.getInitialActionPoints().
	 */
	public int getMaxActionPoints(Worm worm) {
		return (int) worm.getInitialActionPoints();
	}

	
	
	/**
	 * To get the name of the worm.
	 * @param worm
	 * @return
	 *       The name of the worm.
	 *        |worm.getName().
	 *     
	 */
	public String getName(Worm worm) {
		return worm.getName();
	}
	
	
	
	

	/**
	 * To reset the name of the worm but some conditions should be true.
	 * @param worm
	 * @param newName
	 *         To set the name of the worm.
	 * @pre ...
	 *         The new name should be a valid name.
	 *         |worm.isValidName(newName).
	 * @post ..
	 *         The postcondition will be true if the pre condition is true. it gives the new name to the worm.
	 *         |worm.setName().
	 *         
	 * @throws ...
	 *         if the pre-condition is false it throws a Model exception for entering the invalid name.
	 *         |throw new ModelException("Invalid name").
	 *      
	 */
	public void rename(Worm worm, String newName) 
	{
		if(worm.isValidName(newName)){
				worm.setName(newName);}
		else{
			throw new ModelException("Invalid name");}
	}
	
	
	/**
	 * To get the mass of the worm.
	 * @param worm
	 * @return
	 *       The mass of the worm.
	 *       |worm.getMass().
	 */
public double getMass(Worm worm) {
		return worm.getMass();
	}


	@Override
	public void addEmptyTeam(World world, String newName) {
		// TODO Auto-generated method stub
		world.createTeam(newName);
	}

	@Override
	public void addNewFood(World world) {
		// TODO Auto-generated method stub
		world.addFood();
		
	}

	@Override
	public void addNewWorm(World world) {
		// TODO Auto-generated method stub
		world.addNewWorm();
	}

	@Override
	public boolean canFall(Worm worm) {
		// TODO Auto-generated method stub
		return worm.canFall();
	}

	@Override
	public boolean canMove(Worm worm) {
		// TODO Auto-generated method stub
		return worm.canMove(1);
	}

	@Override
	public Food createFood(World world, double x, double y) {
		// TODO Auto-generated method stub
		return world.createFood(x,y);
	}

	@Override
	public World createWorld(double width, double height,
			boolean[][] passableMap, Random random) {
    World world = new World(width, height, passableMap, random);
		return world;
	}

	@Override
	public Worm createWorm(World world, double x, double y, double direction,
			double radius, String name) {
		// TODO Auto-generated method stub
		return world.createWorm(world, x, y, direction, radius, name);
	}

	@Override
	public void fall(Worm worm) {
		// TODO Auto-generated method stub
	worm.Fall();	
		
	}

	@Override
	public Projectile getActiveProjectile(World world) {
		// TODO Auto-generated method stub
		return world.getActiveProjectil();
	}

	@Override
	public Worm getCurrentWorm(World world) {
		// TODO Auto-generated method stub
		return world.getCurrentWorm();
	}

	@Override
	public Collection<Food> getFood(World world) {
		// TODO Auto-generated method stub
		return world.getFoodCollection();
	}

	@Override
	public int getHitPoints(Worm worm) {
		// TODO Auto-generated method stub
		return (int) worm.getHitPoint();
		}

	@Override
	public double[] getJumpStep(Projectile projectile, double t) {
		// TODO Auto-generated method stub
		return projectile.getJumpStepProjectile(t);
	}

	@Override
	public double getJumpTime(Projectile projectile, double timeStep) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getJumpTime(Worm worm, double timeStep) {

		return worm.getTime(timeStep);
	}

	@Override
	public int getMaxHitPoints(Worm worm) {
		// TODO Auto-generated method stub
		
		return (int) worm.getMass();
		
	}

	@Override
	public double getRadius(Food food) {
		// TODO Auto-generated method stub
		return food.getFoodRadius();
	}

	@Override
	public double getRadius(Projectile projectile) {
		// TODO Auto-generated method stub
		return projectile.getRadius();
	}

	@Override
	public String getSelectedWeapon(Worm worm) {
		// TODO Auto-generated method stub
		return worm.getSelectedWeapon();
	}

	@Override
	public String getTeamName(Worm worm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getWinner(World world) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Worm> getWorms(World world) {
		// TODO Auto-generated method stub
		return world.getCollection();
	}

	@Override
	public double getX(Food food) {
		// TODO Auto-generated method stub
		return food.getX();
	}

	@Override
	public double getX(Projectile projectile) {
		// TODO Auto-generated method stub
		return projectile.getRadius();
	}

	@Override
	public double getY(Food food) {
		// TODO Auto-generated method stub
		return food.getY();
	}

	@Override
	public double getY(Projectile projectile) {
		// TODO Auto-generated method stub
		return projectile.getY();
	}

	@Override
	public boolean isActive(Food food) {
		// TODO Auto-generated method stub
		return food.isActive();
	}

	@Override
	public boolean isActive(Projectile projectile) {
		return projectile.isActive();
	}

	@Override
	public boolean isAdjacent(World world, double x, double y, double radius) {
		return world.isAdjacent(x,y,radius);
	}

	@Override
	public boolean isAlive(Worm worm) {
		return worm.isAlive();
	}

	@Override
	public boolean isGameFinished(World world) {
		return world.isGameFinished();
	}

	@Override
	public boolean isImpassable(World world, double x, double y, double radius) {
		// TODO Auto-generated method stub
		return  world.isPassable(x,y);
	}

	@Override
	public void jump(Projectile projectile, double timeStep) {
		projectile.jumpp(timeStep);
	}

	@Override
	public void jump(Worm worm, double timeStep) {
		// TODO Auto-generated method stub
		System.out.println("hhhh");
		worm.jumpp(timeStep);
	}

	@Override
	public void move(Worm worm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectNextWeapon(Worm worm) {
		worm.selectNextWeapon();
	}

	@Override
	public void shoot(Worm worm, int yield) {
      //   worm.shoot(yield);
	
	}

	@Override
	public void startGame(World world) {
		// TODO Auto-generated method stub
		world.startGame();
	}

	@Override
	public void startNextTurn(World world) {
		// TODO Auto-generated method stub
		world.NextTurn();
	}

}
