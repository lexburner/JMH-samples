package moe.cnkirito.jmh.counter;

import moe.cnkirito.jmh.counter.cliff.ConcurrentAutoTable;
import moe.cnkirito.jmh.counter.jdk7.AtomicLongBackport;
import moe.cnkirito.jmh.counter.jdk7.LongAdderBackport;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.CompilerControl.Mode;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class CounterFactory {
    public enum CounterType {
        Atomic7,
        Atomic8,
        ConcurrentAutoTable,
        LongAdder7,
        LongAdder8
    }
    static final Counter build(String counterName) {
        return build(CounterType.valueOf(counterName));
    }
    static final Counter build(CounterType type) {
        switch (type) {
        case Atomic7:
            return new AtomicLong7Counter();
        case Atomic8:
            return new AtomicLong8Counter();
        case ConcurrentAutoTable:
            return new ConcurrentAutoTableCounter();
        case LongAdder7:
            return new LongAdder7Counter();
        case LongAdder8:
            return new LongAdder8Counter();
        default:
            throw new IllegalArgumentException();
        } 
    }
    @SuppressWarnings("serial")
    static class ConcurrentAutoTableCounter extends ConcurrentAutoTable implements Counter {
        @Override
        @CompilerControl(Mode.INLINE)
        public void inc() {
            super.increment();
        }

        @Override
        @CompilerControl(Mode.INLINE)
        public long get() {
            return super.get();
        }
    }
    @SuppressWarnings("serial")
    static class AtomicLong7Counter extends AtomicLongBackport implements Counter {

        @Override
        @CompilerControl(Mode.INLINE)
        public void inc() {
            super.incrementAndGet();
        }
    }
    @SuppressWarnings("serial")
    static class AtomicLong8Counter extends AtomicLong implements Counter {
    
        @Override
        @CompilerControl(Mode.INLINE)
        public void inc() {
            super.incrementAndGet();
        }
    }
    @SuppressWarnings("serial")
    static class LongAdder7Counter extends LongAdderBackport implements Counter {
        @Override
        @CompilerControl(Mode.INLINE)
        public void inc() {
            super.increment();
        }

        @Override
        @CompilerControl(Mode.INLINE)
        public long get() {
            return super.sum();
        }
    }
    @SuppressWarnings("serial")
    static class LongAdder8Counter extends LongAdder implements Counter {
        @Override
        @CompilerControl(Mode.INLINE)
        public void inc() {
            super.increment();
        }

        @Override
        @CompilerControl(Mode.INLINE)
        public long get() {
            return super.sum();
        }        
    }

}
