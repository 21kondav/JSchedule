package simulator.schedule;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import simulator.process.SimulatedProgram;


public abstract class Scheduler implements SchedInterface{
	private Queue<Integer> next;
	private Queue<SimulatedProgram> toTerminate;
	//integers are ids
	List<SimulatedProgram> programs;
	private int timeQuantum;
	private int totalTime;
	public Scheduler(boolean hasPriority) {
		timeQuantum = -1;
		if(hasPriority)
			next = new PriorityQueue<>();
		else
			next = new LinkedList<>();
		toTerminate = new LinkedList<>();
	}
	public Scheduler(int timeQ, boolean hasPriority) {
		timeQuantum = timeQ;
		if(hasPriority)
			next = new PriorityQueue<>();
		else
			next = new LinkedList<>();
		toTerminate = new LinkedList<>();
	}
//	public Queue<SimulatedProgram> getNextQueue() {
//		Queue<SimulatedProgram> programQueue = new LinkedList<SimulatedProgram>();
//		for(int i: next) {
//			programQueue		term = new LinkedList<>();
//.add(programs.get(i));
//		}
//		return programQueue;
//	}

	@Override
	public void enqueue(SimulatedProgram program) {
		if(programs.indexOf(program) == -1)
			programs.add(program);
		next.add(programs.indexOf(program));
	}
	@Override
	public SimulatedProgram dequeue() {
		return programs.get(next.remove());
	}
	public void runTimeQuantum() {
		
	}
	public int getId(SimulatedProgram program) {
		return programs.indexOf(program);
	}
	public int getTimeQuantum() {
		return timeQuantum;
	}

	public void setTimeQuantum(int timeQuantum) {
		this.timeQuantum = timeQuantum;
	}

	@Override
	public boolean terminateProcess(int id) {
		// TODO Auto-generated method stub\
		if(id == next.peek()){
			try {
				next.remove();
				programs.remove(id).terminate();
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				return false;
			}
		}
		toTerminate.add(programs.remove(id));
		return false;
	}
}
