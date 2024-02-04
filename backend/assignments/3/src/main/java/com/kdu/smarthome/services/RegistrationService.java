package com.kdu.smarthome.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kdu.smarthome.dto.DeviceRegisterRequestDto;
import com.kdu.smarthome.entities.Device;
import com.kdu.smarthome.entities.DeviceRegistration;
import com.kdu.smarthome.entities.Residents;
import com.kdu.smarthome.entities.User;
import com.kdu.smarthome.exceptions.custom.NotFoundException;
import com.kdu.smarthome.repository.DeviceRegistrationRepository;
import com.kdu.smarthome.repository.DeviceRepository;
import com.kdu.smarthome.repository.ResidentRepository;
import com.kdu.smarthome.repository.UserRepository;
import com.kdu.smarthome.utilities.JsonUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RegistrationService {
    DeviceRegistrationRepository deviceRegistrationRepository;
    ResidentRepository residentRepository;
    UserRepository userRepository;
    DeviceRepository deviceRepository;
    JsonUtils jsonUtils;

    public RegistrationService(DeviceRegistrationRepository deviceRegistrationRepository, ResidentRepository residentRepository, UserRepository userRepository, DeviceRepository deviceRepository, JsonUtils jsonUtils) {
        this.deviceRegistrationRepository = deviceRegistrationRepository;
        this.residentRepository = residentRepository;
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
        this.jsonUtils = jsonUtils;
    }

    public void register(DeviceRegisterRequestDto deviceDto) throws JsonProcessingException {
        Optional<Device> requiredDevice = deviceRepository.findById(deviceDto.getKickStonId());
        if (requiredDevice.isEmpty()){
            throw new NotFoundException("Device doesn't exist");
        }
        Device device = requiredDevice.get();
        Optional<DeviceRegistration> checkDeviceRegistration = deviceRegistrationRepository.findById(device.getKickstonId());
        if(checkDeviceRegistration.isPresent()){
            throw new NotFoundException("Already registered");
        }

        if(!Objects.equals(device.getDevicePassword(), deviceDto.getDevicePassword())){
            throw new IllegalCallerException("Invalid credentials");
        }
        if(!Objects.equals(device.getDeviceUsername(), deviceDto.getDeviceUsername())){
            throw new NotFoundException("Invalid credentials");
        }
        Optional<User> currentUser = userRepository.findById((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (currentUser.isEmpty()){
            throw new IllegalCallerException("User not authorized");
        }
        User user = currentUser.get();
        List<Residents> residentCheck = residentRepository.findIfUserIsAdmin(user.getUsername());
        if (residentCheck.isEmpty()){
            throw new IllegalCallerException("User not an admin");
        }

        deviceRegistrationRepository.save(new DeviceRegistration(deviceDto.getKickStonId(), user));

        System.out.println(jsonUtils.convertListToJsonString(userRepository.findAll()));

        System.out.println(jsonUtils.convertListToJsonString(residentRepository.findAll()));

        System.out.println(jsonUtils.convertListToJsonString(deviceRepository.findAll()));

        System.out.println(jsonUtils.convertListToJsonString(deviceRegistrationRepository.findAll()));
    }
}
