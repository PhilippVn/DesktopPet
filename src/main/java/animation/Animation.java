package animation;

import window.Window;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;

public class Animation {
    String stateName;
    String stateCategory;
    File animationDirectory;
    File[] frames;
    Window window;
    boolean isPlaying = false;


    @Override
    public String toString() {
        return "Animation{" +
                "stateName='" + stateName + '\'' +
                ", stateCategory='" + stateCategory + '\'' +
                '}';
    }

    public Animation(String stateName, String stateCategory, Window window) throws FileNotFoundException {
        this.stateName = stateName;
        this.stateCategory = stateCategory;
        this.window = window;

        // directory of animation frames
        Path animationPath = Paths.get("","src","main","resources","animations",stateCategory,stateName);
        this.animationDirectory = new File(animationPath.toAbsolutePath().toString());
        if (!this.animationDirectory.exists() || !this.animationDirectory.isDirectory()){
            throw new FileNotFoundException(animationPath.toAbsolutePath() + " animation frame directory not found!");
        }
        this.frames = this.animationDirectory.listFiles();
        if(frames == null){
            throw new FileNotFoundException(animationPath.toAbsolutePath() + " animation frames not found!");
        }
        if(frames.length == 0){
            throw new FileNotFoundException(animationDirectory.getAbsolutePath() + "doesn't contain any animation frames!");
        }
        Arrays.sort(frames,new NumericFileComparator());
    }

    public void playAnimation() {
        isPlaying = true;
        JFrame frame = window.getJFrame();
        for (File file : frames) {
            frame.getContentPane().removeAll();
            ImageIcon icon = new ImageIcon(file.getAbsolutePath());
            System.out.println("> " + file.getAbsolutePath());
            frame.getContentPane().add(new JLabel(icon));
            frame.setVisible(true);
            try {
                Thread.sleep(50);
            } catch (InterruptedException ignored) {
            }
        }
        isPlaying = false;
    }

    // We need to sort the frames numerically instead of lexicographical
    static class NumericFileComparator implements Comparator<File> {
        @Override
        public int compare(File file1, File file2) {
            String fileName1 = file1.getName();
            String fileName2 = file2.getName();

            // Extract numeric parts and compare as integers
            int number1 = extractNumber(fileName1);
            int number2 = extractNumber(fileName2);

            return Integer.compare(number1, number2);
        }

        private int extractNumber(String name) {
            int startIndex = name.lastIndexOf(File.separator) + 1; // Find where the number part begins
            int endIndex = name.lastIndexOf(".png"); // Find where the number part ends (assuming ".png" extension)

            if (startIndex < 0 || endIndex < 0 || startIndex >= endIndex) {
                // Handle cases where the number part is not found or is invalid
                throw new IllegalArgumentException("Invalid file name format: " + name);
            }

            String numberPart = name.substring(startIndex, endIndex);
            return Integer.parseInt(numberPart);
        }
    }

}
