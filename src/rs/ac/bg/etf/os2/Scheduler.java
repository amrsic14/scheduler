package rs.ac.bg.etf.os2;

public abstract class Scheduler {
	
	public abstract Pcb get(int cpuId);

	public abstract void put(Pcb pcb);

	public static Scheduler createScheduler(String[] args) {
		// TODO: Implement this method
		return new ShortestJobFirst(3, true);
	}
}
