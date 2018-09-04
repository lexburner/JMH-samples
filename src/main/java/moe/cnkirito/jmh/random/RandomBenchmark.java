package moe.cnkirito.jmh.random;

import moe.cnkirito.jmh.queue.QueueBenchmark;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 3, time = 5)
@Measurement(iterations = 3, time = 5)
@Threads(10)
@Fork(1)
@State(Scope.Benchmark)
public class RandomBenchmark {

    Random random = new Random();

    ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

    @Benchmark
    public int random() {
        return random.nextInt();
    }

    @Benchmark
    public int threadLocalRandom() {
        return threadLocalRandom.nextInt();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(RandomBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

}
