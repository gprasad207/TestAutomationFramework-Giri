package com.home.request.pojo;

public class UsersPojo {
	
private int id;
private	String name;
private	String email;
private	Address address;
private	Geo geo;
private	Company company;
private	String phone;
private	String website;


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public Address getAddress() {
	return address;
}
public void setAddress(Address address) {
	this.address = address;
}
public Geo getGeo() {
	return geo;
}
public void setGeo(Geo geo) {
	this.geo = geo;
}
public Company getCompany() {
	return company;
}
public void setCompany(Company company) {
	this.company = company;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getWebsite() {
	return website;
}
public void setWebsite(String website) {
	this.website = website;
}

}
