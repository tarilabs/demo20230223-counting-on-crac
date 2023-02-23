package org.acme;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;

import org.crac.Context;
import org.crac.Core;
import org.crac.Resource;

public class App implements Resource {
    private BigDecimal x = ONE;

    public static void main(String[] args) {
        App app = new App();
        Core.getGlobalContext().register(app);
        app.run();
    }

    public void run() { // something a tad dirty intentionally
        while (true) {
            if (x.remainder(new BigDecimal(3)).equals(ZERO) && x.remainder(new BigDecimal(5)).equals(ZERO)) {
                System.out.println(new StringBuilder("Fizz").append("Buzz").toString());
            } else if (x.remainder(new BigDecimal(3)).equals(ZERO)) {
                System.out.println("Fizz");
            } else if (x.remainder(new BigDecimal(5)).equals(ZERO)) {
                System.out.println("Buzz");
            } else {
                System.out.println(x.toPlainString());
            }
            x = x.add(ONE);
        }
    }

    @Override
    public void beforeCheckpoint(Context<? extends Resource> context) throws Exception {
        System.err.println("beforeCheckpoint()");
        System.out.println("beforeCheckpoint()");
    }

    @Override
    public void afterRestore(Context<? extends Resource> context) throws Exception {
        System.err.println("afterRestore()");
        System.out.println("afterRestore()");
    }
}
