package com.etf.os2.project.scheduler;

import java.util.Comparator;
import java.util.PriorityQueue;

import com.etf.os2.project.process.*;

public class ShortestJobFirst extends Scheduler {

	private class SJFComparator implements Comparator<Pcb> {
		@Override
		public int compare(Pcb p1, Pcb p2) {
			if(p1.getPriority() == Integer.MAX_VALUE) return -1;
			if(p2.getPriority() == Integer.MAX_VALUE) return 1;
			int compare = (int) (p1.getPcbData().executionTime - p2.getPcbData().executionTime);
			if (compare == 0)
				compare = p1.getPriority() - p2.getPriority();
			return compare;
		}
	}

	private final double alpha; // exponential average coefficient
	private final boolean isPreemptive;
	private final int numberOfCPUs;
	private PriorityQueue<Pcb> scheduler;

	public ShortestJobFirst(double exponentialAvgerage, boolean isPreemptive, int numberOfCPUs) {
		this.alpha = exponentialAvgerage;
		this.isPreemptive = isPreemptive;
		this.numberOfCPUs = numberOfCPUs;
		this.scheduler = new PriorityQueue<>(new SJFComparator());
	}

	@Override
	public Pcb get(int cpuId) {
		PcbData.runningPCB[cpuId] = scheduler.poll();
		return PcbData.runningPCB[cpuId];
	}

	private void setExecutionTime(Pcb pcb) {
		if (pcb.getPcbData() == null) {
			pcb.setPcbData(new PcbData(pcb.getPriority(), numberOfCPUs));
			pcb.setTimeslice(0);
		} else {
			pcb.getPcbData().executionTime += pcb.getExecutionTime();
			pcb.getPcbData().executionTime *= alpha;
		}
	}

	private void tryPreemption(Pcb pcb) {
		for (int i = 0; i < numberOfCPUs; i++) {
			if (PcbData.runningPCB[i] != null
					&& pcb.getPcbData().executionTime < PcbData.runningPCB[i].getPcbData().executionTime) {
				PcbData.runningPCB[i].preempt();
				return;
			}
		}
	}

	@Override
	public void put(Pcb pcb) {
		setExecutionTime(pcb);
		scheduler.add(pcb);
		if (isPreemptive)
			tryPreemption(pcb);
	}

}
