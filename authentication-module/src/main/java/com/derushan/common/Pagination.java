/**
 * 
 */
package com.derushan.common;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author Derushan Sep 21, 2020
 */
public class Pagination {
	private Object data;
	private Long totalElements;
	private Long fromElement;
	private Integer totalPages;
	private Integer currentPage;

	public static Pageable paginationRequest(Integer pageNo) {

		if (pageNo == 0) {
			Pageable paging = PageRequest.of(pageNo, Constants.PAGINATION_LIMIT, Sort.by(Sort.Direction.ASC, "id"));
			return paging;
		}
		Pageable paging = PageRequest.of((pageNo - 1), Constants.PAGINATION_LIMIT, Sort.by(Sort.Direction.ASC, "id"));
		return paging;
	}

	public static Pageable paginationRequest(Integer pageNo, Integer pageLimit, Sort sort) {

		if (pageNo == 0) {
			Pageable paging = PageRequest.of(pageNo, pageLimit, sort);
			return paging;
		}
		Pageable paging = PageRequest.of((pageNo - 1), pageLimit, sort);
		return paging;
	}

	public static Pageable paginationRequest(Integer pageNo, Sort sort) {

		if (pageNo == 0) {
			Pageable paging = PageRequest.of(pageNo, Constants.PAGINATION_LIMIT, sort);
			return paging;
		}
		Pageable paging = PageRequest.of((pageNo - 1), Constants.PAGINATION_LIMIT, sort);
		return paging;
	}

	public static Object paginatedData(Page<?> contant) {
		Pagination transformData = new Pagination();
		transformData.setData(contant.getContent());
		transformData.setCurrentPage(contant.getPageable().getPageNumber() + 1);
		transformData.setTotalPages(contant.getTotalPages());
		transformData.setTotalElements(contant.getTotalElements());
		transformData.setFromElement(contant.getPageable().getOffset());
		return transformData;
	}

	/**
	 * 
	 */
	public Pagination() {
		super();
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @return the totalElements
	 */
	public Long getTotalElements() {
		return totalElements;
	}

	/**
	 * @param totalElements the totalElements to set
	 */
	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

	/**
	 * @return the fromElement
	 */
	public Long getFromElement() {
		return fromElement;
	}

	/**
	 * @param fromElement the fromElement to set
	 */
	public void setFromElement(Long fromElement) {
		this.fromElement = fromElement;
	}

	/**
	 * @return the totalPages
	 */
	public Integer getTotalPages() {
		return totalPages;
	}

	/**
	 * @param totalPages the totalPages to set
	 */
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	/**
	 * @return the currentPage
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

}
