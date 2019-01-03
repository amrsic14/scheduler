package com.etf.os2.project.scheduler;

import java.util.Comparator;
import java.util.PriorityQueue;

import com.etf.os2.project.process.*;

public class CompletelyFairScheduler extends Scheduler {

	private class CFSComparator implements Comparator<Pcb> {
		@Override
		public int compare(Pcb p1, Pcb p2) {
			return (int) (p1.getPcbData().executionTime - p2.getPcbData().executionTime);
		}
	}

	private PriorityQueue<Pcb> scheduler;

	public CompletelyFairScheduler() {
		this.scheduler = new PriorityQueue<>(new CFSComparator());
	}

	@Override
	public Pcb get(int cpuId) {
		Pcb pcb = scheduler.poll();
		if (pcb != null) {
			pcb.getPcbData().timeInScheduler = Pcb.getCurrentTime() - pcb.getPcbData().timeInScheduler;
			pcb.setTimeslice(pcb.getPcbData().timeInScheduler / Pcb.getProcessCount());
		}
		return pcb;
	}

	@Override
	public void put(Pcb pcb) {
		if (pcb.getPcbData() == null)
			pcb.setPcbData(new PcbData());
		pcb.getPcbData().executionTime = pcb.getPcbData().timeInScheduler + pcb.getExecutionTime();
		pcb.getPcbData().timeInScheduler = Pcb.getCurrentTime();
		scheduler.add(pcb);
	}

}
