package com.github.yll.epoch.core.commons;


/**
 * 返回结果
 *
 * @author luliang_yu
 * @date 2018/11/21
 */
public class Result {

	/**
	 * 结果体
	 */
	protected Object data;

	/**
	 * 状态码
	 */
	protected int status;

	/**
	 * 信息
	 */
	protected String msg;

	private Result() {
		super();
	}

	private Result(int status) {
		this.status = status;
	}

	public static Result create(int status) {
		return new Result(status);
	}

	/**
	 * Description:创建一个成功的结果体
	 *
	 * @return
	 */
	public static Result createSuccessResult() {
		return create(ResultCode.SUCCESS);
	}

	/**
	 * Description:创建一个成功的结果体
	 * 
	 * @param data
	 *            将要返回的数据
	 * @param msg
	 *            消息信息
	 * @return
	 */
	public static Result createSuccessResult(Object data, String msg) {
		return createSuccessResult().setData(data).setMsg(msg);
	}

	/**
	 * Description:创建一个默认的错误结果体
	 * 
	 * @return
	 */
	public static Result createErrorResult() {
		return create(ResultCode.ERROR);
	}

	/**
	 * Description:创建一个错误的结果体
	 * 
	 * @param data
	 *            将要返回的数据
	 * @param msg
	 *            消息信息
	 * @return
	 */
	public static Result createErrorResult(Object data, String msg) {
		return createErrorResult().setData(data).setMsg(msg);
	}

	/**
	 * Description:判断该结果体是否是处理成功状态
	 * 
	 * @return
	 */
	public boolean isSuccess() {
		return ResultCode.SUCCESS == this.status;
	}

	public Object getData() {
		return data;
	}

	public Result setData(Object data) {
		this.data = data;
		return this;
	}

	public int getStatus() {
		return status;
	}

	public Result setStatus(int status) {
		this.status = status;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public Result setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	@Override
	public String toString() {
		return "Result [data=" + data + ", status=" + status + ", msg=" + msg + "]";
	}
	
	
}
