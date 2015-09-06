package cn.charlie.utils;

import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.crimson.tree.XmlDocument;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import cn.charlie.bean.Person;

public class PersonXMLUtils {
	/**
	 * 遍历xml文档
	 */
	public static void queryXmlAll(){
		try{
			//得到DOM解析器的工厂实例
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			//从DOM工厂中获得DOM解析器
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			//把要解析的xml文档读入DOM解析器
			Document doc = dbBuilder.parse("src/cn/charlie/xml/member.xml");
			//System.out.println("处理该文档的DomImplementation对象 = "+doc.getImplementation());
			//得到文档名称为member的元素的节点列表
			NodeList nList = doc.getElementsByTagName("member");
			//遍历该集合，显示集合中的元素及其子元素的名字
			for(int i=0;i<nList.getLength();i++){
				Element node = (Element) nList.item(i);
				System.out.println("username: "+node.getElementsByTagName("username").item(0).getFirstChild().getNodeValue());
				System.out.println("password: "+node.getElementsByTagName("password").item(0).getFirstChild().getNodeValue());
				System.out.println("sex: "+node.getElementsByTagName("sex").item(0).getFirstChild().getNodeValue());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 判断指定username的member是否存在,存在返回true，不存在返回false
	 */
	public static boolean queryXml(String username){
		try{
			//得到DOM解析器的工厂实例
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			//从DOM工厂中获得DOM解析器
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			//把要解析的xml文档读入DOM解析器
			Document doc = dbBuilder.parse("src/cn/charlie/xml/member.xml");
			//System.out.println("处理该文档的DomImplementation对象 = "+doc.getImplementation());
			//得到文档名称为member的元素的节点列表
			NodeList nList = doc.getElementsByTagName("member");
			//遍历该集合，显示集合中的元素及其子元素的名字
			for(int i=0;i<nList.getLength();i++){
				Element node = (Element) nList.item(i);
				String queryName = node.getElementsByTagName("username").item(0).getFirstChild().getNodeValue();
				if(queryName.equals(username)){
					return true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 根据给定的username查询对应的member对象,如果不存在则返回null
	 */
	public static Person queryXmlMember(String username){
		try{
			//得到DOM解析器的工厂实例
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			//从DOM工厂中获得DOM解析器
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			//把要解析的xml文档读入DOM解析器
			Document doc = dbBuilder.parse("src/cn/charlie/xml/member.xml");
			//System.out.println("处理该文档的DomImplementation对象 = "+doc.getImplementation());
			//得到文档名称为member的元素的节点列表
			NodeList nList = doc.getElementsByTagName("member");
			//遍历该集合，显示集合中的元素及其子元素的名字
			for(int i=0;i<nList.getLength();i++){
				Element node = (Element) nList.item(i);
				String queryName = node.getElementsByTagName("username").item(0).getFirstChild().getNodeValue();
				String queryPassword = node.getElementsByTagName("password").item(0).getFirstChild().getNodeValue();
				String querySex = node.getElementsByTagName("sex").item(0).getFirstChild().getNodeValue();
				if(queryName.equals(username)){
					Person member = new Person();
					member.setUsername(queryName);
					member.setPassword(queryPassword);
					member.setSex(querySex);
					return member;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 向已存在的xml文件中插入元素
	 */
	public static void insertXml(){
		Element members = null;
		Element member = null;
		Element username = null;
		Element password = null;
		Element sex = null;
		try{
			//得到DOM解析器的工厂实例
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance(); 
			//从DOM工厂中获得DOM解析器
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			//把要解析的xml文档读入DOM解析器
			Document doc = dbBuilder.parse("src/cn/charlie/xml/member.xml"); 
			//得到文档名称为member的元素的节点列表
			NodeList nList = doc.getElementsByTagName("members");  
            members = (Element)nList.item(0); 
			//创建名称为member的元素
            member = doc.createElement("member");
			//设置元素member的属性值
           // member.setAttribute("examId", "23");
			//创建名称为username的元素
            username = doc.createElement("username");
			//创建名称为香香的文本节点并作为子节点添加到username元素中
            username.appendChild(doc.createTextNode("香香"));
			//将username子元素添加到member中
            member.appendChild(username);
            //按照同样方法添加password节点
            password = doc.createElement("password");
            password.appendChild(doc.createTextNode("123"));
            member.appendChild(password);
            
            sex = doc.createElement("sex");
            sex.appendChild(doc.createTextNode("female"));
            member.appendChild(sex);
			
			//将member作为子元素添加到树的根节点members
            members.appendChild(member);
			//将内存中的文档通过文件流生成member.xml，XmlDocument位于crimson.jar下
            ((XmlDocument)doc).write(new FileOutputStream("src/cn/charlie/xml/member.xml"));
            System.out.println("成功");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 向xml文件中插入给定的person元素
	 */
	public static void insertMember(Person person){
		Element members = null;
		Element member = null;
		Element username = null;
		Element password = null;
		Element sex = null;
		if(!queryXml(person.getUsername())){
			try{
				//得到DOM解析器的工厂实例
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance(); 
				//从DOM工厂中获得DOM解析器
				DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
				//把要解析的xml文档读入DOM解析器
				Document doc = dbBuilder.parse("src/cn/charlie/xml/member.xml"); 
				//得到文档名称为member的元素的节点列表
				NodeList nList = doc.getElementsByTagName("members");  
	            members = (Element)nList.item(0); 
				//创建名称为member的元素
	            member = doc.createElement("member");
				//设置元素member的属性值
	           // member.setAttribute("examId", "23");
				//创建名称为username的元素
	            username = doc.createElement("username");
				//创建名称为香香的文本节点并作为子节点添加到username元素中
	            username.appendChild(doc.createTextNode(person.getUsername()));
				//将username子元素添加到member中
	            member.appendChild(username);
	            //按照同样方法添加password节点
	            password = doc.createElement("password");
	            password.appendChild(doc.createTextNode(person.getPassword()));
	            member.appendChild(password);
	            
	            sex = doc.createElement("sex");
	            sex.appendChild(doc.createTextNode(person.getSex()));
	            member.appendChild(sex);
				
				//将member作为子元素添加到树的根节点members
	            members.appendChild(member);
				//将内存中的文档通过文件流生成member.xml，XmlDocument位于crimson.jar下
	            ((XmlDocument)doc).write(new FileOutputStream("src/cn/charlie/xml/member.xml"));
	            System.out.println("成功");
			}catch(Exception e){
				e.printStackTrace();
			}
		}	
	}
	
	/**
	 * 创建xml文档
	 */
	public static void createXml(){
		Document doc;
		Element members,member;
		Element username,password,sex;
		try{
			//得到DOM解析器的工厂实例
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			//从COM工厂中获得DOM解析器
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			//创建文档树模型对象
			doc = dbBuilder.newDocument();
			if(doc != null){
				members = doc.createElement("members");
				member = doc.createElement("member");
				//member.setAttribute("examId", "23");
				
				username = doc.createElement("username");
				username.appendChild(doc.createTextNode("香香"));
				member.appendChild(username);
				
				password = doc.createElement("password");
				password.appendChild(doc.createTextNode("123"));
				member.appendChild(password);
				
				sex = doc.createElement("sex");
	            sex.appendChild(doc.createTextNode("female"));
	            member.appendChild(sex);
				
				members.appendChild(member);
				doc.appendChild(members);
				//将内存中的文档通过文件流生成member.xml，XmlDocument位于crimson.jar下
				((XmlDocument)doc).write(new FileOutputStream("src/cn/charlie/xml/member.xml"));
				System.out.println("创建成功");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		Person person = new Person();
		person.setUsername("22");
		person.setPassword("123");
		person.setSex("male");
		//createXml();
		//insertXml();
		insertMember(person);
		queryXmlAll();
	}
}
