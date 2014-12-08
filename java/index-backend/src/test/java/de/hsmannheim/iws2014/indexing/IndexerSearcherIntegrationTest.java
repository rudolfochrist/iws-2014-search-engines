package de.hsmannheim.iws2014.indexing;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.RAMDirectory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Category(IntegrationTest.class)
public class IndexerSearcherIntegrationTest {

    private static IndexSearcher index;

    @BeforeClass
    public static void setupClass() throws Exception {
        ArrayList<Developer> developers = new ArrayList<Developer>();
        developers.add(new Developer()
                .addFirstname("Donald")
                .addSurname("Duck")
                .addResidence("Duckburg")
                .addPersonalAbstract("I love Java."));
        developers.add(new Developer()
                .addFirstname("Gus")
                .addSurname("Goose")
                .addResidence("Duckburg")
                .addPersonalAbstract("I hate Java. I love apple pies"));
        developers.add(new Developer()
                .addFirstname("Bruce")
                .addSurname("Wayne")
                .addResidence("Gotham City")
                .addPersonalAbstract("Alfred! Where're my slippers?"));
        developers.add(new Developer()
                .addFirstname("Clark")
                .addSurname("Kent")
                .addResidence("Metropolis")
                .addPersonalAbstract("I will never fully grasp the eating thing..."));

        RAMDirectory dir = new RAMDirectory();
        Indexer indexer = new Indexer(dir);
        indexer.index(developers);
        index = new IndexSearcher(DirectoryReader.open(dir));
    }

    @Test
    public void search_java_in_content() throws Exception {
        Searcher searcher = new Searcher(index);
        SearchResult result = searcher.search("Java");
        assertThat(result.hits.totalHits, is(2));
    }

    @Test
    public void search_for_residence() throws Exception {
        Searcher searcher = new Searcher(index);
        SearchResult result = searcher.search("residence:duckburg");
        assertThat(result.hits.totalHits, is(2));
    }

}
