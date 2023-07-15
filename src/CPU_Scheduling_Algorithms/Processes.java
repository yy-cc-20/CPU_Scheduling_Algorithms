package CPU_Scheduling_Algorithms;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

// Utility class for Process
public class Processes {
	private static void displayAverageTurnaroundTimeAndWaitingTime(Process[] processes, int size) {
		double totalTurnaroundTime = 0;
		double totalWaitingTime = 0;
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
	
	// Time complexity: O(n log n), Space complexity: O(n)
	public static void firstComeFirstServe(Process[] processes, int size, final int MAX_COMPLETION_TIME) {
		TreeSet<Process> readyQueue = new TreeSet<Process>(new ArrivalTimeComparator()); // Self-balancing binary search tree
		
		System.out.println("---------------------------");
		System.out.println("First Come First Serve");
		System.out.println("---------------------------");
		
		// 0. Rearrange the processes by arrival time
		Arrays.sort(processes, new ArrivalTimeComparator()); // O(n log n)
		
		// Display the gantt chart
		System.out.println("Gantt Chart");
		System.out.print("Time\t");
		for (int i = 0; i <= MAX_COMPLETION_TIME; i++)
			System.out.print(i + "\t");
		System.out.println();
		
		// Arrival time of the processes
		System.out.print("Arrive\t");
		for (int currentTime = 0, i = 0; true; ++currentTime) {
			// A new process has arrived
			if (processes[i].getArrivalTime() == currentTime) {
				System.out.print(processes[i].getName() + "\t");
				i++;
				
				// If all the processes have arrived
				if (processes.length == i)
					break;
			} else {
				System.out.print("\t");
			}
		}
		System.out.println();
		
		// Execution order of the processes
		System.out.print("Execute\t\t");
		Process firstProcess = null;
		for (int currentTime = 0, i = 0, j = 0; true; ++currentTime) {
			// 1. A new process has arrived, put the process in the ready queue
			if (processes[i].getArrivalTime() == currentTime) {
				readyQueue.add(processes[i]);
				
				// If there are processes that have not arrived yet
				if (processes.length > i + 1)
					i++;
			}
				
			// 2a. Take out the first arrived process from the ready queue
			if (firstProcess == null) {
				firstProcess = readyQueue.pollFirst(); // Return null 

				// 6. If all the processes have arrived in the ready queue, and the processes in the ready queue all have been executed
				if (processes.length == i + 1 && firstProcess == null)
					break;
				
				// 2b. If no process in the readyQueue and the next process has not arrived yet
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
		System.out.println();
		
		// Display the average turnaround time and waiting time
		displayAverageTurnaroundTimeAndWaitingTime(processes, processes.length);
	}
	
	// Time complexity: O(n log n), Space complexity: O(n)
	public static void shortestProcessFirst(Process[] processes, int size, final int MAX_COMPLETION_TIME) {
		TreeSet<Process> readyQueue = new TreeSet<Process>(new ServiceTimeComparator()); // Self-balancing binary search tree
		Process[] executedProcesses = new Process[5];
		
		System.out.println("---------------------------");
		System.out.println("Shortest Process First");
		System.out.println("---------------------------");
		
		// 0. Rearrange the processes by arrival time
		Arrays.sort(processes, new ArrivalTimeComparator()); // O(n log n)
		
		// Display the gantt chart
		System.out.println("Gantt Chart");
		System.out.print("Time\t");
		for (int i = 0; i <= MAX_COMPLETION_TIME; i++)
			System.out.print(i + "\t");
		System.out.println();
		
		// Arrival time of the processes
		System.out.print("Arrive\t");
		for (int currentTime = 0, i = 0; true; ++currentTime) {
			// A new process has arrived
			if (processes[i].getArrivalTime() == currentTime) {
				System.out.print(processes[i].getName() + "\t");
				i++;
				
				// If all the processes have arrived
				if (processes.length == i)
					break;
			} else {
				System.out.print("\t");
			}
		}
		System.out.println();
		
		// Execution order of the processes
		System.out.print("Execute\t\t");
		Process shortestProcess = null;
		for (int currentTime = 0, i = 0, j = 0; true; ++currentTime) {
			// 1. A new process has arrived, put the process in the ready queue
			if (processes[i].getArrivalTime() == currentTime) {
				readyQueue.add(processes[i]);
				
				// If there are processes that have not arrived yet
				if (processes.length > i + 1)
					i++;
			}
				
			// 2a. Take out the shortest process from the ready queue
			if (shortestProcess == null) {
				shortestProcess = readyQueue.pollFirst(); // Return null if nothing in the readyQueue

				// 6. If all the processes have arrived in the ready queue, and the processes in the ready queue all have been executed
				if (processes.length == i + 1 && shortestProcess == null)
					break;
				
				// 2b. If no process in the readyQueue and the next process has not arrived yet
				else if (shortestProcess == null) {
					System.out.print("\t");
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
		System.out.println();
		
		// Display the average turnaround time and waiting time
		displayAverageTurnaroundTimeAndWaitingTime(executedProcesses, executedProcesses.length);
	}
	
	// Time complexity: O(n log n), space complexity: O(n)
	public static void roundRobbin(Process[] processes, int size, final int MAX_COMPLETION_TIME, final int QUANTUM_TIME) {
		Queue<Process> readyQueue = new LinkedList<Process>(); // First-in-first-out queue
		Process[] executedProcesses = new Process[5];
		
		System.out.println("---------------------------");
		System.out.println("Round Robbin");
		System.out.println("---------------------------");
		
		// 0. Rearrange the processes by arrival time
		Arrays.sort(processes, new ArrivalTimeComparator()); // O(n log n)
		
		// Display the gantt chart
		System.out.println("Gantt Chart");
		System.out.print("Time\t");
		for (int i = 0; i <= MAX_COMPLETION_TIME; i++)
			System.out.print(i + "\t");
		System.out.println();
		
		// Arrival time of the processes
		System.out.print("Arrive\t");
		for (int currentTime = 0, i = 0; true; ++currentTime) {
			// A new process has arrived
			if (processes[i].getArrivalTime() == currentTime) {
				System.out.print(processes[i].getName() + "\t");
				i++;
				
				// If all the processes have arrived
				if (processes.length == i)
					break;
			} else {
				System.out.print("\t");
			}
		}
		System.out.println();
		
		// Execution order of the processes
		System.out.print("Execute\t\t");
		Process currentProcess = null;
		int remainingQuantumTime = QUANTUM_TIME;
		for (int currentTime = 0, i = 0, j = 0; true; ++currentTime) {
			// 1. A new process has arrived, put the process in the ready queue
			if (processes[i].getArrivalTime() == currentTime) {
				readyQueue.add(processes[i]);
				
				// If there are processes that have not arrived yet
				if (processes.length > i + 1)
					i++;
			}
				
			// 2a. Take out the first process from the ready queue
			if (currentProcess == null) {
				currentProcess = readyQueue.poll(); // Return null if nothing in the readyQueue
				remainingQuantumTime = QUANTUM_TIME;
				
				// 6. If all the processes have arrived in the ready queue, and the processes in the ready queue all have been executed
				if (processes.length == i + 1 && currentProcess == null)
					break;
				
				// 2b. If no process in the readyQueue and the next process has not arrived yet
				else if (currentProcess == null) {
					System.out.print("\t");
					continue;
				}
			}
				 
			// 3. Execute this process
			if (currentProcess.getRemainingServiceTime() > 0 && remainingQuantumTime > 0) {
				System.out.print(currentProcess.getName() + "\t");
				currentProcess.setRemainingServiceTime(currentProcess.getRemainingServiceTime() - 1);
				remainingQuantumTime--;
				
				// 4a. Finish the execution of this process
				if (currentProcess.getRemainingServiceTime() == 0) {
					// 5. Calculate the completion time of this process
					currentProcess.setCompletionTime(currentTime + 1); // This function will automatically calculate turnaround time and waiting time

					executedProcesses[j] = currentProcess;
					currentProcess = null;
					j++;
				}
				// 4b. Interrupt the execution of this process and put it at the end of the readyQueue
				else if (remainingQuantumTime == 0) {
					readyQueue.add(currentProcess);
					currentProcess = null;
				}
			} 
		}
		System.out.println();
		System.out.println();
		
		// Display the average turnaround time and waiting time
		displayAverageTurnaroundTimeAndWaitingTime(executedProcesses, executedProcesses.length);
	}
}
