package test;

import java.util.*;

public class Main {
	private static Scanner sc = new Scanner(System.in);
	private static Process[] Process  = new Process[5];
	private static Process[] Order  = new Process[5];
	private static Process[] Temp  = new Process[5];
	public static void main(String [] args)
	{
		int TotalServiceTime = 0;
		Process[0] = new Process('A',0,7);
		Process[1] = new Process('B',3,3);
		Process[2] = new Process('C',8,5);
		Process[3] = new Process('D',5,2);
		Process[4] = new Process('E',9,4);
		
		System.out.println("Process\t\t" + "ArrivalTime\t\t" + "ServiceTime\t\t");
		
		for (int i = 0; i < Process.length; i++)
		{
			System.out.println(Process[i].getName() + "\t\t" + Process[i].getArrivalTime() + "\t\t\t " + Process[i].getServiceTime());
			TotalServiceTime += Process[i].getServiceTime();
		}
		sc.close();
		
		for (int i = 1; i <= TotalServiceTime; i++) 
		{
			System.out.println("Current second: " +i);
			System.out.print("Arrived :"); Arrival(i);
			System.out.print("\nExecution order: ");ExcecuteOrder(i);
			System.out.print("\nExecuting: "); Executing(i);
			System.out.print("\n");
			oneSecondPass();
		}
	}
	
	public static void Arrival(int sec){
		for (int i = 0; i < Process.length; i++)
		{
			if(sec >= Process[i].getArrivalTime()) 
			{
				System.out.print(Process[i].getName() + "(" + Process[i].getServiceTime() + ") ");
			}
		}
	}
	
	public static void ExcecuteOrder(int sec){
		Order = Process;
		for (int i = 0; i < Order.length; i++)   
		{  
			for (int j = i + 1; j < Order.length; j++)   
			{
				if (Order[i].getServiceTime() > Order[j].getServiceTime() && Order[i].getArrivalTime() != 0 && sec >= Order[i].getArrivalTime())   
				{  
					Temp[i] = Order[i];
					Order[i] = Order[j];
					Order[j] = Temp[i];
				}
			}
			if(sec >= Order[i].getArrivalTime()) 
			{
				System.out.print(Order[i].getName() + "(" + Order[i].getServiceTime() + ") ");
			}
		}
	}
	private static int i = 0;
	private static int totalServiceTime = 1;
	
	public static void Executing(int sec){
		if (sec - totalServiceTime - Order[i].getServiceTime() == 0)
		{
			totalServiceTime += Order[i].getServiceTime();
			i++;
		}
		System.out.println(Order[i].getName());
	}
	
	public static void oneSecondPass() 
	{
		try
	      {Thread.sleep(1000);}
	      catch(InterruptedException ex)
	      {
	          ex.printStackTrace();
	      }
	}
	
	
	public static void clearScreen() 
	{
		try
		{	
			new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
		}catch(Exception E)
			{
				System.out.println(E);
			}
	}
}
