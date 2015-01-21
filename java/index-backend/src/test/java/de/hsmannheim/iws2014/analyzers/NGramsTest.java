package de.hsmannheim.iws2014.analyzers;


import org.junit.Test;

public class NGramsTest {

    private static final String text = "Replace this with your own text!";

    @Test
    public void test_n_grams() throws Exception {
        ThreeGramsAnalyzer threeGramsAnalyzer = new ThreeGramsAnalyzer();
        AnalyzerUtils.displayTokens(threeGramsAnalyzer, text);
    }
}
