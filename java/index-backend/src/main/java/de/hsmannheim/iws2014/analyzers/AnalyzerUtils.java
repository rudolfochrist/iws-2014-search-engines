package de.hsmannheim.iws2014.analyzers;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Code taken from: http://www.manning.com/hatcher3/
 */
public class AnalyzerUtils {
    public static String[] tokensFromAnalysis(Analyzer analyzer,
                                              String text) throws IOException {
        TokenStream stream =
                analyzer.tokenStream("contents", new StringReader(text));
        stream.reset();
        ArrayList tokenList = new ArrayList();
        while (true) {
            stream.incrementToken();
            CharTermAttribute token = stream.addAttribute(CharTermAttribute.class);
            if (StringUtils.isEmpty(token.toString())) break;

            tokenList.add(token.toString());
        }

        return (String[]) tokenList.toArray(new String[0]);
  }

  public static void displayTokens(Analyzer analyzer,
                                 String text) throws IOException {
    String[] tokens = tokensFromAnalysis(analyzer, text);

    for (int i = 0; i < tokens.length; i++) {
      String token = tokens[i];

      System.out.print("[" + token + "] ");
    }
  }

  public static void main(String[] args) throws IOException {
    System.out.println("Standard");
    displayTokens(new PigLatinAnalyzer(),
            "The quick brown fox....");
  }
}
