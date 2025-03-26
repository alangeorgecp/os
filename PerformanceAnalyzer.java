import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;

public class PerformanceAnalyzer {

    public static void main(String[] args) {
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

        while (true) {
            double cpuLoad = osBean.getSystemCpuLoad() * 100;
            double memoryUsed = (1.0 - (double) osBean.getFreePhysicalMemorySize() / osBean.getTotalPhysicalMemorySize()) * 100;

            System.out.printf("CPU Usage: %.2f%%\n", cpuLoad);
            System.out.printf("Memory Usage: %.2f%%\n", memoryUsed);

            generateAISuggestions(cpuLoad, memoryUsed);

            try {
                Thread.sleep(1000); // Update every second
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void generateAISuggestions(double cpuLoad, double memoryUsed) {
        if (cpuLoad > 80) {
            System.out.println("AI Suggestion: High CPU usage detected. Consider closing unnecessary applications.");
        } else if (memoryUsed > 80) {
            System.out.println("AI Suggestion: Memory usage is high. Clear memory-intensive tasks.");
        } else {
            System.out.println("AI Suggestion: System is stable. No actions needed.");
        }
    }
} 
