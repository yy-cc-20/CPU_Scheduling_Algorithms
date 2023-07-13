package CPU_Scheduling_Algorithms;

import java.util.Comparator;

public class Process {
	private char name;
	private int arrivalTime;
	private int serviceTime;
	
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
	
	public void setName(char name)
	{
		this.name = name;
	}
	
	public void setArrivalTime(int arrival)
	{
		this.arrivalTime = arrival;
	}
	
	public void setServiceTime(int service)
	{
		this.serviceTime = service;
	}	
	
	public static void displayProcesses(Process[] processes, int size) {
		
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
			else if (p1.getServiceTime() < p2.get)
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
		else if (p1.getServiceTime() < p2.get)
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
