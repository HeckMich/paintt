package com.example.fft;

public class FrequencyAndDecibelCalculator {

    public static double[] calculateFrequencyAndDecibel(double[] fftData, double sampleRate){

        int numSamples = fftData.length;
        double[] frequencyAndDecibel = new double[2];

        double maxMagnitude = 0;
        int maxIndex = 0;

        for (int i = 0; i < numSamples / 2; i++){
            double real = fftData[i * 2];
            double imaginary = fftData[i * 2 + 1];
            double magnitude = Math.sqrt(real * real + imaginary * imaginary);


            //Rauschunterdrückung
            if (magnitude < 100){
                magnitude = 0;
            }
            if (magnitude > maxMagnitude){
                maxMagnitude = magnitude;
                maxIndex = i;
            }
        }

        double frequency = maxIndex * sampleRate / numSamples;
        double decibel = 20 * Math.log10(maxMagnitude / 32768.0); //32768.0 = max. Sample wert für 16 Bit Samples

        frequencyAndDecibel[0] = frequency;
        frequencyAndDecibel[1] = decibel;

        return frequencyAndDecibel;

    }
}
