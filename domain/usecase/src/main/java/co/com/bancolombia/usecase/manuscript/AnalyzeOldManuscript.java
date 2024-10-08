package co.com.bancolombia.usecase.manuscript;

import lombok.experimental.UtilityClass;

import java.util.logging.Logger;

@UtilityClass
public class AnalyzeOldManuscript {

    private static final Logger logger = Logger.getLogger(AnalyzeOldManuscript.class. getName());

    public boolean containsArtifactClue(String[] manuscript) {
        int rows = manuscript.length;
        int cols = manuscript[0].length();
        int wordLength = 4;
        boolean containsClue = false;

        int[][] directions = {
                {0, 1},   // Derecha
                {1, 0},   // Abajo
                {1, 1},   // Diagonal hacia abajo a la derecha
                {1, -1}   // Diagonal hacia abajo a la izquierda
        };

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int[] direction : directions) {
                    String word = checkDirection(manuscript, i, j, direction, wordLength);
                    if (word != null) {
                        if (analyzeFoundWord(word)){
                            containsClue = true;
                            logger.info("Found Word: " + word);
                        }

                    }
                }
            }
        }
        return containsClue;
    }

    private static boolean analyzeFoundWord(String word) {
        char firstChar = word.charAt(0);
        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i) != firstChar) {
                return false;
            }
        }
        return true;
    }

    private static String checkDirection(String[] manuscript, int row, int col, int[] direction, int length) {
        StringBuilder sb = new StringBuilder();

        int dRow = direction[0];
        int dCol = direction[1];
        int rows = manuscript.length;
        int cols = manuscript[0].length();

        for (int i = 0; i < length; i++) {
            int newRow = row + i * dRow;
            int newCol = col + i * dCol;

            if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) {
                return null;
            }
            sb.append(manuscript[newRow].charAt(newCol));
        }
        return sb.toString();
    }

}
