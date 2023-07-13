package CPU_Scheduling_Algorithms;

import java.util.Comparator;

public class Process {
	private char name;
	private int arrivalTime;
	private int serviceTime;
	private int completionTime;
	private int turnaroundTime;
	private int waitingTime;
	
	public Process(char name, int arrival, int service)
	{
		setName(name);
		setArrivalTime(arrival);
		setServiceTime(service);
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
	
	public static void displayAverageTurnaroundTimeAndWaitingTime(Process[] processes, int size) {
		int totalTurnaroundTime = 0;
		int totalWaitingTime = 0;
		System.out.println("Process\tTurnaround Time (ms)\tWaiting Time");
		for (Process process: processes) {
			System.out.println(process.getName() + "\t" + process.getTurnaourndTime() + "\t\t\t" + process.getWaitingTime());
			totalTurnaroundTime += process.getTurnaourndTime();
			totalWaitingTime += process.getWaitingTime();
		}
		System.out.println("Average turnaround time: " + totalTurnaroundTime / size + "ms");
		System.out.println("Average waiting time: " + totalWaitingTime / size + "ms");
	}
	
	public static void displayProcesses(Process[] processes) {
		System.out.println("Process\tArrival Time (ms)\tService Time (ms)");
		for (Process process: processes) {
			System.out.println(process.getName() + "\t" + process.getArrivalTime() + "\t\t\t" + process.getServiceTime());
		}
		System.out.println();
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
				return 1;
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
				return 1;
			
		} 
	}
}
