package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解成员变量只能是String、数组、Class、枚举类、注解
 * @author wsz
 * @date 2019年1月11日
 * @Retention默认是class，而反射是在运行时期来获取信息的
 * @Target指定该注解要注解的目标
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

	String username() default "";

	int age() default 0;
	
	String[] phone() default "";
	
}
