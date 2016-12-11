package com.net.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * <p>
 * <h2>PageDataClass to achieve the package information page，Page data query and display can be achieved。</h2>
 * </p>
 * 
 * <p>
 * Paging query data，To specify the number of lines and the current page data to check the page before the inquiry，ThenDAOClass query
 * The total number of data rows can be found，And record inPageDataClass，We can calculate the total number of pages。If
 * PageDataDo not get the total number and the total number of pages，InDAOCalculate the total function and the total number of pages need to query，
 * otherwise，No longer calculate the total function and the total number of pages。
 * </p>
 * 
 */
public final class PageData {

	//查询得到的总行数，-1表示未执行查询
	private int rowCount = -1;
	
	//查询得到的总页数
	private int pageCount = -1; 
	
	//页面大小，查询数据时，一次查询处的最大行数
	private int pageSize = 10;
	
	//当前页码，当前要查询的页码
	private int currentPage = 1;
	
	
	private List list;
	
	//可能存储的数据是以键值对的形式存储的
	private Map map;
	
	/**Constructing default paging information，The current page：1，Page size
	 * by：SysConfig.getPageSize()Configuration value，By default：10*/
	public PageData() {
		this(1);
	}
	
	/**With the specified current page paging information structure，The current number of not less than1，Page size
	 * by：SysConfig.getPageSize()Configuration value，By default：10*/
	public PageData(int currentPage) {
		this(currentPage, 10);
	}

	/**With the specified current page number and page size of the paging information structure，The current number of not less than1，Page size
	 * Can not be less than1*/
	public PageData(int currentPage, int pageSize) {
		if (currentPage > 0) {
			this.currentPage = currentPage;
		}
		if (pageSize > 0) {
			this.pageSize = pageSize;
		}
	}

	/**Whether you need to calculate the total number and the total number of pages*/
	public boolean isFirstPage() {
		return (rowCount < 0 && pageCount < 0);
	}

	/**Get the current page*/
	public int getCurrentPage() {
		return currentPage;
	}

	/**Set the current page number，Can not be less than1*/
	public void setCurrentPage(int currentPage) {
		if (currentPage >= 0) {
			this.currentPage = currentPage;
		}
	}

	/**The total number of pages*/
	public int getPageCount() {
		if (pageCount >= 0) {
			return pageCount;
		} else {
			return 0;
		}
	}

	/**Get page size*/
	public int getPageSize() {
		return pageSize;
	}

	/**Set page size，Can not be less than1*/
	public void setPageSize(int pageSize) {
		if (pageSize > 0) {
			this.pageSize = pageSize;
		}
	}

	/**Get the number of head office*/
	public int getRowCount() {
		if (rowCount >= 0) {
			return rowCount;
		} else {
			return 0;
		}
	}

	/**Set the number of head office，Can not be less than1，Do not set the number of head office*/
	public void setRowCount(int rowCount) {
		if (rowCount >= 0) {
			this.rowCount = rowCount;
			this.pageCount = Math.round(rowCount / pageSize) + (rowCount%pageSize>0?1:0);
			int pageCnt = getPageCount();
			if (currentPage > pageCnt) {
				if (pageCnt <= 0) {
					this.currentPage = 1;
				} else {
					this.currentPage = pageCnt;
				}
			}
		}
	}

	/**
	 * Reset paging information to the initial state,andresetAllThe difference is to keepcurrentPage
	 *
	 */
	public void reset() {
		rowCount = -1;
		pageCount = -1;
//		currentPage = 1;
	}
	
	/**Reset paging information to the initial state，Back to the current page number1page，And will lead to recalculate the total number and the total number of pages*/
	public void resetAll() {
		rowCount = -1;
		pageCount = -1;
		currentPage = 1;
	}
	
    /**Converts a paging message object into a string*/
	public String toString() {
    	return ToStringBuilder.reflectionToString(this,
    			ToStringStyle.MULTI_LINE_STYLE);
    }

	public String getPagesSql(String sql, PageData page) {
		// TODO Auto-generated method stub
		
		String  result  ="select * from  ("+ sql + ") m   LIMIT "+page.getPageSize()*(page.getCurrentPage()-1)+","+page.getPageSize();
		return result;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}
	
	
	
}