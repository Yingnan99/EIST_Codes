package de.tum.in.ase.eist;

import java.util.List;

public class Context {
    private List<Chapter> book;
    private SearchStrategy searchAlgorithm;

    public Context(List<Chapter> book, SearchStrategy searchAlgorithm) {
        this.book = book;
        this.searchAlgorithm = searchAlgorithm;
    }

    public Context() {

    }

    public int search(String name) {

        return searchAlgorithm.performSearch(book, name);
    }

    public boolean isChaptersSortedByName() {
        // 单个章节或无章节时视为已排序
        if (book.size() <= 1) {
            return true;
        }

        for (int i = 1; i < book.size(); i++) {
            Chapter previousChapter = book.get(i - 1);
            Chapter currentChapter = book.get(i);

            // 发现不按名称排序的章节
            if (previousChapter.getName().compareTo(currentChapter.getName()) > 0) {
                return false;
            }
        }

        return true; // 所有章节按名称排序
    }

    public List<Chapter> getBook() {
        return book;
    }

    public void setBook(List<Chapter> book) {
        this.book = book;
    }

    public SearchStrategy getSearchAlgorithm() {
        return searchAlgorithm;
    }

    public void setSearchAlgorithm(SearchStrategy searchAlgorithm) {
        this.searchAlgorithm = searchAlgorithm;
    }
}
