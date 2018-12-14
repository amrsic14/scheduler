package rs.ac.bg.etf.os2;

public class Pcb {
	
	private PcbData pcbData = null;
	public static int id=0;
	private int myid=++id;
	
	public static Pcb[] RUNNING;

	public enum ProcessState {
		RUNNING, READY, BLOCKED, CREATED;
	}
	
	public Pcb() {}

	public void preempt() {
		System.out.println("preempt");
	};

	public int getId() {
		return myid;
	};

	public int getPriority() {
		return 1;
	};

	public void setTimeslice(long timeslice) {};

	public long getExecutionTime() {
		return (long) (Math.random()*100);
	};

	public ProcessState getPreviousState() {
		return ProcessState.CREATED;
	};

	public PcbData getPcbData() {
		return pcbData;
	};

	public void setPcbData(PcbData pcbData) {
		this.pcbData = pcbData;
	};

	public static long getCurrentTime() {
		return 1L;
	};

	public static int getProcessCount() {
		return RUNNING.length;
	};
}
