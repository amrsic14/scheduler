package com.etf.os2.project.process;

public class PcbData {
	public static Pcb runningPCB[];
	public int numberOfCPUs;
	public long executionTime;
	public long timeInScheduler;
	public int previousQueue;

	public PcbData() {
	}

	public PcbData(int priority, int numberOfCPUs) {
		executionTime = priority;
		this.numberOfCPUs = numberOfCPUs;
		runningPCB = new Pcb[numberOfCPUs];
	}
}
