package com.lsd.lambda.question;

import com.lsd.lambda.common.Artist;
import com.lsd.lambda.common.Utils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author nhsoft.lsd
 */
public class ArtistTest {

    public static void main(String[] args) {

        List<Artist> artistList = Utils.getArtists();

        List<String> result = artistList.stream().map(artist -> artist.getName() + "|" + artist.getCountry()).collect(Collectors.toList());

        System.out.println(result);
    }
}
