package JestGame;

class AppliedEffect implements Effect {
	
	protected String name;
	
	public AppliedEffect(String n) {
		this.name = n;
		
	}
	@Override
	public Effect apply(Effect e) {
		return e;
		
	}
	public static void createEffect() {
		Effect hightest = new AppliedEffect("Hightest");
	}
}
