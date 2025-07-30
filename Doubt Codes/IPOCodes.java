import java.util.*;
public class IPOCodes{
 
}


class Solution {
    class Project {
        int profit, capital;
        Project(int p, int c) {
            this.profit = p;
            this.capital = c;
        }
    }

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;

        // Min-heap: sort by capital (ascending)
        PriorityQueue<Project> capitalHeap = new PriorityQueue<>((a, b) -> a.capital - b.capital);

        // Max-heap: sort by profit (descending)
        PriorityQueue<Project> profitHeap = new PriorityQueue<>((a, b) -> b.profit - a.profit);

        // Add all projects to capitalHeap
        for (int i = 0; i < n; i++) {
            capitalHeap.offer(new Project(profits[i], capital[i]));
        }

        // Try to do up to k projects
        for (int i = 0; i < k; i++) {
            // Move all projects that can be done with current capital to profitHeap
            while (!capitalHeap.isEmpty() && capitalHeap.peek().capital <= w) {
                profitHeap.offer(capitalHeap.poll());
            }

            // If no affordable projects, stop
            if (profitHeap.isEmpty()) break;

            // Pick project with max profit
            w += profitHeap.poll().profit;
        }

        return w;
    }
}

// This is a double Heap question (Leetcode)
// Simple approach -> DONT THINK MUCH
// Take two separate heaps, one for capital (MINHEAP) and one for profit (MAXHEAP)
// fill capital heap and then run a loop for k times
// anyone will think optimal solution is doing for k times BUT NO.
// for each iteration you pop for capital heap till the time capital is less than my current wealth and push in profit heap
// if at any stage profit heap is empty it means we are done and break
// else pick ONE project with max project 