package com.liolik.pill2.service;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.liolik.pill2.repository.JpaPillRepository;
import com.liolik.pill2.repository.PillRepository;

@Configuration
public class SpringConfig {

	private EntityManager entMan;
	
	// Jap DB 설정
	@Autowired
	public SpringConfig(EntityManager entMan) {
		this.entMan = entMan;
	}
	
	@Bean
	public PillRepository pillRepositrory() {
		return new JpaPillRepository(entMan);
	}
}
