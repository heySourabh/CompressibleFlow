/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas.equations;

import in.spbhat.gas.Gas;

public class NormalShock {
    private final double gamma;

    public NormalShock(Gas gas) {
        this.gamma = gas.gamma();
    }
}
