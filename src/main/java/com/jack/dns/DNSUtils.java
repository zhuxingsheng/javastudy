package com.jack.dns;

import com.alibaba.fastjson.JSON;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.TextParseException;
import org.xbill.DNS.Type;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 通过dnsjava 模拟nslookup
 * Created by Jack on 2018/10/17.
 */
public class DNSUtils {

    public static void main(String[] args) {
        long s = System.currentTimeMillis();
        System.out.println(JSON.toJSONString(lookupAddrByName("baidu.com")));
        System.out.println(System.currentTimeMillis() - s);
    }

    private static final AtomicLong refreshAt = new AtomicLong(0);

    public static List<String> lookupAddrByName(String name) {
        if (shouldRefresh()) {
            Lookup.refreshDefault();
        }

        try {
            Lookup lookup = new Lookup(name, Type.A);
            lookup.run();
            if (lookup.getResult() == Lookup.SUCCESSFUL) {
                Record[] answers = lookup.getAnswers();
                if (answers != null && answers.length > 0) {
                    String[] addrs = new String[answers.length];
                    for (int i = 0; i < addrs.length; i++) {
                        addrs[i] = answers[i].rdataToString();
                    }

                    return Arrays.asList(addrs);
                }
            }
        } catch (TextParseException e) {
            e.printStackTrace();;
        }

        return null;
    }

    private static boolean shouldRefresh() {
        for (;;) {
            final long time = System.currentTimeMillis();
            final long current = refreshAt.get();
            if (current > time) {
                return false;
            }
            if (refreshAt.compareAndSet(current, time + 30000)) {
                return true;
            }
        }
    }
}
