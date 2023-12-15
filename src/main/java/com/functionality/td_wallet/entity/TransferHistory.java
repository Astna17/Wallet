package com.functionality.td_wallet.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransferHistory {

    private static List<TransferRecord> transferRecords = new ArrayList<>();

    public static class TransferRecord {
        public Account sourceAccount;
        public Account destinationAccount;
        public double amount;
        public LocalDateTime transferDate;

        public TransferRecord(Account sourceAccount, Account destinationAccount, double amount) {
            this.sourceAccount = sourceAccount;
            this.destinationAccount = destinationAccount;
            this.amount = amount;
            this.transferDate = LocalDateTime.now();
        }
    }

    public static void addTransfer(Account sourceAccount, Account destinationAccount, double amount) {
        TransferRecord transferRecord = new TransferRecord(sourceAccount, destinationAccount, amount);
        transferRecords.add(transferRecord);
    }

    public static List<TransferRecord> getTransfersInDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        List<TransferRecord> transfersInRange = new ArrayList<>();
        for (TransferRecord record : transferRecords) {
            if (record.transferDate.isAfter(startDate) && record.transferDate.isBefore(endDate)) {
                transfersInRange.add(record);
            }
        }
        return transfersInRange;
    }
}
