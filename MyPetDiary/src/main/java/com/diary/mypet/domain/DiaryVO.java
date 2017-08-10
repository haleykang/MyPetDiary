package com.diary.mypet.domain;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;

// Diary 테이블 데이터 저

/**
 * @author Hailey
 *
 */
public class DiaryVO {

	// 1. 변수 선
	private int no;
	private String title;
	private String content;
	private String image;
	private int readcnt;
	private Date regdate;
	private String id;

	// 공유 컬럼 추가
	private String ckshare;

	public DiaryVO() {

	}

	// 생성
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
		// 인코딩... 제발..
		// try {
		// this.image = URLEncoder.encode(image, "utf-8");
		//
		// } catch (UnsupportedEncodingException e) {
		// e.printStackTrace();
		// }

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

	public String getCkshare() {
		return ckshare;
	}

	public void setCkshare(String ckshare) {
		this.ckshare = ckshare;
	}

	// 3.toString()
	@Override
	public String toString() {
		return "DiaryVO [no=" + no + ", title=" + title + ", content=" + content + ", image=" + image + ", readcnt="
				+ readcnt + ", regdate=" + regdate + ", id=" + id + ", ckshare=" + ckshare + "]";
	}

}
