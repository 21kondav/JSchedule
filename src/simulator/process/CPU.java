package simulator.process;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class CPU {
	private SimulatedProgram currentProgram;
	private int highID = 1;
	private int time;
	private ProcessManager boundedPm;
	public CPU(ProcessManager pm) throws IOException{
		// TODO Auto-generated constructor stub
		currentProgram = new SimulatedProgram("../../example_init.txt", time);
		boundedPm = pm;
	}

	public boolean execute() throws IOException {
		//increments line already
		String curr = currentProgram.getLine();

		String[] instruct = curr.split(" ");
		switch(instruct[0]) {
		case "A":
			currentProgram.addInstruct(Integer.parseInt(instruct[1]));;
			break;
		case "S":
			currentProgram.setRegisterVar(Integer.parseInt(instruct[1]));;
			break;
		case "D":
			currentProgram.subtractInstruct(Integer.parseInt(instruct[1]));
			break;
		case "B":
			boundedPm.blockCurrent();
			 break;
		case "E":
			System.out.println("******************");
			System.out.println(currentProgram);
			System.out.println("******************");
			currentProgram.terminate();
			boundedPm.getNextAssignment(currentProgram);
			return true;
		case "F":
			SimulatedProgram tmpProg = new SimulatedProgram(++highID, currentProgram.getPid(), currentProgram.getInstructions(),
					currentProgram.getLineNumber(), boundedPm.getValidID());
			boundedPm.addProcess(tmpProg);
			currentProgram.incrementProgram();//skip over line
		default:
			throw new IOException();
		}
		boundedPm.getNextAssignment(currentProgram);
		return false;
	}
	public LineNumberReader getInstructions() {
		return currentProgram.getInstructions();
	}

	public void setProgram(SimulatedProgram nextProgram) {
		// TODO Auto-generated method stub
		this.currentProgram = nextProgram;
	}
}
