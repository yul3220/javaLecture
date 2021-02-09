class Solution {
    public static long solution(long n) {
        long answer = -1;
        double x = Math.sqrt(n);
        if(x/(int)x==1.0) {
        	answer = (long) Math.pow(x+1, 2);
        }
        
        return answer;
    }
    
    public static void main(String[] args) {
		long result = solution(3);
		System.out.println(result);
	}
}

