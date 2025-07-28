import java.util.*;
class Solution {

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null; // store complete word at leaf node
        boolean containsKey(char ch) {
            return children[ch - 'a'] != null;
        }
        void put(char ch, TrieNode node) {
            children[ch - 'a'] = node;
        }
        TrieNode get(char ch) {
            return children[ch - 'a'];
        }
    }

    TrieNode root = new TrieNode();
    Set<String> result = new HashSet<>();

    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (!node.containsKey(ch)) {
                node.put(ch, new TrieNode());
            }
            node = node.get(ch);
        }
        node.word = word; // mark end
    }

    public List<String> findWords(char[][] board, String[] words) {
        int n = board.length, m = board[0].length;
        for (String word : words) insert(word);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char ch = board[i][j];
                if (root.containsKey(ch)) {
                    dfs(board, i, j, root);
                }
            }
        }

        return new ArrayList<>(result);
    }

    void dfs(char[][] board, int x, int y, TrieNode node) {
        char ch = board[x][y];
        TrieNode nextNode = node.get(ch);
        if (nextNode == null) return;

        if (nextNode.word != null) {
            result.add(nextNode.word); 
            nextNode.word = null; 
        }

        board[x][y] = '#';

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if (nx >= 0 && ny >= 0 && nx < board.length && ny < board[0].length && board[nx][ny] != '#') {
                dfs(board, nx, ny, nextNode);
            }
        }

        board[x][y] = ch;
    }
}

// BACKTRACKING WITH TRIE
// Step-by-Step Approach:
// Insert all words into a Trie:

// Each node stores a character and the full word at the end node.

// For every cell in the board:

// If the character is a valid starting character in Trie, start DFS from there.

// DFS Logic:

// Follow Trie path matching board characters.

// If a complete word is found, add it to the result set.

// Mark visited cells as # temporarily.

// Explore 4 directions (up, down, left, right).

// Backtrack:

// Restore the board after the DFS call.

// Use Set<String> to avoid duplicate results.

// 🧠 Why Trie is used?
// Avoids searching for every word from scratch.

// Cuts off DFS early if current prefix doesn't match any word.

// Speeds up the search with prefix pruning.

// ⏱️ Time Complexity:
// Trie Build: O(N·L), N = number of words, L = avg word length

// DFS Traversal: Worst O(M·N·4^L), M×N = board size, L = word length


public class wordSearch2 {
    public static void main(String[] args) {
        
    }
}
