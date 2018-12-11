package rs.ac.bg.etf;

import java.util.Comparator;
import java.util.PriorityQueue;

public class ShortestJobFirst extends Scheduler {

	private class SJFComparator implements Comparator<Pcb> {
		@Override
		public int compare(Pcb p1, Pcb p2) {
			if (p1.getExecutionTime() > p2.getExecutionTime())
				return 1;
			else if (p1.getExecutionTime() < p2.getExecutionTime())
				return -1;
			return 0;
		}
	}

	private final double alpha; // exponential average coefficient
	private final boolean isPreemptive;
	private PriorityQueue<Pcb> scheduler;

	public ShortestJobFirst(double expAvg, boolean isPreemptive) {
		this.alpha = expAvg;
		this.isPreemptive = isPreemptive;
		this.scheduler = new PriorityQueue<>(new SJFComparator());
	}

	@Override
	public Pcb get(int cpuId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void put(Pcb pcb) {
		// TODO Auto-generated method stub

	}
}
