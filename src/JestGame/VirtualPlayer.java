package JestGame;

public class VirtualPlayer extends Player {

	private String name;
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public String getName() {
		return name;
	}
	
	public VirtualPlayer(String name) {
		super(name);
		this.name = name;
	}

	void makeOffer() {
		
	}
	@Override
	void pickOffer(Card crd) {

	}

	void pickOffer() {
		
	}

}
