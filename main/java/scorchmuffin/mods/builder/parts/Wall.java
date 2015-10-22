package scorchmuffin.mods.builder.parts;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public abstract class Wall {

	public enum Axis {
		AXIS_X, AXIS_M_X, AXIS_Z, AXIS_M_Z
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

	protected Wall(int horiz, int vert, int constPlane, World world, Axis axis) {
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
	}

	public void setSize(int width, int height) {
		this.height = height;
		this.width = width;

		// equation includes the current position (x is 4, width is 3, final x
		// will be 6 (block at 4, 5 and 6)
		// max cannot ever be achieved, all values must be kept less than max
		if ((int)Math.floor(height / 4) >= 2) {
			sectionHeight = 4;
			windowWidth = 4;
		} else {
			sectionHeight = height;
			windowWidth = height;
		}
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
		while (currentY + sectionHeight <= height) {
			buildOutsideColumn();
			buildInsideColumn();
			if (includeWindow) {
				while (currentHoriz + windowWidth < width) {
					buildWindow();
					buildWindowSpacer();
				}
			}
			buildRemainder();
			buildInsideColumn();
			buildOutsideColumn();
			prepareForNextRow(sectionHeight);
		}
		// check for a remainder at the top
		while (currentY < height) {
			buildBaseRow();
			prepareForNextRow(1);
		}
	}

	private void setBlock(int horiz, int vert, Block block) {
		if (axis == Axis.AXIS_X)
			world.setBlock(this.horiz + horiz, this.vert + vert, currentConstPlane, block);
		else if (axis == Axis.AXIS_M_X)
			world.setBlock(this.horiz - horiz, this.vert + vert, currentConstPlane, block);
		else if (axis == Axis.AXIS_Z)
			world.setBlock(currentConstPlane, this.vert + vert, this.horiz + horiz, block);
		else if (axis == Axis.AXIS_M_Z)
			world.setBlock(currentConstPlane, this.vert + vert, this.horiz - horiz, block);
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
		while (currentHoriz < width) {
			setBlock(currentHoriz, currentY, baseBlock);
			currentHoriz++;
		}
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

	/*
	 * Build what is left after a section is built, from where we are to the end
	 * of the width, minus the inside column and outside column
	 */
	private void buildRemainder() {
		int i;
		for (i = currentHoriz; i < width - 2; i++) {
			for (int j = 0; j < sectionHeight; j++) {
				setBlock(i, j + currentY, wallBlock);
			}
		}
		currentHoriz = i;
	}

	private void prepareForNextRow(int sectionHeight) {
		currentY = currentY + sectionHeight;
		currentHoriz = 0;
	}
}
