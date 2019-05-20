import java.util.Scanner;

public class CityStorage {

    static class Queue {
        String[] names;
        int frontItem;
        int lastItem;
        private int size = 0;

        private static final int MAX_SIZE = 5;

        Queue() {
            names = new String[MAX_SIZE];
            frontItem = 0;
            lastItem = 0;
        }

        boolean insert(String name) {
            if (isFull()) {
                return false;
            }
            names[lastItem] = name;
            lastItem = (lastItem + 1) % (names.length);
            size++;
            return true;
        }

        String get() {
            if (isEmpty()) {
                return null;
            }
            size--;
            String name = names[frontItem];
            frontItem = (frontItem + 1) % (names.length);
            return name;
        }

        boolean isEmpty() {
            return size == 0;
        }

        boolean isFull() {
            return MAX_SIZE == size;
        }
    }

    public static void main(String[] args) {

        String answer;
        Scanner scanner = new Scanner(System.in);
        Queue cityQueue = new Queue();

        do {

            System.out.println("Додати нове місто? y/n");
            answer = scanner.nextLine();
            if (!answer.toLowerCase().equals("y"))
                break;
            System.out.println("Введіть назву міста:");
            String cityName = scanner.nextLine();
            if (!cityQueue.insert(cityName))
                System.out.println("Черга повна!");

        } while (true);

        String cityName = cityQueue.get();
        System.out.println("Перелік введених міст:");

        for (int i = 0; cityName != null; ++i, cityName = cityQueue.get())
            System.out.println(i + ":\t" + cityName);
    }

}
