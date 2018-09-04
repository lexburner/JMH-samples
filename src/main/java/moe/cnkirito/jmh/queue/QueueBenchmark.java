package moe.cnkirito.jmh.queue;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author kirito.moe@foxmail.com
 * Date 2018-08-31
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.Throughput)
//@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
public class QueueBenchmark {

    private Queue<Integer> queue;

    @Param
    QueueFactory.QueueType queueType;
    @Param(value = "132000")
    int qCapacity;
    @Param(value = "100000")
    int burstSize;

    @Setup
    public void buildMeCounterHearty() {
        queue = QueueFactory.build(queueType, qCapacity);
    }

    @Benchmark
    public int offerAndPoll() {
        final int fixedBurstSize = burstSize;
        for (int i = 0; i < fixedBurstSize; i++) {
            queue.offer(1);
        }
        int result = 0;
        for (int i = 0; i < fixedBurstSize; i++) {
            result = queue.poll();
        }
        return result;
    }

    @Benchmark
    @Threads(2)
    public boolean concurrentOffer() {
        return queue.offer(1);
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(QueueBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
