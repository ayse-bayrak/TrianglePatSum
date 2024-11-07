package TrianglePatSum;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrianglePathSum {

    // Method to check if a number is non-prime
    public static boolean isNotPrime(int num) {
        if (num <= 1) return true; // 1 and negative numbers are non-prime
        if (num == 2) return false; // 2 is a prime number
        if (num % 2 == 0) return true; // Even numbers are non-prime
        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) return true; // Returns true if not prime
        }
        return false; // Returns false if prime
    }

    // Method to find the maximum path sum
    public static int maxPathSum(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];

        // Initialize dp with non-prime numbers from the last row
        for (int i = 0; i < n; i++) {
            dp[n - 1][i] = isNotPrime(triangle.get(n - 1).get(i)) ? triangle.get(n - 1).get(i) : 0;
        }

        // Process each row from bottom to top using dynamic programming
        for (int row = n - 2; row >= 0; row--) {
            for (int col = 0; col <= row; col++) {
                if (isNotPrime(triangle.get(row).get(col))) {
                    dp[row][col] = triangle.get(row).get(col) 
                        + Math.max(dp[row + 1][col], dp[row + 1][col + 1]);
                } else {
                    dp[row][col] = 0; // Path is set to zero if it's a prime number
                }
            }
        }
        return dp[0][0]; // Return the maximum sum at the top of the triangle
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        String fileName = "/Users/HP/Desktop/ScaleAI/TrianglePatSum/src/main/java/TrianglePatSum/triangle.txt"; // Ensure the correct file path

        // Read the triangle from the file
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.trim().split("\\s+");
                List<Integer> row = new ArrayList<>();
                for (String value : values) {
                    row.add(Integer.parseInt(value));
                }
                triangle.add(row);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        // Check if the triangle data is empty
        if (triangle.isEmpty()) {
            System.out.println("Triangle data is empty. Please check the input file.");
            return;
        }

        // Print the triangle data to the console
        System.out.println("Triangle data:");
        for (List<Integer> row : triangle) {
            System.out.println(row);
        }

        // Calculate and print the maximum path sum
        System.out.println("Maximum path sum: " + maxPathSum(triangle));
    }
}
