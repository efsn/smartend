package org.codeyn.smartend.framework.base;

import java.io.Serializable;

public class Pagination implements Serializable{
    
    /**
     * 当前页码
     */
    private int pageNo;
    
    /**
     * 总记录数
     */
    private int totalCount;
    
    /**
     * 每页记录数
     */
    private int pageSize;
    
    /**
     * 总页码
     */
    private int totalPage;
    
    /**
     * 起始位置
     */
    private int offset;
    
    /**
     * 页范围
     */
    private int[] pageRange;
    
}
