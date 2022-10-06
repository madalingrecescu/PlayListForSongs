// Create a program that implements a playlist for songs
// Create a Song class having Title and Duration for a song.
// The program will have an Album class containing a list of songs.
// The albums will be stored in an ArrayList
// Songs from different albums can be added to the playlist and will appear in the list in the order
// they are added.
// Once the songs have been added to the playlist, create a menu of options to:-
// Quit,Skip forward to the next song, skip backwards to a previous song.  Replay the current song.
// List the songs in the playlist
// A song must exist in an album before it can be added to the playlist (so you can only play songs that
// you own).
// Hint:  To replay a song, consider what happened when we went back and forth from a city before we
// started tracking the direction we were going.
// As an optional extra, provide an option to remove the current song from the playlist

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        ArrayList<Album> albumList = new ArrayList<Album>();
        LinkedList<Song> playList = new LinkedList<Song>();

        Album firstAlbum = new Album("Pop");
        Album secondAlbum = new Album("Rock");

        Song song1 = new Song("Starboy", 3.22);
        Song song2 = new Song("Lose you to love me", 3.11);
        Song song3 = new Song("Perfect", 2.57);
        Song song4 = new Song("Sugar", 3.54);
        Song song5 = new Song("Starboy", 3.22);
        Song song6 = new Song("I want to break free", 3.11);
        Song song7 = new Song("Zombie", 3.21);

        firstAlbum.addSongToAlbum(song1);
        firstAlbum.addSongToAlbum(song2);
        firstAlbum.addSongToAlbum(song3);
        secondAlbum.addSongToAlbum(song4);
        secondAlbum.addSongToAlbum(song5);
        secondAlbum.addSongToAlbum(song6);

        albumList.add(firstAlbum);
        albumList.add(secondAlbum);

        addInPlaylist(playList,albumList,song1);
        addInPlaylist(playList,albumList, song2);
        addInPlaylist(playList,albumList, song3);
        addInPlaylist(playList,albumList, song4);
        addInPlaylist(playList,albumList, song5);
        addInPlaylist(playList,albumList, song6);
        addInPlaylist(playList,albumList, song7);

        listenPlaylist(playList);
//        printAlbums(albumList);
    }

    private static void listenPlaylist(LinkedList<Song> songs) {
        boolean quit = false;
        boolean goingForward = true;
        ListIterator<Song> listIterator = songs.listIterator();
        if (songs.isEmpty()){
            System.out.println("No songs in the playlist");
            return;
        } else {
            System.out.println("Now playing " + listIterator.next().toString());
            printMenu();
        }
        while (!quit) {
            int action = scanner.nextInt();
            scanner.nextLine();
            switch (action) {
                case 0:
                    System.out.println("Closing playlist...");
                    quit = true;
                    break;
                case 1:
                    if (!goingForward) {
                        if (listIterator.hasNext()) {
                            listIterator.next();
                        }
                        goingForward = true;
                    }
                    if (listIterator.hasNext()) {
                        System.out.println("Listening to " + listIterator.next().toString());
                    } else {
                        System.out.println("Reached the end of the list");
                        goingForward = false;
                    }
                    break;
                case 2:
                    if (goingForward) {
                        if (listIterator.hasPrevious()) {
                            listIterator.previous();
                        }
                        goingForward = false;
                    }
                    if (listIterator.hasPrevious()) {
                        System.out.println("Listening to " + listIterator.previous().toString());
                    } else {
                        System.out.println("We are at the start of the list");
                        goingForward = true;
                    }
                    break;
                case 3:
                    if(songs.isEmpty()){
                        System.out.println("The playlist is empty");
                    }
                    if (listIterator.hasNext() && listIterator.hasPrevious()) {
                        if (goingForward) {
                            System.out.println("Listening to " + listIterator.previous().toString());
                            goingForward = false;
                        } else {
                            System.out.println("Listening to " + listIterator.next().toString());
                            goingForward = true;
                        }
                    } else if(listIterator.hasNext() && !listIterator.hasPrevious()){
                        System.out.println("Listening to " + listIterator.next().toString());
                        goingForward = true;
                    } else if(!listIterator.hasNext() && listIterator.hasPrevious()){
                        System.out.println("Listening to " + listIterator.previous().toString());
                        goingForward = false;
                    }
                    break;
                case 4:
                    System.out.println("Your playlist contains:");
                    printPlayList(songs);
                    break;
                case 5:
                    if(songs.isEmpty()){
                        System.out.println("The playlist is empty");
                    }
                    if(listIterator.hasNext() && listIterator.hasPrevious()) {
                        if (goingForward) {
                            System.out.println("You removed the song " + listIterator.previous().toString());
                            goingForward = false;
                            listIterator.remove();
                        } else {
                            System.out.println("You removed the song " + listIterator.next().toString());
                            goingForward = true;
                            listIterator.remove();
                        }
                    }else if(listIterator.hasNext() && !listIterator.hasPrevious()){
                        System.out.println("You removed the song " + listIterator.next().toString());
                        goingForward = true;
                        listIterator.remove();
                    } else if(!listIterator.hasNext() && listIterator.hasPrevious()) {
                        System.out.println("You removed the song " + listIterator.previous().toString());
                        goingForward = false;
                        listIterator.remove();
                    }
                    break;
                case 6:
                    printMenu();
                    break;

            }
        }
    }

    private static void printPlayList(LinkedList<Song> songs) {
        for (int i = 0; i < songs.size(); i++) {
            System.out.println((i + 1) + ". " + songs.get(i).toString());
        }
    }

    private static void addInPlaylist(LinkedList<Song> songLinkedList,ArrayList<Album> albumList, Song newSong) {
        for(int i=0;i<albumList.size();i++){
            if(albumList.get(i).findSong(newSong)){
                songLinkedList.add(newSong);
            }
        }

    }
    private static void printMenu() {
        System.out.println("Available actions:\n press: ");
        System.out.println("0 - to quit\n" +
                "1 - next song\n" +
                "2 - previous song\n" +
                "3 - repeat song\n" +
                "4 - print the list of songs in the playlist\n" +
                "5 - remove current song\n"+
                "6 - print menu");
    }
}