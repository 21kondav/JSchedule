package simulator.process;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class SimulatedProgram {
	private File f;
	private LineNumberReader lineReader;
	private int programCounter;
	private int registerVar;
	private int pid;//use pid to store in hashtable
	private int ppid;
	private int start;
	private int end;
	private int usedTime;
	private boolean isTerminated = false;
	public SimulatedProgram(String filename, int time) throws FileNotFoundException {
		f = new File(filename);
		lineReader = new LineNumberReader(new FileReader(f));
		time = start;
	}
	public SimulatedProgram(int id, int ppid, LineNumberReader program, int lineStart, int time) throws FileNotFoundException {
		lineReader = new LineNumberReader(program);
		lineReader.setLineNumber(lineStart);
		this.ppid = ppid;
		this.start = time;
	}
	public int getLineNumber() {
		return lineReader.getLineNumber();
	}
	public void addInstruct(int n) {
		registerVar+= n;
	}
	public void subtractInstruct(int n) {
		registerVar+=n;
	}
	public String getLine() throws IOException {
		lineReader.setLineNumber(programCounter);
		return lineReader.readLine();
	}
	public LineNumberReader getInstructions() {
		return lineReader;
	}
	public void incrementProgram() throws IOException {
		programCounter+=1;
	}
	public File getF() {
		return f;
	}
	public void setF(String filename) throws IOException {
		lineReader.close();
		programCounter = 0;
		this.f = new File(filename);
		lineReader = new LineNumberReader(new FileReader(f));
	}
	public int getRegisterVar() {
		return registerVar;
	}
	public void setRegisterVar(int registerVar) {
		this.registerVar = registerVar;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getUsedTime() {
		return usedTime;
	}
	public void setUsedTime(int usedTime) {
		this.usedTime = usedTime;
	}
	public int getPid() {
		return pid;
	}
	public int getPpid() {
		return ppid;
	}
	public boolean terminate() throws IOException {
		lineReader.close();
		isTerminated = true;
		return true;
	}
	public boolean isTerminated() {
		return isTerminated;
	}
	@Override
	public String toString() {
		return "SimulatedProgram [registerVar=" + registerVar + ", pid=" + pid + ", ppid=" + ppid + ", start=" + start
				+ ", usedTime=" + usedTime + "]";
	}
	
}
