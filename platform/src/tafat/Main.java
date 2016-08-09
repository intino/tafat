package tafat;

public class Main {
    public static boolean checkStep(tafat.Implementation self, int stepSize) {
        if (self.timeout() == 0) {
            self.timeout(self.step() - stepSize);
            return true;
        }
        self.timeout(self.timeout() - stepSize);
        return false;
    }
}
