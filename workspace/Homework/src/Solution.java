class Solution {
    public static int solution(long num) {
        int answer = 0;
        
        while(true){
	        if(num%2==0) {
	        	num = num/2;
	        	answer++;
	        }else{
	        	num = num*3+1;
	        	answer++;
	        }
	        
	        if(answer>=500) answer = -1;
	        if(num==1) break;
        }  
        return answer;
    }
    
    public static void main(String[] args) {
		int result = solution(626331);
		System.out.println(result);
	}
}