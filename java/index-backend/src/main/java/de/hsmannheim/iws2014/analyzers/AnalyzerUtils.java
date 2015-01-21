package de.hsmannheim.iws2014.analyzers;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Code taken from: http://www.manning.com/hatcher3/
 */
public class AnalyzerUtils {
    private static class StreamItem {
        public String term;
        public int position;
        public StreamItem(String term, int position) {
            this.term = term;
            this.position = position;
        }
    }

    public static List<StreamItem> tokensFromAnalysis(Analyzer analyzer,
                                              String text) throws IOException {
        TokenStream stream =
                analyzer.tokenStream("contents", new StringReader(text));
        stream.reset();
        ArrayList tokenList = new ArrayList();
        while (true) {
            stream.incrementToken();
            CharTermAttribute token = stream.addAttribute(CharTermAttribute.class);
            PositionIncrementAttribute pos = stream.addAttribute(PositionIncrementAttribute.class);
            if (StringUtils.isEmpty(token.toString())) break;

            tokenList.add(new StreamItem(token.toString(), pos.getPositionIncrement()));
        }

        return tokenList;
  }

  public static void displayTokens(Analyzer analyzer,
                                 String text) throws IOException {
    List<StreamItem> tokens = tokensFromAnalysis(analyzer, text);

    for (StreamItem token : tokens) {
        System.out.print(String.format("[%s:%s] ", token.position, token.term ));
    }
  }

  public static void main(String[] args) throws IOException {
    System.out.println("Standard");
    displayTokens(new PigLatinAnalyzer(),
            "The quick brown fox....");
  }
}
