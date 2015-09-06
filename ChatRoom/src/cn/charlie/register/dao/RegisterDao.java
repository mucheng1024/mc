package cn.charlie.register.dao;

import cn.charlie.bean.Person;
import cn.charlie.utils.PersonXMLUtils;

public class RegisterDao {
	private Person person;
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String execute() throws Exception{
		System.out.println("username = "+person.getUsername());
		System.out.println("password = "+person.getPassword());
		System.out.println("sex = "+person.getSex());
		Person p = new Person();
		p.setUsername(person.getUsername());
		p.setPassword(person.getPassword());
		p.setSex(person.getSex());
		if(PersonXMLUtils.queryXml(person.getUsername())){
			System.out.println("改用户已存在！");
			return "registerFail";
		}
		PersonXMLUtils.insertMember(p);
		return "registerSuccess";
	}
}
