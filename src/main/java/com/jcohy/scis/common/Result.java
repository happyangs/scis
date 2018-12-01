package com.jcohy.scis.common;

import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;


public class Result<T> implements Serializable {

    private List<T> data;

    private Meta meta;

    public static <E> Result<E> buildResult(List<E> data, Meta meta) {
        Result<E> result = new Result();
        if (data == null) {
            data = Collections.EMPTY_LIST;
        }

        result.setData(data);
        result.setMeta(meta);
        return result;
    }

    public List<T> getData() {
        return this.data;
    }

    public Meta getMeta() {
        return this.meta;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public String toString() {
        return "Result(data=" + this.getData() + ", meta=" + this.getMeta() + ")";
    }

    public Result() {
    }

    @ConstructorProperties({"data", "meta"})
    public Result(List<T> data, Meta meta) {
        this.data = data;
        this.meta = meta;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Result)) {
            return false;
        } else {
            Result<?> other = (Result)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                Object this$meta = this.getMeta();
                Object other$meta = other.getMeta();
                if (this$meta == null) {
                    if (other$meta != null) {
                        return false;
                    }
                } else if (!this$meta.equals(other$meta)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Result;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $data = this.getData();
        result = result * 59 + ($data == null ? 43 : $data.hashCode());
        Object $meta = this.getMeta();
        result = result * 59 + ($meta == null ? 43 : $meta.hashCode());
        return result;
    }
}
