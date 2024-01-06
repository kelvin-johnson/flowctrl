/*
 *    Copyright 2023 Kelvin Johnson
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.d18sg.flowctrl.lib.api;

import com.d18sg.flowctrl.lib.definition.FlowableDefinitions;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.Map;

public interface UserClient {

    @PostExchange(FlowableDefinitions.USER)
    Mono<String> createUser(@PathVariable String userId, @RequestParam Map<String,String> requestParameters);

    @GetExchange(FlowableDefinitions.USERS)
    Mono<ResponseEntity<String>> getUsers(@RequestParam Map<String,String> requestParameters);

    @GetExchange(FlowableDefinitions.USER)
    Mono<String> getUser(@PathVariable String userId);

    @DeleteExchange(FlowableDefinitions.USER)
    Mono<String> deleteUser(@PathVariable String userId);

    @GetExchange(FlowableDefinitions.USER_PICTURE)
    Flux<DataBuffer> getUserPicture(@PathVariable String userId);

    @GetExchange(FlowableDefinitions.USER_INFO)
    Mono<String> getUserInfoList(@PathVariable String userId);

    @GetExchange(FlowableDefinitions.USER_INFO)
    Mono<String> getUserInfo(@PathVariable String userId, @PathVariable String key);

    @PutExchange(FlowableDefinitions.USER_INFO)
    Mono<String> updateUserInfo(@PathVariable String userId, @PathVariable String key, @RequestParam Map<String,String> requestParameters);

    @PostExchange(FlowableDefinitions.USER_INFO)
    Mono<String> createUserInfo(@PathVariable String userId, @PathVariable String key, @RequestParam Map<String,String> requestParameters);

    @DeleteExchange(FlowableDefinitions.USER_INFO)
    Mono<String> deleteUserInfo(@PathVariable String userId, @PathVariable String key);

    @GetExchange(FlowableDefinitions.USER_PICTURE)
    Flux<DataBuffer> getUserPictureData(@PathVariable String userId);

    @PutExchange(FlowableDefinitions.USER_PICTURE)
    Mono<ResponseEntity<String>> updateUserPicture(@RequestPart FileSystemResource fileSystemResource, @PathVariable String userId, @RequestPart String mimeType);

    String willItWork();
}
