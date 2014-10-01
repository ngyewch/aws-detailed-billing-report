package com.github.ngyewch.aws.billing;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;

public abstract class AbstractDetailedBillIteratorTest {

    protected void process(File file, String usageType)
            throws IOException {
        InputStream istrm = null;
        try {
            Usage usage = new Usage();
            Map<String, Usage> usageMap = new HashMap();
            istrm = new FileInputStream(file);
            DetailedBillIterator billIterator = new DetailedBillIterator(istrm);
            while (billIterator.hasNext()) {
                LineItem lineItem = billIterator.next();
                if (lineItem.getUsageType().equals(usageType)) {
                    String resourceId = String.format("%s/%s", lineItem.getProductName(), lineItem.getResourceId());
                    Usage resourceUsage = usageMap.get(resourceId);
                    if (resourceUsage == null) {
                        resourceUsage = new Usage();
                        usageMap.put(resourceId, resourceUsage);
                    }
                    usage.add(lineItem.getUsageQuantity(), lineItem.getBlendedCost(), lineItem.getUnBlendedCost());
                    resourceUsage.add(lineItem.getUsageQuantity(), lineItem.getBlendedCost(), lineItem.getUnBlendedCost());
                }
            }
            billIterator.close();

            for (Map.Entry<String, Usage> entry : usageMap.entrySet()) {
                String resourceId = entry.getKey();
                Usage resourceUsage = entry.getValue();
                System.out.format(
                        "resourceId=%s, totalUsageQuantity=%f, totalBlendedCost=%f, totalUnBlendedCost=%f\n",
                        resourceId, resourceUsage.getUsageQuantity(), resourceUsage.getBlendedCost(), resourceUsage.getUnBlendedCost());
            }

            System.out.format(
                    "totalUsageQuantity=%f, totalBlendedCost=%f, totalUnBlendedCost=%f\n",
                    usage.getUsageQuantity(), usage.getBlendedCost(), usage.getUnBlendedCost());
        } finally {
            IOUtils.closeQuietly(istrm);
        }
    }

    private static class Usage {

        private double usageQuantity;
        private double blendedCost;
        private double unBlendedCost;

        public void add(double usageQuantity, double blendedCost, double unBlendedCost) {
            this.usageQuantity += usageQuantity;
            this.blendedCost += blendedCost;
            this.unBlendedCost += unBlendedCost;
        }

        public double getUsageQuantity() {
            return usageQuantity;
        }

        public void setUsageQuantity(double usageQuantity) {
            this.usageQuantity = usageQuantity;
        }

        public double getBlendedCost() {
            return blendedCost;
        }

        public void setBlendedCost(double blendedCost) {
            this.blendedCost = blendedCost;
        }

        public double getUnBlendedCost() {
            return unBlendedCost;
        }

        public void setUnBlendedCost(double unBlendedCost) {
            this.unBlendedCost = unBlendedCost;
        }
    }
}
