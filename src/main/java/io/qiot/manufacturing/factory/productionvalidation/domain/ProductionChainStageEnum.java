package io.qiot.manufacturing.factory.productionvalidation.domain;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public enum ProductionChainStageEnum {
    /**
     * 
     */
    WEAVING("weaving"),
    /**
    * 
    */
    COLORING("coloring"),
    /**
    * 
    */
    PRINTING("printing"),
    /**
    * 
    */
    PACKAGING("packaging");

    private final String lcName;

    private ProductionChainStageEnum(String lcName) {
        this.lcName = lcName;
    }

    public String getLCName() {
        return lcName;
    }
}
