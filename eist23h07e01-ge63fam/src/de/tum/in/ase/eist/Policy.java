package de.tum.in.ase.eist;

public class Policy {

    private Context context;

    public Policy(Context context) {
        this.context = context;
    }

    public void configure() {
        if (context.isChaptersSortedByName()) {
            BinarySearch binarySearch = new BinarySearch();
            context.setSearchAlgorithm(binarySearch);
        } else {
            LinearSearch linearSearch = new LinearSearch();
            context.setSearchAlgorithm(linearSearch);
        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
