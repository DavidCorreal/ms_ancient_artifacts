package co.com.bancolombia.usecase.manuscript;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnalyzeOldManuscriptTest {

    @Test
    void shouldAnalyzeOldManuscriptByDefault() {
        String[] manuscript = {
                "RTHGQW",
                "XRLORE",
                "NARURR",
                "REVRAL",
                "EGSILE",
                "BRINDS"
        };
        var actual = AnalyzeOldManuscript.containsArtifactClue(manuscript);

        Assertions.assertTrue(actual);
    }

    @Test
    void shouldAnalyzeOldManuscriptTwoWords() {
        String[] manuscript = {
                "RTPPPP",
                "XRLORE",
                "NARURR",
                "REVRAL",
                "EGSILE",
                "BRINDS"
        };
        var actual = AnalyzeOldManuscript.containsArtifactClue(manuscript);

        Assertions.assertTrue(actual);
    }

    @Test
    void shouldAnalyzeOldManuscriptWordsNotFound() {
        String[] manuscript = {
                "KTPPNP",
                "XMLORE",
                "NARURR",
                "REVRAL",
                "EGSILE",
                "BRINDS"
        };
        var actual = AnalyzeOldManuscript.containsArtifactClue(manuscript);

        Assertions.assertFalse(actual);
    }

    @Test
    void shouldAnalyzeOldManuscriptWordsMoreChars() {
        String[] manuscript = {
                "KTPPNPTUQDSOE",
                "XMLOREROTPEDO",
                "XARURRAYTERRC",
                "XEVRALADSFRGT",
                "XGSILEAQDFERG",
                "BRINDSAQDFRFV",
                "BRINDSAEPPQQX"
        };
        var actual = AnalyzeOldManuscript.containsArtifactClue(manuscript);

        Assertions.assertTrue(actual);
    }

    @Test
    void shouldAnalyzeOldManuscriptWordsMoreCharsAndFields() {
        String[] manuscript = {
                "KTPPNPTUQDSOE",
                "XMLOREROTPEDO",
                "XARURRAYTERRC",
                "XEVRALADSFRGT",
                "XGSILEAQDFERG",
                "BRINDSAQDFRFV",
                "BRINDSAEPPQQI",
                "XGSILEAQDFERI",
                "BRINDSAQDFRFI",
                "BRIBBBBYPPQQI"
        };
        var actual = AnalyzeOldManuscript.containsArtifactClue(manuscript);

        Assertions.assertTrue(actual);
    }

    @Test
    void shouldAnalyzeOldManuscriptWordsMinusCharsAndFields() {
        String[] manuscript = {
                "KTPE",
                "XMLO",
                "SSSS"
        };
        var actual = AnalyzeOldManuscript.containsArtifactClue(manuscript);

        Assertions.assertTrue(actual);
    }

    @Test
    void shouldAnalyzeOldManuscriptWordsMinusCharsAndFieldsNotFound() {
        String[] manuscript = {
                "KTPE",
                "XMLO",
                "SFSS"
        };
        var actual = AnalyzeOldManuscript.containsArtifactClue(manuscript);

        Assertions.assertFalse(actual);
    }
}
