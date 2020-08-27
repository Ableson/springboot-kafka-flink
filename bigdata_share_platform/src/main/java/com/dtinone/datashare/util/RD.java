package com.dtinone.datashare.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
@ApiModel(value = "RD", description = "数据返回实体")
public class RD<T> implements Serializable {

	private static final long serialVersionUID = 1925941624890792441L;

	private static final int OK = 200;

	private static final int FAIL = 500;

	private static final int UN_AUTH_ORIZED = -1;

	@ApiModelProperty(value = "状态")
	private boolean status = true;

	@ApiModelProperty(value = "数据")
	public T data;// 服务端数据

	@ApiModelProperty(value = "状态码")
	public int code = OK;// 状态码

	@ApiModelProperty(value = "消息提示")
	public String msg = "操作成功";// 描述信息

	public RD<T> code(int code) {

		this.setCode(code);

		return this;

	}

	public static RD<Object> isOk() {

		return new RD<>();

	}

	public static RD<Object> isFail() {

		return new RD<>().code(FAIL);

	}

	public static RD<?> isFail(Throwable e) {

		return isFail().msg(e).setStatus(false);

	}

	public RD<T> msg(Throwable e) {

		this.setMsg(e.getMessage());
		return this;

	}

	public RD<T> data(T data) {

		this.setData(data);

		return this;

	}

	RD() {}

	// gettes and setters

	public int getCode() {

		return code;

	}

	public RD<?> setCode(int code) {

		this.code = code;
		return this;
	}

	public String getMsg() {

		return msg;

	}

	public RD<?> setMsg(String msg) {

		this.msg = msg;
		return this;
	}

	public boolean getStatus() {

		return status;

	}

	public RD<?> setStatus(boolean status) {

		this.status = status;
		return this;
	}

	public T getData() {

		return data;

	}

	public RD<?> setData(T data) {

		this.data = data;
		return this;
	}


	@Override
	public String toString() {

		return "ResposeResult [code=" + code + ", msg=" + msg + ", data=" + data + "]";

	}
}
