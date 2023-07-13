
/**
 * @description CPU Scheduling Algorithms
 * compare the efficiency of 3 CPU scheduling Algorithms
 * 
 */

package CPU_Scheduling_Algorithms;

import java.util.Arrays;

public class CPU_Scheduling_Algorithms {
	// Choose to use array because we need random access, but do no need insertion and deletion
	private static Process[] processes = new Process[] {
		new Process('A', 0, 7),
		new Process('B', 3, 3),
		new Process('C', 8, 5),
		new Process('D', 5, 2),
		new Process('E', 9, 4)
	};
	
	private static final int MAX_COMPLETION_TIME = 30;
	
	public static void firstComeFirstServe(Process[] processes, int size) {
		System.out.println("First Come First Serve");
		
		// 1. Rearrange the processes by arrival time
		Arrays.sort(processes, new ArrivalTimeComparator());
		
		// 2. Display the gantt chart
		System.out.println("Gantt Chart");
		for (int i = 0; i < MAX_COMPLETION_TIME; ++i)
			System.out.print(i + "\t");
		System.out.println();
		
		int completionTime = 0;
		for (Process process : processes) {
			for (int j = 0; j < process.getServiceTime(); ++j) {
				System.out.print(process.getName() + "\t");
				completionTime++;
			}
			// Calculate completion time
			process.setCompletionTime(completionTime); // This function will automatically calculate turnaround time and waiting time			
		}
		System.out.println();
		
		// 3. Display the average turnaround time and waiting time
		Process.displayAverageTurnaroundTimeAndWaitingTime(processes, processes.length);
	}
	
	public static void main(String... args) {
		System.out.printf("%nCPU Scheduling Algorithms%n");
		
		Process.displayProcesses(processes);
		
		firstComeFirstServe(processes, processes.length);		
			
			
		
	}
}
