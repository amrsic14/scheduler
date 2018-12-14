package rs.ac.bg.etf.os2;

import java.util.Comparator;
import java.util.PriorityQueue;

public class CompletelyFairScheduler extends Scheduler {

	private class CFSComparator implements Comparator<Pcb> {
		@Override
		public int compare(Pcb p1, Pcb p2) {
			if (p1.getPcbData().executionTime > p2.getPcbData().executionTime)
				return 1;
			else if (p1.getPcbData().executionTime < p2.getPcbData().executionTime)
				return -1;
			return 0;
		}
	}

	private PriorityQueue<Pcb> scheduler;

	public CompletelyFairScheduler() {
		this.scheduler = new PriorityQueue<>(new CFSComparator());
	}

	@Override
	public Pcb get(int cpuId) {
		Pcb pcb = scheduler.remove();
		pcb.getPcbData().timeInScheduler = Pcb.getCurrentTime() - pcb.getPcbData().timeInScheduler;
		pcb.setTimeslice(pcb.getPcbData().timeInScheduler / Pcb.getProcessCount());
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

	public static void main(String[] args) {
		int x = 5;
		x = 7 - x;
		System.out.println(x);
	}

}
