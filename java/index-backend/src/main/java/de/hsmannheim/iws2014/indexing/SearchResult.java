package de.hsmannheim.iws2014.indexing;

import org.apache.lucene.search.TopDocs;

/**
 * Represents a single search results.
 */
public class SearchResult {

    public TopDocs hits;
    public Long searchTime;

    public SearchResult(TopDocs hits, long time) {
        this.hits = hits;
        this.searchTime = time;
    }
}
