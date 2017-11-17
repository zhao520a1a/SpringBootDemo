package com.example.demo.common;


/**
 * 服务异常
 */
public class ServiceException extends Exception {

    private static final long serialVersionUID = -319844289653018942L;
    
    /**
     * 错误编码.默认为0,表示系统异常.
     */
    private int errorCode = 0;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 具体错误信息,用于调试
     */
    private String debugMessage;

    private Throwable cause = this;
    
    /**
     * 返回错误编码, 默认为0，表示系统异常.
     * @return 错误编码
     */
    public int getErrorCode() {
        return this.errorCode;
    }

    /**
     * 获取异常消息.
     * @return  异常的信息.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * 获取调试的错误消息
	 * @return 
	 */
	public String getDebugMessage() {
		return this.debugMessage;
	}

	/**
     * Constructs a new exception with the specified detail message.
     * @param   errorCode   the error code.
     */
    public ServiceException(int errorCode) {
        this.errorCode = errorCode;
        this.message = new StringBuffer("error code is:").append(errorCode).toString();
        this.debugMessage = new StringBuffer("error code is:").append(errorCode).toString();
    }
    
    public ServiceException(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
        this.debugMessage = message;
    }

    public ServiceException(int errorCode, String message, String debugMessage) {
        this.errorCode = errorCode;
        this.message = message;
        this.debugMessage = debugMessage;
    }

    public ServiceException(int errorCode, Throwable cause) {
        fillInStackTrace();
        this.errorCode = errorCode;
        this.message = new StringBuffer("error code is:").append(errorCode).toString();
        this.debugMessage = new StringBuffer("error code is:").append(errorCode).toString();
        this.cause = cause;
    }

    public ServiceException(int errorCode, Throwable cause, String message) {
        fillInStackTrace();
        this.errorCode = errorCode;
        this.message = message;
        this.debugMessage = message;
        this.cause = cause;
    }

    public ServiceException(int errorCode, Throwable cause, String message, String debugMessage) {
        fillInStackTrace();
        this.errorCode = errorCode;
        this.message = message;
        this.debugMessage = debugMessage;
        this.cause = cause;
    }

    public ServiceException(String message) {
        this.message = message;
        this.debugMessage = message;
    }

    public ServiceException(String message, String debugMessage) {
        this.message = message;
        this.debugMessage = debugMessage;
    }

    public ServiceException(Throwable cause) {
        fillInStackTrace();
        this.message = (cause == null ? null : cause.toString());
        this.debugMessage = (cause == null ? null : cause.toString());
        this.cause = cause;
    }

    public ServiceException(String message, Throwable cause) {
        fillInStackTrace();
        this.message = message;
        this.debugMessage = message;
        this.cause = cause;
    }

    public ServiceException(String message, String debugMessage, Throwable cause) {
        fillInStackTrace();
        this.message = message;
        this.debugMessage = debugMessage;
        this.cause = cause;
    }

    public Throwable getCause() {
        return (cause==this ? null : cause);
    }
}
