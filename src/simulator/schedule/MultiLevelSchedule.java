package simulator.schedule;

import java.util.Map;

import simulator.process.SimulatedProgram;

public abstract class MultiLevelSchedule implements SchedInterface{
	//maps a priorty to a queue
	//Map<Integer, Queue<SimulatedProgram>> queuePriority;
	private Map<Integer, Scheduler> prioritySched;
	
	public MultiLevelSchedule() {
		// TODO Auto-generated constructor stub
	}
	public abstract boolean addToQueue(int priorty, SimulatedProgram program);
	public Map<Integer, Scheduler> getPrioritySched() {
		return prioritySched;
	}
}
