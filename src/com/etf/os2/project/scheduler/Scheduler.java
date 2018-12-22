package com.etf.os2.project.scheduler;

import com.etf.os2.project.process.Pcb;

public abstract class Scheduler {

	public abstract Pcb get(int cpuId);

	public abstract void put(Pcb pcb);
	
	//TODO check arguments validity
	public static Scheduler createScheduler(String[] args) {
		switch (args[0]) {
		case "SJF":
			return new ShortestJobFirst(Double.parseDouble(args[1]), Boolean.parseBoolean(args[2]));
		case "MFQ":
			long[] timeSlices = new long[args.length -2];
			for(int i = 2; i < args.length; i++)
				timeSlices[i-2] = Long.parseLong(args[i]);
			return new MultilevelFeedbackQueue(Integer.parseInt(args[1]), timeSlices);
		case "CFS":
			return new CompletelyFairScheduler();
		default:
			System.out.println("Wrong parameters!");
			return null;
		}
	}
	
}
