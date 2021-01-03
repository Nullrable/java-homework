package com.lsd.lambda.Stream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @Author: nhsoft.lsd
 */
public class FilesTest {

    public static void main(String args[]) {
        long uniqueWords = 0;
        try(Stream<String> lines =
                    Files.lines(Paths.get(System.getProperty("user.dir"), "/module-work06-lambda/src/main/resources/data.txt"), Charset.defaultCharset())){
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
            System.out.println(uniqueWords);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
