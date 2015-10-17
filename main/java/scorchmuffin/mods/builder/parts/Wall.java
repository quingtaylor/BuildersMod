package scorchmuffin.mods.builder.parts;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class Wall {

	private int currentX;
	private int currentY;
	private int currentZ;
	private int initialX;
	private int initialY;
	private int initialZ;
	private int height;
	private int width;

	private int windowWidth;
	private int sectionHeight;
	private int maxX;
	private int maxY;
	private int spacerWidth;

	private boolean includeDoor;
	private Block windowBlock;
	private Block baseBlock;
	private Block columnBlock;
	private Block wallBlock;
	private World world;

	public Wall(World world, int x, int y, int z) {
		this.initialX = x;
		this.initialY = y;
		this.initialZ = z;
		this.currentX = x;
		this.currentY = y;
		this.currentZ = z;
		this.world = world;
		windowBlock = Blocks.glass;
		baseBlock = Blocks.brick_block;
		columnBlock = Blocks.log;
		wallBlock = Blocks.stonebrick;
		spacerWidth = 2;
	}

	public void setSize(int height, int width) {
		this.height = height;
		this.width = width;

		this.maxX = initialX + width;
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

	public void build() {

		// first row is the base (above the floor)
		buildBaseRow();
		prepareForNextRow(1);

		// windows and doors, these are the middle parts of the wall
		while (currentY < maxY - 1) {
			buildColumn();
			while (currentX + windowWidth + spacerWidth < maxX) {
				buildWindow();
				buildWindowSpacer();
			}
			buildRemainder(world);
			buildColumn();
			prepareForNextRow(sectionHeight);
		}
		buildBaseRow();
	}

	private void buildColumn() {
		for (int j = 0; j < sectionHeight; j++) {
			world.setBlock(currentX, currentY + j, currentZ, columnBlock);
		}
		currentX++;
	}

	private void buildBaseRow() {
		while (currentX < maxX) {
			world.setBlock(currentX, currentY, currentZ, baseBlock);
			currentX++;
		}
	}

	private void buildWindow() {
		if (currentX + windowWidth >= maxX)
			return;
		int i;
		for (i = 0; i < windowWidth; i++) {
			for (int j = 0; j < sectionHeight; j++) {
				world.setBlock(i + currentX, j + currentY, currentZ, windowBlock);
			}
		}
		currentX = currentX + i;
	}
	
	private void buildWindowSpacer() {
		if (currentX + windowWidth >= maxX)
			return;
		int i;
		for (i = 0; i < spacerWidth; i++) {
			for (int j = 0; j < sectionHeight; j++) {
				world.setBlock(i + currentX, j + currentY, currentZ, wallBlock);
			}
		}
		currentX = currentX + i;
	}

	/*
	 * Build what is left after a section is built, from where we are to the end of the width
	 */
	private void buildRemainder(World world) {
		int i;
		for (i = currentX; i < maxX; i++) {
			for (int j = 0; j < sectionHeight; j++) {
				world.setBlock(i + currentX, j + currentY, currentZ, wallBlock);
			}
		}
		currentX = i;
	}

	private void prepareForNextRow(int sectionHeight) {
		currentY = currentY + sectionHeight;
		currentX = initialX;
	}
}
