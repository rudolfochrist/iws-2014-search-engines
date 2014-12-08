package de.hsmannheim.iws2014.analyzers;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.util.List;

/**
 * Indexer using a custom analyzer.
 */
public class CustomAnalyzingIndexer {

    private final IndexWriter indexWriter;

    public CustomAnalyzingIndexer(Analyzer analyzer, Directory directory) throws IOException {
        IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, analyzer);
        indexWriter = new IndexWriter(directory, config);
    }

    public void index(List<String> texts) throws IOException {
        for (String text : texts) {
            Document doc = new Document();
            doc.add(new TextField("content", text, Field.Store.NO));
            indexWriter.addDocument(doc);
        }
        indexWriter.close();
    }
}
