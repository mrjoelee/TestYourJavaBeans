package com.testyourjavabeans;

import com.apps.util.Console;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Intro implements Runnable {

    private static final String introImages = "images/image-N.txt";
    List<String> images = this.loadImages();

    //loads the images from the filepath.
    private List<String> loadImages() {
        ArrayList<String> images = new ArrayList<>();

        try {
            for (int i = 0; i <= 1; ++i) {
                String imageFile = introImages.replace('N', String.valueOf(i).charAt(0));
                images.add(Files.readString(Path.of(imageFile)));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return images;
    }

    //shows each image according to the 'N' of the image folder size.
    public void show() {
        System.out.println(this.images.get(0));
        Console.clear();
    }

    public void showBanner() {
        System.out.println(this.images.get(1));
    }

    public void next() {
        this.run();
    }

    public Intro() {
    }

    @Override
    public void run() {
        Console.clear();

        Iterator var1 = this.images.iterator();

        while (var1.hasNext()) {
            String image = (String) var1.next();
            System.out.println(image);
            pause();
            Console.clear();
        }
    }

    private static void pause() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException var1) {
        }
    }
}
