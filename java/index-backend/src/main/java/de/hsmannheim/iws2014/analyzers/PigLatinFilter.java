package de.hsmannheim.iws2014.analyzers;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.util.List;

/**
 * Actually transforms each token.
 */
public class PigLatinFilter extends TokenFilter {

    public static final String VOCALS = "aeio";

    public PigLatinFilter(TokenStream stream) {
        super(stream);
    }

    @Override
    public boolean incrementToken() throws IOException {
        return true;
    }
}

