package com.bancolombia.prubea.dto;

import com.bancolombia.prubea.util.Constants;

import java.util.LinkedHashMap;
import java.util.Map;

public class ControllerDto {
	
	private int statusCode;
	private Map<String, Object> body;
	
	/**
	 * getStatusCode
	 * @return int
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
	 * getBody
	 * @return Map
	 */
	public Map<String, Object> getBody() {
		return body;
	}
	
	/**
	 * setBody
	 * @param statusCode
	 * @param data
	 * @param isObject
	 * @return void
	 */
	public void setBody(int statusCode, Map<String, Object> data, boolean isData) {
		String status, message, description;
		Map<String, Object> body = new LinkedHashMap<String, Object>();
		switch (statusCode) {
			case Constants.SUCCESS_STATUS_CODE:
				status = "success";
				message = "SUCCESS";
				description = "Service executed successfully";
			break;
			case Constants.UNAUTHORIZED_STATUS_CODE:
				status = "error";
				message = "UNAUTHORIZED";
				description = "Permission denied";
			break;
			case Constants.BAD_REQUEST_STATUS_CODE:
				status = "error";
				message = "BAD_REQUEST";
				description = "Some params are missing";
			break;
			case Constants.FORBIDDEN_STATUS_CODE:
				status = "error";
				message = "FORBIDDEN";
				description = "User role without permissions";
			break;
			case Constants.NOT_FOUND_STATUS_CODE:
				status = "error";
				message = "NOT_FOUND";
				description = "Resource not found";
			break;
			case Constants.INTERNAL_SERVER_ERROR_STATUS_CODE:
				status = "error";
				message = "INTERNAL_SERVER_ERROR";
				description = "Internal server error";
			break;
			case Constants.CONFLICT_WITH_CURRENT_STATE:
				status = "error";
				message = "CONFLICT";
				description = "Resource already exists";
			break;
			default:
				status = "undefined";
				message = "undefined";
				description = "undefined";
			break;
        }
		body.put("status", status);
		body.put("message", message);
		body.put("description", description);
		body.put("data", (isData) ? (data) : (data.get("data")));
		this.body = body;
	}
	
}
