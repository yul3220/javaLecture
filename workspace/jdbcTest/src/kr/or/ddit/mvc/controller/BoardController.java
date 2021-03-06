package kr.or.ddit.mvc.controller;

import java.util.List;
import java.util.Scanner;
import kr.or.ddit.mvc.service.BoardServiceImpl;
import kr.or.ddit.mvc.service.IBoardService;
import kr.or.ddit.mvc.vo.BoardVO;

public class BoardController {
	private Scanner scan = new Scanner(System.in);
	private IBoardService service;
	int boardNo;

	public BoardController(){
		//service = new BoardServiceImpl();
		service = BoardServiceImpl.getInstance();//싱글톤(01.29)
	}

	public static void main(String[] args){
		new BoardController().Start();
	}

	private int displayMenu(){
		System.out.println();
		System.out.println("-------------------------------------------------------------");
		System.out.println("No\t제 목\t\t작성자\t조회수   ");
		System.out.println("-------------------------------------------------------------");
		displayBoard();
		System.out.println("-------------------------------------------------------------");
		System.out.println("메뉴 : 1. 새글작성     2. 게시글보기    3. 검색    0. 작업끝");
		System.out.print("작업선택>> "); 
		int num = scan.nextInt();
		System.out.println();
		return num;
	}

	private void Start() {
		while(true){
			int choice = displayMenu(); 

			switch(choice){
			case 1: // 추가
				insertBoard();
				break;
			case 2:
				showBoard();
				break;
			case 3:
				searchBoard();
				break;
			case 0: // 작업 끝
				System.out.println("작업을 마칩니다.");
				return;
			default:
				System.out.println("번호를 잘못 입력했습니다.");
				System.out.println("다시 입력하세요.");
			}
		}
	}

	private void displayBoard() {
		List<BoardVO> boardList = service.AllBoard();

		if(boardList==null || boardList.size()==0){//null이면 오류가 뜨는 경우도 있기때문에 검사를 해야한다.
			System.out.println("등록된 게시물이 하나도 없습니다.");
		}else{
			for(BoardVO boardVo : boardList){
				System.out.print(boardVo.getBoard_no()+"\t");
				System.out.print(boardVo.getBoard_title()+"\t");
				System.out.print(boardVo.getBoard_writer()+"\t");
				System.out.println(boardVo.getBoard_cnt()+"\t");
			}
		}
	}

	private void insertBoard(){
		System.out.println();
		System.out.println("새글 작성하기");
		System.out.println("-----------------------------------");

		scan.nextLine();
		System.out.print("- 제  목 : ");
		String title = scan.nextLine();

		System.out.print("- 작성자 : ");
		String writer = scan.nextLine();

		System.out.print("- 내  용 : ");
		String content = scan.nextLine();

		BoardVO boardVo = new BoardVO();
		boardVo.setBoard_title(title);
		boardVo.setBoard_writer(writer);
		boardVo.setBoard_content(content);

		int cnt = service.insertBoard(boardVo);

		if(cnt>0){
			System.out.println("새글이 작성되었습니다.");
		}else{
			System.out.println("새글의 작성이 실패했습니다.");
		}
	}

	private void searchBoard(){
		scan.nextLine();
		System.out.println("검색 작업");
		System.out.println("--------------------------------------------");
		System.out.print("- 검색할 제목 입력 : ");
		String title = scan.nextLine();

		if(title == ""){
			return;
		}

		List<BoardVO> boardList = service.SearchBoard(title);

		if(boardList==null || boardList.size()==0){//null이면 오류가 뜨는 경우도 있기때문에 검사를 해야한다.
			System.out.println("검색된 게시글이 없습니다.");
			return;
		}else{
			System.out.println();
			System.out.println("-------------------------------------------------------------");
			System.out.println("No	        제 목            작성자 	조회수   ");
			System.out.println("-------------------------------------------------------------");
			for(BoardVO boardVo : boardList){
				System.out.print(boardVo.getBoard_no()+"\t");
				System.out.print(boardVo.getBoard_title()+"\t");
				System.out.print(boardVo.getBoard_writer()+"\t");
				System.out.println(boardVo.getBoard_cnt()+"\t");
			}
			System.out.println("-------------------------------------------------------------");
			System.out.println("메뉴 : 1. 새글작성     2. 게시글보기    3. 검색    0. 작업끝");
			System.out.print("작업선택>> "); 
			int num = scan.nextInt();

			switch(num){
				case 1: // 추가
					insertBoard();
					break;
				case 2:
					showBoard();
					break;
				case 3:
					searchBoard();
					break;
				case 0: // 작업 끝
					System.exit(0);
				default:
					System.out.println("번호를 잘못 입력했습니다.");
					System.out.println("다시 입력하세요.");
				}
		}
	}

	private void showBoard() {
		System.out.println();
		System.out.print("보기를 원하는 게시물 번호 입력 >> ");
		boardNo = scan.nextInt();
		int count = service.existBoard(boardNo);
		if(count<=0){
			System.out.println(boardNo+"번의 게시물이 존재하지 않습니다.");
		}else{
			service.CountUp(boardNo);
			BoardVO boardVo = service.showBoard(boardNo);
			System.out.println();
			System.out.println(boardVo.getBoard_no() + "번글 내용");
			System.out.println(" ------------------------------------------------------------");
			System.out.println("제   목 : " + boardVo.getBoard_title());
			System.out.println("작성자 : " + boardVo.getBoard_writer());
			System.out.println("내  용  : " + boardVo.getBoard_content());
			System.out.println("작성일 : " + boardVo.getBoard_date());
			System.out.println("조회수 : " + boardVo.getBoard_cnt());
			System.out.println(" ------------------------------------------------------------");
			System.out.println("메뉴 : 1. 수정    2. 삭제    3. 리스트로 가기");
			System.out.print("작업선택 >> ");
			int input = scan.nextInt();

			switch(input){
				case 1:
					updateBoard();
					break;
				case 2:
					deleteBoard();
					break;
				case 3:
					return;
				default:
					System.out.println("번호를 잘못 입력했습니다.");
					System.out.println("다시 입력하세요.");
				}
		}
	}

	private void updateBoard() {
		scan.nextLine();
		System.out.println("수정 작업하기");
		System.out.println("-----------------------------------");
		System.out.print("- 제 목 : ");
		String title = scan.nextLine();
		System.out.print("- 내 용 : ");
		String content = scan.nextLine();

		BoardVO boardVo = new BoardVO();
		boardVo.setBoard_title(title);
		boardVo.setBoard_content(content);
		boardVo.setBoard_no(boardNo);
		int cnt = service.updateBoard(boardVo);

		if(cnt>0){
			System.out.println(boardNo+"번 글이 수정되었습니다.");
		}else{
			System.out.println(boardNo+"번 글이 수정 실패했습니다.");
		}
	}

	private void deleteBoard() {
		int cnt = service.deleteBoard(boardNo);

		if(cnt>0){
			System.out.println(boardNo+"번글이 삭제되었습니다.");
		}else{
			System.out.println(boardNo+"번글이 삭제 실패 되었습니다.");
		}
	}

}//controller끝
