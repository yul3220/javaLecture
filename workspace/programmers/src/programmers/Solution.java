package programmers;

import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
	 public static int solution(int n) {
	        int answer = 0;
	        
	        boolean[] primeList = new boolean[n+1];
	        ArrayList<Integer> prime = new ArrayList<Integer>();
	        
	        primeList[0] = false;
	        primeList[1] = false;
	        
	        for(int i =2; i<=n; i++) {
	            primeList[i] = true;
	        }
	        
	        for(int i=2; i<n+1; i++) {
	            if(primeList[i]) {
	                prime.add(i);
	                for(int j=2*i; j<=n; j+=i) {
	                    primeList[j] = false;
	                }
	            }
	        }
	        answer = prime.size();
	        
	        System.out.println(primeList.toString());
	        return answer;
	    }
	 
	 public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);
	     int n = sc.nextInt();
	     int a = solution(n);
	     System.out.println(a);
	}
}
