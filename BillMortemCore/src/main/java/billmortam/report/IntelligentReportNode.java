package billmortam.report;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by pp00344204 on 19/07/17.
 */
public class IntelligentReportNode {
    Map<Character, IntelligentReportNode> pool;
    boolean isWord;
    double total;
    int count;

    public int getCount() {
        return count;
    }

    public void increaseCount() {
        ++count;
    }

    public IntelligentReportNode() {
        pool = new HashMap<>();
    }

    public IntelligentReportNode getNext(Character key) {
        return pool.get(key);
    }

    public Set getKeySet() {
        return pool.keySet();
    }

    public boolean isWord() {
        return isWord;
    }

    public void isWord(boolean word) {
        isWord = word;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setPool(Character character, IntelligentReportNode node) {
        pool.put(character, node);
    }
}
