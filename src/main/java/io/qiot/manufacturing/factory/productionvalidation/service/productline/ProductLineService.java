package io.qiot.manufacturing.factory.productionvalidation.service.productline;

import java.util.UUID;

import io.qiot.manufacturing.commons.domain.productline.ProductLineDTO;

/**
 * @author andreabattaglia
 *
 */
public interface ProductLineService {
    /**
     * @param productLineId
     * @return
     */
    ProductLineDTO getProductLine(UUID productLineId);
}
