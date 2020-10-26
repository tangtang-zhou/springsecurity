package com.tang.handle;

import com.tang.exception.BizException;
import com.tang.exception.CommonEnum;
import com.tang.model.ResultBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /*
     * 处理自定义的业务异常
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public ResultBody bizExceptionHandler(HttpServletRequest req, BizException e) {
        logger.error("发生业务异常！原因是：{}", e.getErrorMsg());
        return ResultBody.error(e.getErrorCode(), e.getErrorMsg());
    }

    /*
     * 处理空指针的异常
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ResultBody exceptionHandler(HttpServletRequest req, NullPointerException e) {
        logger.error("发生空指针异常！原因是:", e);
        return ResultBody.error(CommonEnum.BODY_NOT_MATCH);
    }

    @ExceptionHandler(value = NumberFormatException.class)
    @ResponseBody
    public ResultBody numberFormatException(HttpServletRequest req, NumberFormatException e) {
        logger.error("未找到该资源！原因是:", e);
        return ResultBody.error(CommonEnum.NOT_FOUND);
    }
    // 请求方式错误
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResultBody numberFormatException(HttpServletRequest req, HttpRequestMethodNotSupportedException e) {
        return ResultBody.error(CommonEnum.HttpRequestMethodNotSupportedException);
    }

    //参数名不匹配
    @ExceptionHandler(value = IllegalStateException.class)
    @ResponseBody
    public ResultBody IllegalStateException(HttpServletRequest req, IllegalStateException e) {
        return ResultBody.error(CommonEnum.IllegalStateException);
    }


    /*
     * 处理其他异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultBody exceptionHandler(HttpServletRequest req, Exception e) {
        logger.error("未知异常！原因是:", e);
        return ResultBody.error(CommonEnum.INTERNAL_SERVER_ERROR);
    }
}

