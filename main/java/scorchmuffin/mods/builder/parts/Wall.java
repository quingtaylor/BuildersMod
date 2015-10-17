package scorchmuffin.mods.builder.parts;

public class Wall {

	private int currentX;
	private int currentY;
	private int currentZ;
	private int height;
	private int width;
	private boolean includeDoor;

	public Wall(int x, int y, int z) {
		this.currentX = x;
		this.currentY = y;
		this.currentZ = z;
	}

	public void setSize(int height, int width) {
		this.height = height;
		this.width = width;
		
	}

	public void setIncludeDoor(boolean door) {
		this.includeDoor = door;
	}

	public void build() {

	}
}
