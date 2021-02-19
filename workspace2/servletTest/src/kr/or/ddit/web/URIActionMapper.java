package kr.or.ddit.web;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

// uri_mapping.properties파일에 설정된
// uri에 맞는 Action객체를 구해와서 그 Action객체의 
// 인스턴스를 생성해서 반환하는 역할을 수행하는 클래스
public class URIActionMapper {
	// uri_mapping.properties파일의 내용을 읽어와
	// uri값은 key값으로, action객체명은 value값으로 하는
	// Map객체를 생성한다.
	private static Map<String, String> actionMap =
			new HashMap<String, String>();
	
	static {
		// properties파일을 읽어와 Bundle객체를 생성한다.
		ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.web.uri_mapping");
		
		// Bundle객체의 key값들 가져오기
		Enumeration<String> en = bundle.getKeys();//getKeys()가 번들에 있는 키값을 다 가져오는 메소드
		while(en.hasMoreElements()) {
			String key = en.nextElement(); // 키값 1개 가져오기(키값-요청 uri)
			String value = bundle.getString(key); // value값으로 Action객체명을 가져온다.
			
			// key값과 value값을 Map에 추가한다.
			actionMap.put(key, value);
		}
	}// 초기화 블럭 끝...
	
	// 인수값으로 주어진 uri에 맞는 Action객체를 찾아서
	// 그 Action객체의 인스턴스를 생성해서 반환하는 메서드
	public static IAction getAction(String uri) {
		IAction action = null;
		
		// 인수값으로 넘어온 uri가 properties파일에 등록된것인지 확인
		if(actionMap.containsKey(uri)) {
			try {
				// 문자열로된 Action객체의 이름을 이용해서 해당 클래스 메모리에 로딩한다.
				Class cls = Class.forName(actionMap.get(uri));//Class.forName => jdbc할때의 경로의 문자열을 작성했음
				
				// 메모리에 로딩된 Action클래스를 '인스턴스화'한다.
				// ==> 즉, 객체를 생성한다.
				action = (IAction) cls.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}// if문 끝
		
		return action;
	}
}