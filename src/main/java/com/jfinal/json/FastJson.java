/**
 * Copyright (c) 2011-2019, James Zhan 詹波 (jfinal@126.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jfinal.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jfinal.plugin.activerecord.Record;

/**
 * Json 转换 fastjson 实现.
 */
public class FastJson extends Json {

    static {
        // 支持序列化 ActiveRecord 的 Record 类型
        SerializeConfig.getGlobalInstance().put(Record.class, new FastJsonRecordSerializer());
    }

    public static FastJson getJson() {
        return new FastJson();
    }

    public String toJson(Object object) {
        // 优先使用对象级的属性 datePattern, 然后才是全局性的 defaultDatePattern
        String dp = datePattern != null ? datePattern : getDefaultDatePattern();
        if (dp == null) {
            return JSON.toJSONString(object);
        } else {
            return JSON.toJSONStringWithDateFormat(object, dp, SerializerFeature.WriteDateUseDateFormat);    // return JSON.toJSONString(object, SerializerFeature.WriteDateUseDateFormat);
        }
    }

    public <T> T parse(String jsonString, Class<T> type) {
        return JSON.parseObject(jsonString, type);
    }
}


