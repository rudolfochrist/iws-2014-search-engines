package de.hsmannheim.iws2014.analyzers;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;

/**
 * Actually transforms each token.
 */
public class PigLatinFilter extends TokenFilter {

    public PigLatinFilter(TokenStream stream) {
        super(stream);
    }

    @Override
    public boolean incrementToken() throws IOException {
        return true;
    }
}
