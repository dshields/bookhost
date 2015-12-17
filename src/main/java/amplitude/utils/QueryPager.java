package amplitude.utils;

import amplitude.model.IPager;
import org.hibernate.Query;

import java.util.List;

public class QueryPager<T> implements IPager<T> {
    Query countQuery;
    Query query;
    int currentPage;
    int pageSize;
    int pageCount = -1;

    public QueryPager(Query countQuery, Query itemQuery, int pageSize) {
        this.countQuery = countQuery;
        this.query = itemQuery;
        setPageSize(pageSize);
        setPage(0);
    }

    private void setPageCount() {
        int maxCount = ((Long) countQuery.uniqueResult()).intValue();
        pageCount = (maxCount / getPageSize());
        if (maxCount % getPageSize() > 0) {
            pageCount++;
        }

    }

    public int getPageCount() {
        if (pageCount >= 0)
            return pageCount;
        setPageCount();
        return pageCount;
    }

    public List<T> getPageItems() {
        query.setMaxResults(getPageSize());
        query.setFirstResult((getCurrentPage() - 1) * getPageSize());
        return query.list();
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void nextPage() {
        setPage(currentPage++);
    }

    public void setPage(int pageNumber) {
        int pageCount = getPageCount();
        currentPage = pageNumber;
        if (currentPage >= pageCount) {
            currentPage = pageCount - 1;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int size) {
        pageSize = size;
        setPageCount();
    }

    public boolean hasMorePages() {
        if (pageCount == -1)
            return false;
        return currentPage != pageCount - 1;
    }

}
