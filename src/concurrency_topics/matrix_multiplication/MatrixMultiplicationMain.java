package concurrency_topics.matrix_multiplication;

import java.util.ArrayList;
import java.util.List;

public class MatrixMultiplicationMain {
    public static void main(String[] args) {

        List<List<Integer>> matrixA = new ArrayList<>();
        List<List<Integer>> matrixB = new ArrayList<>();
        int size = 4;

        //create matrixA elements

        // add rows
        matrixA.add(new ArrayList<>());
        matrixA.add(new ArrayList<>());
        matrixA.add(new ArrayList<>());
        matrixA.add(new ArrayList<>());

        // add elements to the rows (columns)
        matrixA.get(0).add(33);
        matrixA.get(0).add(34);
        matrixA.get(0).add(35);
        matrixA.get(0).add(36);

        matrixA.get(1).add(37);
        matrixA.get(1).add(38);
        matrixA.get(1).add(39);
        matrixA.get(1).add(40);

        matrixA.get(2).add(41);
        matrixA.get(2).add(42);
        matrixA.get(2).add(43);
        matrixA.get(2).add(44);

        matrixA.get(3).add(45);
        matrixA.get(3).add(46);
        matrixA.get(3).add(47);
        matrixA.get(3).add(48);


        // create matrixB elements

        // add rows
        matrixB.add(new ArrayList<>());
        matrixB.add(new ArrayList<>());
        matrixB.add(new ArrayList<>());
        matrixB.add(new ArrayList<>());

        // add elements to the rows (columns)
        matrixB.get(0).add(17);
        matrixB.get(0).add(18);
        matrixB.get(0).add(19);
        matrixB.get(0).add(20);

        matrixB.get(1).add(21);
        matrixB.get(1).add(22);
        matrixB.get(1).add(23);
        matrixB.get(1).add(24);

        matrixB.get(2).add(25);
        matrixB.get(2).add(26);
        matrixB.get(2).add(27);
        matrixB.get(2).add(28);

        matrixB.get(3).add(29);
        matrixB.get(3).add(30);
        matrixB.get(3).add(31);
        matrixB.get(3).add(32);


        MatrixMultiplication matrixMultiplication = new MatrixMultiplication(matrixA, matrixB, size);

        List<List<Integer>> results = matrixMultiplication.invoke();

        System.out.println("I have result matrix: " + results);
    }
}
