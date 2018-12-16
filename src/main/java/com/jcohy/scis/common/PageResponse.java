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
public class PageResponse<T> implements Serializable {
    private String code;
    private String msg;
    private int count;
    private T data;
    private static PageResponse successEmptyResponse;

    protected PageResponse(String code, String msg,int count) {
        if (!StringUtils.isEmpty(code) && (code.length() == 6 || code.equals(ErrorCodeDefine.MessageCode.OK.getCode()))) {
            this.code = code;
            this.msg = msg;
            this.count = count;
        } else {
            throw new IllegalArgumentException("响应编码错误！");
        }
    }

    protected PageResponse(String serviceCode, ErrorCodeDefine codeDefine) {
        if (!StringUtils.isEmpty(serviceCode) && serviceCode.length() == 2) {
            this.code = serviceCode + codeDefine.getCode();
            this.msg = codeDefine.getMsg();
        } else {
            throw new IllegalArgumentException("服务编码错误！");
        }
    }

    private static PageResponse buildErrorResponse(String serviceCode, ErrorCodeDefine codeDefine) {
        return new PageResponse(serviceCode, codeDefine);
    }

    public static PageResponse buildSuccessEmptyResponse() {
        return successEmptyResponse;
    }

    public static <T> PageResponse<Result<T>> buildSuccessResponseWithResult(List<T> data, int totalCount) {
        return (new PageResponse(ErrorCodeDefine.MessageCode.OK.getCode(), ErrorCodeDefine.MessageCode.OK.getMsg(),totalCount)).setData(data);
    }

    @JsonIgnore
    public boolean isSuccessResponse() {
        return ErrorCodeDefine.MessageCode.OK.getCode().equals(this.code);
    }

    public PageResponse setCode(String code) {
        this.code = code;
        return this;
    }

    public PageResponse setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public PageResponse setCount(int count) {
        this.count = count;
        return this;
    }

    public PageResponse setData(T data) {
        this.data = data;
        return this;
    }

    public String toString() {
        return "Response(code=" + this.getCode() + ", msg=" + this.getMsg() +", count=" + this.getCount() + ", data=" + this.getData() + ")";
    }

    public PageResponse() {
    }

    @ConstructorProperties({"code", "msg", "data"})
    public PageResponse(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
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

                Object this$info = this.getData();
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
        Object $info = this.getData();
        result = result * 59 + ($info == null ? 43 : $info.hashCode());
        return result;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getCount() {
        return this.count;
    }

    public T getData() {
        return this.data;
    }

    static {
        successEmptyResponse = new PageResponse.SuccessEmptyResponse(ErrorCodeDefine.MessageCode.OK.getCode(), ErrorCodeDefine.MessageCode.OK.getMsg());
    }

    private static class SuccessEmptyResponse extends PageResponse {
        private static final long serialVersionUID = -7722537724801482736L;

        SuccessEmptyResponse(String code, String msg) {
            super.code = code;
            super.msg = msg;
        }

        @JsonIgnore
        public boolean isSuccessResponse() {
            return true;
        }

        public PageResponse setCode(String code) {
            throw new UnsupportedOperationException();
        }

        public PageResponse setMsg(String msg) {
            throw new UnsupportedOperationException();
        }

        public PageResponse setInfo(Object info) {
            throw new UnsupportedOperationException();
        }
    }
}
