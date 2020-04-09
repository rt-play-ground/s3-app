package com.company.app.service;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.company.app.model.S3ObjectDto;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* rtempalli created on 4/8/20 inside the package - com.company.app.service */
@Service
public class S3Service {

    private AmazonS3 s3Client;

    private static final String bucketName = "test-app-rt";

    public List<S3ObjectDto> list() throws IOException {
        List<S3ObjectDto> s3Objects = new ArrayList<S3ObjectDto>();

        ObjectListing list = getS3Client().listObjects(bucketName);
        List<S3ObjectSummary> objects = list.getObjectSummaries();

        for (S3ObjectSummary os : objects) {
            System.out.println("* " + os.getKey());
            S3Object s3File = s3Client.getObject(new GetObjectRequest(bucketName, os.getKey()));
            s3Objects.add(new S3ObjectDto(os.getKey(), displayTextInputStream(s3File.getObjectContent())));
        }

        return s3Objects;
    }

    private String displayTextInputStream(InputStream input) throws IOException {
        StringBuffer buffer = new StringBuffer();

        // Read the text input stream one line at a time and display each line.
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line = null;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
            System.out.println(line);
        }

        return buffer.toString();
    }

    public void create(String key, String value) {
        getS3Client().putObject(bucketName, key, value);
    }

    private AmazonS3 getS3Client() {
        if (s3Client == null) {
            s3Client = AmazonS3ClientBuilder.standard()
                    .withCredentials(new EnvironmentVariableCredentialsProvider())
                    .withRegion(Regions.US_EAST_1)
                    .build();
        }

        return s3Client;
    }
}
