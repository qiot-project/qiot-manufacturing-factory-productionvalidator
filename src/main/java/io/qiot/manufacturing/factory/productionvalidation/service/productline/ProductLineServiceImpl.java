package io.qiot.manufacturing.factory.productionvalidation.service.productline;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;

import io.qiot.manufacturing.all.commons.domain.productline.ProductLineDTO;

/**
 * @author andreabattaglia
 *
 */
@ApplicationScoped
public class ProductLineServiceImpl implements ProductLineService {

    @Inject
    Logger LOGGER;

    @Inject
    @RestClient
    FactoryProductLineServiceClient factoryProductLineServiceClient;

    private Map<UUID, ProductLineDTO> productLines;

    public ProductLineServiceImpl() {
        productLines = new TreeMap<UUID, ProductLineDTO>();
    }

    @Override
    public ProductLineDTO getProductLine(UUID productLineId) {
        if (!productLines.containsKey(productLineId)) {
            LOGGER.debug(
                    "Product Line id {} is missing, contacting ProductLineService...",
                    productLineId);
            productLines.put(productLineId, factoryProductLineServiceClient
                    .getProductLineById(productLineId));
            LOGGER.debug(
                    "Product Line id {} downloaded from ProductLineService.",
                    productLineId);
        }
        return productLines.get(productLineId);
    }
}
