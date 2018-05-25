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

package com.jfinal.aop;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Enhancer
 */
@SuppressWarnings("unchecked")
public class Enhancer {

    private static final ConcurrentHashMap<String, Object> singleton = new ConcurrentHashMap<String, Object>();

    private Enhancer() {
    }

    public static <T> T enhance(Class<T> targetClass) {
        return (T) net.sf.cglib.proxy.Enhancer.create(targetClass, new Callback());
    }

    public static <T> T enhance(Class<T> targetClass, Interceptor... injectInters) {
        return (T) net.sf.cglib.proxy.Enhancer.create(targetClass, new Callback(injectInters));
    }

    public static <T> T enhance(Class<T> targetClass, Class<? extends Interceptor>... injectIntersClasses) {
        return (T) enhance(targetClass, createInjectInters(injectIntersClasses));
    }

    public static <T> T enhance(Class<T> targetClass, Class<? extends Interceptor> injectIntersClass) {
        return (T) enhance(targetClass, createInjectInters(injectIntersClass));
    }

    public static <T> T enhance(Class<T> targetClass, Class<? extends Interceptor> injectIntersClass1, Class<? extends Interceptor> injectIntersClass2) {
        return (T) enhance(targetClass, createInjectInters(injectIntersClass1, injectIntersClass2));
    }

    public static <T> T enhance(Class<T> targetClass, Class<? extends Interceptor> injectIntersClass1, Class<? extends Interceptor> injectIntersClass2, Class<? extends Interceptor> injectIntersClass3) {
        return (T) enhance(targetClass, createInjectInters(injectIntersClass1, injectIntersClass2, injectIntersClass3));
    }

    public static <T> T getTarget(String singletonKey) {
        return (T) singleton.get(singletonKey);
    }

    public static <T> T enhance(String singletonKey, Class<T> targetClass) {
        Object ret = singleton.get(singletonKey);
        if (ret == null) {
            synchronized (Enhancer.class) {
                ret = singleton.get(singletonKey);
                if (ret == null) {
                    ret = enhance(targetClass);
                    singleton.put(singletonKey, ret);
                }
            }
        }
        return (T) ret;
    }

    public static <T> T enhance(String singletonKey, Class<T> targetClass, Interceptor... injectInters) {
        Object ret = singleton.get(singletonKey);
        if (ret == null) {
            synchronized (Enhancer.class) {
                ret = singleton.get(singletonKey);
                if (ret == null) {
                    ret = enhance(targetClass, injectInters);
                    singleton.put(singletonKey, ret);
                }
            }
        }
        return (T) ret;
    }

    public static <T> T enhance(String singletonKey, Class<T> targetClass, Class<? extends Interceptor>... injectIntersClasses) {
        Object ret = singleton.get(singletonKey);
        if (ret == null) {
            synchronized (Enhancer.class) {
                ret = singleton.get(singletonKey);
                if (ret == null) {
                    ret = enhance(targetClass, injectIntersClasses);
                    singleton.put(singletonKey, ret);
                }
            }
        }
        return (T) ret;
    }

    public static <T> T enhance(Object target) {
        return (T) net.sf.cglib.proxy.Enhancer.create(target.getClass(), new Callback(target));
    }

    public static <T> T enhance(Object target, Interceptor... injectInters) {
        return (T) net.sf.cglib.proxy.Enhancer.create(target.getClass(), new Callback(target, injectInters));
    }

    public static <T> T enhance(Object target, Class<? extends Interceptor>... injectIntersClasses) {
        return (T) enhance(target, createInjectInters(injectIntersClasses));
    }

    public static <T> T enhance(Object target, Class<? extends Interceptor> injectIntersClass) {
        return (T) enhance(target, createInjectInters(injectIntersClass));
    }

    public static <T> T enhance(Object target, Class<? extends Interceptor> injectIntersClass1, Class<? extends Interceptor> injectIntersClass2) {
        return (T) enhance(target, createInjectInters(injectIntersClass1, injectIntersClass2));
    }

    public static <T> T enhance(Object target, Class<? extends Interceptor> injectIntersClass1, Class<? extends Interceptor> injectIntersClass2, Class<? extends Interceptor> injectIntersClass3) {
        return (T) enhance(target, createInjectInters(injectIntersClass1, injectIntersClass2, injectIntersClass3));
    }

    public static <T> T enhance(String singletonKey, Object target) {
        Object ret = singleton.get(singletonKey);
        if (ret == null) {
            synchronized (Enhancer.class) {
                ret = singleton.get(singletonKey);
                if (ret == null) {
                    ret = enhance(target);
                    singleton.put(singletonKey, ret);
                }
            }
        }
        return (T) ret;
    }

    public static <T> T enhance(String singletonKey, Object target, Interceptor... injectInters) {
        Object ret = singleton.get(singletonKey);
        if (ret == null) {
            synchronized (Enhancer.class) {
                ret = singleton.get(singletonKey);
                if (ret == null) {
                    ret = enhance(target, injectInters);
                    singleton.put(singletonKey, ret);
                }
            }
        }
        return (T) ret;
    }

    public static <T> T enhance(String singletonKey, Object target, Class<? extends Interceptor>... injectIntersClasses) {
        Object ret = singleton.get(singletonKey);
        if (ret == null) {
            synchronized (Enhancer.class) {
                ret = singleton.get(singletonKey);
                if (ret == null) {
                    ret = enhance(target, injectIntersClasses);
                    singleton.put(singletonKey, ret);
                }
            }
        }
        return (T) ret;
    }

    private static Interceptor[] createInjectInters(Class<? extends Interceptor>... injectInterClasses) {
        return InterceptorManager.me().createInterceptor(injectInterClasses);
    }
}


