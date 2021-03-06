package hw0104_0108;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
  문제 ) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 student 클래스를 작성한다.
  	   이 클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로 받아서 초기화 처리한다.
  	   
  	  이 Student객체는 List에 저장하여 관리한다.
  	 
  	 List에 저장된 데이터들을 학번의 오름차순으로 정렬 할 수 있는 
  	 내부 정렬 기준을 구현하고, 
  	 총점의 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬되는 
  	 외부 정렬 기준을  클래스를 작성하여 정렬한 결과를 출력하시오.
  	 
  	 (등수는 List에 전체 데이터가 추가된 후에 저장되도록 한다.)
 */

public class StudentTest {

	//등수를 구하는 메서드
	public void setRanking(List<Student> stdlist){
		for(int i=0;i<stdlist.size();i++){//기준데이터를 구하는 반복문
			int rank = 1;//처음에는 1등으로 설정해 놓고 시작한다.
			for(int j=0; j<stdlist.size();j++){//비교 대상을 나타내는 반복문
				//기준보다 큰 값을 만나면 rank값을 증가시킨다.
				if(stdlist.get(i).getTotal() < stdlist.get(j).getTotal()){
					rank++;
				}
			}//for - j
			//구해진 등수를 Student객체의 rank변수에 저장한다.
			stdlist.get(i).setRank(rank);
		}//for - i
	}
	
	public static void main(String[] args) {
		ArrayList<Student> studentlist = new ArrayList<>();
		StudentTest t = new StudentTest();
		
		studentlist.add(new Student(2015, "홍길동", 80, 90, 80));
		studentlist.add(new Student(1011, "이순신", 85, 78, 55));
		studentlist.add(new Student(2031, "성춘향", 80, 90, 80));
		studentlist.add(new Student(2016, "강감찬", 41, 85, 75));
		studentlist.add(new Student(2021, "일지매", 90, 83, 70));
		studentlist.add(new Student(2011, "변학도", 88, 56, 46));
		
		t.setRanking(studentlist);
		
		System.out.println("정렬 전....");
		for(Student student : studentlist){
			System.out.println(student);
		}
		
		System.out.println("------------------------------------------------------------------------");
		System.out.println("번호로만 정렬 후....");
		Collections.sort(studentlist);
		for(Student student : studentlist){
			System.out.println(student);
		}
		
		System.out.println("------------------------------------------------------------------------");
		System.out.println("총점의 역순으로 정렬 후....");
		Collections.sort(studentlist, new desc1());
		for(Student student : studentlist){
			System.out.println(student);
		}
		
		/*System.out.println("랭크 삽입후....");
		Collections.sort(studentlist, new desc1());
		int i = 0;
		for(Student student : studentlist){
			i++;
			student.setRank(i);
			System.out.println(student);
		}
		*/
	}
}//

class Student implements Comparable<Student>{
	private int id;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int total;
	private int rank;
	
	public Student(int id, String name, int kor, int eng, int math) {
		super();
		this.id = id;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.total = kor+eng+math;
		this.rank = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Student [id = " + id + ", name = "+ name + ", 국어 = "
	+kor +", 영어 = "+eng +", 수학 = "+math + ", 총점 = "+total + ", 랭크 = "+rank + "]";
	}

	@Override
	public int compareTo(Student student) {
		return Integer.compare(this.id, student.getId());
	}
}

class desc1 implements Comparator<Student>{
	@Override
	public int compare(Student stu1, Student stu2) {
		if(stu1.getTotal() == stu2.getTotal()){
			return stu1.getName().compareTo(stu2.getName());
		}else{
			return Integer.compare(stu1.getTotal(), stu2.getTotal())*-1;
		}
		
		/*if(stu1.getTotal()> stu2.getTotal()){
			return -1;
		}else if(stu1.getTotal()< stu2.getTotal()){
			return 1;
		}else{
			if(stu1.getName().compareTo(stu2.getName())>0){
				return 1;
			}else if(stu1.getName().compareTo(stu2.getName())<0){
				return -1;
			}else{
				return 0;
			}
		}*/
	}
	
}