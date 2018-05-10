package wb.bar.util;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Map 对象与 JavaBean 对象互转工具类 
 * @param <T>
 */
public class BeanToMapUtil<T>
{

    public static Map<String, Object> beanToMap(Object object) throws Exception
    {
        Map<String, Object> map = new HashMap<String, Object>();
 
        Class cls = object.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(object));
        }
        return map;
    }

    public static Object mapToBean(Map<String, Object> map, Class cls) throws Exception
    {
        Object object = cls.newInstance();
        for (String key : map.keySet()){
            Field temFiels = cls.getDeclaredField(key);
            temFiels.setAccessible(true);
            temFiels.set(object, map.get(key));
        }
        return object;
    }
    
    @SuppressWarnings("unchecked")
	public List<T> ListMap2JavaBean(List<Map<String, Object>> datas, Class<T> beanClass) throws Exception   {
        // 返回数据集合
        List<T> list = new ArrayList<T>();
        for (Map<String, Object> mapdata : datas) {
        	T t = (T) mapToBean(mapdata, beanClass);
        	list.add(t);
        }
        // 返回
        return list;
    }
    
}