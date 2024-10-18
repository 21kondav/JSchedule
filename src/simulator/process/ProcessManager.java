package simulator.process;

import java.io.IOException;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import simulator.schedule.Scheduler;


public class ProcessManager {
	//stores pcb info by process id
	private Hashtable<Integer, SimulatedProgram> pcbTable;
	private Queue<SimulatedProgram> ready;
	private Queue<SimulatedProgram> blocked;
	private int time;
	private CPU cpu;
	Scheduler sched;
	public ProcessManager(Scheduler sch) throws IOException {
		// TODO Auto-generated constructor stub
		this.sched= sch;
		this.pcbTable = new Hashtable<>();
		this.ready = new LinkedList<>();
		this.blocked = new LinkedList<>();
		cpu = new CPU(this);
	}
	public int getValidID() {
		return Collections.max(pcbTable.keySet())+1;
	}

	public boolean exec(String command) throws IOException{
		switch(command) {
		case "P":
			
			System.out.println("Print state");
			break;
		case "Q":
			System.out.println("Run 1 time step");
			cpu.execute();
			break;
		case "U":
			break;
		case "T":
			//TODO: terminate all processes safely, print state, and shutdown
			return true;
		default:
			System.err.println("Unrecognized Input");
		}
		return false;
	}
	public void blockCurrent() {
		blocked.add(ready.remove());
	}
	public void addProcess(SimulatedProgram program) {
		ready.add(program);
	}
	//FCFS, but could do round robin. Technically should be abstract
	public void getNextAssignment(SimulatedProgram program) {
		SimulatedProgram nextProgram = null;
		if(program.isTerminated()){
			if(ready.isEmpty())
				System.out.println();
			else
				nextProgram = ready.remove();
		}
		cpu.setProgram(nextProgram);
	}
	public void schedule() {
		
	}
}
