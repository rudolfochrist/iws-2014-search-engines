package de.hsmannheim.iws2014.indexing;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.miscellaneous.PerFieldAnalyzerWrapper;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class does all the hard work. Indexing developers.
 */
public class Indexer {

    public static final String FIRSTNAME = "firstname";
    public static final String SURNAME = "surname";
    public static final String RESIDENCE = "residence";
    public static final String PERSONAL_ABSTRACT = "personalAbstract";
    private final IndexWriter index;

    public Indexer(Directory directory) throws IOException {
        StandardAnalyzer defaultAnalyzer = new StandardAnalyzer();
        SimpleAnalyzer simpleAnalyzer = new SimpleAnalyzer();
        HashMap<String, Analyzer> analyzerMapping = new HashMap<String, Analyzer>();
        analyzerMapping.put(FIRSTNAME, simpleAnalyzer);
        analyzerMapping.put(SURNAME, simpleAnalyzer);
        analyzerMapping.put(RESIDENCE, simpleAnalyzer);

        IndexWriterConfig conf = new IndexWriterConfig(Version.LATEST, new PerFieldAnalyzerWrapper(defaultAnalyzer, analyzerMapping));
        index = new IndexWriter(directory, conf);
    }

    public Indexer() throws IOException {
        this(new RAMDirectory());
    }

    public void index(List<Developer> developers) {
        try {
            try {
                for (Developer developer : developers) {
                    Document doc = new Document();
                    doc.add(textField(FIRSTNAME, developer.firstname));
                    doc.add(textField(SURNAME, developer.surname));
                    doc.add(textField(RESIDENCE, developer.residence));
                    doc.add(textField(PERSONAL_ABSTRACT, developer.personalAbstract));
                    index.addDocument(doc);
                }
            } finally {
                index.close();
            }
        } catch (IOException e) {
            new IndexerException("Cannot add dcoument to index.", e);
        }
    }

    private static TextField textField(String name, String value) {
        return new TextField(name, value, Field.Store.YES);
    }

    static class IndexerException extends RuntimeException {
        public IndexerException(String message, Throwable e) {
            super(message, e);
        }
    }
}