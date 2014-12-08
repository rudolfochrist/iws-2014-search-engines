package de.hsmannheim.iws2014.analyzers;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardTokenizer;

import java.io.Reader;

/**
 *  Transforms each token.
 */
public class PigLatinAnalyzer extends Analyzer {
    @Override
    protected TokenStreamComponents createComponents(String fieldName, Reader reader) {
        StandardTokenizer tokenizer = new StandardTokenizer(reader);
        PigLatinFilter filter = new PigLatinFilter(tokenizer);
        return new TokenStreamComponents(tokenizer, filter);
    }
}
