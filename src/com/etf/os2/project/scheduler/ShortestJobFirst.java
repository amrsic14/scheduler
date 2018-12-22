package com.etf.os2.project.scheduler;

import java.util.Comparator;
import java.util.PriorityQueue;

import com.etf.os2.project.process.*;

public class ShortestJobFirst extends Scheduler {

	private class SJFComparator implements Comparator<Pcb> {
		@Override
		public int compare(Pcb p1, Pcb p2) {
			if (p1.getPcbData().executionTime > p2.getPcbData().executionTime)
				return 1;
			else if (p1.getPcbData().executionTime < p2.getPcbData().executionTime)
				return -1;
			return 0;
		}
	}

	private final double alpha; // exponential average coefficient
	private final boolean isPreemptive;
	private PriorityQueue<Pcb> scheduler;

	public ShortestJobFirst(double exponentialAvgerage, boolean isPreemptive) {
		this.alpha = exponentialAvgerage;
		this.isPreemptive = isPreemptive;
		this.scheduler = new PriorityQueue<>(new SJFComparator());
	}

	@Override
	public Pcb get(int cpuId) {
		PcbData.runningPCB = scheduler.poll();
		return PcbData.runningPCB;
	}

	private void setExecutionTime(Pcb pcb) {
		// if it is new process, it will have default priority
		if (pcb.getPcbData() == null) {
			pcb.setPcbData(new PcbData());
			pcb.setTimeslice(0);
		} else {
			pcb.getPcbData().executionTime += pcb.getExecutionTime();
			pcb.getPcbData().executionTime *= alpha;
		}
	}

	private void tryPreemption(Pcb pcb) {
		if (PcbData.runningPCB != null
				&& pcb.getPcbData().executionTime < PcbData.runningPCB.getPcbData().executionTime)
			PcbData.runningPCB.preempt();
	}

	@Override
	public void put(Pcb pcb) {
		setExecutionTime(pcb);
		scheduler.add(pcb);
		if (isPreemptive)
			tryPreemption(pcb);
	}

}
