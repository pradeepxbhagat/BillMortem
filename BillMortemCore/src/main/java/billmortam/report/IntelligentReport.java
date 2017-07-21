package billmortam.report;

import billmortam.util.Util;

import java.util.*;

/**
 * Created by pp00344204 on 18/07/17.
 */
public class IntelligentReport {

    private IntelligentReportNode root;

    public IntelligentReport() {
        root = new IntelligentReportNode();
    }

    public Map<String, Double> getReport(TransactionReport report) {
        Map<String, Double> distinctKeyTotalReport = report.getDistinctKeyTotalReport();
        Set<String> keys = distinctKeyTotalReport.keySet();

        for (String key : keys) {
            String[] words = key.split(" ");
            for (String word : words) {
                if (isValidWord(word)) {
                    insert(word, 0, root, distinctKeyTotalReport.get(key));
                }
            }
        }

        Map<String, Double> stringDoubleMap = getAllNodes(root, new ArrayList<>(), ' ', 0, new HashMap<>());
        return stringDoubleMap;
    }

    private boolean isValidWord(String word) {
        return word.trim().length() > 2 && !Util.inIgnoreKeyList(word);
    }

    private IntelligentReportNode insert(String data, int i, IntelligentReportNode root, double total) {
        if (isIndexValid(data, i)) {
            char val = data.charAt(i);
            if (nodeHasData(root, val)) {
                insert(data, ++i, root.getNext(val), total);
            } else {
                IntelligentReportNode node = new IntelligentReportNode();
                root.setPool(val, node);
                insert(data, ++i, node, total);
            }
        } else {
            updateWord(root, total);
        }

        return root;
    }

    private void updateWord(IntelligentReportNode root, double total) {
        root.isWord(true);
        double prevTotal = root.getTotal() + total;
        root.setTotal(prevTotal);
        root.increaseCount();
    }

    private boolean nodeHasData(IntelligentReportNode root, char val) {
        return root.getKeySet().contains(val);
    }

    private boolean isIndexValid(String data, int i) {
        return i < data.length();
    }

    private Map<String, Double> getAllNodes(IntelligentReportNode node, List<Character> data, Character key, int len, Map<String, Double> result) {

        if (node.isWord()) {
            data.add(key);
            if (isWordRepeated(node)) {
                fill(node, data,result);
            }
            data.remove(key);
        }

        if (hasEmptyNodes(node)) {
            data.remove(key);
            return result;
        }

        Set<Character> characters = node.getKeySet();
        int prevLen = len;
        for (Character character : characters) {
            if(isTopInHierarchy(prevLen)){
                data.clear();
            }else {
                data.add(key);
            }
            getAllNodes(node.getNext(character), data, character, ++len, result);
            data.remove(key);
        }

        return result;
    }

    private boolean isTopInHierarchy(int prevLen) {
        return prevLen == 0;
    }

    private boolean hasEmptyNodes(IntelligentReportNode node) {
        return node.getKeySet().size() == 0;
    }

    private boolean isWordRepeated(IntelligentReportNode node) {
        return node.getCount() > 1;
    }

    private void fill(IntelligentReportNode node, List<Character> data, Map<String, Double> result) {
        String resultStr = "";
        for (Character c : data) {
            resultStr += c;
        }
        result.put(resultStr.trim(),node.getTotal());
    }

}
