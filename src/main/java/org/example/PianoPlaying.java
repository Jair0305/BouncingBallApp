package org.example;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class PianoPlaying {

    public static void main(String[] args) {
        try {
            Synthesizer synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();

            MidiChannel channel = synthesizer.getChannels()[0];

            // Establece el instrumento (piano en este caso)
            channel.programChange(0);

            // Reproduce algunas notas (por ejemplo, C4, D4, E4, F4)
//            playNote(channel, 64);
//            playNote(channel, 63);
//            playNote(channel, 64);
//            playNote(channel, 63);
//            playNote(channel, 64);
//            playNote(channel, 59);
//            playNote(channel, 62);
//            playNote(channel, 60);
//            playNote(channel, 57);
//            playNote(channel, 65);
            int[] furElise = {64,63,64,63,64,59,62,60,57,65};
            for(int i = 0; i<furElise.length;i++)
            {
                playNote(channel, furElise[i]);
            }
            // Cierra el sintetizador
            synthesizer.close();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    private static void playNote(MidiChannel channel, int note) {
        int velocity = 100; // Velocidad de la nota (0-127)
        int duration = 350; // Duración de la nota en milisegundos

        channel.noteOn(note, velocity);
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        channel.noteOff(note);
    }
}
