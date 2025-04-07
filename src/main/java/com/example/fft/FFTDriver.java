package com.example.fft;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;

public class FFTDriver {

    public static void main(String[] args) throws LineUnavailableException, IOException {

/*
        AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4 * 2, 44100, false);
        TargetDataLine line = AudioLineSelector.selectLine(format);
        System.out.println("Selected Line: " + line.getLineInfo());
        line.close();

 */


        byte[] audioData = AudioRecorder.recordAudio(5000); //nimmt nur 5 Sekunden auf...
        double[] fftData = FFTAnalyzer.performFFT(audioData);
        double[] frequencyAndDecibel = FrequencyAndDecibelCalculator.calculateFrequencyAndDecibel(fftData, 44100);



        System.out.println("Frequenz: " + frequencyAndDecibel[0] + " Hz");
        System.out.println("Dezibel: " + frequencyAndDecibel[1] + "dB");


    }
}
