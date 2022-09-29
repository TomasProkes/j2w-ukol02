package cz.czechitas.java2webapps.ukol2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Controller
public class QuoteController {
    private static final Logger LOG = LoggerFactory.getLogger(QuoteController.class);
    private static final Random random = new Random();

    private static List<String> readAllLines(String resource) {
        // Get the current classloader to provide input stream for reading the resource file
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        // Get and convert the input stream to buffered reader to fetch uft8-encoded text
        try (InputStream inputStream = classLoader.getResourceAsStream(resource)) {
            assert inputStream != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

                // Convert Stream<String> to List<String>
                return reader.lines().toList();
            }
        } catch (IOException e) {
            LOG.error("Failed to read the resource file. Cause: {}", Arrays.toString(e.getStackTrace()));
            return Collections.emptyList();
        }
    }

    private List<String> quotes = List.of();

    public QuoteController() {
        String filePath = "citaty.txt";
        quotes = readAllLines(filePath);
    }


    @GetMapping("/")
    public ModelAndView selectQuote() {
        ModelAndView result = new ModelAndView("quote");
        int randNum = random.nextInt(quotes.size());

        String quote = quotes.get(randNum);

        String image = "https://source.unsplash.com/user/tomastuma/400x300";
        String bodyStyle = "background-image: url(" + image + ");";

        result.addObject("quote", quote);
        result.addObject("bodyStyle", bodyStyle);
        return result;
    }
}
