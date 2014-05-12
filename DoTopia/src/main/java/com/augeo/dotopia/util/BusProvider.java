package com.augeo.dotopia.util;

import com.squareup.otto.Bus;

/**
 * Created by krasimir.karamazov on 2/28/14.
 */
public final class BusProvider {
    private static final Bus BUS = new Bus();

    public static Bus getInstance(){
        return BUS;
    }

    private BusProvider(){}
}
