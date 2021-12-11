package com.xently.parser.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class KopoKopo {

    public static class Transaction {
        private String id = null;
        private Date transactionDate;
        private double transactionAmount;

        public boolean isDefaultInstance() {
            return id == null;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Transaction that = (Transaction) o;
            return ((Transaction) o).getId().equals(that.getId());
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Date getTransactionDate() {
            return transactionDate;
        }

        public void setTransactionDate(Date transactionDate) {
            this.transactionDate = transactionDate;
        }

        public double getTransactionAmount() {
            return transactionAmount;
        }

        public void setTransactionAmount(double transactionAmount) {
            this.transactionAmount = transactionAmount;
        }
    }

    public static List<String> getBestCustomerIDs(String transactionsFile, int numberOfCustomers) {
        try {
            List<Transaction> customerTransactions = Files.lines(Paths.get(transactionsFile)).filter(s -> !s.startsWith("Customer ID"))
                    .map(s -> {
                        String[] row = s.split(",");
                        Transaction transaction = new Transaction();
                        transaction.setId(row[0]);
                        transaction.setTransactionAmount(Double.parseDouble(row[1]));
                        try {
                            transaction.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(row[2]));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return transaction;
                    }).collect(Collectors.toList());

            Map<String, List<Transaction>> map = new HashMap<>();
            for (Transaction transaction : customerTransactions) {
                String id = transaction.getId();
                if (map.containsKey(id)) {
                    List<Transaction> v = map.get(id);
                    v.add(transaction);
                    map.put(id, v);
                } else {
                    map.put(id, new ArrayList<>(Collections.singleton(transaction)));
                }
            }
            Map<String, Integer> processed = new HashMap<>();
            for (Map.Entry<String, List<Transaction>> entry : map.entrySet()) {
                List<Date> transactionDates = entry.getValue().stream()
                        .sorted((transaction, t1) -> transaction.transactionDate.compareTo(t1.getTransactionDate()))
                        .map(Transaction::getTransactionDate).distinct().collect(Collectors.toList());

                int largestConsecutive = 1, maxLargestConsecutive = 1;
                for (int i = 0; i < transactionDates.size(); i++) {
                    if (i == 0) continue;
                    long currentTime = transactionDates.get(i).getTime();
                    long previousTime = transactionDates.get(i - 1).getTime();
                    if (currentTime - (24 * 60 * 60 * 1000) == previousTime || currentTime == previousTime) {
                        largestConsecutive += 1;
                    } else {
                        if (maxLargestConsecutive < largestConsecutive) {
                            maxLargestConsecutive = largestConsecutive;
                        }
                        largestConsecutive = 1;
                    }
                }
                processed.put(entry.getKey(), maxLargestConsecutive);
            }

            return processed.entrySet().stream()
                    .sorted((stringIntegerEntry, t1) -> {
                        int compare = t1.getValue().compareTo(stringIntegerEntry.getValue());
                        if (compare == 0) {
                            compare = t1.getKey().compareTo(stringIntegerEntry.getKey());
                        }
                        return compare;
                    })
                    .map(stringIntegerEntry -> stringIntegerEntry.getKey())
                    .limit(numberOfCustomers)
                    .collect(Collectors.toList());

//            return customerTransactions.stream().map(Transaction::getId).distinct().limit(numberOfCustomers).sorted().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
