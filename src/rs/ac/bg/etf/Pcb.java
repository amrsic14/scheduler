package rs.ac.bg.etf;

public class Pcb {
	
	private PcbData pcbData;
	
	public static Pcb[] RUNNING;

	public enum ProcessState {
		RUNNING, READY, BLOCKED, CREATED;
	}

	public void preempt() {};

	public int getId() {
		return 1;
	};

	public int getPriority() {
		return 1;
	};

	public void setTimeslice(long timeslice) {};

	public long getExecutionTime() {
		return 1L;
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
