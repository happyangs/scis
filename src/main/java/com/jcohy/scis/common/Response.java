package com.jcohy.scis.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jcohy.scis.common.interfaces.ErrorCodeDefine;
import org.apache.commons.lang3.StringUtils;

import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class Response<T> implements Serializable {
    private String code;
    private String msg;
    private T info;
    private static Response successEmptyResponse;

    protected Response(String code, String msg) {
        if (!StringUtils.isEmpty(code) && (code.length() == 6 || code.equals(ErrorCodeDefine.MessageCode.OK.getCode()))) {
            this.code = code;
            this.msg = msg;
        } else {
            throw new IllegalArgumentException("响应编码错误！");
        }
    }

    protected Response(String serviceCode, ErrorCodeDefine codeDefine) {
        if (!StringUtils.isEmpty(serviceCode) && serviceCode.length() == 2) {
            this.code = serviceCode + codeDefine.getCode();
            this.msg = codeDefine.getMsg();
        } else {
            throw new IllegalArgumentException("服务编码错误！");
        }
    }

    private static Response buildErrorResponse(String serviceCode, String code, String msg) {
        if (!StringUtils.isEmpty(serviceCode) && serviceCode.length() == 2) {
            return new Response(serviceCode + code, msg);
        } else {
            throw new IllegalArgumentException("服务编码错误！");
        }
    }

    private static Response buildErrorResponse(String serviceCode, ErrorCodeDefine codeDefine) {
        return new Response(serviceCode, codeDefine);
    }

    public static Response buildSuccessEmptyResponse() {
        return successEmptyResponse;
    }

    public static <T> Response<T> buildSuccessResponse() {
        return new Response(ErrorCodeDefine.MessageCode.OK.getCode(), ErrorCodeDefine.MessageCode.OK.getMsg());
    }

    public static <T> Response<T> buildSuccessResponseWithInfo(T info) {
        return (new Response(ErrorCodeDefine.MessageCode.OK.getCode(), ErrorCodeDefine.MessageCode.OK.getMsg())).setInfo(info);
    }

    public static <T> Response<Result<T>> buildSuccessResponseWithResult(List<T> data, int totalCount) {
        return (new Response(ErrorCodeDefine.MessageCode.OK.getCode(), ErrorCodeDefine.MessageCode.OK.getMsg())).setInfo(Result.buildResult(data, Meta.buildMeta(totalCount)));
    }

    public static <T> Response<Result<T>> buildSuccessResponseWithResult(List<T> data, Meta metaData) {
        return (new Response(ErrorCodeDefine.MessageCode.OK.getCode(), ErrorCodeDefine.MessageCode.OK.getMsg())).setInfo(Result.buildResult(data, metaData));
    }

    @JsonIgnore
    public boolean isSuccessResponse() {
        return ErrorCodeDefine.MessageCode.OK.getCode().equals(this.code);
    }

    public Response setCode(String code) {
        this.code = code;
        return this;
    }

    public Response setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Response setInfo(T info) {
        this.info = info;
        return this;
    }

    public String toString() {
        return "Response(code=" + this.getCode() + ", msg=" + this.getMsg() + ", info=" + this.getInfo() + ")";
    }

    public Response() {
    }

    @ConstructorProperties({"code", "msg", "info"})
    public Response(String code, String msg, T info) {
        this.code = code;
        this.msg = msg;
        this.info = info;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Response)) {
            return false;
        } else {
            Response<?> other = (Response)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47: {
                    Object this$code = this.getCode();
                    Object other$code = other.getCode();
                    if (this$code == null) {
                        if (other$code == null) {
                            break label47;
                        }
                    } else if (this$code.equals(other$code)) {
                        break label47;
                    }

                    return false;
                }

                Object this$msg = this.getMsg();
                Object other$msg = other.getMsg();
                if (this$msg == null) {
                    if (other$msg != null) {
                        return false;
                    }
                } else if (!this$msg.equals(other$msg)) {
                    return false;
                }

                Object this$info = this.getInfo();
                Object other$info = other.getInfo();
                if (this$info == null) {
                    if (other$info != null) {
                        return false;
                    }
                } else if (!this$info.equals(other$info)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Response;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        Object $msg = this.getMsg();
        result = result * 59 + ($msg == null ? 43 : $msg.hashCode());
        Object $info = this.getInfo();
        result = result * 59 + ($info == null ? 43 : $info.hashCode());
        return result;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public T getInfo() {
        return this.info;
    }

    static {
        successEmptyResponse = new Response.SuccessEmptyResponse(ErrorCodeDefine.MessageCode.OK.getCode(), ErrorCodeDefine.MessageCode.OK.getMsg());
    }

    private static class SuccessEmptyResponse extends Response {
        private static final long serialVersionUID = -7722537724801482736L;

        SuccessEmptyResponse(String code, String msg) {
            super.code = code;
            super.msg = msg;
        }

        @JsonIgnore
        public boolean isSuccessResponse() {
            return true;
        }

        public Response setCode(String code) {
            throw new UnsupportedOperationException();
        }

        public Response setMsg(String msg) {
            throw new UnsupportedOperationException();
        }

        public Response setInfo(Object info) {
            throw new UnsupportedOperationException();
        }
    }
}
