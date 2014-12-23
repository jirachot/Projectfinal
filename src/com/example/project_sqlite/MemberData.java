package com.example.project_sqlite;

public class MemberData {

	private int id;
	private String fname;
	private String lname;
	private int age;
	//private String status;
	private String address;
	private String bloodgroup;
	private String weight;
	private String height;
	
	public MemberData(int id, String fname, String lname, int age, String address, String bloodgroup, String weight, String height){
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.age = age;
		//this.status = status;
		this.address = address;
		this.bloodgroup = bloodgroup;
		this.weight = weight;
		this.height = height;
	}
	public int getId(){
		return id;
	}
	public String getFname(){
		return fname;
	}
	public String getLname(){
		return lname;
	}
	public int getAge(){
		return age;
	}
	//public String getStatus(){
	//	return status;
	//}
	public String getAddress(){
		return address;
	}
	public String getBloodgroup(){
		return bloodgroup;
	}
	public String getWeight(){
		return weight;
	}
	public String getHeight(){
		return height;
	}
}
