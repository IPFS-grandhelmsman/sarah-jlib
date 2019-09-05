package com.helmsman.sarah.node.handler;

import com.ppblock.common.vo.ResultVo;
import com.helmsman.sarah.common.enums.ErrorCode;
import com.helmsman.sarah.node.exception.AppException;
import com.helmsman.sarah.node.exception.UnAuthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Controller exception handler
 */
@ControllerAdvice
public class AppExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(AppExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultVo handle(HttpServletRequest request, HttpServletResponse response, Exception e) {

        logger.error("======> {}", e);
        if (e instanceof UnAuthorizedException) {
            // TODO: do access control here
        }
        if (e instanceof IllegalArgumentException || e instanceof AppException) {
            return ResultVo.instance(((AppException) e).getCode(), e.getMessage());
        }
        return ResultVo.instance(ErrorCode.FAIL.getCode(), ErrorCode.FAIL.getMessage());
    }
}
