package rs.ac.bg.etf;

import java.util.Comparator;
import java.util.PriorityQueue;

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
		// TODO Auto-generated method stub
		return scheduler.remove();
	}

	@Override
	public void put(Pcb pcb) {
		// if it is new process, it will have default priority
		if (pcb.getPcbData() == null) {
			pcb.setPcbData(new PcbData());
		} else {
			pcb.getPcbData().executionTime += pcb.getExecutionTime();
			pcb.getPcbData().executionTime /= alpha;
		}
		scheduler.add(pcb);
	}

	public static void main(String args[]) {
		Pcb[] p = new Pcb[5];
		ShortestJobFirst s = new ShortestJobFirst(0.7, false);
		for (int i = 0; i < 5; i++)
			p[i] = new Pcb();
		for (int i = 0; i < 5; i++)
			s.put(p[i]);
		for (int i = 0; i < 5; i++) {
			Pcb pp = s.get(1);
			System.out.println(pp.getId() + " - " + pp.getPcbData().executionTime);
		}
		for (int i = 0; i < 5; i++)
			s.put(p[i]);
		for (int i = 0; i < 5; i++) {
			Pcb pp = s.get(1);
			System.out.println(pp.getId() + " - " + pp.getPcbData().executionTime);
		}
		for (int i = 0; i < 5; i++)
			s.put(p[i]);
		for (int i = 0; i < 5; i++) {
			Pcb pp = s.get(1);
			System.out.println(pp.getId() + " - " + pp.getPcbData().executionTime);
		}
	}
}
