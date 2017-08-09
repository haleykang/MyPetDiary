package com.diary.mypet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diary.mypet.dao.DUserDao;
import com.diary.mypet.domain.DUserVO;

@Service
public class DUserServiceImpl implements DUserService {

	@Autowired
	private DUserDao dao;

	@Override
	public DUserVO login(DUserVO vo) {

		return dao.login(vo);
	}

}
