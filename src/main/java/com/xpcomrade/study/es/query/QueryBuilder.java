package com.xpcomrade.study.es.query;

/**
 * Created by xpcomrade on 2016/11/7.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */
public class QueryBuilder {



    private String normalize(String query){
        if (!StringUtils.hasText(query)) {
            return query;
        }
        query = toDBC(query);//全角转半角
        query = query.replaceAll("\\s+", " ");//多空格转单空格    }

    /**
     * 全角转半角
     * @param input
     * @return
     */
    private String toDBC(String input) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '\u3000') {
                c[i] = ' ';
            } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
                c[i] = (char) (c[i] - 65248);

            }
        }
        String returnString = new String(c);
        return returnString;
    }



}
