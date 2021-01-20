package com.strikete.moment;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class Main {

	public static Logger log;
	
	public static void main(String[] args) {
		
		// Initialize Logger
		log = Logger.getLogger(Main.class);
		BasicConfigurator.configure();
		
	}

}