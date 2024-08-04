import java.io.*;
import java.util.Scanner;

class Node {
    String song;
    Node next;
    Node prev;

    Node(String song) {
        this.song = song;
        this.next = null;
        this.prev = null;
    }
}

public class PlaylistManager {
    static Node top, temp, top1;

    public static void toFile(String song) {
        try {
            FileWriter fw = new FileWriter("playlist.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            out.println(song);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addNode(Node first) {
        Scanner scanner = new Scanner(System.in);
        String song;
        while (first.next != null) {
            first = first.next;
        }
        first.next = new Node("");
        first.prev = first;
        first = first.next;
        System.out.print("\nEnter Song name: ");
        song = scanner.nextLine();
        first.song = song;
        toFile(song);
        first.next = null;
    }
    
    
    public static void deleteFile(String song) {
        try {
            File inputFile = new File("playlist.txt");
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = song;
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                if (!currentLine.equals(lineToRemove)) {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
            }
            writer.close();
            reader.close();
            inputFile.delete();
            tempFile.renameTo(inputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteNode(Node first) {
        while (first.next.next != null) {
            first = first.next;
        }
        Node tempNode = first.next.next;
        first.next = null;
        System.out.println("Deleted");
        tempNode = null;
    }

    public static void printList(Node first) {
        System.out.print("\nPlaylist Name: ");
        while (first.next != null) {
            System.out.println(first.song);
            first = first.next;
        }
        System.out.println(first.song);
    }

    public static void countNodes(Node first) {
        int i = 0;
        while (first.next != null) {
            first = first.next;
            i++;
        }
        i++;
        System.out.println("\nTotal songs: " + (i - 1));
    }

    public static Node deletePosition(Node pointer, int pos) {
        Node n1, prev1 = null, temp;
        int i = 0;
    
        if (pos == 1) {
            temp = pointer;
            deleteFile(temp.song);
            pointer = pointer.next;
            if (pointer != null) {
                pointer.prev = null;
            }
            temp = null;
            System.out.println("\nThe list is updated.\nUse the display function to check.");
            return pointer;
        }
    
        while (i < pos - 1) {
            prev1 = pointer;
            pointer = pointer.next;
            i++;
        }
    
        if (pointer.next == null) {
            temp = pointer;
            deleteFile(temp.song);
            if (prev1 != null) {
                prev1.next.prev = null;
            }
            if (prev1 != null) {
                prev1.next = null;
            }
            temp = null;
            System.out.println("\nThe list is updated.\nUse the display function to check.");
        } else {
            temp = pointer;
            deleteFile(temp.song);
            if (prev1 != null) {
                prev1.next = temp.next;
            }
            if (temp.next != null) {
                temp.next.prev = prev1;
            }
            temp = null;
            System.out.println("\nThe list is updated.\nUse the display function to check.");
        }
    
        return pointer;
    }
    

    public static void search(Node first) {
        Scanner scanner = new Scanner(System.in);
        String song;
        System.out.print("\nEnter song To be Searched: ");
        song = scanner.nextLine();
        int flag = 0;

        while (first != null) {
            if (first.song.equals(song)) {
                System.out.println("\n#Song Found");
                flag++;
                break;
            } else {
                first = first.next;
            }
        }

        if (flag == 0) {
            System.out.println("\n#Song Not found");
        }
    }

    public static void create() {
        top = null;
    }

    public static void push(String data) {
        if (top == null) {
            top = new Node(data);
        } else if (!top.song.equals(data)) {
            temp = new Node(data);
            temp.next = top;
            top = temp;
        }
    }

    public static void display() {
        top1 = top;
        if (top1 == null) {
            System.out.println("\n=>NO recently played tracks.");
            return;
        }
        System.out.println("\n#Recently played tracks-");
        while (top1 != null) {
            System.out.println(top1.song);
            top1 = top1.next;
        }
    }

    public static void play(Node first) {
        Scanner scanner = new Scanner(System.in);
        String song;
        printList(first);
        System.out.print("\nChoose song you wish to play: ");
        song = scanner.nextLine();
        int flag = 0;

        while (first != null) {
            if (first.song.equals(song)) {
                System.out.println("\n=>Now Playing......" + song);
                flag++;
                push(song);
                break;
            } else {
                first = first.next;
            }
        }

        if (flag == 0) {
            System.out.println("\n#Song Not found");
        }
    }

    public static void recent() {
        display();
    }

    public static void topElement() {
        top1 = top;
        if (top1 == null) {
            System.out.println("\n#NO last played tracks.");
            return;
        }
        System.out.println("\n=>Last Played Song - " + top.song);
    }

    public static void sort(Node pointer) {
        Node a = null;
        Node b = null;
        Node c = null;
        Node e = null;
        Node tmp = null;
        while (e != pointer.next) {
            c = a = pointer;
            b = a.next;
            while (a != e) {
                if (a.song.compareTo(b.song) > 0) {
                    if (a == pointer) {
                        tmp = b.next;
                        b.next = a;
                        a.next = tmp;
                        pointer = b;
                        c = b;
                    } else {
                        tmp = b.next;
                        b.next = a;
                        a.next = tmp;
                        c.next = b;
                        c = b;
                    }
                } else {
                    c = a;
                    a = a.next;
                }
                b = a.next;
                if (b == e) {
                    e = a;
                }
            }
        }
    }

    public static void addPlaylist(Node start) {
        try {
            File file = new File("playlist.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                addNode(start);  // Corrected method usage
            }
            System.out.println("Playlist Added");
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    

    public static void delSearch(Node start) {
        Scanner scanner = new Scanner(System.in);
        String song;
        printList(start);
        System.out.print("\nChoose song you wish to delete: ");
        song = scanner.nextLine();
        int flag = 0;
        while (start != null) {
            if (start.song.equals(song)) {
                System.out.println("\n#Song Found");
                Node tempNode = start;
                deleteFile(tempNode.song);
                tempNode.prev.next = tempNode.next;
                tempNode.next.prev = tempNode.prev;
                tempNode = null;
                flag++;
                break;
            } else {
                start = start.next;
            }
        }

        if (flag == 0) {
            System.out.println("\n#Song Not found");
        }
    }

    public static void deleteMenu(Node start) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("Which type of delete do you want?");
        System.out.println("1. By Search");
        System.out.println("2. By Position");
        choice = scanner.nextInt();
        switch (choice) {
            case 1:
                delSearch(start);
                break;
            case 2:
                int pos;
                System.out.print("\nEnter the pos of the song: ");
                pos = scanner.nextInt();
                deletePosition(start, pos - 1);
                break;
        }
    }

    public static void main(String[] args) {
        int choice;
        Node start, hold;
        start = new Node("");
        System.out.println("\t\t\t**WELCOME**");
        System.out.println("**please use '_' for space.");
        System.out.print("\nEnter your playlist name: ");
        Scanner scanner = new Scanner(System.in);
        start.song = scanner.nextLine();
        start.next = null;
        hold = start;
        create();

        do {
            System.out.println("\n1. Add New Song");
            System.out.println("2. Delete Song");
            System.out.println("3. Display Entered Playlist");
            System.out.println("4. Total Songs");
            System.out.println("5. Search Song");
            System.out.println("6. Play Song");
            System.out.println("7. Recently Played List");
            System.out.println("8. Last Played");
            System.out.println("9. Sorted playlist");
            System.out.println("10. Add From File");
            System.out.println("11. Exit");
            System.out.print("\nEnter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addNode(start);
                    break;
                case 2:
                    deleteMenu(start);
                    break;
                case 3:
                    printList(start);
                    break;
                case 4:
                    countNodes(hold);
                    break;
                case 5:
                    search(start);
                    break;
                case 6:
                    play(start);
                    break;
                case 7:
                    recent();
                    break;
                case 8:
                    topElement();
                    break;
                case 9:
                    sort(start.next);
                    printList(start);
                    break;
                case 10:
                    addPlaylist(start);
                    break;
                case 11:
                    System.exit(0);
            }
        } while (choice != 11);
    }
}

