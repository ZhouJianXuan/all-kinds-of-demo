package work.koreyoshi.base.result;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoujx
 */
public class ResultMap extends HashMap<String, Object>{

    public ResultMap() {
        super(8);
    }

    public static Map<String, Object> build() {
        return new ResultMap();
    }

    @Override
    public Map<String, Object> put(String key, Object data) {
        super.put(key, data);
        return this;
    }

}
