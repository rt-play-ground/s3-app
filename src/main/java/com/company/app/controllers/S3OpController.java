package com.company.app.controllers;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/* rtempalli created on 4/6/20 inside the package - com.company.controllers */
@RestController
@RequestMapping("/api/v1/s3")
@Api(value = "s3", description = "S3 Operations", tags = "S3")
public class S3OpController {

    @GetMapping("/a")
    @ApiOperation(value = "Get Item", nickname = "Get Item")
    public ResponseEntity<?> getValue(@RequestParam String prefix, @RequestParam String key) {
        return null;
    }

    @PostMapping("/b")
    @ApiOperation(value = "Post Item", nickname = "Post Item")
    public ResponseEntity<?> createEntry(@RequestParam String prefix, @RequestParam String key, @RequestParam String value) {
        AWSCredentials credentials = new BasicAWSCredentials("ASIA5MXXEEEKKR2EORYK", "VkrScUiidbapddB1fah2x7WzAhGqlOeJE3MKrI27");
        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setProtocol(Protocol.HTTP);

        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
        s3.setEndpoint("https://s3.console.aws.amazon.com/s3/buckets/test-app-rt/?region=us-east-1");

        s3.putObject(prefix, key, value);

        return ResponseEntity.ok().build();
    }

}
