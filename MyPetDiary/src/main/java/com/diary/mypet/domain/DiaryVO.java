package com.diary.mypet.domain;

import java.sql.Date;

// Diary 테이블 데이터베이스 저장 클래스 

public class DiaryVO {

	// 1. 컬럼 이름과 동일한 변수 선언
	private int no;
	private String title;
	private String content;
	private String nickname;
	private int readcnt;
	private Date regdate;

	// 2. Getter, Setter 메소드 생성
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getReadcnt() {
		return readcnt;
	}

	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	// 3. toString() 재정의 -> 코드 테스트를 위해
	@Override
	public String toString() {
		return "DiaryVO [no=" + no + ", title=" + title + ", content=" + content + ", nickname=" + nickname
				+ ", readcnt=" + readcnt + ", regdate=" + regdate + "]";
	}

}
