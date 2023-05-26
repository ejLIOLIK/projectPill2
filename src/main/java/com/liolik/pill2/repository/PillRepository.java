package com.liolik.pill2.repository;

import java.util.List;
import java.util.Optional;

import com.liolik.pill2.domin.Pill;

public interface PillRepository {

	//입력
	Pill save(Pill pill);

	//조회
	Optional<Pill> findByCode(int code);
	Optional<Pill> findByName(String name);
	Optional<Pill> findByCompany(String company);
	List<Pill> findAll();

	//수정
	int update(int code, Pill pill);

	//삭제
	int delete(Pill pill);
}
