package rs.ac.bg.etf.os2;

public class PcbData {
	public static Pcb runningPCB;
	public long executionTime = (long) (Math.random() * 30);
	public long timeInScheduler;
	public int previousQueue;
	
	public PcbData() {}

	public PcbData(int previousQueue) {
		this.previousQueue = previousQueue;
	}
}
