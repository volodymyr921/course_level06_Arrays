package com.shpp.p2p.cs.vomelianchuk.assignment6.sg;

import acm.graphics.*;

/**
 * File: SteganographyLogic.java
 * The program builds the logic of classifying
 * a black-and-white drawing into an image,
 * and vice versa, the output from the image
 * is a black-and-white message
 */

public class SteganographyLogic {
    /**
     * Given a GImage containing a hidden message, finds the hidden message
     * contained within it and returns a boolean array containing that message.
     * <p/>
     * A message has been hidden in the input image as follows.  For each pixel
     * in the image, if that pixel has a red component that is an even number,
     * the message value at that pixel is false.  If the red component is an odd
     * number, the message value at that pixel is true.
     *
     * @param source The image containing the hidden message.
     * @return The hidden message, expressed as a boolean array.
     */
    public static boolean[][] findMessage(GImage source) {
        int[][] pixels = source.getPixelArray();
        boolean[][] secretMessage = new boolean[pixels.length][pixels[0].length];

        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                secretMessage[i][j] = GImage.getRed(pixels[i][j]) % 2 != 0;
            }
        }

        return secretMessage;
    }

    /**
     * Hides the given message inside the specified image.
     * <p/>
     * The image will be given to you as a GImage of some size, and the message will
     * be specified as a boolean array of pixels, where each white pixel is denoted
     * false and each black pixel is denoted true.
     * <p/>
     * The message should be hidden in the image by adjusting the red channel of all
     * the pixels in the original image.  For each pixel in the original image, you
     * should make the red channel an even number if the message color is white at
     * that position, and odd otherwise.
     * <p/>
     * You can assume that the dimensions of the message and the image are the same.
     * <p/>
     *
     * @param message The message to hide.
     * @param source  The source image.
     * @return A GImage whose pixels have the message hidden within it.
     */
    public static GImage hideMessage(boolean[][] message, GImage source) {
        int[][] pixels = source.getPixelArray();

        for (int i = 0; i < message.length; i++) {
            for (int j = 0; j < message[0].length; j++) {

                int red = GImage.getRed(pixels[i][j]);
                int green = GImage.getGreen(pixels[i][j]);
                int blue = GImage.getBlue(pixels[i][j]);

                if (message[i][j]) {
                    if (red % 2 != 0) continue;
                    red++;
                } else {
                    if (red % 2 == 0) continue;
                    red--;
                }

                pixels[i][j] = GImage.createRGBPixel(red, green, blue);
            }
        }

        return new GImage(pixels);
    }
}
