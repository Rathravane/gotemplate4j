package com.github.verils.gotemplate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class GoTemplateTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void test() {
        String letter = "\n" +
                "Dear {{.Name}},\n" +
                "{{if .Attended}}\n" +
                "It was a pleasure to see you at the wedding.\n" +
                "{{- else}}\n" +
                "It is a shame you couldn't make it to the wedding.\n" +
                "{{- end}}\n" +
                "{{with .Gift -}}\n" +
                "Thank you for the lovely {{.}}.\n" +
                "{{end}}\n" +
                "Best wishes,\n" +
                "Josie\n";

        class Recipient {
            private final String name;
            private final String gift;
            private final boolean attended;

            public Recipient(String name, String gift, boolean attended) {
                this.name = name;
                this.gift = gift;
                this.attended = attended;
            }

            public String getName() {
                return name;
            }

            public String getGift() {
                return gift;
            }

            public boolean isAttended() {
                return attended;
            }
        }

        Recipient[] recipients = new Recipient[]{
                new Recipient("Aunt Mildred", "bone china tea set", true),
                new Recipient("Uncle John", "moleskin pants", false),
                new Recipient("Cousin Rodney", "", false)
        };

        GoTemplate goTemplate = new GoTemplate(letter);

        String text1 = goTemplate.execute(recipients[0]);
        assertNotNull(text1);
        assertFalse(text1.contains("{{.Name}}"));
        assertEquals("\n" +
                "Dear Aunt Mildred,\n" +
                "\n" +
                "It was a pleasure to see you at the wedding.\n" +
                "Thank you for the lovely bone china tea set.\n" +
                "\n" +
                "Best wishes,\n" +
                "Josie\n", text1);

        String text2 = goTemplate.execute(recipients[1]);
        assertNotNull(text2);
        assertFalse(text2.contains("{{.Name}}"));
        assertEquals("\n" +
                "Dear Uncle John,\n" +
                "\n" +
                "It is a shame you couldn't make it to the wedding.\n" +
                "Thank you for the lovely moleskin pants.\n" +
                "\n" +
                "Best wishes,\n" +
                "Josie\n", text2);

        String text3 = goTemplate.execute(recipients[2]);
        assertNotNull(text3);
        assertFalse(text3.contains("{{.Name}}"));
        assertEquals("\n" +
                "Dear Cousin Rodney,\n" +
                "\n" +
                "It is a shame you couldn't make it to the wedding.\n" +
                "\n" +
                "Best wishes,\n" +
                "Josie\n", text3);
    }

    private String readTemplateInClassPath(String path) throws FileNotFoundException {
        InputStream in = GoTemplateTest.class.getResourceAsStream(path);
        if (in == null) {
            throw new FileNotFoundException();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        return reader.lines().collect(Collectors.joining("\n"));
    }


}