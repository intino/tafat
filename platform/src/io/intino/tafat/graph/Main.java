package io.intino.tafat.graph;

public class Main {
    public static boolean checkStep(Implementation self, int stepSize) {
        if (self.timeout() == 0) {
            self.timeout(self.step() - stepSize);
            return true;
        }
        self.timeout(self.timeout() - stepSize);
        return false;
    }
}
