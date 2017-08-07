package com.diary.mypet.domain;

import java.sql.Date;

// Diary 테이블 데이터베이스 저장 클래스 

public class DiaryVO {

	// 1. 컬럼 이름과 동일한 변수 선언
	private int no;
	private String title;
	private String content;
	private String image;
	private int readcnt;
	private Date regdate;
	private String id;

	public DiaryVO() {

	}

	// id, no 받는 생성자
	public DiaryVO(int no, String id) {
		this.no = no;
		this.id = id;
	}

	// 2. Get, Set
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	// 3.toString() -> 디버깅용
	@Override
	public String toString() {
		return "DiaryVO [no=" + no + ", title=" + title + ", content=" + content + ", image=" + image + ", readcnt="
				+ readcnt + ", regdate=" + regdate + ", id=" + id + "]";
	}

}
