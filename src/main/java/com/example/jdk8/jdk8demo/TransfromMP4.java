package com.example.jdk8.jdk8demo;

import java.io.File;
import java.io.IOException;

import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.EncodingAttributes;
import ws.schild.jave.encode.VideoAttributes;
import ws.schild.jave.info.MultimediaInfo;
import ws.schild.jave.info.VideoInfo;
import ws.schild.jave.info.VideoSize;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/1/29
 * @since 3.0.1
 */
public class TransfromMP4 {

    public static void main(String[] args) throws IOException, EncoderException {
        String filePath = "C:\\Users\\dongbao.zhao\\Desktop\\taoyuan.mp4";
        String outFilePath = filePath.substring(0, filePath.lastIndexOf("\\"));
        File sourceFile = new File(filePath);
        String fileName = filePath.substring(filePath.lastIndexOf("\\"), filePath.lastIndexOf("."));
        outFilePath = outFilePath + fileName + ".gif";
        File targetFile = new File(outFilePath);
        //获得原视频的分辨率
        MultimediaObject mediaObject = new MultimediaObject(sourceFile);
        MultimediaInfo multimediaInfo = mediaObject.getInfo();
        VideoInfo videoInfo = multimediaInfo.getVideo();
        VideoSize sourceSize = videoInfo.getSize();
        //设置视频属性
        VideoAttributes video = new VideoAttributes();
        video.setCodec("gif");
        //设置视频帧率 正常为10 ，值越大越流畅
        video.setFrameRate(120);
        //设置视频分辨率
        VideoSize targetSize = new VideoSize(sourceSize.getWidth() / 4, sourceSize.getHeight() / 4);
        video.setSize(targetSize);
        //设置转码属性
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setVideoAttributes(video);
        // 音频转换格式类
        Encoder encoder = new Encoder();
        encoder.encode(mediaObject, targetFile, attrs);
        System.out.println("转换已完成...");
    }

}
