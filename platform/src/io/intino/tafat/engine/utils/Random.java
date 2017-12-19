package io.intino.tafat.engine.utils;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Random {

	private static java.util.Random random;

	public static void init(int seed){
		random = seed == -1 ? new java.util.Random() : new java.util.Random(seed);
	}

	public static void setSeed(long seed) {
		random.setSeed(seed);
	}

	public static float nextFloat() {
		return random.nextFloat();
	}

	public static IntStream ints(long streamSize, int randomNumberOrigin, int randomNumberBound) {
		return random.ints(streamSize, randomNumberOrigin, randomNumberBound);
	}

	public static LongStream longs() {
		return random.longs();
	}

	public static DoubleStream doubles() {
		return random.doubles();
	}

	public static LongStream longs(long randomNumberOrigin, long randomNumberBound) {
		return random.longs(randomNumberOrigin, randomNumberBound);
	}

	public static boolean nextBoolean() {
		return random.nextBoolean();
	}

	public static DoubleStream doubles(long streamSize, double randomNumberOrigin, double randomNumberBound) {
		return random.doubles(streamSize, randomNumberOrigin, randomNumberBound);
	}

	public static IntStream ints(long streamSize) {
		return random.ints(streamSize);
	}

	public static IntStream ints() {
		return random.ints();
	}

	public static IntStream ints(int randomNumberOrigin, int randomNumberBound) {
		return random.ints(randomNumberOrigin, randomNumberBound);
	}

	public static void nextBytes(byte[] bytes) {
		random.nextBytes(bytes);
	}

	public static LongStream longs(long streamSize, long randomNumberOrigin, long randomNumberBound) {
		return random.longs(streamSize, randomNumberOrigin, randomNumberBound);
	}

	public static DoubleStream doubles(long streamSize) {
		return random.doubles(streamSize);
	}

	public static LongStream longs(long streamSize) {
		return random.longs(streamSize);
	}

	public static DoubleStream doubles(double randomNumberOrigin, double randomNumberBound) {
		return random.doubles(randomNumberOrigin, randomNumberBound);
	}

	public static double nextDouble() {
		return random.nextDouble();
	}

	public static int nextInt() {
		return random.nextInt();
	}

	public static long nextLong() {
		return random.nextLong();
	}

	public static double nextGaussian() {
		return random.nextGaussian();
	}

	public static int nextInt(int bound) {
		return random.nextInt(bound);
	}
}
