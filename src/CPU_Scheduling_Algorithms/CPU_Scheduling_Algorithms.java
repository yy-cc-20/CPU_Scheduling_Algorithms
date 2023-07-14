
/**
 * @description CPU Scheduling Algorithms
 * compare the efficiency of 3 CPU scheduling Algorithms
 * 
 */

package CPU_Scheduling_Algorithms;

public class CPU_Scheduling_Algorithms {
	// Choose to use array because we need random access, but do no need insertion and deletion
	private static Process[] processes = new Process[] {
		new Process('A', 0, 7),
		new Process('B', 3, 3),
		new Process('C', 8, 5),
		new Process('D', 5, 2),
		new Process('E', 9, 4)
	};
	
	private static final int MAX_COMPLETION_TIME = 25;
	
	private static final int QUANTUM_TIME = 4;
	
	public static void main(String... args) {
		System.out.println("===============================");
		System.out.println("CPU Scheduling Algorithms");
		System.out.println("===============================");
		System.out.println();
		
		Processes.displayProcesses(processes);
		
		Processes.firstComeFirstServe(processes, processes.length, MAX_COMPLETION_TIME);		
			
		Processes.shortestProcessFirst(processes, processes.length, MAX_COMPLETION_TIME);
		
		Processes.roundRobbin(processes, processes.length, MAX_COMPLETION_TIME, QUANTUM_TIME);
	}
}
