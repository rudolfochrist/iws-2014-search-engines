package de.hsmannheim.iws2014.analyzers;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardTokenizer;

import java.io.Reader;

public class ThreeGramsAnalyzer extends Analyzer {
    @Override
    protected TokenStreamComponents createComponents(String fieldName, Reader reader) {
        StandardTokenizer tokenizer = new StandardTokenizer(reader);
        ThreeGramsFilter threeGramsFilter = new ThreeGramsFilter(tokenizer);
        return new TokenStreamComponents(tokenizer, threeGramsFilter);
    }
}
