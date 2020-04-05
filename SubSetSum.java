import java.util.Random;
import java.util.Scanner;

public class SubSetSum {
	
	static int[] num; // My random number array
	static int n = 1; // The n number user choose
	static long nano_startTime;
	static long nano_endTime;
	private static Scanner input;
	
	public static boolean subSetDP(int[] A, int sum) {
		
		boolean[][] matrixTable = new boolean[A.length + 1][sum + 1];	
		
		// if sum is not zero and subset is 0, we can't make it 
		for (int i=1; i<=sum; i++) {
			matrixTable[0][i]=false;
		}
		// if sum is 0 the we can make the empty subset to make sum 0
		for(int i=0;i<=A.length;i++){
			matrixTable[i][0]=true;
		}
		
		
		
		for(int i=1; i<=A.length; i++) {
			
			for(int j=1; j<=sum; j++) {	
				
				//first copy the data from the top
				matrixTable[i][j] = matrixTable[i-1][j];				
				//If solution[i][j]==false check if can be made
				if(matrixTable[i][j]==false && j>=A[i-1])
				{
					matrixTable[i][j] = matrixTable[i][j] || matrixTable[i-1][j-A[i-1]];		
				}
			}
		}		
		return matrixTable[A.length][sum];
	}
	
	public static void randomArray(int n) 
	{
		num = new int[n];
		Random r = new Random();
		int Low = 1;
		int High = 10;
		
		for (int i = 0; i < n; i++) {
			num[i] = r.nextInt(High - Low) + Low;
		}
		
	}
	

	public static void main(String[] args) {
		
		//int[] A = { 1, 2, 4, 5, 9};
		
		//System.out.println("\nFrom DP: " + subSetDP(A, 15) );
		
        System.out.print("Enter 0 to stop program " +"\n");
		
		while(n != 0)
		{		
			System.out.print("Please enter new n number or 0 to stop: ");
		
			input = new Scanner(System.in); //Input from the user       
			n = input.nextInt();                
			randomArray(n); //Call randomArray to create random size array
			
			nano_startTime = System.nanoTime(); // By calling nano_startTime it will count the execution time
	        boolean result = subSetDP(num, 15);         
	        nano_endTime = System.nanoTime();  // By calling nano_endTime it will stop counting the execution time
			
	        System.out.println(" ");
	        
			if ( result == true )
			{			
				System.out.println("Found a subset with given sum");
				System.out.println("\nTime taken by DP subset sum: " + (nano_endTime - nano_startTime)+ " ns"); //Display execution time
			}
			
			else
			{
				System.out.println("No subset found with given sum");
				System.out.println("\nTime taken by DP subset sum: " + (nano_endTime - nano_startTime)+ " ns"); //Display execution time
			}
			
			System.out.println("****************************************** ");
		}  
		
		System.out.print("merge sort program end\n");
		
		
		
	}

}