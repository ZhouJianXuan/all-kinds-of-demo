package work.koreyoshi.base.util;

import cn.hutool.Hutool;
import org.apache.commons.lang3.StringUtils;
import work.koreyoshi.base.service.TreeStructure;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author zhoujx
 */
public class TreeUtil<T extends TreeStructure> {

    public List<T> initTree(List<T> list) {
        return initTree(list, TreeStructure.PARENT_ID);
    }

    public List<T> initTree(List<T> list, String parentId) {
        LinkedList<T> childList = new LinkedList<>();
        if (list != null && list.size() > 0) {
            Map<String, List<T>> treeMap = new HashMap<>(list.size());
            for (T tree : list) {
                String pId = tree.getTreeId();
                if (StringUtils.equals(parentId, pId)) {
                    childList.add(tree);
                }
                if (!treeMap.containsKey(pId)) {
                    treeMap.put(pId, new LinkedList<T>());
                }
                List<T> tempList = treeMap.get(pId);
                tempList.add(tree);
            }
            cycle(treeMap, childList);
        }
        return childList;
    }

    private void cycle(Map<String, List<T>> treeMap, List<T> childList){
        if(childList != null && childList.size() > 0){
            for(T tree : childList){
                if(treeMap.containsKey(tree.getTreeId())){
                    List<T> child = new LinkedList<T>(treeMap.get(tree.getTreeId()));
                    tree.setChilds(child);
                    if(child.size() > 0){
                        cycle(treeMap, child);
                    }
                }
            }
        }
    }
}
