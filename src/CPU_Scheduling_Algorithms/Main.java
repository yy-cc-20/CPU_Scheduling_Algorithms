
/**
 * @description CPU Scheduling Algorithms
 * compare the efficiency of 3 CPU scheduling Algorithms
 * 
 */

package CPU_Scheduling_Algorithms;

import java.util.Scanner;

public class Main {
	// Choose to use array because we need random access, but do no need insertion and deletion
	private static Process[] processes = new Process[] {
		new Process('A', 0, 7),
		new Process('B', 3, 3),
		new Process('C', 8, 5),
		new Process('D', 5, 2),
		new Process('E', 9, 4)
	};
	
	private static final int MAX_COMPLETION_TIME = 21;
	
	private static final int QUANTUM_TIME = 4;
	
	public static final int SCREEN_HEIGHT = 40;
	public static final Scanner scanner = new Scanner(System.in);
	
	public static void clearScreen() {
		Main.scanner.nextLine();
		for (int i = 0; i < Main.SCREEN_HEIGHT; ++i)
			System.out.println();
	}
	
	public static void main(String... args) {
		System.out.println("===============================");
		System.out.println("CPU Scheduling Algorithms");
		System.out.println("===============================");
		System.out.println();
		
		Processes.displayProcesses(processes);
		System.out.println("Press [ENTER] to continue.");
		
		//Processes.firstComeFirstServeStimulation(processes, processes.length, MAX_COMPLETION_TIME);		
		//Processes.firstComeFirstServeSummary(processes, processes.length, MAX_COMPLETION_TIME);		
		//Main.clearScreen();
		
		//Processes.shortestProcessFirstStimulation(processes, processes.length, MAX_COMPLETION_TIME);
		//Processes.shortestProcessFirstSummary(processes, processes.length, MAX_COMPLETION_TIME);
		//Main.clearScreen();
		
		Processes.roundRobbinStimulation(processes, processes.length, MAX_COMPLETION_TIME, QUANTUM_TIME);
		Processes.roundRobbinSummary(processes, processes.length, MAX_COMPLETION_TIME, QUANTUM_TIME);
		Main.clearScreen();
		
		scanner.close();
	}
}
