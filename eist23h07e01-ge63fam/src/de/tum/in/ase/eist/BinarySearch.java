package de.tum.in.ase.eist;

import java.util.List;

public class BinarySearch implements SearchStrategy {

    public int performSearch(List<Chapter> chapters, String chapterName) {
        int left = 0;
        int right = chapters.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Chapter chapter = chapters.get(mid);
            int comparison = chapter.getName().compareTo(chapterName);

            if (comparison == 0) {
                return chapter.getPageNumber();
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}
