package com.company.app.controllers;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.StringUtils;
import com.company.app.service.S3Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/* rtempalli created on 4/6/20 inside the package - com.company.controllers */
@RestController
@RequestMapping("/api/v1/s3")
@Api(value = "s3", description = "S3 Operations", tags = "S3")
@ComponentScan("com.company.app.service")
public class S3OpController {

    @Autowired
    private S3Service service;

    @GetMapping("/list")
    @ApiOperation(value = "Get List", nickname = "Get List")
    public ResponseEntity<?> getList() {
        try {
            return ResponseEntity.ok().body(service.list());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/item")
    @ApiOperation(value = "Get Item", nickname = "Get Item")
    public ResponseEntity<?> getValue(@RequestParam String head) {
        try {
            return ResponseEntity.ok().body(service.list());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/create")
    @ApiOperation(value = "Post Item", nickname = "Post Item")
    public ResponseEntity<?> createEntry(
            @RequestParam String key, @RequestParam String value) {
        service.create(key, value);
        return ResponseEntity.ok().build();
    }

}
