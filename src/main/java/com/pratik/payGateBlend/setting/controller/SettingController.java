package com.pratik.payGateBlend.setting.controller;

import com.pratik.payGateBlend.common.model.GeneralResponse;
import com.pratik.payGateBlend.constants.AppConstants;
import com.pratik.payGateBlend.setting.dao.controller.SettingDAOController;
import com.pratik.payGateBlend.setting.model.SettingData;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@AllArgsConstructor(onConstructor_ = @__({@Autowired}))
@RestController
@RequestMapping("/setting")
public class SettingController {

    private final SettingDAOController settingDAOController;

    @PostMapping (
            value = "/save",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS"),
            @ApiResponse(code = 400, message = "FAILED"),
            @ApiResponse(code = 500, message = "Exception Occurred")
    })
    @ApiOperation(value = "")
    public Object addSettingData(
            HttpServletRequest request,
            @RequestHeader String projectId,
            @Valid @RequestBody SettingData settingData
            ) throws Exception {

        settingData.setProjectId(projectId);
        Long id = this.settingDAOController.saveSettingData(settingData);

        if(id == null) {
            throw new Exception("Failed to save setting data!");
        }

        return new ResponseEntity<>(
                new GeneralResponse(
                        true,
                        AppConstants.RESPONSE_SUCCESS_STATUS_CODE,
                        "Operation performed successfully",
                        id
                ),
                HttpStatus.OK
        );
    }

    @GetMapping(
            value = "get",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS"),
            @ApiResponse(code = 400, message = "FAILED"),
            @ApiResponse(code = 500, message = "Exception Occurred")
    })
    @ApiOperation(value = "")
    public Object getSettingDataById(
            HttpServletRequest request,
            @RequestParam Long id
    ) throws Exception {


        return new ResponseEntity<>(
                new GeneralResponse(
                        true,
                        AppConstants.RESPONSE_SUCCESS_STATUS_CODE,
                        "Operation performed successfully",
                        this
                                .settingDAOController
                                .getSettingDataById(id)
                ),
                HttpStatus.OK
        );
    }
    @GetMapping(
            value = "get-all",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS"),
            @ApiResponse(code = 400, message = "FAILED"),
            @ApiResponse(code = 500, message = "Exception Occurred")
    })
    @ApiOperation(value = "")
    public Object getSettingDataList(
            HttpServletRequest request
    ) throws Exception {


        return new ResponseEntity<>(
                new GeneralResponse(
                        true,
                        AppConstants.RESPONSE_SUCCESS_STATUS_CODE,
                        "Operation performed successfully",
                        this
                                .settingDAOController
                                .getSettingDataList()
                ),
                HttpStatus.OK
        );
    }

}
