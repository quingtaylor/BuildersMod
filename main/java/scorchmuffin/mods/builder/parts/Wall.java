package scorchmuffin.mods.builder.parts;

import scorchmuffin.mods.builder.ScorchUtils.Direction;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public abstract class Wall {

	public enum Axis {
		AXIS_X, AXIS_Z
	};

	private int currentHoriz;
	private int currentY;
	private int currentConstPlane;
	private int initialHoriz;
	private int initialY;
	private int initialZ;
	private int height;
	private int width;
	private int effWidth;

	private int windowWidth;
	private int sectionHeight;
	private int spacerWidth;

	private boolean includeDoor;
	private boolean includeWindow;
	private Block windowBlock;
	private Block baseBlock;
	private Block columnBlock;
	private Block insideColumnBlock;
	private Block wallBlock;
	private World world;
	private Axis axis;
	private int vert;
	private int horiz;
	private BuilderUtils builder;

	protected Wall(int horiz, int vert, int constPlane, World world, Axis axis, BuilderUtils builder) {
		this.initialHoriz = horiz;
		this.initialY = vert;
		this.horiz = horiz;
		this.vert = vert;
		this.currentHoriz = 0;
		this.currentY = 0;
		this.currentConstPlane = constPlane;
		this.world = world;
		windowBlock = Blocks.glass;
		baseBlock = Blocks.brick_block;
		columnBlock = Blocks.log;
		insideColumnBlock = Blocks.stonebrick;
		wallBlock = Blocks.stonebrick;
		// wallBlock = Blocks.air;
		spacerWidth = 1;
		this.axis = axis;
		this.builder = builder;
	}

	public void setSize(int width, int height) {
		this.height = height;
		this.width = width;

		// equation includes the current position (x is 4, width is 3, final x
		// will be 6 (block at 4, 5 and 6)
		// max cannot ever be achieved, all values must be kept less than max
		if ((int)Math.floor(height / 5) >= 2) {
			sectionHeight = 5;
			windowWidth = 5;
		} else if ((int)Math.floor(height / 4) >= 2) {
			sectionHeight = 4;
			windowWidth = 4;
		} else if (height > 4) {
			sectionHeight = 4;
			windowWidth = 4;
		} else {
			sectionHeight = 3;
			windowWidth = 3;
		}
	}

	public void setIncludeDoor(boolean door) {
		this.includeDoor = door;
	}

	public void setIncludeWindow(boolean includeWindow) {
		this.includeWindow = includeWindow;
	}

	public void build() {

		// effective width is width - 2, since we have outside columns
		effWidth = width - 2;
		int sectionWidth = windowWidth + (2 * spacerWidth);
		int numSections = (int) Math.floor(effWidth / sectionWidth);
		int remainder = effWidth - (sectionWidth * numSections);
		int lRemainder = (int) Math.floor(remainder / 2);
		int rRemainder = remainder - lRemainder;

		// windows and doors, these are the middle parts of the wall
		while (currentY + sectionHeight <= height) {
			buildOutsideColumn();
			buildCol(lRemainder, wallBlock);
			if (includeWindow) {
				for (int i = 0; i< numSections; i++) {
					buildWindowSpacer();
					buildWindow();
					buildWindowSpacer();
				}
			}
			buildCol(rRemainder, wallBlock);
			buildOutsideColumn();
			prepareForNextRow(sectionHeight);
		}
		// check for a remainder at the top
		while (currentY < height) {
			buildBaseRow();
			prepareForNextRow(1);
		}
	}
	
	private void buildCol(int width, Block b) {
		int i;
		for (i = 0; i < width; i++) {
			for (int j = 0; j < sectionHeight; j++) {
				setBlock(i + currentHoriz, j + currentY, b);
			}
		}
		currentHoriz = currentHoriz + i;
	}

	private void setBlock(int horiz, int vert, Block block) {
		if (axis == Axis.AXIS_X)
			builder.build(horiz, vert, 0, block);
		else if (axis == Axis.AXIS_Z)
			builder.build(0, vert, horiz, block);
	}

	private void buildOutsideColumn() {
		for (int j = 0; j < sectionHeight; j++) {
			setBlock(currentHoriz, currentY + j, columnBlock);
		}
		currentHoriz++;
	}

	private void buildBaseRow() {
		setBlock(currentHoriz++, currentY, columnBlock);
		while (currentHoriz < width -1 ) {
			setBlock(currentHoriz, currentY, baseBlock);
			currentHoriz++;
		}
		setBlock(currentHoriz++, currentY, columnBlock);
	}

	private void buildWindow() {
		if (currentHoriz + windowWidth >= width)
			return;
		int i;

		for (i = 0; i < windowWidth; i++) {
			for (int j = 0; j < sectionHeight; j++) {
				if (i == 0 || i + 1 == windowWidth || j == 0
						|| j + 1 == sectionHeight)
					setBlock(i + currentHoriz, j + currentY, Blocks.coal_block);
				else
					setBlock(i + currentHoriz, j + currentY, windowBlock);
			}
		}
		currentHoriz = currentHoriz + i;
	}

	private void buildWindowSpacer() {
		if (currentHoriz + spacerWidth >= width)
			return;
		int i;
		for (i = 0; i < spacerWidth; i++) {
			for (int j = 0; j < sectionHeight; j++) {
				setBlock(i + currentHoriz, j + currentY, wallBlock);
			}
		}
		currentHoriz = currentHoriz + i;
	}

	private void prepareForNextRow(int sectionHeight) {
		currentY = currentY + sectionHeight;
		currentHoriz = 0;
	}
}
