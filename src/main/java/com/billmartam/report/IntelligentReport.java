package com.billmartam.report;

import com.billmartam.util.Util;

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
                if (word.trim().length() > 2 && !Util.inIgnoreKeyList(word)) {
                    insert(word, 0, root, distinctKeyTotalReport.get(key));
                }
            }
        }

        Map<String, Double> stringDoubleMap = printAllNodes(root, new ArrayList<>(), ' ', 0, new HashMap<>());
        return stringDoubleMap;
    }

    private IntelligentReportNode insert(String data, int i, IntelligentReportNode root, double total) {
        if (i < data.length()) {
            char val = data.charAt(i);
            if (root.getKeySet().contains(val)) {
                insert(data, ++i, root.getNext(val), total);
            } else {
                IntelligentReportNode node = new IntelligentReportNode();
                root.setPool(val, node);
                insert(data, ++i, node, total);
            }
        } else {
            root.isWord(true);
            double prevTotal = root.getTotal() + total;
            root.setTotal(prevTotal);
            root.increaseCount();
        }

        return root;
    }

    private Map<String, Double> printAllNodes(IntelligentReportNode node, List<Character> data, Character key, int len, Map<String, Double> result) {

        if (node.isWord()) {
            data.add(key);
            if (node.getCount() > 1) {
                print(node, data,result);
            }
            data.remove(key);
        }

        if (node.getKeySet().size() == 0) {
            data.remove(key);
            return result;
        }


        /*int i = prevLen;
        System.out.print(len);
        while(i>0){
            System.out.print("-");
            i--;
        }
        for(Character character : characters){
            System.out.print(character);
        }
        System.out.println();*/
        Set<Character> characters = node.getKeySet();
        int prevLen = len;
        for (Character character : characters) {
            if(prevLen == 0){
                data.clear();
            }else {
                data.add(key);
            }
            printAllNodes(node.getNext(character), data, character, ++len, result);
            data.remove(key);
        }

        return result;
    }

    private void print(IntelligentReportNode node, List<Character> data, Map<String, Double> result) {
        String resultStr = "";
        for (Character c : data) {
            resultStr += c;
        }
        result.put(resultStr.trim(),node.getTotal());
        /*resultStr += " " + node.getTotal();

        System.out.println(resultStr.trim());*/
    }

    private class IntelligentReportNode {
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
}
