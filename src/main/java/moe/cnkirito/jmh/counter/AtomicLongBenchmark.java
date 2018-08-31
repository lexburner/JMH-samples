package moe.cnkirito.jmh.counter;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.GroupThreads;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author kirito.moe@foxmail.com
 * Date 2018-08-21
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
public class AtomicLongBenchmark {

    AtomicLong counter;

    @Setup
    public void buildMeCounterHearty() {
        counter = new AtomicLong();
    }

    @Benchmark
    @Group("rw_1")
    @GroupThreads(1)
    public long increment_1() {
        return counter.incrementAndGet();
    }

    @Benchmark
    @Group("rw_1")
    @GroupThreads(1)
    public long get_1() {
        return counter.get();
    }

    @Benchmark
    @Group("rw_3")
    @GroupThreads(3)
    public long increment_3() {
        return counter.incrementAndGet();
    }

    @Benchmark
    @Group("rw_3")
    @GroupThreads(3)
    public long get_3() {
        return counter.get();
    }

    @Benchmark
    @Group("rw_5")
    @GroupThreads(5)
    public long increment_5() {
        return counter.incrementAndGet();
    }

    @Benchmark
    @Group("rw_5")
    @GroupThreads(5)
    public long get_5() {
        return counter.get();
    }

    @Benchmark
    @Group("rw_10")
    @GroupThreads(10)
    public long increment_10() {
        return counter.incrementAndGet();
    }

    @Benchmark
    @Group("rw_10")
    @GroupThreads(10)
    public long get_10() {
        return counter.get();
    }

    @Benchmark
    @Group("rw_20")
    @GroupThreads(20)
    public long increment_20() {
        return counter.incrementAndGet();
    }

    @Benchmark
    @Group("rw_20")
    @GroupThreads(20)
    public long get_20() {
        return counter.get();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(AtomicLongBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}
