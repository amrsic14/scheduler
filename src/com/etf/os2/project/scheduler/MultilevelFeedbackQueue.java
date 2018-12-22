package com.etf.os2.project.scheduler;

import java.util.LinkedList;

import com.etf.os2.project.process.*;

public class MultilevelFeedbackQueue extends Scheduler {

	private long[] timeSlices;
	private LinkedList<Pcb>[] queues;

	@SuppressWarnings("unchecked")
	private void initializeFields(int numberOfQueues, long[] timeSlices) {
		queues = new LinkedList[numberOfQueues];
		for (int i = 0; i < queues.length; i++)
			queues[i] = new LinkedList<Pcb>();
		this.timeSlices = new long[timeSlices.length];
		System.arraycopy(timeSlices, 0, this.timeSlices, 0, timeSlices.length);
	}

	public MultilevelFeedbackQueue(int numberOfQueues, long[] timeSlices) {
		initializeFields(numberOfQueues, timeSlices);
	}

	private int getNextQueue() {
		int iterator = -1;
		while (++iterator <= queues.length - 1) {
			if (!queues[iterator].isEmpty())
				return iterator;
		}
		//empty scheduler
		return -1;
	}

	@Override
	public Pcb get(int cpuId) {
		int nextQueue = getNextQueue();
		if (nextQueue != -1)
			return queues[getNextQueue()].removeFirst();
		return null;
	}

	private int nextQueue(Pcb pcb) {
		// TODO READY should never happen
		switch (pcb.getPreviousState()) {
		case BLOCKED:
			pcb.getPcbData().previousQueue = (pcb.getPcbData().previousQueue > 0) ? pcb.getPcbData().previousQueue - 1
					: 0;
			return pcb.getPcbData().previousQueue;
		case CREATED:
			return 0;
		case IDLE:
			return queues.length - 1;
		case READY:
		case RUNNING:
			pcb.getPcbData().previousQueue = (pcb.getPcbData().previousQueue < queues.length - 1)
					? pcb.getPcbData().previousQueue + 1
					: queues.length - 1;
			return pcb.getPcbData().previousQueue;
		default:
			System.out.println("PCB stste error!");
			return -1;
		}
	}

	@Override
	public void put(Pcb pcb) {
		if (pcb.getPcbData() == null)
			pcb.setPcbData(new PcbData());
		int nextQueue = nextQueue(pcb);
		pcb.setTimeslice(timeSlices[nextQueue]);
		queues[nextQueue].add(pcb);
	}

}
