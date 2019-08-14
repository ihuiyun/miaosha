package com.zld.mall.exception;

import com.zld.mall.result.CodeMsg;

/**
 * 描述：
 *
 * @author lida
 * @time 2019/8/14 19:52
 */
public class GlobalException extends RuntimeException{
    private CodeMsg codeMsg;

    public GlobalException(CodeMsg codeMsg){
        super(codeMsg.toString());
        this.codeMsg = codeMsg;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }
}
