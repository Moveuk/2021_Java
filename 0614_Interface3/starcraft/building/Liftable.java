package starcraft.building;

public interface Liftable {
	void liftoff();
	void move(int x,int y);
	void stop();
	void land();
	
}
