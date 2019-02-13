package annotation;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;
/**
 * 注解测试
 * 参考https://mp.weixin.qq.com/s/n-P8W8OzcKIg3UiFC-JycA
 * @author wsz
 * @date 2019年1月11日
 */
public class AnnotationDemo {

	@Test
	@Desc("注解信息注入到方法中")
	public void annoToMethod() {
		try {
			//方法目标类+new
			Class<Person> person = Person.class;
			Method method = person.getMethod("initPerson", String.class, int.class, String[].class);
			Person instance = person.newInstance();
			//注解class+目标方法反射
			MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
			method.invoke(instance, annotation.username(), annotation.age(), annotation.phone());
			//初始化成功
			System.out.println(instance);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Desc("注入类")
	public void annoObjToMethod() {
		try {
			PropertyDescriptor descriptor = new PropertyDescriptor("person", PersonDao.class);
			Person person = (Person)descriptor.getPropertyType().newInstance();
			
			Method method = descriptor.getWriteMethod();
			InjectPerson annotation = method.getAnnotation(InjectPerson.class);
			Method[] methods = annotation.getClass().getMethods();
			
			for(Method m : methods) {
				String name = m.getName();
				try {
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(name, Person.class);
					Method writeMethod = propertyDescriptor.getWriteMethod();
					Object invoke = m.invoke(annotation, null);
					writeMethod.invoke(person, invoke);
				} catch (Exception e) {
					//跳过Person对象中不存在的方法
					continue;
				}
			}
			PersonDao personDao = new PersonDao();
			method.invoke(personDao, person);
			System.out.println(personDao.getPerson());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	@Test
	@Desc("在成员变量上使用注解")
	public void annoToVar() {
		try {
			Field field = PersonDao.class.getDeclaredField("person");
			Person person = (Person) field.getType().newInstance();
			InjectPerson annotation = field.getAnnotation(InjectPerson.class);
			
			//获取注解的属性
			Method[] methods = annotation.getClass().getMethods();
			for (Method m : methods) {
				String name = m.getName();
				try {
					PropertyDescriptor descriptor = new PropertyDescriptor(name, Person.class);
					Method writeMethod = descriptor.getWriteMethod();
					//当前方法获取注解上的值
					Object obj = m.invoke(annotation, null);
					//注解上的值赋值到person
					writeMethod.invoke(person, obj);
				} catch (Exception e) {
					continue;
				}
			}
	        PersonDao personDao = new PersonDao();
	        field.setAccessible(true);
	        field.set(personDao, person);
	        System.out.println(personDao.getPerson());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
