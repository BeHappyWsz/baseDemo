package annotation;

import java.util.Arrays;

public class Person {

	private String username;
	
	private int age;

	private String[] phone;
	
	public Person() {}
	
	@MyAnnotation(username="abc", age=0, phone = {"a","b"})
	public void initPerson(String username, int age, String[] phone) {
		this.username = username;
		this.age = age;
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Person [username=" + username + ", age=" + age + ", phone=" + Arrays.toString(phone) + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String[] getPhone() {
		return phone;
	}

	public void setPhone(String[] phone) {
		this.phone = phone;
	}
	
}
