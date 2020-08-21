package com.example.common.page;

import com.github.pagehelper.PageHelper;

/**
 * 分页工具
 * @author wuchaoqun
 *
 */
public class PageUtils {

	/**
	 * 分页
	 * @param page
	 */
	public static void startPage(Page page) {
		if (page == null) {
			return;
		}
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
	}
}
