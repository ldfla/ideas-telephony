/*
 * Copyright 2023-present, Leandro Figueiredo
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE.md file in the root directory of this source tree.
 *
 */

package la.ldf.ideastelephony.controllers;

import la.ldf.ideastelephony.entity.SipEvent;
import la.ldf.ideastelephony.service.SipEventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sipEvents")
public class SipEventController {
    private SipEventService sipEventService;

    public SipEventController(SipEventService sipEventService) {
        this.sipEventService = sipEventService;
    }
    @PostMapping()
    int create(@RequestBody SipEvent sipEvent){
        return sipEventService.push(sipEvent).getId();
    }

    @GetMapping()
    List<SipEvent> list(){
        return sipEventService.list();
    }

    @PutMapping()
    SipEvent update(@RequestBody SipEvent sipEvent){
        return sipEventService.update(sipEvent);
    }

    @DeleteMapping("{id}")
    List<SipEvent> delete(@PathVariable("id") Long id){
        return sipEventService.delete(id);
    }

}
