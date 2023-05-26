package com.liolik.pill2.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.transaction.annotation.Transactional;

import com.liolik.pill2.domin.Pill;

//Jpa
@Transactional
public class JpaPillRepository implements PillRepository {

	//Jpa
	private final EntityManager entMan;
	
	//생성자
	public JpaPillRepository(EntityManager entMan) {
		this.entMan = entMan;
	}
	
	//메소드
	//입력 
	//insert into pill (name, company, price, img) values ( , , , );
	@Override
	public Pill save(Pill pill) {
		entMan.persist(pill);
		return pill;
	}

	//조회
	@Override
	public Optional<Pill> findByCode(int code) {
		Pill pill = entMan.find(Pill.class, code);
		// ofNullable 비어있을 가능성 있는 객체
		return Optional.ofNullable(pill);
	}

	@Override
	public Optional<Pill> findByName(String name) {
		List<Pill> result = entMan.createQuery("select m from Pill m where m.name = :name", Pill.class)
				.setParameter("name", name)
				.getResultList();
		return result.stream().findAny();
	}

	@Override
	public Optional<Pill> findByCompany(String company) {
		List<Pill> result = entMan.createQuery("select m from Pill m where m.company = :company", Pill.class)
				.setParameter("company", company)
				.getResultList();
		return result.stream().findAny();
	}

	@Override
	public List<Pill> findAll() {
		return entMan.createQuery("select m from Pill m", Pill.class).getResultList();
	}

	//수정
	@Override
	public int update(int code, Pill pill) {
		return entMan.createQuery("update Pill m set m.name = :name, m.company = :company, m.price = :price, m.img = :img where m.code = :code")
				.setParameter("code", code)
				.setParameter("name", pill.getName())
				.setParameter("company", pill.getCompany())
				.setParameter("price", pill.getPrice())
				.setParameter("img", pill.getImg())
				.executeUpdate();
	}

	//삭제
	@Override
	public int delete(Pill pill) {
		return entMan.createQuery("delete from Pill m where m.code = :code")
			.setParameter("code", pill.getCode())
			.executeUpdate();
	}

}
