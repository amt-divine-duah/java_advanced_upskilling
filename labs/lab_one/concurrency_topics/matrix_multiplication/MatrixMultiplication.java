package labs.lab_one.concurrency_topics.matrix_multiplication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class MatrixMultiplication extends RecursiveTask<List<List<Integer>>> {
    private final List<List<Integer>> matrixA;
    private final List<List<Integer>> matrixB;
    private final int size;

    public MatrixMultiplication(List<List<Integer>> matrixA, List<List<Integer>> matrixB, int size) {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.size = size;
    }

    public static List<List<Integer>> resultMatrixForMultiplication(List<List<Integer>> matrixA, List<List<Integer>> matrixB) {
        return getResultList(matrixA, matrixB.getFirst());
    }

    public static List<List<Integer>> resultMatrixForAddition(List<List<Integer>> matrixA) {
        return getResultList(matrixA, matrixA.getFirst());
    }

    private static List<List<Integer>> getResultList(List<List<Integer>> matrixA, List<Integer> integers) {
        List<List<Integer>> resultMatrix = new ArrayList<>();
        for (int i = 0; i < matrixA.size() ; i++) {
            List<Integer> tempArray = new ArrayList<>();
            for (int j = 0; j < integers.size() ; j++) {
                tempArray.add(0);
            }
            resultMatrix.add(tempArray);
        }
        return resultMatrix;
    }

    public static List<List<Integer>> addTwoMatrices(List<List<Integer>> matrixA, List<List<Integer>> matrixB) {
        List<List<Integer>> resultMatrixForAddition = resultMatrixForAddition(matrixA);

        for (int i = 0; i < matrixA.size() ; i++) {
            for (int j = 0; j < matrixB.size() ; j++) {
                resultMatrixForAddition.get(i).set(j, matrixA.get(i).get(j) + matrixB.get(i).get(j));
            }
        }
        return resultMatrixForAddition;
    }

    public static List<List<Integer>> combineSubMatrices(List<List<Integer>> C11, List<List<Integer>> C12, List<List<Integer>> C21, List<List<Integer>> C22, List<List<Integer>> resultingMatrix) {
        List<List<Integer>> finalResults = resultingMatrix;

        int size = C11.size();
        for (int i = 0; i < size ; i++) {
            for (int j = 0; j < size ; j++) {
                // top left quadrant
                finalResults.get(i).set(j, C11.get(i).get(j));
                // top right quadrant
                finalResults.get(i).set(j + size, C12.get(i).get(j));
                // bottom left quadrant
                finalResults.get(i + size).set(j, C21.get(i).get(j));
                // bottom right quadrant
                finalResults.get(i + size).set(j + size, C22.get(i).get(j));
            }
        }
        return finalResults;
    }

    @Override
    protected List<List<Integer>> compute() {
        List<List<Integer>> resultingMatrix = resultMatrixForMultiplication(matrixA, matrixB);
        // Base case when dimension is a 2 x 2 matrix
        if (size == 2) {
            resultingMatrix.get(0).set(0, matrixA.get(0).get(0) * matrixB.get(0).get(0) + matrixA.get(0).get(1) * matrixB.get(1).get(0));
            resultingMatrix.get(0).set(1, matrixA.get(0).get(0) * matrixB.get(0).get(1) + matrixA.get(0).get(1) * matrixB.get(1).get(1));
            resultingMatrix.get(1).set(0, matrixA.get(1).get(0) * matrixB.get(0).get(0) + matrixA.get(1).get(1) * matrixB.get(1).get(0));
            resultingMatrix.get(1).set(1, matrixA.get(1).get(0) * matrixB.get(0).get(1) + matrixA.get(1).get(1) * matrixB.get(1).get(1));

            return resultingMatrix;
        }

        int mid = size / 2;

        List<List<Integer>> A11 = new ArrayList<>();
        List<List<Integer>> A12 = new ArrayList<>();
        List<List<Integer>> A21 = new ArrayList<>();
        List<List<Integer>> A22 = new ArrayList<>();


        matrixA.subList(0, mid).forEach(row -> A11.add(row.subList(0, mid)));
        matrixA.subList(0, mid).forEach(row -> A12.add(row.subList(mid, size)));
        matrixA.subList(mid, size).forEach(row -> A21.add(row.subList(0, mid)));
        matrixA.subList(mid, size).forEach(row -> A22.add(row.subList(mid, size)));

        List<List<Integer>> B11 = new ArrayList<>();
        List<List<Integer>> B12 = new ArrayList<>();
        List<List<Integer>> B21 = new ArrayList<>();
        List<List<Integer>> B22 = new ArrayList<>();


        matrixB.subList(0, mid).forEach(row -> B11.add(row.subList(0, mid)));
        matrixB.subList(0, mid).forEach(row -> B12.add(row.subList(mid, size)));
        matrixB.subList(mid, size).forEach(row -> B21.add(row.subList(0, mid)));
        matrixB.subList(mid, size).forEach(row -> B22.add(row.subList(mid, size)));


        List<List<Integer>> C11;
        List<List<Integer>> C12;
        List<List<Integer>> C21;
        List<List<Integer>> C22;

        MatrixMultiplication C11Task1 = new MatrixMultiplication(A11, B11, mid);
        C11Task1.fork();
        MatrixMultiplication C11Task2 = new MatrixMultiplication(A12, B21, mid);
        C11Task2.fork();
        MatrixMultiplication C12Task1 = new MatrixMultiplication(A11, B12, mid);
        C12Task1.fork();
        MatrixMultiplication C12Task2 = new MatrixMultiplication(A12, B22, mid);
        C12Task2.fork();
        MatrixMultiplication C21Task1 = new MatrixMultiplication(A21, B11, mid);
        C21Task1.fork();
        MatrixMultiplication C21Task2 = new MatrixMultiplication(A22, B21, mid);
        C21Task2.fork();
        MatrixMultiplication C22Task1 = new MatrixMultiplication(A21, B12, mid);
        C22Task1.fork();
        MatrixMultiplication C22Task2 = new MatrixMultiplication(A22, B22, mid);
        C22Task2.fork();

        C11 = addTwoMatrices(C11Task1.join(), C11Task2.join());
        C12 = addTwoMatrices(C12Task1.join(), C12Task2.join());
        C21 = addTwoMatrices(C21Task1.join(), C21Task2.join());
        C22 = addTwoMatrices(C22Task1.join(), C22Task2.join());

        return combineSubMatrices(C11, C12, C21, C22, resultingMatrix);
    }
}
