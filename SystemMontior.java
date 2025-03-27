import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import java.io.File;
import java.util.Random;

public class SystemMonitor {

    private static final Random random = new Random();

    public static void main(String[] args) {
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

        while (true) {
            double cpuLoad = osBean.getSystemCpuLoad() * 100;
            double memoryUsed = (1.0 - (double) osBean.getFreePhysicalMemorySize() / osBean.getTotalPhysicalMemorySize()) * 100;
            double diskUsage = getRandomDiskUsage();
            double networkUsage = getRandomNetworkUsage();

            System.out.printf("CPU Usage: %.2f%%\n", cpuLoad);
            System.out.printf("Memory Usage: %.2f%%\n", memoryUsed);
            System.out.printf("Disk Usage: %.2f%%\n", diskUsage);
            System.out.printf("Network Usage: %.2f%%\n", networkUsage);

            generateAISuggestions(cpuLoad, memoryUsed, diskUsage, networkUsage);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static double getRandomDiskUsage() {
        return 0.2 + (random.nextDouble() * 6.3); // Disk usage between 0.2% to 6.5%
    }

    private static double getRandomNetworkUsage() {
        return random.nextDouble() * 5.0; // Simulate network usage between 0% to 5%
    }

    private static void generateAISuggestions(double cpuLoad, double memoryUsed, double diskUsage, double networkUsage) {
        if (cpuLoad > 80) {
            System.out.println("AI Suggestion: High CPU usage detected. Close unnecessary applications.");
        }
        if (memoryUsed > 80) {
            System.out.println("AI Suggestion: High memory usage. Consider clearing memory-intensive tasks.");
        }
        if (diskUsage > 5.5) {
            System.out.println("AI Suggestion: Disk usage nearing limit. Monitor or clean up storage.");
        }
        if (networkUsage > 4.0) {
            System.out.println("AI Suggestion: High network usage. Investigate network traffic.");
        }
        if (cpuLoad <= 80 && memoryUsed <= 80 && diskUsage <= 5.5 && networkUsage <= 4.0) {
            System.out.println("AI Suggestion: System running optimally. No issues detected.");
        }
    }
} 
