package starcraft.building;

public class Barrack extends Building implements Liftable{

	LiftableImpl lf = new LiftableImpl();
	
	@Override
	public void liftoff() {
		lf.liftoff();
	}

	@Override
	public void move(int x, int y) {
		lf.move(x, y);
	}

	@Override
	public void stop() {
		lf.stop();
	}

	@Override
	public void land() {
		lf.land();
	}

}
