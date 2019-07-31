package lt_1100_1199;

/**
 * 两扇门
 * gate1 -> hp=2表示放过去两个hydrogen，op=1表示要放过去一个oxygen
 * gate2 -> run hydrogen, oxygen, 并对消费计数，全部消费掉则调用reset 还原参数，为下一个h2o消费做准备
 */
public class LC_1117 {
    static class H2O {
        private volatile int hp, op; // gate1
        private volatile int release;
        private final Object gate2;
        public H2O() {
            this.hp = 2;
            this.op = 1;
            this.release = 0;
            gate2 = new Object();
        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            synchronized (this) {
                while (true) {
                    if (hp > 0) {
                        hp--;
                        break;
                    } else {
                        wait();
                    }
                }
            }
            synchronized (gate2) {
                releaseHydrogen.run();
                release++;
                if (release == 3) {
                    reset();
                }
            }


        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            synchronized (this) {
                while (true) {
                    if (op > 0) {
                        op--;
                        break;
                    } else {
                        wait();
                    }
                }
            }
            synchronized (gate2) {
                releaseOxygen.run();
                release++;
                if (release == 3) {
                    reset();
                }
            }
        }

        private void reset() {
            release = 0;
            synchronized (this) {
                hp = 2;
                op = 1;
                notifyAll();    // 唤醒所有wait this的线程
            }
        }
    }
}
