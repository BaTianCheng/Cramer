package com.cw.cramer.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cw.cramer.common.base.BaseController;
import com.cw.cramer.common.util.LogUtils;

/**
 * 全局Controller层异常处理类
 */
@ControllerAdvice
public class GlobalExceptionResolver extends BaseController{

    /**
     * 处理所有不可知异常
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleException(Exception e) {
        LogUtils.error(e.getMessage(), e);
        return this.renderFailJson();
    }

}
