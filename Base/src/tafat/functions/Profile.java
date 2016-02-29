package tafat.functions;

import tara.magritte.Model;

import java.util.Random;

@FunctionalInterface
public interface Profile {

	void execute(Model model, Random random);

}
