package com.jack.wife;

import com.google.common.collect.Maps;
import java.util.Map;

/**
 * @description: data context
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-10-31 19:06
 */
public class DataContext {

//    public static final Multimap<String, Right> rights = ArrayListMultimap.create();
//
//    public static final Multimap<String,Total> totals = ArrayListMultimap.create();
    public static final Map<String, Right> rights = Maps.newHashMap();

    public static final Map<String,Total> totals = Maps.newHashMap();

}
