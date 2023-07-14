package CPU_Scheduling_Algorithms;

import java.util.Arrays;
import java.util.TreeSet;

// Utility class for Process
public class Processes {
	private static void displayAverageTurnaroundTimeAndWaitingTime(Process[] processes, int size) {
		int totalTurnaroundTime = 0;
		int totalWaitingTime = 0;
		System.out.println("Process\tTurnaround Time (ms)\tWaiting Time (ms)");
		for (Process process: processes) {
			System.out.println(process.getName() + "\t" + process.getTurnaourndTime() + "\t\t\t" + process.getWaitingTime());
			totalTurnaroundTime += process.getTurnaourndTime();
			totalWaitingTime += process.getWaitingTime();
		}
		System.out.println("Average turnaround time: " + totalTurnaroundTime / size + "ms");
		System.out.println("Average waiting time: " + totalWaitingTime / size + "ms");
		System.out.println();
	}
	
	public static void displayProcesses(Process[] processes) {
		System.out.println("Process\tArrival Time (ms)\tService Time (ms)");
		for (Process process: processes) {
			System.out.println(process.getName() + "\t" + process.getArrivalTime() + "\t\t\t" + process.getServiceTime());
		}
		System.out.println();
	}
	
	public static void firstComeFirstServe(Process[] processes, int size, final int MAX_COMPLETION_TIME) {
		System.out.println("First Come First Serve");
		
		// 1. Rearrange the processes by arrival time
		Arrays.sort(processes, new ArrivalTimeComparator());
		
		// 2. Display the gantt chart
		System.out.println("Gantt Chart");
		for (int i = 0; i < MAX_COMPLETION_TIME; ++i)
			System.out.print(i + "\t");
		System.out.println();
		
		int currentTime = 0;
		for (Process process : processes) {
			while (true) {
				if (process.getArrivalTime() <= currentTime) {
					// The process has arrived, execute the process
					for (int j = 0; j < process.getServiceTime(); ++j) {
						System.out.print(process.getName() + "\t");
						currentTime++;
					}
					// Calculate completion time
					process.setCompletionTime(currentTime); // This function will automatically calculate turnaround time and waiting time
					break;
				} else {
					// Wait for the arrival of next process
					currentTime++;
				}		
			}		
		}
		System.out.println();
		
		// 3. Display the average turnaround time and waiting time
		displayAverageTurnaroundTimeAndWaitingTime(processes, processes.length);
	}
	
	public static void shortestProcessFirst(Process[] processes, int size, final int MAX_COMPLETION_TIME) {
		TreeSet<Process> readyQueue = new TreeSet<Process>(new ServiceTimeComparator()); // Self-balancing binary search tree
		
		System.out.println("Shortest Process First");
		
		// 1. Rearrange the processes by arrival time
		Arrays.sort(processes, new ArrivalTimeComparator());
		
		// 2. Display the gantt chart
		System.out.println("Gantt Chart");
		for (int i = 0; i < MAX_COMPLETION_TIME; ++i)
			System.out.print(i + "\t");
		System.out.println();
		
		int currentTime = 0;
		Process shortestProcess = null;
		for (Process process : processes) {
			while (true) {
				if (process.getArrivalTime() <= currentTime) {
					// The process has arrived
					readyQueue.add(process);
					
					// Execute the shortest process in the ready queue
					shortestProcess = readyQueue.pollFirst(); // Get the shortest process in the ready queue
					for (int j = 0; j < shortestProcess.getServiceTime(); ++j) {
						System.out.print(shortestProcess.getName() + "\t");
						currentTime++;
					}
					// Calculate completion time
					shortestProcess.setCompletionTime(currentTime); // This function will automatically calculate turnaround time and waiting time
					break;
				} else {
					// Wait for the arrival of next process
					currentTime++;
				}		
			}		
		}
		System.out.println();
		
		// 3. Display the average turnaround time and waiting time
		displayAverageTurnaroundTimeAndWaitingTime(processes, processes.length);
	}
	
	public static void roundRobbin(Process[] processes, int size, final int MAX_COMPLETION_TIME, final int QUANTUM_TIME) {
		
	}
}
