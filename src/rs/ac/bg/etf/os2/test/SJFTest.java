package rs.ac.bg.etf.os2.test;

import rs.ac.bg.etf.os2.Pcb;
import rs.ac.bg.etf.os2.ShortestJobFirst;

public class SJFTest {

	public static void main(String[] args) {
		Pcb[] p = new Pcb[5];
		ShortestJobFirst s = new ShortestJobFirst(0.5, true);

		for (int i = 0; i < 5; i++)
			p[i] = new Pcb();
		for (int i = 0; i < 5; i++)
			s.put(p[i]);
		for (int i = 0; i < 3; i++) {
			Pcb pp = s.get(1);
			System.out.println(pp.getId() + " - " + pp.getPcbData().executionTime);
		}
		for (int i = 0; i < 5; i++)
			p[i] = new Pcb();
		for (int i = 0; i < 5; i++)
			s.put(p[i]);
		for (int i = 0; i < 3; i++) {
			Pcb pp = s.get(1);
			System.out.println(pp.getId() + " - " + pp.getPcbData().executionTime);
		}
		for (int i = 0; i < 5; i++)
			p[i] = new Pcb();
		for (int i = 0; i < 5; i++)
			s.put(p[i]);
		for (int i = 0; i < 9; i++) {
			Pcb pp = s.get(1);
			System.out.println(pp.getId() + " - " + pp.getPcbData().executionTime);
		}

	}

}
