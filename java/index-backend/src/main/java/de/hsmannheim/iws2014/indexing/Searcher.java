package de.hsmannheim.iws2014.indexing;

import org.apache.lucene.search.IndexSearcher;

/**
 * Search for indexed Developers.
 */
public class Searcher {

    private IndexSearcher index;

    public Searcher(IndexSearcher index) {
        this.index = index;
    }

    public void search(String queryString) {

    }
}
