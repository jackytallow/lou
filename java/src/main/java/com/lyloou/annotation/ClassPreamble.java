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

package com.lyloou.annotation;

/**
 * Author:    Lou
 * Version:   V1.0
 * Date:      2017.05.19 16:13
 * <p>
 * Description:
 */
@interface ClassPreamble {
    String author();

    String date();

    int currentReversion() default 1;

    String lastModified() default "N/A";

    String lastModifiedBy() default "N/A";

    String[] reviewers();
}

@ClassPreamble(
        author = "LiLou",
        date = "2017.05.19",
        currentReversion = 2,
        lastModified = "2017.05.19",
        lastModifiedBy = "Lou",
        reviewers = {"Li", "Lou"}
)
class TestClassPreamble {

}