
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Team {

    String name;
    int points;
    int goalDiff;

    Team(String name, int points, int goalDiff) {
        this.name = name;
        this.points = points;
        this.goalDiff = goalDiff;
    }

    public String toString() {
        return name + "(" + points + "," + goalDiff + ")";
    }
}

class Leaderboard<T> {

    private List<T> items = new ArrayList<>();
    private Comparator<? super T> order;

    Leaderboard(Comparator<? super T> order) {
        this.order = order;
    }

    void add(T item) {
        items.add(item);
    }

    // Accepts a list of T or its subclasses
    void addAll(List<? extends T> newItems) {
        items.addAll(newItems);
    }

    // Returns a sorted copy without changing original items
    List<T> ranking() {
        List<T> result = new ArrayList<>(items);
        result.sort(order);
        return result;
    }
}

public class LeaderboardDemo {

    public static void main(String[] args) {

        // Ranking: points high to low,
        // goal difference high to low,
        // name alphabetical

        Comparator<Team> byRank =
            Comparator.comparingInt((Team t) -> t.points)
            .reversed()
            .thenComparing(
                Comparator.comparingInt((Team t) -> t.goalDiff).reversed()
            )
            .thenComparing(t -> t.name);

        Leaderboard<Team> board = new Leaderboard<>(byRank);

        board.add(new Team("Panthers", 9, 4));
        board.add(new Team("Eagles", 9, 6));
        board.add(new Team("Cobras", 6, 1));
        board.add(new Team("Bisons", 9, 4));

        System.out.println("Sports Leaderboard:");
        System.out.println(board.ranking());

        // Test with another dataset
        System.out.println("\nTesting Faulty Comparator:");

        Team team1 = new Team("Team A", 10, 5);
        Team team2 = new Team("Team B", 10, 8);

        // Faulty comparator ignores goal difference
        Comparator<Team> faulty =
            (a, b) -> b.points - a.points;

        System.out.println("Faulty Comparator Result: "
                + faulty.compare(team1, team2));

        System.out.println("Correct Comparator Result: "
                + byRank.compare(team1, team2));

        System.out.println("\nCorrect Ranking:");
        
        Leaderboard<Team> testBoard = new Leaderboard<>(byRank);
        testBoard.add(team1);
        testBoard.add(team2);

        System.out.println(testBoard.ranking());
    }
}
