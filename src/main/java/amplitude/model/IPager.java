package amplitude.model;

import java.util.List;

public interface IPager<T> {
    int getPageCount();

    int getPageSize();

    int getCurrentPage();

    void setPage(int pageNumber);

    void nextPage();

    boolean hasMorePages();

    List<T> getPageItems();
}
