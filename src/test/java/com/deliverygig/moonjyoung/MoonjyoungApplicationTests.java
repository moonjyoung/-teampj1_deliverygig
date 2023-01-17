package com.deliverygig.moonjyoung;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.deliverygig.moonjyoung.entity.account.CustomerInfoEntity;
import com.deliverygig.moonjyoung.repository.account.CustomerRepository;
import com.deliverygig.moonjyoung.repository.delivery.PickUpAreaRepository;
import com.deliverygig.moonjyoung.repository.delivery.UnivInfoRepository;
import com.deliverygig.moonjyoung.vo.delivery.PickUpAreaVO;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class MoonjyoungApplicationTests {
	@Autowired PickUpAreaRepository pickUpAreaRepository;
	@Autowired UnivInfoRepository univInfoRepository;
	@Autowired CustomerRepository cRepo;

	@Transactional
	@Test
	void customerAccountAdd() {
		CustomerInfoEntity account = CustomerInfoEntity.builder().ciId("wrko613").ciPwd("1234").ciName("이영은")
				.ciNickName("영니").ciPhone("010-4944-5209").ciJoinDt(LocalDate.now()).ciEmail("duddms613@naver.com")
				.ciUiSeq(1L).ciBirthday(LocalDate.of(1997, 6, 13)).ciStatus(1).build();
		cRepo.save(account);
		log.info("accout={}", account);
	}
	@Test
		void testLogin() {
			String id = "wrk613";
			String pwd = "duddms4944";
			CustomerInfoEntity loginUser = cRepo.findByCiIdAndCiPwd(id, pwd);
			assertNotEquals(loginUser, null); // 로그인 유저가 null과 같지 않으면 통과
		}
		@Test
		void testLogOut() {

		}

	@Test
	public void addUniv() { // 새로운 대학 추가 test
		PickUpAreaVO vo = new PickUpAreaVO();
		vo.setUiName("testname");
		univInfoRepository.save(vo.toUnivInfoEntity());
	}

}
