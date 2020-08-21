package com.example.common.page;

import com.example.common.constant.Constants;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分页用
 *
 */
public class Page<T> {

    @ApiModelProperty("每页的数量")
    private int pageSize;

    @ApiModelProperty("当前页，从1开始")
    private int pageNum;

	@ApiModelProperty(value = "记录开始位置，从0开始", hidden = true)
	private int firstResult;

	@ApiModelProperty(value = "查询记录数量", hidden = true)
	private int maxResults;

	@ApiModelProperty("是否分页，默认true")
	private boolean isPage;

    public int getPageSize() {
        if (pageSize == 0) {
            pageSize = Constants.MAX_PAGE_SIZE;
        }else if(pageSize > Constants.MAX_PAGE_SIZE) {
        	pageSize = Constants.MAX_PAGE_SIZE;
        }
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        if (pageNum == 0) {
            pageNum = Constants.DEFAULT_PAGE_NUM;
        }
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setFirstResult(int firstResult) {
        this.firstResult = firstResult;
    }

    public int getFirstResult() {
        return firstResult;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public int getMaxResults() {
        return maxResults;
    }

    public boolean isPage() {
        return isPage;
    }

    public void setPage(boolean page) {
        isPage = page;
    }
}
