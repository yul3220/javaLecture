import java.util.*;

public class Solution {
    public static int solution(int n) {
        int answer = 0;
        
        while(n >= 1) {
			answer += n % 10;
			n /= 10;
		}
        
        System.out.println("Hello Java");

        return answer;
    }
    
    public static void main(String[] args) {
		int n = 123;
		int result = solution(n);
		System.out.println(result);
	}
}
