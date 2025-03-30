package com.example.fft;

import javax.sound.sampled.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class AudioRecorder {

    public static byte[] recordAudio(int durationMillis) throws LineUnavailableException, IOException{
        AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4 * 2, 44100, false);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

        if (!AudioSystem.isLineSupported(info)){
            System.err.println("Line not supported");
            return null;
        }

        TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);
        line.open(format);
        line.start();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < durationMillis){
            bytesRead = line.read(buffer, 0, buffer.length);
            out.write(buffer, 0, bytesRead);
        }

        line.stop();
        line.close();
        return out.toByteArray();
    }
}
