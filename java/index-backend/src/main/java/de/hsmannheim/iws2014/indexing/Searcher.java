package de.hsmannheim.iws2014.indexing;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;

import java.io.IOException;

/**
 * Search for indexed Developers.
 */
public class Searcher {

    private IndexSearcher index;

    public Searcher(IndexSearcher index) {
        this.index = index;
    }

    public SearchResult search(String queryString) throws ParseException, IOException {
        QueryParser queryParser = new QueryParser(Indexer.PERSONAL_ABSTRACT, new StandardAnalyzer());
        Query query= queryParser.parse(queryString);
        long start = System.currentTimeMillis();
        TopDocs hits = index.search(query, 1000);
        long stop = System.currentTimeMillis();
        return new SearchResult(hits, stop-start);
    }
}
