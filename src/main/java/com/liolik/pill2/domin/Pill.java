package com.liolik.pill2.domin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Jpa
@Entity
public class Pill {
	
	// 의약품 키, 이름, 제조사, 가격, 이미지
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int code;
	
	private String name;
	private String company;
	private int price;
	private String img;
	
	public Pill() {}
	
	public Pill(String name, String company, int price, String img) {
		this.name = name;
		this.company = company;
		this.price = price;
		this.img = img;
	}
	
	// getter setter
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
}
