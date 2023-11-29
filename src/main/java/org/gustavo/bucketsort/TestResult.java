/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.gustavo.bucketsort;

import java.text.DecimalFormat;

public class TestResult {
    private final String algorithm;
    private final String elapsedTime;
    private final long swaps;
    
    public TestResult(String algorithm, long elapsedTime, long swaps) {
        DecimalFormat df = new DecimalFormat("#0.0000000");
        
        this.algorithm = algorithm;
        this.elapsedTime = df.format((double) elapsedTime / 1E9);
        this.swaps = swaps;
    }

    public String getElapsedTime() {
        return elapsedTime;
    }

    public long getSwaps() {
        return swaps;
    }

    public String getAlgorithm() {
        return algorithm;
    }
}
