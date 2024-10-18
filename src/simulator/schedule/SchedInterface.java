package simulator.schedule;

import java.util.stream.Stream;

import simulator.process.SimulatedProgram;


public interface SchedInterface {
	public void reschedule();
	public void schedule(Stream<SimulatedProgram> processes);
	public boolean acceptProcess(SimulatedProgram process);
	public boolean terminateProcess(int id);
	void enqueue(SimulatedProgram program);
	SimulatedProgram dequeue();
}
