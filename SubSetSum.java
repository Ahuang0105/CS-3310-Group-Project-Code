import java.util.Random;
import java.util.Scanner;

public class SubSetSum {
	
	static int[] num; // My random number array
	static int n = 1; // The n number user choose
	static long nano_startTime;
	static long nano_endTime;
	static long nano_startTime2;
	static long nano_endTime2;
	
	private static Scanner input;
	
	public static boolean subsetSumDP (int[] testSet, int sum) {
		
		boolean[][] matrixTable = new boolean[testSet.length + 1][sum + 1];	
		 
		//First set first row to all false
		for (int i=1; i<=sum; i++) 
		{
			matrixTable[0][i]=false;
		}

		//Second set first column all true
		for(int i=0;i<=testSet.length;i++)
		{
			matrixTable[i][0]=true;
		}
				
		//Use dynamic programming approach to look for the match subset
		for(int i=1; i<=testSet.length; i++) 
		{			
			for(int j=1; j<=sum; j++) {	
				
				matrixTable[i][j] = matrixTable[i-1][j];				
				
				if(matrixTable[i][j]==false && j>=testSet[i-1])
				{
					matrixTable[i][j] = matrixTable[i][j] || matrixTable[i-1][j-testSet[i-1]];		
				}
			}
		}		
		
		return matrixTable[testSet.length][sum];
	}
	
	static boolean subsetSumRecursive(int testSet[],  int n, int sum) 
	{ 

		if (sum == 0) 
		{
			return true; 
		}

		if (n == 0 && sum != 0) 
		{
			return false; 
		}
		
		if (testSet[n-1] > sum) 
		{
			return subsetSumRecursive(testSet, n-1, sum); 
		}
		return subsetSumRecursive(testSet, n-1, sum) ||  subsetSumRecursive(testSet, n-1, sum-testSet[n-1]); 

	} 
	
	//This method will create random array
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
		
        System.out.print("Enter 1 to use  " +"\n");
		
		while(n != 0)
		{		
			System.out.print("Please enter new n number or 0 to stop: ");
		
			input = new Scanner(System.in); //Input from the user       
			n = input.nextInt();                
			randomArray(n); //Call randomArray to create random size array
			
			nano_startTime = System.nanoTime(); // By calling nano_startTime it will count the execution time
	        boolean result = subsetSumDP(num, 18);         
	        nano_endTime = System.nanoTime();  // By calling nano_endTime it will stop counting the execution time
	        
	        nano_startTime2 = System.nanoTime(); // By calling nano_startTime it will count the execution time
	        boolean result2 = subsetSumRecursive(num, num.length, 18);         
	        nano_endTime2 = System.nanoTime();  // By calling nano_endTime it will stop counting the execution time
			
	        System.out.println(" ");
	        
			if ( result == true && result2 == true )
			{			
				System.out.println("************************************************ ");
				System.out.println("Found a subset with given sum                  ");
				System.out.println("Time taken by DP subset sum: " + (nano_endTime - nano_startTime)+ " ns"); //Display execution time
				System.out.println("Time taken by recursive subset sum: " + (nano_endTime2 - nano_startTime2)+ " ns"); //Display execution time
				System.out.println("************************************************ ");
			}
			
			else if( result == false && result2 == false )
			{
				System.out.println("************************************************ ");
				System.out.println("No subset found with given sum                * ");
				System.out.println("Time taken by DP subset sum: " + (nano_endTime - nano_startTime)+ " ns"); //Display execution time
				System.out.println("Time taken by recursive subset sum: " + (nano_endTime2 - nano_startTime2)+ " ns"); //Display execution time
				System.out.println("************************************************ ");
			}
			else 
			{
				System.out.print("Error one of the program has different result");
			}
			
		}  
	}

}
