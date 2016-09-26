package com.xpcomrade.study.es.index;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.deletebyquery.DeleteByQueryAction;
import org.elasticsearch.action.deletebyquery.DeleteByQueryRequestBuilder;
import org.elasticsearch.action.deletebyquery.DeleteByQueryResponse;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.junit.Test;

/**
 * Created by xpcomrade on 2016/9/22.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */
public class DeleteAPI extends AbstractBaseTest {

    @Test
    public void deleteById() {
        DeleteResponse response = client.prepareDelete("twitter", "tweet", "1").get();

        ImmutableOpenMap<Object, Object> result = response.getContext();

        System.out.println(result.toString());
    }


    @Test
    public void deleteIndex (){
        String indexName = "hxjbapilog";
        DeleteIndexResponse dResponse = client.admin().indices().prepareDelete(indexName).execute().actionGet();
        if (dResponse.isAcknowledged()) {
            System.out.println("delete index "+indexName+"  successfully!");
        }else{
            System.out.println("Fail to delete index "+indexName);
        }
    }

    @Test
    public void deleteByQuery () {
        String deletebyquery = "{\"query\": {\"match_all\": {}}}";
        DeleteByQueryResponse response =  new DeleteByQueryRequestBuilder(client,
                DeleteByQueryAction.INSTANCE)
                .setIndices("hxjbapilog")
                .setTypes("apilog")
                .setSource(deletebyquery)
                .execute()
                .actionGet();

    }


}
