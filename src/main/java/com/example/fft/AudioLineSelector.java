package com.example.fft;

import javax.sound.sampled.*;
import java.util.Scanner;

public class AudioLineSelector {


    public static TargetDataLine selectLineInteractively(AudioFormat format) throws LineUnavailableException {
        Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
        System.out.println("Available Mixers:");
        for (int i = 0; i < mixerInfos.length; i++) {
            System.out.println(i + ": " + mixerInfos[i].getName());
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter mixer index: ");
        int mixerIndex = scanner.nextInt();
        return selectLineByIndex(mixerIndex, format);
    }

    public static TargetDataLine selectLineByIndex(int mixerIndex, AudioFormat format) throws LineUnavailableException {
        Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
        if (mixerIndex >= 0 && mixerIndex < mixerInfos.length) {
            Mixer mixer = AudioSystem.getMixer(mixerInfos[mixerIndex]);
            Line.Info[] targetLineInfos = mixer.getTargetLineInfo();
            for (Line.Info lineInfo : targetLineInfos) {
                if (lineInfo.getLineClass().equals(TargetDataLine.class)) {
                    try {
                        TargetDataLine line = (TargetDataLine) mixer.getLine(lineInfo);
                        line.open(format);
                        return line;
                    } catch (LineUnavailableException e) {
                        // Line nicht verfügbar, versuche die nächste Line
                    }
                }
            }
            throw new LineUnavailableException("No suitable TargetDataLine found for mixer at index: " + mixerIndex);
        } else {
            throw new IllegalArgumentException("Invalid mixer index: " + mixerIndex);
        }
    }







}
