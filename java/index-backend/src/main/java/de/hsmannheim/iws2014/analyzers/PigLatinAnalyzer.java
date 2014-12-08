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
        return new TokenStreamComponents(new StandardTokenizer(reader));
    }
}
