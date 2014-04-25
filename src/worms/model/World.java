package worms.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.Random;

public class World  {
	private double width;
	private double height;
	private boolean[][] passable;
	private Random random;
    ArrayList<Worm> collection ;
    private double radius;
    ArrayList<Food> coll;
    private Object worms;
    private ArrayList teams;
    private int j = 0;
    private int iterator = 0;
    boolean StartGame = false;

	public World(double width, double height, boolean[][] passableMap, Random random) {
		assert(this.isValidheight(height));
		this.setHeight(height);
		assert(this.isValidWidth(width));
		this.setWidth(width);
		this.passable = Arrays.copyOf(passableMap,passableMap.length);
		this.random = random;
		collection = new ArrayList(); 
		coll = new ArrayList();
		teams = new ArrayList<>();
	}
	
	
	
	
	

	
	
	
	
	public void setHeight(double height){
		this.height = height;
	}
	
	public void setWidth(double width){
	this.width = width;
	}
	
   public double getHeight(){
	   return this.height;
   }
   
   public double getWidth(){
	   return this.width;
   }
   
   
   public boolean isValidheight(double height){
	   if(height > 0 && height < Double.MAX_VALUE)
		   return true;
	   else
		   return false;
   }
   
   
   public boolean isValidWidth(double width){
	   if(width > 0 && width < Double.MAX_VALUE)
		   return true;
	   else
		   return false;
   }
   
   

   
   
