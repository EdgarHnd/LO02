package JestGame;

public class VirtualPlayer extends Player {


	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public String getName() {
		return name;
	}
	
	public VirtualPlayer(String name, int i) {
		super(name,i);
		
	}

	public int getNb() {
		return nb;
	}

	public void makeOffer() {
		
	}
	@Override
	public void pickOffer() {

	}


}
