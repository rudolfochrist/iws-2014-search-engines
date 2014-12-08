package de.hsmannheim.iws2014.analyzers;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;

import java.io.IOException;

/**
 * Actually transforms each token.
 */
public final class PigLatinFilter extends TokenFilter {

    public static final String VOCALS = "aeio";

    public PigLatinFilter(TokenStream stream) {
        super(stream);
    }

    @Override
    public boolean incrementToken() throws IOException {
        return true;
    }
}

