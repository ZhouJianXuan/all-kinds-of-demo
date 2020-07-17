package work.koreyoshi.base.util;

import org.springframework.context.ApplicationContext;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhoujx
 */
public class InterfaceDispatcher {

    private final static Map<Class<?>, String[]> BEANS = new ConcurrentHashMap<>();

    public static InterfaceDispatcher build() {
        return new InterfaceDispatcher();
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> collect(Class<T> clazz){
        ApplicationContext context = SpringContextUtil.getContext();
        List<T> list = new LinkedList<>();
        if (!BEANS.containsKey(clazz)) {
            String[] beanNames = context.getBeanNamesForType(clazz);
            BEANS.put(clazz, beanNames);    
        }
        String[] beanNames = BEANS.get(clazz);
        if (beanNames != null && beanNames.length > 0) {
            for (String beanName : beanNames) {
                list.add((T) context.getBean(beanName));
            }
        }
        return list;
    }
}
