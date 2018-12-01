package com.jcohy.scis.common;

/**
 * Created by Bryant on 2018.11.15
 */
import java.io.Serializable;

public class RequestVo implements Serializable {
    public RequestVo() {
    }

    public String toString() {
        return "RequestVo()";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof RequestVo)) {
            return false;
        } else {
            RequestVo other = (RequestVo)o;
            return other.canEqual(this);
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof RequestVo;
    }

    public int hashCode() {
        int result = 1;
        return result;
    }
}
