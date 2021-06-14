package starcraft.building;

public class LiftableImpl implements Liftable {

	@Override
	public void liftoff() {
		System.out.println("ÅÍº¸±â´É ÀåÂø! ÀÌ·úÇÑ´Ù.");
	}

	@Override
	public void move(int x, int y) {
		System.out.println("¿òÁ÷ÀÎ´Ù.");
	}

	@Override
	public void stop() {
		System.out.println("¸ØÃá´Ù.");
	}

	@Override
	public void land() {
		System.out.println("Âø·úÇÑ´Ù.");
	}

}
