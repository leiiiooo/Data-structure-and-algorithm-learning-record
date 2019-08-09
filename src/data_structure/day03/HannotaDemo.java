package data_structure.day03;

public class HannotaDemo {
    public static void main(String[] args) {
        hannota(3, 1, 2, 3);
    }

    private static void hannota(int plateNumber, int startLine, int middleLine, int endLine) {
        if (plateNumber == 1) {
            System.out.println("move:from->" + startLine + " to:end->" + endLine);
        } else {
            hannota(plateNumber - 1, startLine, endLine, middleLine);
            System.out.println("move:from->" + startLine + " to:end->" + endLine);
            hannota(plateNumber - 1, middleLine, startLine, endLine);
        }
    }
}
