package com.solventa.device.utils.enums;

import com.solventa.device.utils.DeviceConstants;

public enum ErrorCatalog {
	DEVICE_NOT_FOUND(DeviceConstants.DEVICE_NOT_FOUND_CODE, DeviceConstants.DEVICE_NOT_FOUND),
	DEVICE_CONNECT_TIME_OUT(DeviceConstants.DEVICE_CONNECT_TIME_OUT_CODE, DeviceConstants.TIME_OUT_ERROR),
	DEVICE_SERVER_ERROR(DeviceConstants.DEVICE_SERVER_ERROR_CODE, DeviceConstants.SERVER_ERROR);

	private final String code;
	private final String message;

	private ErrorCatalog(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
