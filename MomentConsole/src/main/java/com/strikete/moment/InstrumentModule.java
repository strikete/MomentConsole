package com.strikete.moment;

import java.util.ArrayList;

/**
 * THIS CLASS IS INCOMPLETE, PLEASE COMPLETE AND REMOVE THIS COMMENT UPON COMPLETION.
 * @author Ben Arrigo
 *
 */

public class InstrumentModule {
	
	/*
	 * VARIABLES
	 */
	private int dmxSlot; //The starting DMX Slot for this module within an Instrument.
	private int dmxSlotSize;
	private int value;
	private int homeValue;
	private int maxValue;
	private int minValue;
	private boolean defaultAppearance;
	private ArrayList<Integer> ranges = new ArrayList<Integer>();
	private ArrayList<String> rangeAppearance = new ArrayList<String>();
	private ArrayList<Byte> dmxValues = new ArrayList<Byte>();
	
	/*
	 * METHODS
	 */
	private String getRangeAppearance(int index) {
		int rangeLimit = ranges.size();
		if(defaultAppearance) {
			return (Integer.toString(index * 100 / maxValue)); //Maps the 0-maxValue value to 0-100
		} else {
			for(int x = 0; x < rangeLimit; x++) { //Otherwise, find the appropriate range identifier.
				if(ranges.get(x) > index) {
					return rangeAppearance.get(x);
				}
			}
		}
		Main.log.warn("WARNING: Invalid range index, returning NULL."); //Sometimes NULL is the correct rangemap. TODO: put in channel number here?
		return null;
	}
	
	/**
	 * Sets a range definition.
	 * @param topOfRange - Range limit
	 * @param rangeAppearanceIn - 
	 */
	public void setRange(int topOfRange, String rangeAppearanceIn) {
		if(defaultAppearance) {
			Main.log.warn("WARNING: You cannot set ranges in a default RangeMap");
		} else {
			ranges.add(topOfRange);
			rangeAppearance.add(rangeAppearanceIn);
		}
	}
	
	public synchronized ArrayList<Byte> getDmxValues() {
		return dmxValues;
	}
	public int getDmxSlot() {
		return dmxSlot;
	}
	public int getDmxSlotSize() {
		return dmxSlotSize;
	}
	public int getMaxValue() {
		return maxValue;
	}
	public int getMinValue() {
		return minValue;
	}
	public int getHomeValue() {
		return homeValue;
	}
	public synchronized void setValue(int valueIn) {
		this.value = valueIn;
		//TODO: Map values to dmxValues ArrayList. Create a custom error and throw it if a value tries to exceed the min or max value.
	}
	public String getAppearance() {
		return getRangeAppearance(value);
	}
	public String getHomeAppearance() {
		return getRangeAppearance(homeValue);
	}
	
	/*
	 * CONSTRUCTORS
	 */
	
	/**
	 * Extremely basic, default Instrument Module Constructor.
	 * @param dmxSlotIn
	 * @param dmxSlotSize
	 */
	public InstrumentModule(int dmxSlotIn, int dmxSlotSizeIn) {
		this.dmxSlot = dmxSlotIn;
		this.dmxSlotSize = dmxSlotSizeIn;
		this.minValue = 0;
		this.maxValue = (int) Math.pow(2,(dmxSlotSizeIn * 8));
		
		this.defaultAppearance = true;
	}
}
