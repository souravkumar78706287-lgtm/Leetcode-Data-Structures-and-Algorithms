import java.util.*;

// Class representing a Library
class Library {
    int id;
    int books;
    int signup;
    int booksPerDay;
    ArrayList<Integer> bookIDs;

    Library(int id, int books, int signup, int booksPerDay, ArrayList<Integer> bookIDs) {
        this.id = id;
        this.books = books;
        this.signup = signup;
        this.booksPerDay = booksPerDay;
        this.bookIDs = bookIDs;
    }
}

// Comparator for sorting libraries by signup time (greedy heuristic)
class SortBySignup implements Comparator<Library> {
    public int compare(Library a, Library b) {
        return a.signup - b.signup;
    }
}

public class hc {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int totalBooks = sc.nextInt();
        int numOfLib = sc.nextInt();
        int totalDays = sc.nextInt();

        int[] bookScore = new int[totalBooks];

        for (int i = 0; i < totalBooks; i++) {
            bookScore[i] = sc.nextInt();
        }

        // Store libraries
        HashMap<Integer, Library> map = new HashMap<>();

        for (int i = 0; i < numOfLib; i++) {

            int books = sc.nextInt();
            int signup = sc.nextInt();
            int bpd = sc.nextInt();

            ArrayList<Integer> bid = new ArrayList<>();

            for (int j = 0; j < books; j++) {
                bid.add(sc.nextInt());
            }

            map.put(i, new Library(i, books, signup, bpd, bid));
        }

        // Convert to list for sorting
        ArrayList<Library> libs = new ArrayList<>(map.values());
        libs.sort(new SortBySignup());

        // Result storage
        ArrayList<Integer> selectedLibs = new ArrayList<>();

        int remainingDays = totalDays;

        for (Library lib : libs) {

            if (remainingDays <= lib.signup) break;

            remainingDays -= lib.signup;

            int shipCapacity = remainingDays * lib.booksPerDay;

            if (shipCapacity > 0) {
                selectedLibs.add(lib.id);
            }
        }

        // Output
        System.out.println(selectedLibs.size());

        for (int id : selectedLibs) {

            Library lib = map.get(id);

            int remainingDaysAfterSignup = totalDays - lib.signup;
            int shipCapacity = remainingDaysAfterSignup * lib.booksPerDay;

            shipCapacity = Math.min(shipCapacity, lib.bookIDs.size());

            System.out.println(id + " " + shipCapacity);

            for (int k = 0; k < shipCapacity; k++) {
                System.out.print(lib.bookIDs.get(k) + " ");
            }
            System.out.println();
        }

        sc.close();
    }
}
