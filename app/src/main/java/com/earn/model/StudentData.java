package com.earn.model;

import java.util.ArrayList;

public class StudentData {

	private int status;
	public int getStatus(){
		return status;
	}
	public void setStatus(int status){
		this.status = status;
	}


	private ArrayList<Students> students;
	
	public ArrayList<Students> getStudents(){
		return students;
	}
	
	public void setStudents(ArrayList<Students> students){
		this.students = students;
	}
	


	public class Students {
		private String name;
		private String myselfMoney;
		public Students(){

		}
		public String getName(){
			return name;
		}
		public void setName(String name){
			this.name = name;
		}

		public String getMyselfMoney(){
			return myselfMoney;
		}
		public void setMyselfMoney(String myselfMoney)
		{
			this.myselfMoney = myselfMoney;
		}


		@Override
		public String toString() {
			return "Students{" +
					"name='" + name + '\'' +
					", money=" + myselfMoney +
					'}';
		}
	}

}

