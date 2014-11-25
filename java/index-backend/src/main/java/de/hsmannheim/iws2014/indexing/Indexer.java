package de.hsmannheim.iws2014.indexing;

import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;

import java.io.IOException;
import java.util.List;

/**
 * This class does all the hard work. Indexing developers.
 */
public class Indexer {

    private final IndexWriter index;

    public Indexer(IndexWriter index) {
        this.index = index;
    }

    public void index(List<Developer> developers) {
        try {
            try {
                for (Developer developer : developers) {
                    Document doc = new Document();
                    doc.add(stringField("firstname", developer.firstname));
                    doc.add(stringField("surname", developer.surname));
                    doc.add(stringField("residence", developer.residence));
                    doc.add(textField("personalAbstract", developer.personalAbstract));
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