package structure50;

import java.util.ArrayList;
import java.util.List;

/**
 * A simplistic, in-memory implementation of the Value List
 * Handler (aka FastTrack) pattern. Example use:<br/>
 * In Controller:<br/>
 * ValueListHandler&lt;CatalogItem> list = new ValueListHandler(resultsList);
 * <br/>In View:<br/>
 * &lt;c:forEach items=${list} item="catalogItem"><br/>
 * &lt;tr>&lt;td>${item.title}&lt;td>${item.price}&lt;/tr>
 * <p>
 * Not exhaustively tested for all edge cases - use with care.
 */
public class ValueListHandler<T> {
	private static final int DEFAULT_PAGE_SIZE = 10;
	private int pageSize = DEFAULT_PAGE_SIZE;
	private int currentPage = -1;
	private final List<T> list;

	public ValueListHandler() {
		list = new ArrayList<T>();
	}

	public ValueListHandler(List<T> myList) {
		list = myList;
	}

	public void add(T t) {
		list.add(t);
	}

	public List<T> nextPage() {
		if (currentPage < (list.size() / pageSize)) {
			++currentPage;
		}
		List<T> subList = subList();
		return subList;
	}

	public List<T> prevPage() {
		if (currentPage > 0) {
			--currentPage;
		}
		List<T> subList = subList();
		return subList;
	}

	/**
	 * @return The current page
	 */
	private List<T> subList() {
		final int startIndex = currentPage * pageSize;
		final int endIndex =
			Math.min(list.size(), (1 + currentPage) * pageSize);
		return list.subList(startIndex, endIndex);
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		// XXX need to adjust for current page before setting
		this.pageSize = pageSize;
	}

	public int getListSize() {
		return list.size();
	}
}