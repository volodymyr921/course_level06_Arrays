package com.shpp.p2p.cs.vomelianchuk.assignment6.hg;

public class HistogramEqualizationLogic {
    // The maximum luminance of a pixel
    private static final int MAX_LUMINANCE = 255;

    // The length of the histogram of the image
    private static final int LENGTH_HISTOGRAM_IMAGE = 256;

    /**
     * Given the luminance of the pixels in an image, returns a histogram of the frequencies of
     * those luminance.
     * <p/>
     * You can assume that pixel luminance range from 0 to MAX_LUMINANCE, inclusive.
     *
     * @param luminance The luminance in the picture.
     * @return A histogram of those luminance.
     */
    public static int[] histogramFor(int[][] luminance) {
        int[] histogramImage = new int[LENGTH_HISTOGRAM_IMAGE];

        for (int i = 0; i <= MAX_LUMINANCE; i++) {
            histogramImage[i] = numberTheSameFrequency(luminance, i);
        }

        return histogramImage;
    }

    /**
     * Counts the number of pixels of the same luminance
     *
     * @param luminance The luminance in the picture.
     * @param valueLuminance The luminance value in the current cell
     * @return The number of pixels with the same luminance
     */
    private static int numberTheSameFrequency(int[][] luminance, int valueLuminance) {
        int countFrequency = 0;

        for (int i = 0; i < luminance.length; i++) {
            for (int j = 0; j < luminance[0].length; j++) {
                if (luminance[i][j] == valueLuminance) countFrequency++;
            }
        }

        return countFrequency;
    }

    /**
     * Given a histogram of the luminance in an image, returns an array of the cumulative
     * frequencies of that image.  Each entry of this array should be equal to the sum of all
     * the array entries up to and including its index in the input histogram array.
     * <p/>
     * For example, given the array [1, 2, 3, 4, 5], the result should be [1, 3, 6, 10, 15].
     *
     * @param histogram The input histogram.
     * @return The cumulative frequency array.
     */
    public static int[] cumulativeSumFor(int[] histogram) {
        int[] histogramCumulative = new int[LENGTH_HISTOGRAM_IMAGE];

        histogramCumulative[0] = histogram[0];
        for (int i = 1; i < LENGTH_HISTOGRAM_IMAGE; i++) {
            histogramCumulative[i] = histogramCumulative[i - 1] + histogram[i];
        }

        return histogramCumulative;
    }

    /**
     * Returns the total number of pixels in the given image.
     *
     * @param luminance A matrix of the luminance within an image.
     * @return The total number of pixels in that image.
     */
    public static int totalPixelsIn(int[][] luminance) {
		return luminance.length * luminance[0].length;
    }

    /**
     * Applies the histogram equalization algorithm to the given image, represented by a matrix
     * of its luminance.
     * <p/>
     * You are strongly encouraged to use the three methods you have implemented above in order to
     * implement this method.
     *
     * @param luminance The luminance of the input image.
     * @return The luminance of the image formed by applying histogram equalization.
     */
    public static int[][] equalize(int[][] luminance) {
        int[][] histogramAlignment = new int[luminance.length][luminance[0].length];

        int[] cumulativeHistogram = cumulativeSumFor(histogramFor(luminance));
        int totalPixels = totalPixelsIn(luminance);

        for (int i = 0; i < LENGTH_HISTOGRAM_IMAGE; i++) {

            for (int j = 0; j < luminance.length; j++) {
                for (int k = 0; k < luminance[0].length; k++) {
                    if (luminance[j][k] == i) {
                        histogramAlignment[j][k] = Math.toIntExact(Math.round((double) MAX_LUMINANCE * cumulativeHistogram[i] / totalPixels));
                    }
                }
            }

        }

        return histogramAlignment;
    }
}
