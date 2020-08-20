package work.koreyoshi.base.service;

import java.util.List;

/**
 * @author zhoujx
 */
public interface TreeStructure {

    String PARENT_ID = "0";

    /**
     * 返回id
     * @return id
     */
    String getTreeId();

    /**
     * 获取父ID
     * @return 父id
     */
    String getTreeParentId();

    /**
     * 获取子列表
     * @return 实现这个方法，返回子列表
     */
    public List<?> getChilds();

    /**
     * 实现
     * @param childs 子列表
     */
    public void setChilds(List<?> childs);
}
