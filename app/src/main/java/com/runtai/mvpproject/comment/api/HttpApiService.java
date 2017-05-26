package com.runtai.mvpproject.comment.api;

import com.runtai.mvpproject.mudule.bean.DreamBean;
import com.runtai.mvpproject.mudule.bean.IPBean;
import com.runtai.mvpproject.mudule.bean.MovieBean;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface HttpApiService {

    /**
     * 查询豆瓣排名前250的电影
     *
     * @param start 从第几部开始
     * @param count 几页(一页有12部)
     * @return
     */
    @GET("v2/movie/top250")
    Observable<MovieBean> getMovies(@Query("start") int start, @Query("count") int count);

    /**
     * 查询ip地址信息的接口
     *
     * @param ip 需查询的ip
     * @return RxJava 对象
     */
    @GET("service/getIpInfo.php")
    Observable<IPBean> queryIp(@Query("ip") String ip);

    /**
     * 请求示例：
     * http://v.juhe.cn/dream/query
     * q:梦境关键字，如：黄金 需要utf8 urlencode
     * cid:指定分类，默认全部
     * full: 是否显示详细信息，1:是 0:否，默认0
     */
    @GET("dream/query")
    Observable<DreamBean> getDream(@QueryMap Map<String, Object> options);

}
