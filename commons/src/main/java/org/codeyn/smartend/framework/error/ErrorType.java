package org.codeyn.smartend.framework.error;

public enum ErrorType{
    
    PARAM_EMPTY(0x1000001, "%s不能为空哦"),
    PARAMS_ERROR(0x1000002, "参数%s错误"), 
    OBJ_IS_EMPTY(0x1000003, "对象%s不存在"), 
    TOO_LONG(0x1000004, "%s最多只能输入%s个字"), 
    SYSTEM_BUSY(0x1000005, "系统繁忙，请稍后再试！"),
    SYSTEM_FORBIDDEN(0x1000006, "没有操作权限！");
    
    private int code;
    private String msg;
    
    private ErrorType(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }
    
    public static ErrorType get(int code){
        for(ErrorType error : ErrorType.values()){
            if(error.getCode() == code){
                return error;
            }
        }
        return null;
    }
    
    
}
