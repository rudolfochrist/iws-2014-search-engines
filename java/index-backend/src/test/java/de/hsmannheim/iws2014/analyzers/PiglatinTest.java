package de.hsmannheim.iws2014.analyzers;

import com.sun.corba.se.impl.orb.ParserTable;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.simple.SimpleQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PiglatinTest {

    private String[] terms = new String[] {
            "foobar", "gumba", "trantual", "alfredolino", "ugulu", "bla"
    };

    @Test
    public void test_without_analyzer() throws Exception {
         RAMDirectory dir = new RAMDirectory();
         CustomAnalyzingIndexer indexer = new CustomAnalyzingIndexer(new StandardAnalyzer(), dir);
         indexer.index(Arrays.asList(terms));

         TopDocs foobar = contentSearch("foobar", dir);
         assertThat(foobar.totalHits, is(equalTo(1)));

         TopDocs oobarfay = contentSearch("oobarfay", dir);
         assertThat(oobarfay.totalHits, is(equalTo(0)));
     }

    @Test
    public void test_with_pig_lating_analyzer() throws Exception {
        RAMDirectory dir = new RAMDirectory();
        CustomAnalyzingIndexer indexer = new CustomAnalyzingIndexer(new PigLatinAnalyzer(), dir);
        indexer.index(Arrays.asList(terms));

        TopDocs foobar = contentSearch("foobar", dir);
        assertThat(foobar.totalHits, is(equalTo(0)));

        TopDocs oobarfay = contentSearch("oobarfay", dir);
        assertThat(oobarfay.totalHits, is(equalTo(1)));
    }

    @Test
    public void test_conversion() throws Exception {
        StringBuffer sb = new StringBuffer();
        for (String term : terms) {
            sb.append(term).append(" ");
        }
        AnalyzerUtils.displayTokens(new PigLatinAnalyzer(), sb.toString());
    }

    private static TopDocs contentSearch(String query, Directory directory) throws IOException {
        IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(directory));
        SimpleQueryParser queryParser = new SimpleQueryParser(new StandardAnalyzer(), "content");
        return indexSearcher.search(queryParser.parse(query), 1000);
    }
}
