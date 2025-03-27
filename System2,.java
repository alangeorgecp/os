import java.io.File;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;

public class SimpleSystemMonitor {

    public static void main(String[] args) {
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

        while (true) {
            double cpuLoad = osBean.getSystemCpuLoad() * 100;
            double memoryUsed = (1.0 - (double) osBean.getFreePhysicalMemorySize() / osBean.getTotalPhysicalMemorySize()) * 100;
            double diskUsage = getDiskUsage();

            System.out.printf("CPU Usage: %.2f%%\n", cpuLoad);
            System.out.printf("Memory Usage: %.2f%%\n", memoryUsed);
            System.out.printf("Disk Usage: %.2f%%\n", diskUsage);

            logSuggestions(cpuLoad, memoryUsed, diskUsage);

            try {
                Thread.sleep(1500); // Update every 1.5 seconds
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static double getDiskUsage() {
        File diskPartition = new File("C:\"); // Change to appropriate drive
        long totalSpace = diskPartition.getTotalSpace();
        long freeSpace = diskPartition.getFreeSpace();
        return ((double) (totalSpace - freeSpace) / totalSpace) * 100;
    }

    private static void logSuggestions(double cpuLoad, double memoryUsed, double diskUsage) {
        if (cpuLoad > 85) {
            System.out.println("[AI Suggestion]: High CPU usage. Consider optimizing applications.");
        }
        if (memoryUsed > 80) {
            System.out.println("[AI Suggestion]: High memory usage. Close unused applications.");
        }
        if (diskUsage > 90) {
            System.out.println("[AI Suggestion]: Disk almost full. Clean up unnecessary files.");
        }
        if (cpuLoad < 85 && memoryUsed < 80 && diskUsage < 90) {
            System.out.println("[AI Suggestion]: System stable. No actions required.");
        }
    }
} 
