package de.tum.in.ase.eist;

import java.util.List;

public class LinearSearch implements SearchStrategy {

    public int performSearch(List<Chapter> chapters, String chapterName) {
        for (int i = 0; i < chapters.size(); i++) {
            Chapter chapter = chapters.get(i);
            if (chapter.getName().equals(chapterName)) {
                return chapter.getPageNumber();
            }
        }
        return -1;
    }
}
