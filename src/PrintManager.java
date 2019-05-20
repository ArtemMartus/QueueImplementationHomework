public class PrintManager {

    static class Queue {
        private String[] names;
        private int frontItem;
        private int lastItem;
        private int size = 0;
        private int printCounter = 1;

        private static final int MAX_SIZE = 10;
        private static final int SECONDS_PER_PAGE = 10;

        Queue() {
            names = new String[MAX_SIZE];
            frontItem = 0;
            lastItem = 0;
        }

        void updateTime(){
            if (!isEmpty()  && ++printCounter > SECONDS_PER_PAGE ) {
                printCounter = 1;
                System.out.println("Закінчив друк файлу " + get());

            }
        }

        boolean insert(String name) {
            System.out.println("Додаю файл " + name + " у чергу на друк");
            if (isFull()) {
                System.out.println("Черга повна!");
                return false;
            }
            names[lastItem] = name;
            lastItem = (lastItem + 1) % (names.length);
            size++;
            return true;
        }

        String getCurrent() {
            if (isEmpty()) {
                return null;
            }
            return names[frontItem];
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

    public static void main(String[] args) throws InterruptedException {

        final String[] files = {
                "file1.txt", "file2.png", "file3.dex", "database.sqlite", "design.psd",
                "fileX.txt", "word.png", "Special.csv", "Schema.txt", "Sun.psd",
                "fileY.txt", "presentation.png", "Architecture.doc", "sheet.csv", "car.psd",
                "fileZ.txt", "document.pdf", "book.pdf", "beauty.rtf", "meme.jpg"
        };

        int printIteration = 0, currentFile = 0;


        Queue printingQueue = new Queue();
        printingQueue.insert(files[currentFile]);

        while (!printingQueue.isEmpty()) {
            printIteration++;
            System.out.println("Друкую файл " + printingQueue.getCurrent() + "\tМинуло "+printIteration+"с");
            Thread.sleep(1000);
            printingQueue.updateTime();

            if ((printIteration % 5) == 0 && currentFile < files.length) {
                if (!printingQueue.insert(files[currentFile++]))
                    currentFile--;
            }


        }

    }

}
