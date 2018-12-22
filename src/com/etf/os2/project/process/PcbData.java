package com.etf.os2.project.process;

public class PcbData {
	public static Pcb runningPCB;
	public long executionTime = (long) (Math.random() * 30);
	public long timeInScheduler;
	public int previousQueue;
}
