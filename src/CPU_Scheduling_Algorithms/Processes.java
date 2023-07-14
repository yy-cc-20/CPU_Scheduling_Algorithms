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
	
	// Time complexity: O(n), Space complexity: O(n)
	public static void firstComeFirstServe(Process[] processes, int size, final int MAX_COMPLETION_TIME) {
		TreeSet<Process> readyQueue = new TreeSet<Process>(new ArrivalTimeComparator()); // Self-balancing binary search tree
		
		System.out.println("First Come First Serve");
		
		// 0. Rearrange the processes by arrival time
		Arrays.sort(processes, new ArrivalTimeComparator());
		
		// Display the gantt chart
		System.out.println("Gantt Chart");
		for (int i = 0; i < MAX_COMPLETION_TIME; ++i)
			System.out.print(i + "\t");
		System.out.println();
		
		Process firstProcess = null;
		for (int currentTime = 0, i = 0, j = 0; true; ++currentTime) {
			// 1. A new process has arrived, put the process in the ready queue
			if (processes[i].getArrivalTime() == currentTime) {
				readyQueue.add(processes[i]);
				
				// If there are processes that have not arrived yet
				if (processes.length > i + 1)
					i++;
			}
				
			// 2. Take out the first arrived process from the ready queue
			if (firstProcess == null) {
				firstProcess = readyQueue.pollFirst(); // Return null 

				// 6. If all the processes have arrived in the ready queue, and the processes in the ready queue all have been executed
				if (processes.length == i + 1 && firstProcess == null)
					break;
				
				// If no process in the readyQueue and the next process has not arrived yet
				else if (firstProcess == null) {
					System.out.print(" \t");
					continue;
				}
					
				// 3. Calculate the completion time of this process
				firstProcess.setCompletionTime(currentTime + firstProcess.getServiceTime()); // This function will automatically calculate turnaround time and waiting time
			}
				 
			
			// 4. Execute this process
			if (firstProcess.getCompletionTime() > currentTime) {
				System.out.print(firstProcess.getName() + "\t");
				
				// 5. Finish the execution of this process
				if (firstProcess.getCompletionTime() - 1 == currentTime) {
					firstProcess = null;
					j++;
				}
			}
		}
		
		System.out.println();
		
		// Display the average turnaround time and waiting time
		displayAverageTurnaroundTimeAndWaitingTime(processes, processes.length);
	}
	
	// Time complexity: O(n), Space complexity: O(n)
	public static void shortestProcessFirst(Process[] processes, int size, final int MAX_COMPLETION_TIME) {
		TreeSet<Process> readyQueue = new TreeSet<Process>(new ServiceTimeComparator()); // Self-balancing binary search tree
		Process[] executedProcesses = new Process[5];
		
		System.out.println("Shortest Process First");
		
		// 0. Rearrange the processes by arrival time
		Arrays.sort(processes, new ArrivalTimeComparator());
		
		// Display the gantt chart
		System.out.println("Gantt Chart");
		for (int i = 0; i < MAX_COMPLETION_TIME; ++i)
			System.out.print(i + "\t");
		System.out.println();
		
		Process shortestProcess = null;
		for (int currentTime = 0, i = 0, j = 0; true; ++currentTime) {
			// 1. A new process has arrived, put the process in the ready queue
			if (processes[i].getArrivalTime() == currentTime) {
				readyQueue.add(processes[i]);
				
				// If there are processes that have not arrived yet
				if (processes.length > i + 1)
					i++;
			}
				
			// 2. Take out the shortest process from the ready queue
			if (shortestProcess == null) {
				shortestProcess = readyQueue.pollFirst(); // Return null if nothing in the readyQueue

				// 6. If all the processes have arrived in the ready queue, and the processes in the ready queue all have been executed
				if (processes.length == i + 1 && shortestProcess == null)
					break;
				
				// If no process in the readyQueue and the next process has not arrived yet
				else if (shortestProcess == null) {
					System.out.print(" \t");
					continue;
				}
				
				// 3. Calculate the completion time of this process
				shortestProcess.setCompletionTime(currentTime + shortestProcess.getServiceTime()); // This function will automatically calculate turnaround time and waiting time
			}
				 
			// 4. Execute this process
			if (shortestProcess.getCompletionTime() > currentTime) {
				System.out.print(shortestProcess.getName() + "\t");
				
				// 5. Finish the execution of this process
				if (shortestProcess.getCompletionTime() - 1 == currentTime) {
					executedProcesses[j] = shortestProcess;
					shortestProcess = null;
					j++;
				}
			}
		}
		System.out.println();
		
		// Display the average turnaround time and waiting time
		displayAverageTurnaroundTimeAndWaitingTime(executedProcesses, executedProcesses.length);
	}
	
	public static void roundRobbin(Process[] processes, int size, final int MAX_COMPLETION_TIME, final int QUANTUM_TIME) {
		
	}
}