   public void setRandom(Random random){
	   this.random = random;
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
	
   
  
   int k = 0;
      public Worm createWorm(World world, double x, double y, double direction, double radius, String name){
	   Worm worm = new Worm(this,x, y, direction, radius, name);
		worm.setActionPoints((int)worm.getMass());
		worm.setHitPoints((int)worm.getMass());
		worm.setMaxHitPoint((int)worm.getMass());
		worm.setActionPoints(worm.getInitialActionPoints());
		 this.collection.add(worm);
		 worms = this.collection.get(k);
	  return worm;
   }
      
      Worm worm;
      public Worm getCurrentWorm(){
    	  worm = this.getCollection().get(iterator);
    	  return worm;
      }
      
   
   public ArrayList<Worm>getCollection(){
	return this.collection;
   }
  
   
   

   private boolean [][] array;
   public Worm addWorm()
   {

	  double x = this.getWidth()/6 + ((this.getWidth()-this.getWidth()/6) - this.getWidth()/6) * random.nextDouble();
	   double y = this.getHeight()/6 + ((this.getHeight()-this.getHeight()/6) -this.getHeight()/6) * random.nextDouble();
	   double direction = Math.PI/2.0;
	   double radius = 0.30;
	   this.setX(x);
	   this.setY(y);
	if(this.isAdjacent(x,y,radius)){
          return   this.createWorm(this, x,y,direction,radius,"name");
	}
    return   this.addWorm();
   }

 
   
  
 
   
   private double x;
   public void setX(double x){
	   this.x = x;
   }
   
   public double getX(){
	   return x;
   }
   
   private double y;
   public void setY(double y){
	   this.y = y;
   }
   
   public double getY(){
	   return y;
   }
   

   
  
   

	   
   
   public boolean isPassable(double x, double y){
	   double xCo;
	   double yCo;
	   for(int k = 0; k < 360;k++){
		   xCo = radius * Math.cos(Math.toRadians(k));
		   yCo = radius * Math.sin(Math.toRadians(k));
		   int [] circlePixels = getCirclePixels(x+xCo, y+yCo);
		   int variable = circlePixels[0];
		   int variableY = circlePixels[1];
		   if(this.passable[variableY][variable] == true){
			   return true;
		   }
	   }
	return false;
   }
   
   
   
   
  public int[] getCirclePixels(double x, double y){
	  int [] array = this.ConvertToPixels(x, y);
	return array;
	  
  }
  
  public double getLength(){
	  return this.passable.length;
  }
   
   public double getWidthPerPixel(){
	   return this.passable[0].length;
   }
   
  
  public static double Difference(double x,double y,double newX,double newY){
		double value = Math.sqrt(Math.pow((newX - x), 2) + Math.pow((newY -y), 2));
		return value;
	}
  
   
   
  
 
   
   private int[] ConvertToPixels(double x, double y) {
		double YCo = this.getHeight() / this.getLength();
		double xCo = this.getWidth() / this.getWidthPerPixel();
		int Pixels = (int) (x / xCo);
		int yResult = (int) ((this.getHeight() - y) / YCo);
		int[] result = new int[2];
		result[0] = Pixels;
		result [1]= yResult;
		return result;
	}
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   public Food createFood(double x , double y){
	   Food food = new Food(x,y);
	   this.coll.add(food);
	   return food;
   }
   
   
   
   
   
   
   public boolean isAdjacent(double x, double y, double radius) {
	   double xCo;
	   double yCo;
		for (int i = 0; i < 360; i++) {
			xCo = radius * Math.cos(Math.toRadians(i));
			yCo = radius * Math.sin(Math.toRadians(i));
			int[] arr = ConvertToPixels(x + xCo * 0.1, y+ yCo*0.1);
			int[] arrSecond = ConvertToPixels(x + xCo, y+ yCo);
			int variable = arr[0];
			int varaiableSecond = arr[1];
			int var = arrSecond[0];
			int vr = arrSecond[1];
			if (passable[vr][var]&& !(passable[varaiableSecond][variable])) 
				return true;
		}
		return false;
	}
 
   
		
   
   public ArrayList<Food> getFoodCollection(){
	   return this.coll;
   }
 
   public Food addFood(){
	   double x = this.getWidth()/6 + ((this.getWidth()-this.getWidth()/6) - this.getWidth()/6) * random.nextDouble();
	   double y =  this.getHeight()/6 + ((this.getHeight()-this.getHeight()/6) -this.getHeight()/6) * random.nextDouble();
	   double [] array = this.getAdjacnet();
	   double xCo = array[0];
	   double yCo = array[1];
	   return this.createFood(xCo,yCo);
	 }


   

   
public boolean isImpassable(double x, double y, double radius) {
	 double xCo;
	   double yCo;
	   for(int k = 0; k < 360;k++){
		   xCo = radius * Math.cos(Math.toRadians(k));
		   yCo = radius * Math.sin(Math.toRadians(k));
		   int [] circlePixels = getCirclePixels(x+xCo, y+yCo);
		   int variable = circlePixels[0];
		   int variableY = circlePixels[1];
		   if((!this.passable[variableY][variable])){
			   return true;
		   }
	   }
	return false;
}





	

public void createTeam(String name){
	if(this.teams.size()<10){
		Team team = new Team(name);
		this.teams.add(team);
		
		
	}
}

















public boolean isGameFinished() {
if(this.collection.isEmpty())
	return true;
if(this.getCollection().size()==1)
	return true;
return false;
}




















public void removeWormFromWorld(Worm worm) {
	this.collection.remove(worm);
}



public double totalDistance(double x1, double y1,double x2,double y2){
	double totalDistance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 -y1), 2));
	return totalDistance;
	
}




	public double getNumberOfWorms(){
		int NumberOfWorm = this.collection.size();
		return NumberOfWorm;
	}


   public double getMinimumRadius(){
	   return 0.25;
   }
	
	
   public double getRandomRadius(){
	   double radius = this.getMinimumRadius() + ((0.75)-this.getMinimumRadius()) - this.getMinimumRadius() * random.nextDouble();
          return radius;
   }
   

   public Worm addNewWorm(){
	   double[] array = this.getAdjacentPlace();
	   double xCo = array[0];
	   double yCo = array[1];
	   double angle = array[2];
	   if(!(this.getCheck()<0)){
		   return this.createWorm(this,xCo,yCo,angle,this.getRandomRadius(), "Worm");
	   }
	   else
		   return this.addNewWorm();
   }
   
   

public double[] getAdjacentPlace(){
	double [] array = this.getAdjacnet();
	double toCheck = array[0];
	this.setCheck(toCheck);
	return array;
}






public double getCenterOfTheWidth(){
	return this.getWidth()/2;
}



public double getCenterOfTheHeight(){
	return this.getHeight()/2;
}

private double toCheck;
public void setCheck(double toCheck){
	this.toCheck = toCheck;
}

public double getCheck(){
	return this.toCheck;
}


