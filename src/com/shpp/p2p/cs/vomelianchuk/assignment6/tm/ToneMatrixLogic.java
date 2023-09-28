package com.shpp.p2p.cs.vomelianchuk.assignment6.tm;

public class ToneMatrixLogic {
    /**
     * Given the contents of the tone matrix, returns a string of notes that should be played
     * to represent that matrix.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column     The column number that is currently being played.
     * @param samples    The sound samples associated with each row.
     * @return A sound sample corresponding to all notes currently being played.
     */
    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {
        double[] result = new double[ToneMatrixConstants.sampleSize()];
        int numberEnabledCells = 0;

        for (int row = 0; row < toneMatrix.length; row++) {
            if (toneMatrix[row][column]) {
                for (int i = 0; i < samples[row].length; i++) {
                    result[i] += samples[row][i];
                }
                numberEnabledCells++;
            }
        }

        if (numberEnabledCells == 0) return result;

        double maxValue = getMaxValue(result);

        return normalizeSoundWave(result, maxValue);
    }

    /**
     * Calculates what is the maximum amplitude in a sound wave
     *
     * @param result A new sound wave is created
     * @return The maximum amplitude in a sound wave
     */
    private static double getMaxValue(double[] result) {
        double maxValue = 0;
        for (double sample : result) {
            maxValue = Math.max(maxValue, Math.abs(sample));
        }
        return maxValue;
    }

    /**
     * Converts a sound wave into a normalized
     * one that falls within [ -1; 1]
     *
     * @param result  A new sound wave is created
     * @param maxValue The maximum amplitude in a sound wave
     * @return A normalized sound wave
     */
    private static double[] normalizeSoundWave(double[] result, double maxValue) {
        if (maxValue > 1.0) {
            for (int i = 0; i < result.length; i++) {
                result[i] /= maxValue;
            }
        }
        return result;
    }
}
