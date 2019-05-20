import java.util.Arrays;
import java.util.Scanner;

public class CityStorage2 {

    static class Queue {
        String[] names;
        int frontItem;
        int lastItem;
        private int size = 0;

        private int maxSize = 1;

        Queue(){
            names = new String[maxSize];
            frontItem = 0;
            lastItem =0;
        }

        void insert(String name){
            if(isFull()){
                maxSize += 1;
                names = Arrays.copyOf(names, maxSize);
            }
            names[lastItem]=name;
            lastItem++;
            size++;
        }

        String get(){
            if(isEmpty()){
                return null;
            }
            size--;
            String name = names[frontItem];
            frontItem = (frontItem +1)%(names.length);
            return name;
        }

        boolean isEmpty(){
            return size == 0;
        }

        boolean isFull(){
            return maxSize == size;
        }
    }

    public static void main(String[] args) {

        String answer;
        Scanner scanner = new Scanner(System.in);
        Queue cityQueue = new Queue();

        do {

            System.out.println("Додати нове місто? y/n");
            answer = scanner.nextLine();
            if(!answer.toLowerCase().equals("y"))
                break;
            System.out.println("Введіть назву міста:");
            String cityName = scanner.nextLine();
            cityQueue.insert(cityName);

        } while (true);

        String cityName = cityQueue.get();
        System.out.println("Перелік введених міст:");

        for (int i = 0; cityName != null; ++i, cityName = cityQueue.get())
            System.out.println(i+":\t"+cityName);
    }

}
