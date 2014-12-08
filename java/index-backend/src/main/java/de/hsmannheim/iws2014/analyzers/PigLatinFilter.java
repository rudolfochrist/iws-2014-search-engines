package de.hsmannheim.iws2014.analyzers;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;

/**
 * Actually transforms each token.
 */
public final class PigLatinFilter extends TokenFilter {

    public static final String VOCALS = "aeio";

    private final CharTermAttribute term;

    public PigLatinFilter(TokenStream stream) {
        super(stream);
        this.term = addAttribute(CharTermAttribute.class);
    }

    @Override
    public boolean incrementToken() throws IOException {
        if (!input.incrementToken()) return false;

        StringBuilder sTerm = new StringBuilder(term);
        char firstChar = sTerm.charAt(0);

        if(!VOCALS.contains(""+firstChar)) {
            sTerm.deleteCharAt(0);
            sTerm.append(firstChar);
        }

        sTerm.append("ay");
        term.setEmpty();
        term.append(sTerm);

        return true;
    }
}