private double[] getAdjacnet() {
	double radius = this.getRandomRadius();
	Random rand = new Random();
	double xCo = 0;
     double yCo = 0;
	double Angle;
	double x;
	double y;
	int min = 1;
	int max= 4;
	double centerOfTheYco = this.getCenterOfTheHeight();
	double centerOfxCo = this.getCenterOfTheWidth();
	int Generated = rand.nextInt(max) + min;
	if (Generated == 1 || Generated == 2) { 
		xCo = (this.getWidth() - 3.0 * radius) * rand.nextDouble()+ radius * 1.5;
		if (Generated == 1) 
			yCo = this.getHeight() - radius * 1.5;
		if (Generated == 2) 
			yCo = radius * 1.5;
	} else if (Generated == 3 || Generated == 4) { 
		yCo = (this.getHeight() - 3.0 * radius) * rand.nextDouble()
				+ radius * 1.5;
		if (Generated == 3)
			xCo = radius * 1.5;
		if (Generated == 4) 
			xCo = this.getWidth() - radius * 1.5;
	}
	
	
	
	
	
	
	
	
	x = xCo;
	y = yCo;
	if (yCo >= centerOfTheYco && xCo >= centerOfxCo) { 
		Angle = Math.PI+ Math.atan((yCo - centerOfTheYco) / (xCo - centerOfxCo));
		while (x >= centerOfxCo && y >= centerOfTheYco) {
			if (this.isAdjacent(x, y, radius)) {
				double[] result = { x, y, Angle };
				return result;
			} else {
				x += Math.cos(Angle) * 0.1 * radius;
				y += Math.sin(Angle) * 0.1 * radius;
			}
		}
	} else if (yCo >= centerOfTheYco && xCo <= centerOfxCo) { 
		Angle = (3.0 / 2.0) * Math.PI
				+ Math.atan((centerOfxCo - xCo) / (yCo - centerOfTheYco));
		while (x <= centerOfxCo && y >= centerOfTheYco) {
			if (this.isAdjacent(x, y, radius)) {
				double[] result = { x, y, Angle };
				return result;
			} else {
				
				x += Math.cos(Angle) * 0.1 * radius;
				y += Math.sin(Angle) * 0.1 * radius;
			}
		}
	} else if (yCo <= centerOfTheYco && xCo <= centerOfxCo) { 
		Angle = Math.atan((centerOfTheYco - yCo)
				/ (centerOfxCo - xCo));
		while (x <= centerOfxCo && y <= centerOfTheYco) {
			if (this.isAdjacent(x, y, radius)) {
				double[] result = { x, y, Angle };
				return result;
			} else {
				x += Math.cos(Angle) * 0.1 * radius;
				y += Math.sin(Angle) * 0.1 * radius;
			}
		}
	} else if (yCo <= centerOfTheYco && xCo >= centerOfxCo) { 
		Angle = (Math.PI / 2)
				+ Math.atan((xCo - centerOfxCo) / (centerOfTheYco - yCo));
		while (x >= centerOfxCo && y <= centerOfTheYco) {
			if (this.isAdjacent(x, y, radius)) {
				double[] result = { x, y, Angle };
				return result;
			} 
			else
			{
				x += Math.cos(Angle) * 0.1 * radius;
				y += Math.sin(Angle) * 0.1 * radius;
			}
		}
	}
	return this.getAdjacnet();
   }





public void NextTurn(){
	iterator = iterator+1;
	if(iterator > this.collection.size()-1){
		iterator = 0;
	}
	Worm atTurn =this.collection.get(iterator);
	this.setTurnPoints(atTurn);
}



public void setTurnPoints(Worm worm){
	if(isValidPoints(10)){
		worm.setHitPoints((int) (worm.getHitPoint()+10));
	}
	else
		worm.setHitPoints((int)worm.getHitPoint());
}




public boolean isValidLocation(double x, double y,double radius){
	if(isProperPlace(x, y, radius))
		return true;
	else
		return false;
}










public boolean isProperPlace(double x,double y,double radius){
	if( ( (x - radius < 0) || (x + radius > getWidth()) || (y - radius < 0) 
			|| (y+radius > getHeight()) || Double.isNaN(x) || Double.isNaN(y) ))
		return true;
	return false;
}


private Food food;
public void removeFromTheWorld(Food food) {
	this.remove(food);
}

public void remove(Food food){
	food.isActive = false;
	food = null;
}








public boolean isValidPoints(int points){
	if(worm.getHitPoint()+10>worm.getMaxHitPoints())
		return false;
	return true;
}


public void startGame(){
	this.NextTurn();
}







private Projectile projectile;
ArrayList<Projectile> projectil = new ArrayList<Projectile>();

public Projectile getActiveProjectil() {
  projectil.add(new Projectile(this.worm, this.worm.getX(), this.worm.getY(), this.worm.getDirection(), "Rifle"));
  projectil.add(new Projectile(this.worm,this.worm.getX(),this.worm.getY(),this.worm.getDirection(),"Bazooka"));
	return checkActiveProjectil();
 }

Projectile counter;
public Projectile checkActiveProjectil(){
	return counter;	
	}
}



	



