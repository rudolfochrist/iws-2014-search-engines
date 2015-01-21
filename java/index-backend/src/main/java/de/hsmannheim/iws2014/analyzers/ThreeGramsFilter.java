package de.hsmannheim.iws2014.analyzers;


import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;

import java.io.IOException;
import java.util.ArrayList;

public final class ThreeGramsFilter extends TokenFilter {

    private CharTermAttribute term;
    private PositionIncrementAttribute pos;
    private ArrayList<String> grams;

    public ThreeGramsFilter(Tokenizer tokenizer) {
        super(tokenizer);
        term = addAttribute(CharTermAttribute.class);
        pos = addAttribute(PositionIncrementAttribute.class);
        grams = new ArrayList<String>();
    };

    @Override
    public boolean incrementToken() throws IOException {
        /*
         IMPLEMENTATION NOTES:

        The consumer, most of the time this is the Lucene indexer, repeatedly calls incrementToken() until it gets a `false'.
        `term' is the current token under consideration. Feel free to modify the term at your will. After returning true
        from incrementToken() the consumer puts the term, AS IS, into the index.

        You'll have to call input.incrementToken() inside your incrementToken() method to read the next token from the
        stream. It is then placed in your own `term' variable (this is where the Lucene magic kicks in). But caution,
        if you call input.incrementToken() more than once, you're actually skipping tokens! Best is you take
        this idiom

          `if (!input.incrementToken()) return false;

        and put it in the right place of your incrementToken() method.

        As a small refresher:
        What you're trying to accomplish is to write n-grams of a token, after the token itself, into the index. Because
        the n-grams should be found at the same position as the original token, every n-gram receives a (relative) position
        of 1. So, if you read this token [test] than your filter should generate n-gram tokens for it. The final tokens
        written to the index are

        [test] [tes] and [est]

        That's it. Happy hacking!


        BEWARE:

        - Token filters should not modify offsets. If you feel that your filter would need to modify offsets, then it should probably be implemented as a tokenizer.
        - Token filters should not insert positions. If a filter needs to add tokens, then they should all have a position increment of 0.
          Important if you write something like a n-grams filter :)
        - When they add tokens, token filters should call AttributeSource.clearAttributes() first. Important if you write something like a n-grams filter :)
        - When they remove tokens, token filters should increment the position increment of the following token.
        - Token filters should preserve position lengths.

        For more information see also: https://lucene.apache.org/core/4_10_3/core/org/apache/lucene/analysis/package-summary.html
         */

        // add code here

        if (!input.incrementToken()) return false;

        // add code here

        return true;
    }
}
