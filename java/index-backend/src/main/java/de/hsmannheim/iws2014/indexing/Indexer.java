package de.hsmannheim.iws2014.indexing;

import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;

import java.io.IOException;
import java.util.List;

/**
 * This class does all the hard work. Indexing developers.
 */
public class Indexer {

    public static final String FIRSTNAME = "firstname";
    public static final String SURNAME = "surname";
    public static final String RESIDENCE = "residence";
    public static final String PERSONAL_ABSTRACT = "personalAbstract";
    private final IndexWriter index;

    public Indexer(IndexWriter index) {
        this.index = index;
    }

    public void index(List<Developer> developers) {
        try {
            try {
                for (Developer developer : developers) {
                    Document doc = new Document();
                    doc.add(stringField(FIRSTNAME, developer.firstname));
                    doc.add(stringField(SURNAME, developer.surname));
                    doc.add(stringField(RESIDENCE, developer.residence));
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

    private static StringField stringField(String name, String value) {
        return new StringField(name, value, Field.Store.YES);
    }

    private static TextField textField(String name, String value) {
        return new TextField(name, value, Field.Store.NO);
    }

    static class IndexerException extends RuntimeException {
        public IndexerException(String message, Throwable e) {
            super(message, e);
        }
    }
}