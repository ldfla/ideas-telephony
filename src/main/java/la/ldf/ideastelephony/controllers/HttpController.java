/*
 * Copyright 2023-present, Leandro Figueiredo
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE.md file in the root directory of this source tree.
 *
 */

package la.ldf.ideastelephony.controllers;

import la.ldf.ideastelephony.entity.SipEventType;
import la.ldf.ideastelephony.service.SipEventTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class HttpController {
    private SipEventTypeService sipEventTypeService;

    public HttpController(SipEventTypeService sipEventTypeService) {
        this.sipEventTypeService = sipEventTypeService;
    }

    @GetMapping("/")
    String unsafeRoute() {
        return "🔓 This is an unsafe route!";
    }

    @GetMapping("/sipEventType")
    List<SipEventType> list(){
        return sipEventTypeService.list();
    }
}