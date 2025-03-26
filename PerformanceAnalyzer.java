import com.sun.management.OperatingSystemMXBean;
import java.awt.*;
import java.lang.management.ManagementFactory;
import java.util.Random;
import javax.swing.*;

public class PerformanceAnalyzer4 extends JFrame {

    private static final Random random = new Random();
    private JProgressBar cpuBar, memoryBar, diskBar, ethernetBar;

    public PerformanceAnalyzer4() {
        setTitle("System Performance Analyzer");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        cpuBar = createProgressBar("CPU Usage");
        memoryBar = createProgressBar("Memory Usage");
        diskBar = createProgressBar("Disk Usage");
        ethernetBar = createProgressBar("Ethernet Usage (0%)");

        Timer timer = new Timer(1000, e -> updateMetrics());
        timer.start();
    }

    private JProgressBar createProgressBar(String title) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title));
        JProgressBar bar = new JProgressBar(0, 100);
        bar.setStringPainted(true);
        panel.add(bar, BorderLayout.CENTER);
        add(panel);
        return bar;
    }

    private void updateMetrics() {
        try {
            OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
            double cpuLoad = osBean.getSystemCpuLoad() * 100;
            double memoryUsed = (1.0 - (double) osBean.getFreePhysicalMemorySize() / osBean.getTotalPhysicalMemorySize()) * 100;
            double diskUsage = getRandomDiskUsage();
            double ethernetUsage = 0.0; // Ethernet usage set to 0

            updateBar(cpuBar, cpuLoad);
            updateBar(memoryBar, memoryUsed);
            updateBar(diskBar, diskUsage);
            updateBar(ethernetBar, ethernetUsage);

            generateAISuggestions(cpuLoad, memoryUsed, diskUsage, ethernetUsage);
        } catch (Exception e) {
            System.err.println("Error updating metrics: " + e.getMessage());
        }
    }

    private void updateBar(JProgressBar bar, double value) {
        int intValue = (int) Math.min(Math.max(value, 0), 100);
        bar.setValue(intValue);
        bar.setString(String.format("%.2f%%", value));
    }

    private double getRandomDiskUsage() {
        return 0.2 + (random.nextDouble() * 6.3); // Random disk usage from 0.2% to 6.5%
    }

    private void generateAISuggestions(double cpuLoad, double memoryUsed, double diskUsage, double ethernetUsage) {
        if (cpuLoad > 80) {
            System.out.println("AI Suggestion: High CPU usage detected. Consider closing unnecessary applications.");
        }
        if (memoryUsed > 80) {
            System.out.println("AI Suggestion: Memory usage is high. Clear memory-intensive tasks.");
        }
        if (diskUsage > 6.0) {
            System.out.println("AI Suggestion: Disk space usage is relatively high. Monitor storage utilization.");
        }
        if (ethernetUsage > 80) {
            System.out.println("AI Suggestion: High Ethernet usage detected. Check network traffic.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PerformanceAnalyzer4().setVisible(true));
    }
}









