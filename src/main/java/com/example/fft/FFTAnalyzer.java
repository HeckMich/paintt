package com.example.fft;

import org.jtransforms.fft.DoubleFFT_1D;



public class FFTAnalyzer {

    public static double[] performFFT(byte[] audioData){
        int numSamples = audioData.length / 2;
        double[] samples = new double[numSamples];

        for(int i = 0; i < numSamples; i++){
            samples[i] = (double) ((audioData[i * 2] & 0xFF) | (audioData[i * 2 + 1] << 8));

        }

        DoubleFFT_1D fft = new DoubleFFT_1D(numSamples);
        fft.realForward(samples);

        return samples;
    }
}
