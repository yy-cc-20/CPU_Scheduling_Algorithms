package CPU_Scheduling_Algorithms;

import java.util.Comparator;

public class Process {
	private char name;
	
	// in ms
	private int arrivalTime;
	private int serviceTime;
	private int completionTime;
	private int turnaroundTime;
	private int waitingTime;
	private int remainingServiceTime;
	
	private Process() 
	{
	}
	
	public Process(char name, int arrival, int service)
	{
		setName(name);
		setArrivalTime(arrival);
		setServiceTime(service);
		setRemainingServiceTime(service);
	}
	
	public char getName()
	{
		return name;
	}
	
	public int getArrivalTime()
	{
		return arrivalTime;
	}
	
	public int getServiceTime()
	{
		return serviceTime;
	}
	
	public int getCompletionTime() {
		return completionTime;
	}
	
	public int getTurnaourndTime() {
		if (completionTime == 0)
			throw new IllegalStateException("Completion time is not known.");
		
		return turnaroundTime;
	}
	
	public int getWaitingTime() {
		if (completionTime == 0)
			throw new IllegalStateException("Completion time is not known.");
		
		return waitingTime;
	}
	
	public int getRemainingServiceTime() {
		return remainingServiceTime;
	}
	
	private void setName(char name)
	{
		if ((name >= 'A' && name <= 'Z') || (name >= 'a' && name <= 'z'))
			this.name = name;
		else
			throw new IllegalArgumentException();
	}
	
	private void setArrivalTime(int arrival)
	{
		if (arrival < 0)
			throw new IllegalArgumentException();
		
		this.arrivalTime = arrival;
	}
	
	private void setServiceTime(int service)
	{
		if (service < 0)
			throw new IllegalArgumentException();
		
		this.serviceTime = service;
	}	
	
	public void setCompletionTime(int completion) {
		if (completion < 0)
			throw new IllegalArgumentException();
		
		this.completionTime = completion;
		this.turnaroundTime = completionTime - arrivalTime;
		this.waitingTime = turnaroundTime - serviceTime;
	}
	
	private void setRemainingServiceTime(int remainingService) {
		if (remainingService < 0 || remainingService > this.serviceTime)
			throw new IllegalArgumentException();
		this.remainingServiceTime = remainingService;
	}
	
	public void reduceRemainingServiceTime(int reduceBy) {
		if (this.remainingServiceTime - reduceBy < 0 || this.remainingServiceTime - reduceBy > this.serviceTime)
			throw new IllegalArgumentException();
		this.remainingServiceTime -= reduceBy;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Process process2 = new Process();
		process2.setArrivalTime(this.arrivalTime);
		process2.setCompletionTime(this.completionTime);
		process2.setName(this.name);
		process2.setServiceTime(this.serviceTime);
		process2.setRemainingServiceTime(this.remainingServiceTime);
		return process2;
	}
}

class ArrivalTimeComparator implements Comparator<Process> {
	public int compare(Process p1, Process p2) {
		if (p1.getArrivalTime() > p2.getArrivalTime())
			return 1;
		else if (p1.getArrivalTime() < p2.getArrivalTime())
			return -1;
		else {
			if (p1.getServiceTime() > p2.getServiceTime())
				return 1;
			else if (p1.getServiceTime() < p2.getServiceTime())
				return -1;
			else
				return 0;
		} 
	}
}

class ServiceTimeComparator implements Comparator<Process> {
	public int compare(Process p1, Process p2) {
		if (p1.getServiceTime() > p2.getServiceTime())
			return 1;
		else if (p1.getServiceTime() < p2.getServiceTime())
			return -1;
		else {
			if (p1.getArrivalTime() > p2.getArrivalTime())
				return 1;
			else if (p1.getArrivalTime() < p2.getArrivalTime())
				return -1;
			else 
				return 0;
		} 
	}
}
