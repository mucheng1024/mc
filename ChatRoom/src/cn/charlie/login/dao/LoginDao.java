package cn.charlie.login.dao;

import com.opensymphony.xwork2.ActionSupport;

import cn.charlie.bean.Person;
import cn.charlie.utils.PersonXMLUtils;

public class LoginDao extends ActionSupport{
	private Person person;
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String execute() throws Exception{
		return "success";
	}
	
	public void validateExecute(){
		if(this.person.getUsername()==null || "".equals(this.person.getUsername().trim())){
			this.addFieldError("username", "用户名不能为空");
		}
		if(this.person.getPassword()==null || "".equals(this.person.getPassword().trim())){
			this.addFieldError("password", "密码不能为空");
		}
		if(!PersonXMLUtils.queryXml(person.getUsername())){
			if(this.person.getUsername()==null || "".equals(this.person.getUsername().trim())){
				
			}else{
				System.out.println("该用户不存在！");
				this.addFieldError("notExist", "该用户不存在");
			}		
		}else{
			Person member = PersonXMLUtils.queryXmlMember(this.person.getUsername());
			if(!member.getPassword().equals(this.person.getPassword())){
				System.out.println("密码不正确！");
				this.addFieldError("passwordError", "密码不正确，请重新输入");
			}
		}
		
		if(this.getFieldErrors().get("username")!=null){
			this.addFieldError("error", "用户名不能为空");
		}else{
			if(this.getFieldErrors().get("password")!=null){
				this.addFieldError("error", "密码不能为空");
			}else{
				if(this.getFieldErrors().get("notExist")!=null){
					this.addFieldError("error", "该用户不存在");
				}else if(this.getFieldErrors().get("passwordError")!=null){
					this.addFieldError("error", "密码不正确，请重新输入");
				}
			}
		}
	}
}
