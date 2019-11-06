/*
 * Copyright  (c) 2017 Lyloou
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lyloou.test.gank;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Author:    Lou
 * Version:   V1.0
 * Date:      2017.05.04 15:17
 * <p>
 * Description:
 */
public interface GankApi {
    @GET("data/%E7%A6%8F%E5%88%A9/{number}/{page}")
    Observable<WelfareResult> getWelfares(@Path("number") int number, @Path("page") int page);

    @GET("day/{year}/{month}/{day}")
    Call<ResponseBody> getGankData(@Path("year") String year, @Path("month") String month, @Path("day") String day);

    @GET("history/content/day/{year}/{month}/{day}")
    Observable<GankContentResult> getGankContent(@Path("year") String year, @Path("month") String month, @Path("day") String day);
}
