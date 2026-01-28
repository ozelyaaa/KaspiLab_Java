package kz.kaspilab.projectwebflux.utils;

import kz.kaspilab.projectwebflux.models.DeliveryDTO;

public class KeyGeneratorUtil {

    public static String deliveryGenerateKey(DeliveryDTO dto) {
        return "delivery:create:" + dto.getId() + ":" + dto.getAddress();
    }
}
