package com.yll.springcloud.demozuul;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;
import rx.Observer;

import java.util.HashMap;

/**
 * 使用RxJava结合Zuul实现微服务请求的聚合
 * @author luliang_yu
 * @date 2018-12-10
 */
@RestController
public class AggregationController {

    public static final Logger logger = LoggerFactory.getLogger(AggregationController.class);

    @Autowired
    private AggregationService aggregationService;

    @GetMapping(value = "/agg/{id}",produces = "application/json")
    public DeferredResult<HashMap<String, User>> agg(@PathVariable Long id) {
        Observable<HashMap<String, User>> result = this.aggregateObservable(id);
        return this.toDeferredResult(result);

    }

    private DeferredResult<HashMap<String, User>> toDeferredResult(Observable<HashMap<String, User>> details) {
        DeferredResult<HashMap<String, User>> result = new DeferredResult<>();
        details.subscribe(new Observer<HashMap<String, User>>() {
            @Override
            public void onCompleted() {
                logger.info("完成。。。");
            }

            @Override
            public void onError(Throwable throwable) {
                logger.info("发生错误。。。");
            }

            @Override
            public void onNext(HashMap<String, User> stringUserHashMap) {
                result.setResult(stringUserHashMap);
            }
        });
        return result;
    }

    private Observable<HashMap<String, User>> aggregateObservable(Long id) {
        return Observable.zip(
                this.aggregationService.getUserById(id),
                aggregationService.getMovieUserById(id),
                (user,movieUser)->{
                 HashMap<String,User>map = Maps.newHashMap();
                    map.put("user", user);
                    map.put("movieUser", movieUser);
                    return map;
                }
        );
    }
}
