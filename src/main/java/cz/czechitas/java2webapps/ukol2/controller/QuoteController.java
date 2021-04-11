package cz.czechitas.java2webapps.ukol2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Controller
public class QuoteController {
    // TODO replace the following workaround with proper line reading from txt file when the filepath issue is resolved
    private static final List<String> quotes = Arrays.asList("Debugging /de·bugh·ing/ (verb): The Classic Mystery Game where you are the detective, the victim, and the murderer.",
            "A user interface is like a joke. If you have to explain it, it's not that good.",
            "To replace programmers with robots, clients will have to accurately describe what they want. We're safe.",
            "I have a joke on programming but it only works on my computer.",
            "99 little bugs in the code, 99 bugs in the code. Take one down, patch it around. 127 little bugs in the code…",
            "When I wrote this code, only God & I understood what it did. Now… Only God knows.",
            "Programmer (noun.): A machine that turns coffee into code.",
            "Real programmers count from 0.",
            "Always code as if the guy who ends up maintaining your code will be a violent psychopath who knows where you live.",
            "A misplaced decimal point will always end up where it will do the greatest damage.",
            "A good programmer looks both ways before crossing a one-way street.",
            "C is quirky, flawed, and an enormous success.",
            "Beta is Latin for still doesn’t work.",
            "Hey! It compiles! Ship it!",
            "Experience is the name everyone gives to their mistakes.",
            "If brute force doesn’t solve your problems, then you aren’t using enough.",
            "The best thing about a boolean is even if you are wrong, you are only off by a bit.",
            "The nice thing about standards is that there are so many to choose from.",
            "There is no place like 127.0.0.1",
            "To err is human, but for a real disaster you need a computer.",
            "Weeks of coding can save you hours of planning.",
            "I love deadlines. I like the whooshing sound they make as they fly by.",
            "Measuring programming progress by lines of code is like measuring aircraft building progress by weight.",
            "The problem with troubleshooting is that trouble shoots back.",
            "Walking on water and developing software from a specification are easy if both are frozen.");
    private static final List<String> images = Arrays.asList("https://source.unsplash.com/RFHFV7lVQBY/1600x900",
            "https://unsplash.com/photos/JRhVvF5VHG4",
            "https://unsplash.com/photos/onpxyxjwKm0",
            "https://unsplash.com/photos/lRwGMe1MFj4",
            "https://unsplash.com/photos/dEzd6GpIEys",
            "https://unsplash.com/photos/_8zfgT9kS2g",
            "https://unsplash.com/photos/6WuPn9JfHjI",
            "https://unsplash.com/photos/N0wr6vRpwCQ",
            "https://unsplash.com/photos/hRwDbwef88w",
            "https://unsplash.com/photos/cGMFif58sUc",
            "https://unsplash.com/photos/5VFSdzaq69c",
            "https://unsplash.com/photos/Bcj6vbINzuM",
            "https://unsplash.com/photos/Dd2OENYz8H0",
            "https://unsplash.com/photos/IKU9YqfDFc4"
            );

    private final String filePath = "citaty.txt";
    private final Random random;
    private final int quoteCount;
    private final int imageCount;

    public QuoteController() {
        random = new Random();

        // FIXME when the filepath issue is resolved
//        try {
//            quoteCount = (int) Files.lines(Paths.get(filePath), Charset.defaultCharset()).count();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        quoteCount = quotes.size();
        imageCount = images.size();
    }

    @GetMapping("/")
    public ModelAndView selectQuote() throws IOException {
        ModelAndView result = new ModelAndView("quote");
        int randNum = random.nextInt(quoteCount);

        // FIXME when the filepath issue is resolved
//        String quote = Files.readAllLines(Paths.get(filePath)).get(randNum);
        String quote = quotes.get(randNum);
        // FIXME when the image loading issue is resolved
//        String image = images.get(randNum % imageCount);
        String image = "https://source.unsplash.com/user/tomastuma/600x400";

        result.addObject("quote", quote);
        result.addObject("image", image);
        return result;
    }
}
