package com.jcohy.scis.common;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Bryant on 2018.11.20
 */
public class PageInfo {
    private Integer pageStartIndex;
    private Integer pageSize;
    private Integer totalCount;
    private Integer pageNumber;
    private boolean autoPage = false;
    private String orderColumn;
    private PageInfo.SortType orderSort;
    private boolean autoOrderBy = false;

    public PageInfo() {
    }

    public PageInfo orderBy(String orderColumn, PageInfo.SortType sortType) {
        if (StringUtils.isEmpty(orderColumn)) {
            this.orderColumn = null;
            this.orderSort = null;
            this.autoOrderBy = false;
            return this;
        } else {
            this.autoOrderBy = true;
            this.orderColumn = orderColumn;
            this.orderSort = sortType == null ? PageInfo.SortType.ASC : sortType;
            return this;
        }
    }

    public PageInfo buildPage(Integer pageSize, Integer pageNumber) {
        return this.buildPage(pageSize, pageNumber, true);
    }

    public PageInfo buildPage(Integer pageSize, Integer pageNumber, boolean pageableWhileNull) {
        if (pageableWhileNull) {
            pageSize = pageSize == null ? ServiceConsts.DEFAULT_PAGE_SIZE : pageSize;
            pageNumber = pageNumber == null ? 1 : pageNumber.intValue();
        } else if (pageSize == null || pageNumber == null) {
            this.autoPage = false;
            this.pageStartIndex = null;
            this.pageSize = null;
            this.totalCount = null;
            return this;
        }

        this.autoPage = true;
        int size = (pageSize.intValue() < 1 ? ServiceConsts.DEFAULT_PAGE_SIZE : pageSize).intValue();
        int number = pageNumber.intValue() < 1 ? 1 : pageNumber.intValue();
        this.pageSize = size;
        this.pageStartIndex = (number - 1) * size;
        this.pageNumber = number;
        return this;
    }

    public PageInfo disableAutoPage() {
        this.autoPage = false;
        return this;
    }

    public String toString() {
        return "PageInfo(pageStartIndex=" + this.getPageStartIndex() + ", pageSize=" + this.getPageSize() + ", totalCount=" + this.getTotalCount() + ", pageNumber=" + this.getPageNumber() + ", autoPage=" + this.isAutoPage() + ", orderColumn=" + this.getOrderColumn() + ", orderSort=" + this.getOrderSort() + ", autoOrderBy=" + this.isAutoOrderBy() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof PageInfo)) {
            return false;
        } else {
            PageInfo other = (PageInfo)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label91: {
                    Object this$pageStartIndex = this.getPageStartIndex();
                    Object other$pageStartIndex = other.getPageStartIndex();
                    if (this$pageStartIndex == null) {
                        if (other$pageStartIndex == null) {
                            break label91;
                        }
                    } else if (this$pageStartIndex.equals(other$pageStartIndex)) {
                        break label91;
                    }

                    return false;
                }

                Object this$pageSize = this.getPageSize();
                Object other$pageSize = other.getPageSize();
                if (this$pageSize == null) {
                    if (other$pageSize != null) {
                        return false;
                    }
                } else if (!this$pageSize.equals(other$pageSize)) {
                    return false;
                }

                Object this$totalCount = this.getTotalCount();
                Object other$totalCount = other.getTotalCount();
                if (this$totalCount == null) {
                    if (other$totalCount != null) {
                        return false;
                    }
                } else if (!this$totalCount.equals(other$totalCount)) {
                    return false;
                }

                label70: {
                    Object this$pageNumber = this.getPageNumber();
                    Object other$pageNumber = other.getPageNumber();
                    if (this$pageNumber == null) {
                        if (other$pageNumber == null) {
                            break label70;
                        }
                    } else if (this$pageNumber.equals(other$pageNumber)) {
                        break label70;
                    }

                    return false;
                }

                if (this.isAutoPage() != other.isAutoPage()) {
                    return false;
                } else {
                    Object this$orderColumn = this.getOrderColumn();
                    Object other$orderColumn = other.getOrderColumn();
                    if (this$orderColumn == null) {
                        if (other$orderColumn != null) {
                            return false;
                        }
                    } else if (!this$orderColumn.equals(other$orderColumn)) {
                        return false;
                    }

                    Object this$orderSort = this.getOrderSort();
                    Object other$orderSort = other.getOrderSort();
                    if (this$orderSort == null) {
                        if (other$orderSort != null) {
                            return false;
                        }
                    } else if (!this$orderSort.equals(other$orderSort)) {
                        return false;
                    }

                    if (this.isAutoOrderBy() != other.isAutoOrderBy()) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof PageInfo;
    }

    public int hashCode() {
        int result = 1;
        Object $pageStartIndex = this.getPageStartIndex();
        result = result * 59 + ($pageStartIndex == null ? 43 : $pageStartIndex.hashCode());
        Object $pageSize = this.getPageSize();
        result = result * 59 + ($pageSize == null ? 43 : $pageSize.hashCode());
        Object $totalCount = this.getTotalCount();
        result = result * 59 + ($totalCount == null ? 43 : $totalCount.hashCode());
        Object $pageNumber = this.getPageNumber();
        result = result * 59 + ($pageNumber == null ? 43 : $pageNumber.hashCode());
        result = result * 59 + (this.isAutoPage() ? 79 : 97);
        Object $orderColumn = this.getOrderColumn();
        result = result * 59 + ($orderColumn == null ? 43 : $orderColumn.hashCode());
        Object $orderSort = this.getOrderSort();
        result = result * 59 + ($orderSort == null ? 43 : $orderSort.hashCode());
        result = result * 59 + (this.isAutoOrderBy() ? 79 : 97);
        return result;
    }

    public Integer getPageStartIndex() {
        return this.pageStartIndex;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public Integer getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageNumber() {
        return this.pageNumber;
    }

    public boolean isAutoPage() {
        return this.autoPage;
    }

    public String getOrderColumn() {
        return this.orderColumn;
    }

    public PageInfo.SortType getOrderSort() {
        return this.orderSort;
    }

    public boolean isAutoOrderBy() {
        return this.autoOrderBy;
    }

    public static enum SortType {
        ASC("ASC"),
        DESC("DESC");

        private String sort;

        private SortType(String sort) {
            this.sort = sort;
        }

        public String getSort() {
            return this.sort;
        }
    }
}
