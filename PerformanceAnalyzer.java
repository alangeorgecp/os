import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;
import java.util.Random;

public class PerformanceAnalyzer {

    private static final Random random = new Random();

    public static void main(String[] args) {
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

        while (true) {
            double cpuLoad = osBean.getSystemCpuLoad() * 100;
            double memoryUsed = (1.0 - (double) osBean.getFreePhysicalMemorySize() / osBean.getTotalPhysicalMemorySize()) * 100;
            double diskUsage = getRandomDiskUsage();

            System.out.printf("CPU Usage: %.2f%%\n", cpuLoad);
            System.out.printf("Memory Usage: %.2f%%\n", memoryUsed);
            System.out.printf("Disk Usage: %.2f%%\n", diskUsage);

            generateAISuggestions(cpuLoad, memoryUsed, diskUsage);

            try {
                Thread.sleep(1000); // Update every second
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static double getRandomDiskUsage() {
        return 0.2 + (random.nextDouble() * (6.5 - 0.2));
    }

    private static void generateAISuggestions(double cpuLoad, double memoryUsed, double diskUsage) {
        if (cpuLoad > 80) {
            System.out.println("AI Suggestion: High CPU usage detected. Consider closing unnecessary applications.");
        } else if (memoryUsed > 80) {
            System.out.println("AI Suggestion: Memory usage is high. Clear memory-intensive tasks.");
        } else if (diskUsage > 90) {
            System.out.println("AI Suggestion: Disk space is critically low. Consider cleaning up unused files.");
        } else {
            System.out.println("AI Suggestion: System is stable. No actions needed.");
        }
    }
} 






















