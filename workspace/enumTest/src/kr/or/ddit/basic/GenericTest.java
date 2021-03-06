package kr.or.ddit.basic;

class NonGenericClass{//일반적으로 그동안 제네릭을 사용하지 않고 사용한 경우
	private Object value;
	
	public void setValue(Object value){
		this.value = value;
	}

	public Object getValue() {
		return value;
	}
	
	/*get()과 set() 쓰는 이유 : 우리는 필드로 무언인가를 선언할 때, 
	 	일반적으로는 프라이빗 접근제한자를 사용한다(고 한다 → 클래스를 선언할 때 필드는 일반적으로 접근 제한을 한다. )
		이는 필드의 값을 보호하기 위함으로 private 접근 제한자로 보호하게 되면 
		외부에서 필드의 값을 읽거나 쓸수가 없게 되므로 getter와 setter를 추가적으로 제공하여
		필드의 값도 보호하면서 외부에서 필드의 값을 읽고 변경을 할 수 있도록 방법을 제공해주기 위함이다.
		
		또한, private를 해놓고 if를 통해 다른클래스에서  값을 받을때 범위에 따라 제한해서 받을 수 있다.*/
}

/*
 	제네릭 클래스를 만드는 방법
 	형식) 
 	Class 클래스명<제네릭타입글자>{
 		제네릭타입글자 변수명;		// 변수 선언에 제네릭을 사용할 경우
 		...
 		
 		제네릭타입글자 메서드명(){  // 반환값이 있는 메서에서 사용할 경우
 			...
 			return 값;
 		}
 		
 		void 메서드명(제네릭타입글자 변수명){ // 메서드의 매개변수에 제네릭을 사용할 경우
 			...
 		}
 	}
 	
 	-- 제네릭 타입 글자로 많이 사용되는 것--
 	T ==> Type
 	K ==> Key
 	V ==> Value
 	E ==> Element(자료구조에 들어가는 것들을 주로 나타낸다)
 	
 */
class MyGeneric<T>{
	private T value;
	
	public void setValue(T value){
		this.value = value;
	}
	
	public T getValue(){
		return value;
	}
	
	//만들때는 반환타입을 정하지 않고, 컴파일 할떄 반환타입이 정한다.
}

public class GenericTest {
	public static void main(String[] args) {
		//제네릭을 사용하지 않은 경우(형변환이 필요)
		NonGenericClass ng1 = new NonGenericClass();
		ng1.setValue("가나다라");
		
		NonGenericClass ng2 = new NonGenericClass();
		ng2.setValue(100);//set을 할때 Object를 했기 때문에 아무 값이나 집어넣을수 있다.
		
		String rtnNg1 = (String) ng1.getValue();
		System.out.println("문자열 반환값 : " + rtnNg1);
		//getValue()를 하면 Object형으로 만들었기때문에 Object형이 변환딤
		//하지만 부모쪽 데이터를 자식쪽 데이터를 넣으려고 할때 형이 다르기  때문에 오류가 남
		
		int rtnNg2 = (int) ng2.getValue();
		System.out.println("정수 반환값 : " + rtnNg2);
		System.out.println();
		
		//String rtnNg3 = (String)ng2.getValue();
		//System.out.println("rtnNg3 : " + rtnNg3);
		//문법적으로 에러가 발생하지 않으나, 실행하면 에러 발생 
		
		System.out.println("----------------------------------------");
		System.out.println();
		
		//제네릭을 사용하는 경우(형변환 필요x)
		MyGeneric<String> mg1 = new MyGeneric<>();
		MyGeneric<Integer> mg2 = new MyGeneric<>();
		
		mg1.setValue("우리나라");
		mg2.setValue(500);
		
		String rtnMg1 = mg1.getValue();
		int rtnMg2 = mg2.getValue();
		
		//mg1.setValue(200);
		//mg2.setValue("가나다라");
		//바로 오류가 뜸으로써 오류 잡기가 수월해진다.
		
		System.out.println("문자열 반환값 : " + rtnMg1);
		System.out.println("정수 반환값 : " + rtnMg2);
	}
}
