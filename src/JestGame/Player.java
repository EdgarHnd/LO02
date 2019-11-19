package JestGame;

public abstract class Player {
	private String name;
	private CardsCollection hand;
	private CardsCollection jest;
	private int finalScore;
	private int finalBoolean;
	private boolean hasPlayed;
	
	public String getName() {
		return name;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public Player (String name) {
		this.name = name;
	}
	
	abstract void makeOffer();
	
	abstract void pickOffer();
	
	public static void main(String[] args) {
			
		
		
	}
	

}
