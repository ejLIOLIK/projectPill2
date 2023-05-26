package com.liolik.pill2.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.liolik.pill2.domin.Pill;

//DB데이터 롤백
//SpringConfig가 실행된 상태여야 JpaRepository주입이 가능해서 @SpringBootTest 이 어노테이션 없이는 테스트가 안됐던 거 같음 (예상)
//테스트 너무 오래 걸리는데 방법 없는지.
@SpringBootTest
@Transactional
public class PillRepositoryTest {
	
	@Autowired
	PillRepository pillRepositrory;
	
	@BeforeEach
	public void beforeEach() {
		Pill pill1 = new Pill("testName1", "testCampany1", 100, "noImg1");
		Pill pill2 = new Pill("testName2", "testCampany2", 200, "noImg2");
		
		pillRepositrory.save(pill1);
		pillRepositrory.save(pill2);
	}
		
	@Test
	public void 쓰기() {
		Pill pill = new Pill("testName", "testCampany", 50, "noImg");
		
		Pill result = pillRepositrory.save(pill);
		
		// 코드는 자동생성이라 비교하지 않았음.
		assertThat(result.getName()).isEqualTo(pill.getName());
	}

	@Test
	public void 조회_코드() {
		// 새 데이터 입력
		Pill pill = new Pill("testName", "testCampany", 50, "noImg");		
		Pill resultSave = pillRepositrory.save(pill); 
		// 해당 데이터 코드 받아옴
		Pill resultFindCode = pillRepositrory.findByCode(resultSave.getCode()).get();

		// 최초 입력한 데이터와 코드로 불러온 데이터가 일치하는지.
		assertThat(resultFindCode).isEqualTo(resultSave);
	}

	@Test
	public void 조회_이름() {
		Pill result = pillRepositrory.findByName("testName1").get();
		assertThat(result.getName()).isEqualTo("testName1");
	}

	@Test
	public void 조회_회사() {
		Pill result = pillRepositrory.findByCompany("testCampany1").get();
		assertThat(result.getCompany()).isEqualTo("testCampany1");
	}

	@Test
	public void 조회_전체() {
		List<Pill> result = pillRepositrory.findAll();
		assertThat(result.size()).isEqualTo(2);
	}

	// @Transactional 을 주석처리하니까 테스트가 성공하고 DB에서도 데이터 수정된 거 확인 가능했음
	// >>롤백 시점 문제인지?
	// executeUpdate 결과값 받아오는 방식으로 변경함
	@Test
	public void 수정() {
		Pill pill = new Pill("updateName", "updateCompany", 999, "updateNoImg");
		int code = pillRepositrory.findByName("testName1").get().getCode();

		int result = pillRepositrory.update(code, pill);
		
		assertThat(result).isEqualTo(1);
	}

	// null 이 아니라 Optional 의 empty로 체크 (boolean 반환)
	@Test
	public void 삭제_데이터확인() {
		Pill pill = new Pill("testName", "testCampany", 50, "noImg");
		Pill resultSave = pillRepositrory.save(pill);
	
		// 삭제한 코드의 데이터가 비어있는지
		Optional<Pill> resultDelete = pillRepositrory.findByCode(resultSave.getCode());
		assertThat(resultDelete.isEmpty());
	}

	@Test
	public void 삭제_리턴값확인() {
		Pill pill = new Pill("testName", "testCampany", 50, "noImg");
		Pill resultSave = pillRepositrory.save(pill);
		
		int resultDelete = pillRepositrory.delete(resultSave);
		
		// executeUpdate 결과값 확인
		assertThat(resultDelete).isEqualTo(1);
	}
}
