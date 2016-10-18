package com.xpcomrade.study.es.api;

import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.elasticsearch.common.xcontent.XContentFactory.*;

/**
 * Created by xpcomrade on 2016/9/23.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: (
  By default, BulkProcessor:
        sets bulkActions to 1000
        sets bulkSize to 5mb
        does not set flushInterval
        sets concurrentRequests to 1
        sets backoffPolicy to an exponential backoff with 8 retries and a start delay of 50ms. The total wait time is roughly 5.1 seconds.
 * ). <br/>
 */
public class BulkProcessorAPI extends AbstractBaseTest {

    @Test
    public void bulkProcessor() throws Exception{


        BulkProcessor bulkProcessor = BulkProcessor.builder(

                client,
                new BulkProcessor.Listener() {

                    /**
                     * This method is called just before bulk is executed.
                     * You can for example see the numberOfActions with request.numberOfActions()
                     */
                    public void beforeBulk(long l, BulkRequest bulkRequest) {
                        System.out.println("beforeBulk > " + bulkRequest.numberOfActions());
                    }

                    /**
                     * This method is called after bulk execution.
                     * You can for example check if there was some failing requests with response.hasFailures()
                     */
                    public void afterBulk(long l, BulkRequest bulkRequest, BulkResponse bulkResponse) {
                        System.out.println("afterBulk > " + bulkRequest.numberOfActions() + " ," +bulkResponse.hasFailures());
                    }

                    /**
                     * This method is called when the bulk failed and raised a Throwable
                     */
                    public void afterBulk(long l, BulkRequest bulkRequest, Throwable throwable) {

                    }
                })
                .setBulkActions(1000) // We want to execute the bulk every 10 000 requests
                .setBulkSize(new ByteSizeValue(1, ByteSizeUnit.MB)) // We want to flush the bulk every 1mb
                //.setFlushInterval(TimeValue.timeValueSeconds(5)) // We want to flush the bulk every 5 seconds whatever the number of requests
                .setConcurrentRequests(1) // Set the number of concurrent requests
                /**
                 * Set a custom backoff policy which will initially wait for 100ms, increase exponentially and retries up to three times.
                 * A retry is attempted whenever one or more bulk item requests have failed with an EsRejectedExecutionException
                 * which indicates that there were too little compute resources available for processing the request.
                 * To disable backoff, pass BackoffPolicy.noBackoff().
                 */
                .setBackoffPolicy(BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3))
                .build();

        bulkProcessor.add(
                new IndexRequest("twitter", "tweet", "4")
                        .source(
                                jsonBuilder()
                                .startObject()
                                .field("user", "xpc")
                                .field("postDate", new Date())
                                .field("message", "another  ")
                                .endObject()
                        )
        );

        bulkProcessor.add(new DeleteRequest("twitter", "tweet", "1"));

        //When all documents are loaded to the BulkProcessor it can be closed by using awaitClose or close methods:
        bulkProcessor.awaitClose(1, TimeUnit.MINUTES);
        //bulkProcessor.close();


    }
}
