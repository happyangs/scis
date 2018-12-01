package com.jcohy.scis.common;

import java.io.Serializable;

public class Meta implements Serializable {

    private Integer count;

    public Meta(Integer count) {
        this.count = count;
    }

    public Meta(String count) {
        this.count = Integer.valueOf(count);
    }

    public static Meta buildMeta(Integer count) {
        return new Meta(count == null ? 0 : count.intValue());
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String toString() {
        return "Meta(count=" + this.getCount() + ")";
    }

    public Meta() {
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Meta)) {
            return false;
        } else {
            Meta other = (Meta)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$count = this.getCount();
                Object other$count = other.getCount();
                if (this$count == null) {
                    if (other$count != null) {
                        return false;
                    }
                } else if (!this$count.equals(other$count)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Meta;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $count = this.getCount();
        result = result * 59 + ($count == null ? 43 : $count.hashCode());
        return result;
    }
}
