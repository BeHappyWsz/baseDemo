package annotation;

public class PersonDao {

	@InjectPerson(age = 100, username = "qqqq",phone= {"a"})
	private Person person;

	public Person getPerson() {
		return person;
	}

	@InjectPerson(age = 10, username = "abc", phone= {"123","3445"})
	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return "PersonDao [person=" + person + "]";
	}
	
}
