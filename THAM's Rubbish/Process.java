package test;

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
}
