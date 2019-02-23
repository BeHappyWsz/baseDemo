package map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * jdk1.8-HashMap/ConcurrentHashMap源码
 * @author wsz
 * @date 2019年2月19日
 */
public class MapCode {

	Map<Object, String> hashMap = new HashMap<Object, String>();
	
	Map<Object, String> cHashMap = new ConcurrentHashMap<Object, String>();
	
}
