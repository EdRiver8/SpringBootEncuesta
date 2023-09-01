package com.bancolombia.prubea.dto;

import java.util.LinkedHashMap;
import java.util.Map;

public class ServiceResponseDto {
	
	private int statusCode;
	private Map<String, Object> data;
	
	/**
	 * getStatusCode
	 * @return void
	 */
	public int getStatusCode() {
		return statusCode;
	}
	
	/**
	 * setStatusCode
	 * @param statusCode
	 * @return void
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	/**
	 * getData
	 * @return void
	 */
	public Map<String, Object> getData() {
		return data;
	}
	
	/**
	 * setData
	 * @param data
	 * @return void
	 */
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	/**
	 * 
	 * @param data
	 * @param statusCode
	 * @return void
	 */
	public void setInfo(String messageContent, int statusCode, String keyData) {
		Map<String, Object> data = new LinkedHashMap<>();
		data.put(keyData, messageContent);
		this.statusCode = statusCode;
		this.setData(data);
	}
	
}