package com.kdu.smarthome.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kdu.smarthome.dto.RequestInventoryDto;
import com.kdu.smarthome.entities.Device;
import com.kdu.smarthome.repository.DeviceRepository;
import com.kdu.smarthome.utilities.JsonUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    DeviceRepository deviceRepository;
    JsonUtils jsonUtils;

    public InventoryService(DeviceRepository deviceRepository, JsonUtils jsonUtils) {
        this.deviceRepository = deviceRepository;
        this.jsonUtils = jsonUtils;
    }

    public String getAllItems() throws JsonProcessingException {
        List<Device> allDevices = deviceRepository.findAll();
        return jsonUtils.convertListToJsonString(allDevices);
    }

    public String registerDevice(RequestInventoryDto requestInventoryDto){
        return "";
    }
}
