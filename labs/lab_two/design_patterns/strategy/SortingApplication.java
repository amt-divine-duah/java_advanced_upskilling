package labs.lab_two.design_patterns.strategy;

import java.util.Arrays;
import java.util.Scanner;

interface SortStrategy {
    void sort(int[] data);
}

class BubbleSortStrategy implements SortStrategy {

    @Override
    public void sort(int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 0; j < data.length - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    swap(data, j, j + 1);
                }
            }
        }
    }

    private void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}

class SelectionSortStrategy implements SortStrategy {

    @Override
    public void sort(int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[j] < data[minIndex]) {
                    minIndex = j;
                }
            }
            swap(data, i, minIndex);
        }
    }

    private void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}

class Sorter {

    private SortStrategy sortStrategy;

    public void setSortStrategy(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    public void sortData(int[] data) {
        if (sortStrategy != null) {
            sortStrategy.sort(data);
        } else {
            System.out.println("No sorting strategy set.");
        }
    }
}


public class SortingApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Sorter sorter = new Sorter();

        System.out.println("Enter the elements of the array separated by spaces:");
        String[] input = scanner.nextLine().split(" ");
        int[] data = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            data[i] = Integer.parseInt(input[i]);
        }

        while (true) {
            System.out.println("Choose a sorting algorithm:");
            System.out.println("1. Bubble Sort");
            System.out.println("2. Selection Sort");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    sorter.setSortStrategy(new BubbleSortStrategy());
                    break;
                case 2:
                    sorter.setSortStrategy(new SelectionSortStrategy());
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    continue;
            }

            sorter.sortData(data);
            System.out.println("Sorted Array: " + Arrays.toString(data));
        }
    }
}
