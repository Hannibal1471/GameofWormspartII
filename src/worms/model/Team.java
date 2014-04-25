package worms.model;

import java.util.ArrayList;




public class Team {
	private ArrayList<Worm> worms;
	private ArrayList<Worm> living;

	public Team(String name) {
		if(isValidName(name))
			this.setName(name);
		worms = new ArrayList<Worm>();	
		living = new ArrayList<Worm>();
	}
	
	
	public boolean isValidName(String name){

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
	
	private String name;
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void addWorm(Worm worm){
		this.worms.add(worm);
	}
	
	public ArrayList<Worm> LivingWorms(){
		for(Worm checkedWorm: this.worms)
			if(checkedWorm.isAlive())
				living.add(checkedWorm);
		return living;
	}
	
	

}
