package com.example.jdk8.jdk8demo.testLambda;

import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.Retention;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUploader {
    public static void main(String[] args)
            throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            // Create a minioClient with the MinIO server playground, its access key and secret key.
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint("http://10.0.2.129:29001")
                            .credentials("service-desk", "service-desk")
                            .build();
            // Make 'asiatrip' bucket if not exist.
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket("test3").build());
            if (!found) {
                // Make a new bucket called 'asiatrip'.
                minioClient.makeBucket(MakeBucketArgs.builder().region("service-desk").bucket("test3").build());
            } else {
                System.out.println("Bucket 'test3' already exists.");
            }
            // 列出所有存储桶
            List<Bucket> bucketList = minioClient.listBuckets();
            for (Bucket bucket : bucketList) {
                System.out.println(bucket.creationDate() + ", " + bucket.name());
            }


            // Upload '/home/user/Photos/asiaphotos.zip' as object name 'asiaphotos-2015.zip' to bucket
            // 'asiatrip'.
          /*  minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket("test2")
                            .object("middleware.json")
                            .filename("/Users/zzz/Downloads/middleware.json")
                            .build());
            System.out.println(
                    "'/Users/zzz/Downloads/middleware.json' is successfully uploaded as "
                            + "object 'middleware.json' to bucket 'asiatrip'.");*/

           /* InputStream stream = minioClient.getObject(GetObjectArgs
                    .builder()
                    .bucket("test2")
                    .object("middleware.json")
                    .build());

            // 读流到EOF，并输出到控制台。
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = stream.read(buf, 0, buf.length)) >= 0) {
                System.out.println(new String(buf, 0, bytesRead, StandardCharsets.UTF_8));
            }

            // 关闭流。
            stream.close();*/
            /*String presignedObjectUrl = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs
                            .builder()
                            .method(Method.GET)
                            .bucket("test2")
                            .object("middleware.json")
                            .build());
            System.out.println("presignedObjectUrl = " + presignedObjectUrl);*/
//            minioClient.removeObject(RemoveObjectArgs.builder().bucket("test2").object("middleware.json").build() );
            /*try {
                minioClient.statObject(StatObjectArgs.builder().bucket("test2").object("middleware.json").build());
                InputStream stream = minioClient.getObject(GetObjectArgs.builder().bucket("test2").object("middleware.json").build());
                // 读取输入流直到EOF并打印到控制台。
                byte[] buf = new byte[16384];
                int bytesRead;
                while ((bytesRead = stream.read(buf, 0, buf.length)) >= 0) {
                    System.out.println(new String(buf, 0, bytesRead));
                }
                // 关闭流，此处为示例，流关闭最好放在finally块。
                stream.close();
            } catch (MinioException e) {
                System.out.println("Error occurred: " + e);
            }

*/
//            StatObjectResponse test2 = minioClient.statObject(StatObjectArgs.builder().bucket("test2").object("middleware.json").build());
//            System.out.println(test2);
            /*String presignedObjectUrl = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs
                            .builder()
                            .method(Method.GET)
                            .bucket("test2")
                            .expiry(10000)
                            .object("middleware.json")
                            .build());
            System.out.println("presignedObjectUrl = " + presignedObjectUrl);*/

//            minioClient.removeObject(RemoveObjectArgs.builder().bucket("test2").object("middleware.json").build());

        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        }
    }
}
