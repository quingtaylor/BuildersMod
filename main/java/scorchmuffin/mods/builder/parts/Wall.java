package scorchmuffin.mods.builder.parts;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public abstract class Wall {

	public enum Axis {
		AXIS_X, AXIS_Y, AXIS_Z
	};

	private int currentHoriz;
	private int currentY;
	private int currentConstPlane;
	private int initialHoriz;
	private int initialY;
	private int initialZ;
	private int height;
	private int width;

	private int windowWidth;
	private int sectionHeight;
	private int maxHoriz;
	private int maxY;
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

	protected Wall(int horiz, int vert, int constPlane, World world, Axis axis) {
		this.initialHoriz = horiz;
		this.initialY = vert;
		this.currentHoriz = horiz;
		this.currentY = vert;
		this.currentConstPlane = constPlane;
		this.world = world;
		windowBlock = Blocks.glass;
		baseBlock = Blocks.brick_block;
		columnBlock = Blocks.log;
		insideColumnBlock = Blocks.stonebrick;
//		wallBlock = Blocks.stonebrick;
		wallBlock = Blocks.air;
		spacerWidth = 2;
		this.axis = axis;
	}

	public void setSize(int width, int height) {
		this.height = height;
		this.width = width;

		this.maxHoriz = initialHoriz + width;
		this.maxY = initialY + height;

		if (height > 3)
			sectionHeight = height - 2;
		else
			sectionHeight = 0;

		if (width > 5 && sectionHeight < width - 2)
			windowWidth = sectionHeight;
		else if (width > 6)
			windowWidth = 3;
		else if (width > 5)
			windowWidth = 2;
		else
			windowWidth = 0;
	}

	public void setIncludeDoor(boolean door) {
		this.includeDoor = door;
	}

	public void setIncludeWindow(boolean includeWindow) {
		this.includeWindow = includeWindow;
	}

	public void build() {

		// first row is the base (above the floor)
		// buildBaseRow();
		// prepareForNextRow(1);

		// windows and doors, these are the middle parts of the wall
		while (currentY < maxY - 1) {
			buildOutsideColumn();
			buildInsideColumn();
			if (includeWindow) {
				while (currentHoriz + windowWidth + spacerWidth < maxHoriz) {
					buildWindow();
					buildWindowSpacer();
				}
			}
			buildRemainder(world);
			buildInsideColumn();
			buildOutsideColumn();
			prepareForNextRow(sectionHeight);
		}
		buildBaseRow();
	}

	private void setBlock(int horiz, int vert, Block block) {
		if (axis == Axis.AXIS_X)
			world.setBlock(horiz, vert, currentConstPlane, block);
		else if (axis == Axis.AXIS_Z)
			world.setBlock(currentConstPlane, vert, horiz, block);
		else
			world.setBlock(horiz, currentConstPlane, vert, block);
	}

	private void buildOutsideColumn() {
		for (int j = 0; j < sectionHeight; j++) {
			setBlock(currentHoriz, currentY + j, columnBlock);
		}
		currentHoriz++;
	}

	private void buildInsideColumn() {
		for (int j = 0; j < sectionHeight; j++) {
			setBlock(currentHoriz, currentY + j, insideColumnBlock);
		}
		currentHoriz++;
	}

	private void buildBaseRow() {
		while (currentHoriz <= maxHoriz) {
			setBlock(currentHoriz, currentY, baseBlock);
			currentHoriz++;
		}
	}

	private void buildWindow() {
		if (currentHoriz + windowWidth >= maxHoriz)
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
		if (currentHoriz + windowWidth >= maxHoriz)
			return;
		int i;
		for (i = 0; i < spacerWidth; i++) {
			for (int j = 0; j < sectionHeight; j++) {
				setBlock(i + currentHoriz, j + currentY, wallBlock);
			}
		}
		currentHoriz = currentHoriz + i;
	}

	/*
	 * Build what is left after a section is built, from where we are to the end
	 * of the width
	 */
	private void buildRemainder(World world) {
		int i;
		for (i = currentHoriz; i <= maxHoriz - 2; i++) {
			for (int j = 0; j < sectionHeight; j++) {
				setBlock(i, j + currentY, wallBlock);
			}
		}
		currentHoriz = i;
	}

	private void prepareForNextRow(int sectionHeight) {
		currentY = currentY + sectionHeight;
		currentHoriz = initialHoriz;
	}
}
