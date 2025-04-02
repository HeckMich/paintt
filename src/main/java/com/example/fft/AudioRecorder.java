package com.example.fft;

import javax.sound.sampled.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class AudioRecorder {

    public static byte[] recordAudio(int durationMillis) throws LineUnavailableException, IOException {
        AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 48000, 16, 2, 4, 48000, false);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

        try {
            TargetDataLine line = AudioLineSelector.selectLineInteractively(format);
            line.open(format);
            line.start();

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;
            long startTime = System.currentTimeMillis();

            while (System.currentTimeMillis() - startTime < durationMillis) {
                bytesRead = line.read(buffer, 0, buffer.length);
                out.write(buffer, 0, bytesRead);
            }

            line.stop();
            line.close();
            return out.toByteArray();
        }catch (LineUnavailableException e){

            System.err.println("Line unavailable: " + e.getMessage());
            return null;

        }
    }
}
